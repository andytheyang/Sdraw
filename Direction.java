public enum Direction { 
    DOWN, RIGHT, UP, LEFT;

    private static Direction[] vals = values();

    public Direction counterClockwise() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public Direction clockwise() {
        return vals[(this.ordinal() - 1 + vals.length) % vals.length];
    }

    public static void main (String[] args) {
        Direction down = Direction.DOWN;

        System.out.println(down);
        System.out.println(down.counterClockwise());
        System.out.println(down.clockwise());
    }
}