package Utils;

import java.util.Objects;

public class Celda {
    public final int x;
    public final int y;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Celda translate(int direccion) {
        if (direccion == Direccion.UP) {
            return new Celda(x, y - 1);
        } else if (direccion == Direccion.DOWN) {
            return new Celda(x, y + 1);
        } else if (direccion == Direccion.LEFT) {
            return new Celda(x - 1, y);
        } else if (direccion == Direccion.RIGHT) {
            return new Celda(x + 1, y);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return x == celda.x && y == celda.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int distance(Celda pos) {
        return (int) Math.ceil(Math.hypot(pos.x - x, pos.y - y));

    }
}
