package utils;

import java.util.Objects;

import graphics.Renderable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Celda implements Renderable, Interactable, Movable{
	
    public final int x;
    public final int y;
    
    protected ImageView image;
    
    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        
    }

    public Celda translate(int direccion) {
        if (direccion == Direction.UP) {
            return new Celda(x, y - 1);
        } else if (direccion == Direction.DOWN) {
            return new Celda(x, y + 1);
        } else if (direccion == Direction.LEFT) {
            return new Celda(x - 1, y);
        } else if (direccion == Direction.RIGHT) {
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

	@Override
	public Node getRender() {
		return image;
	}

	@Override
	public void interactWith(Celda target) {
		
	}

	@Override
	public void tryMove(Celda pos, int dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPos(Celda pos) {
		// TODO Auto-generated method stub
		
	}
	
}
