import java.awt.Graphics2D;

public class S {
    private Graphics2D g;
    
    private double hatHeight;
    private double hatWidth;
    private double vertHeight;
    private double gapHeight;
    public static final double gapHeightFactor = 1.5; // multiply by hatHeight
    

    public S(double hh, double hw, double vh, Graphics2D g2) {
        hatHeight = hh;
        hatWidth = hw;
        vertHeight = vh;
        gapHeight = hatHeight * gapHeightFactor;
        g = g2;
    }

    public void draw(int center, int top) {
        // calculate borders
        double leftBorder = center - hatWidth;
        double rightBorder = center + hatWidth;
        double layer1 = top + hatHeight;
        double layer2 = layer1 + vertHeight;
        double layer3 = layer2 + gapHeight;
        double layer4 = layer3 + vertHeight;
        double bottom = layer4 + hatHeight;

        // draw the hat
        drawLine(center, top, leftBorder, layer1);
        drawLine(center, top, rightBorder, layer1);

        // draw first set of verticals
        drawLine(leftBorder, layer1, leftBorder, layer2);
        drawLine(center, layer1, center, layer2);
        drawLine(rightBorder, layer1, rightBorder, layer2);

        // draw gap zone
        drawLine(leftBorder, layer2, center, layer3);       // full-length
        drawLine(center, layer2, rightBorder, layer3);
        drawLine(leftBorder, layer3, (leftBorder + center) / 2, (layer2 + layer3) / 2);        // half-length
        drawLine(rightBorder, layer2, (center + rightBorder) / 2, (layer2 + layer3) / 2);

        // draw second set of verticals
        drawLine(leftBorder, layer3, leftBorder, layer4);
        drawLine(center, layer3, center, layer4);
        drawLine(rightBorder, layer3, rightBorder, layer4);

        // draw bottom hat
        drawLine(center, bottom, leftBorder, layer4);
        drawLine(center, bottom, rightBorder, layer4);

    }

    private void drawLine(double x, double y, double x2, double y2) {
        g.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x2), (int) Math.round(y2));
    }


}