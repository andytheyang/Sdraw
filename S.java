import java.awt.Graphics2D;

public class S {
    public static final double gapHeightFactor = 1.5; // multiply by hatHeight
    private Graphics2D g;
    
    // Basic S attributes
    private double hatHeight;
    private double hatWidth;
    private double vertHeight;
    private double gapHeight;       // dependent on gapHeightFactor

    // Fractal attributes
    private boolean flipped;        // flip y and x axes for drawing
    private int level;

    // public constructor for regular use
    public S(Graphics2D g2, double hh, double hw, double vh, boolean flip) {
        hatHeight = hh;
        hatWidth = hw;
        vertHeight = vh;
        gapHeight = hatHeight * gapHeightFactor;
        flipped = flip;
        g = g2;

        level = 0;
    }

    // Fractal constructor with level parameter
    public S(Graphics2D g2, double hh, double hw, double vh, boolean flip, int l) {
        this(g2, hh, hw, vh, flip);

        level = l;
    }

    public void draw(double x, double y) {
        draw((int) Math.round(x), (int) Math.round(y));
    }

    public void draw(int x, int y) {
        int center;
        int top;

        if (flipped) {
            center = y;
            top = x;
        } else {
            center = x;
            top = y;
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

        if (!isLeaf()) {      // must continue recursion
            S newS = new S(g, hatWidth / 2, midY - layer2, vertHeight / 2, !flipped, level - 1);
            newS.draw(rightMidX, midY);
        }
    }

    private void drawLine(double x, double y, double x2, double y2) {
        if (flipped)
            g.drawLine((int) Math.round(y), (int) Math.round(x), (int) Math.round(y2), (int) Math.round(x2));
        else
            g.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x2), (int) Math.round(y2));
    }

    private boolean isLeaf() {
        return (level == 0);
    }
}