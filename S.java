import java.awt.Graphics2D;

public class S {
    public static final double gapHeightFactor = 1.5; // multiply by hatHeight
    private Graphics2D g;
    
    // Basic S attributes
    private double hatHeight;
    private double hatWidth;
    private double vertHeight;
    private double gapHeight;       // dependent on gapHeightFactor

    // Draw attributes
    private int center;
    private int top;
    private Direction dir;

    // public constructor for regular use
    public S(Graphics2D g2, double hh, double hw, double vh) {
        hatHeight = hh;
        hatWidth = hw;
        vertHeight = vh;
        gapHeight = hatHeight * gapHeightFactor;
        g = g2;

        center = 0;
        top = 0;
    }

    public void draw(double x, double y) {
        draw(x, y, 0, Direction.DOWN);
    }
    
    public void draw(int x, int y) {
        draw(x, y, 0, Direction.DOWN);
    }

    public void draw(double x, double y, int level, Direction dir) {
        draw((int) Math.round(x), (int) Math.round(y), level, dir);
    }

    public void draw(int x, int y, int level, Direction dir) {
        // Set up draw attributes
        this.dir = dir;
        center = x;
        top = y;

        // calculate borders
        double leftBorder = 0 - hatWidth;
        double rightBorder = hatWidth;

        double layer1 = hatHeight;
        double layer2 = layer1 + vertHeight;
        double layer3 = layer2 + gapHeight;
        double layer4 = layer3 + vertHeight;
        double bottom = layer4 + hatHeight;

        double leftMidX = leftBorder / 2;
        double rightMidX = rightBorder / 2;
        double midY = (layer2 + layer3) / 2;
        
        // draw the hat
        drawLine(0, 0, leftBorder, layer1);
        drawLine(0, 0, rightBorder, layer1);

        // draw first set of verticals
        drawLine(leftBorder, layer1, leftBorder, layer2);
        drawLine(0, layer1, 0, layer2);
        drawLine(rightBorder, layer1, rightBorder, layer2);

        // draw gap zone
        drawLine(leftBorder, layer2, 0, layer3);
        drawLine(0, layer2, rightBorder, layer3);
        drawLine(leftBorder, layer3, leftMidX, midY);
        drawLine(rightBorder, layer2, rightMidX, midY);

        // draw second set of verticals
        drawLine(leftBorder, layer3, leftBorder, layer4);
        drawLine(0, layer3, 0, layer4);
        drawLine(rightBorder, layer3, rightBorder, layer4);

        // draw bottom hat
        drawLine(0, bottom, leftBorder, layer4);
        drawLine(0, bottom, rightBorder, layer4);

        if (level > 0) {      // must continue recursion
            S newS = new S(g, hatWidth / 2, midY - layer2, vertHeight / 2);

            Coord right = new Coord(rightMidX, midY);
            Coord rightTransformed = right.transform(center, top, dir);
            newS.draw(rightTransformed.x, rightTransformed.y, level - 1, dir.counterClockwise());

            Coord left = new Coord(leftMidX, midY);
            Coord leftTransformed = left.transform(center, top, dir);
            newS.draw(leftTransformed.x, leftTransformed.y, level - 1, dir.clockwise());            
            
        }
    }

    private void drawLine(double x, double y, double x2, double y2) {
        Coord p1 = new Coord(x, y);
        Coord p2 = new Coord(x2, y2);

        Coord p1Transformed = p1.transform(center, top, dir);
        Coord p2Transformed = p2.transform(center, top, dir);

        g.drawLine((int) Math.round(p1Transformed.x), (int) Math.round(p1Transformed.y), (int) Math.round(p2Transformed.x), (int) Math.round(p2Transformed.y));

        // switch (dir) {
        //     case DOWN: {
        //         g.drawLine(center + (int) Math.round(x), top + (int) Math.round(y), center + (int) Math.round(x2), top + (int) Math.round(y2));
        //         break;
        //     }
        //     case RIGHT: {
        //         g.drawLine(center + (int) Math.round(y), top + (int) Math.round(x), center + (int) Math.round(y2), top + (int) Math.round(x2));
        //         break;
        //     }
        //     case UP: {
        //         g.drawLine(center + (int) Math.round(x), top - (int) Math.round(y), center + (int) Math.round(x2), top - (int) Math.round(y2));
        //         break;
        //     }
        //     case LEFT: {
        //         g.drawLine(center - (int) Math.round(y), top + (int) Math.round(x), center - (int) Math.round(y2), top + (int) Math.round(x2));
        //         break;
        //     }
        // }
    }
}