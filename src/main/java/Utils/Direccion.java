package Utils;

public class Direccion {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static int getRotation(int direccion) {
        return switch (direccion) {
            case DOWN -> 0;
            case LEFT -> 90;
            case RIGHT -> 270;
            default -> 180;
        };
    }
}
