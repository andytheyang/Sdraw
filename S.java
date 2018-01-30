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

        switch (dir) {
            case DOWN: {
                center = x;
                top = y;
                break;
            }
            case RIGHT: {
                center = y;
                top = x;
                break;
            }
            case UP: {
                center = x;
                top = y;
                break;
            }
            case LEFT: {
                center = y;
                top = x;    
                break;
            }
        }

        // calculate borders
        double leftBorder = center - hatWidth;
        double rightBorder = center + hatWidth;

        double layer1 = top + hatHeight;
        double layer2 = layer1 + vertHeight;
        double layer3 = layer2 + gapHeight;
        double layer4 = layer3 + vertHeight;
        double bottom = layer4 + hatHeight;

        double leftMidX = (leftBorder + center) / 2;
        double rightMidX = (center + rightBorder) / 2;
        double midY = (layer2 + layer3) / 2;
        
        // draw the hat
        drawLine(center, top, leftBorder, layer1);
        drawLine(center, top, rightBorder, layer1);

        // draw first set of verticals
        drawLine(leftBorder, layer1, leftBorder, layer2);
        drawLine(center, layer1, center, layer2);
        drawLine(rightBorder, layer1, rightBorder, layer2);

        // draw gap zone
        drawLine(leftBorder, layer2, center, layer3);
        drawLine(center, layer2, rightBorder, layer3);
        drawLine(leftBorder, layer3, leftMidX, midY);
        drawLine(rightBorder, layer2, rightMidX, midY);

        // draw second set of verticals
        drawLine(leftBorder, layer3, leftBorder, layer4);
        drawLine(center, layer3, center, layer4);
        drawLine(rightBorder, layer3, rightBorder, layer4);

        // draw bottom hat
        drawLine(center, bottom, leftBorder, layer4);
        drawLine(center, bottom, rightBorder, layer4);

        if (level > 0) {      // must continue recursion
            S newS = new S(g, hatWidth / 2, midY - layer2, vertHeight / 2);
            newS.draw(rightMidX, midY, level - 1, dir.counterClockwise());
        }
    }

    private void drawLine(double x, double y, double x2, double y2) {
        switch (dir) {
            case DOWN: {
                g.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x2), (int) Math.round(y2));
                break;
            }
            case RIGHT: {
                g.drawLine((int) Math.round(y), (int) Math.round(x), (int) Math.round(y2), (int) Math.round(x2));
                break;
            }
            case UP: {
                break;
            }
            case LEFT: {
                break;
            }
        }
    }
}