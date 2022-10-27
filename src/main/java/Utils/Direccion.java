package Utils;

public class Direccion {
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 3;
    public static final int RIGHT = -3;

    public static int getRotation(int direccion) {
        switch (direccion) {
            case DOWN:
                return 0;
            case LEFT:
                return 90;
            case RIGHT:
                return 270;
            default:
                return 180;
        }
    }

    public static int reverse(int direccion) {
        return -direccion;
    }
}
