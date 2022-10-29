package utils;

import java.util.Objects;

import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Celda implements Renderable, Interactable{
	
    public int j;
    public int i;
    
    protected ImageView image;
    protected boolean valid = true;
    
    public Celda(int i, int j) {
    	this.i = i;
        this.j = j;
        
    }

    public Celda translate(int direccion) {
        if (direccion == Direction.UP) {
            return new Celda(i - 1, j);
        } else if (direccion == Direction.DOWN) {
            return new Celda(i + 1, j);
        } else if (direccion == Direction.LEFT) {
            return new Celda(i, j - 1);
        } else if (direccion == Direction.RIGHT) {
            return new Celda(i, j + 1);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return j == celda.j && i == celda.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(j, i);
    }

    public int distance(Celda pos) {
        return (int) Math.ceil(Math.hypot(pos.j - j, pos.i - i));
    }

	@Override
	public Node getRender() {
		return image;
	}

	public void interactWith(Player p) {
	}
	
	public boolean isValid() {
		return valid;
	}

	@Override
	public boolean canInteract() {
		return false;
	}
	
	public void visible(boolean s) {
		image.setVisible(s);
	}
	
	public void tryMove(Celda pos, int dir) {
		
	}

}
