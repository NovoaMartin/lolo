package Utils;

import java.util.Objects;

import lolo.GameMap;

public class Cell {
    public final int x;
    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell translate(int direction) {
        if (direction == Direction.UP) {
            return new Cell(x, y - 1);
        } else if (direction == Direction.DOWN) {
            return new Cell(x, y + 1);
        } else if (direction == Direction.LEFT) {
            return new Cell(x - 1, y);
        } else if (direction == Direction.RIGHT) {
            return new Cell(x + 1, y);
        } else {
            return null;
        }
    }
    
    public boolean isOutsideMap(GameMap map) {
    	return x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell celda = (Cell) o;
        return x == celda.x && y == celda.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
