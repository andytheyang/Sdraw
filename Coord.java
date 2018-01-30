public class Coord {
    public double x;
    public double y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coord transform(double center, double top, Direction dir) {
        double xRet = 0;
        double yRet = 0;

        switch (dir) {
            case DOWN: {
                xRet = center + x;
                yRet = top + y;
                break;
            }
            case RIGHT: {
                xRet = center + y;
                yRet = top - x;
                break;
            }
            case UP: {
                xRet = center - x;
                yRet = top - y;
                break;
            }
            case LEFT: {
                xRet = center - y;
                yRet = top + x;
                break;
            }
        }

        return new Coord(xRet, yRet);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}