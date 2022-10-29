package enviroment;

import character.Player;
import javafx.geometry.Rectangle2D;
import utils.Constants;

public class Door extends Enviroment{

	private boolean isOpen = false;
	
	public Door(int x, int y, int dir, int from, int to) {
		super(x, y, from, to);
        image.setRotate(90 * dir);
        image.setViewOrder(10);
	}
	
    @Override
    public void interactWith(Player p) {
        if (isOpen) {
            p.tryMove(this, p.facingDir());
        }
    }
    
    @Override 
    public boolean canInteract() {
    	return isOpen;
    }
    
    public void open() {
    	isOpen = true;
    	image.setViewport(new Rectangle2D(80 * Constants.MULTIPLIER, 64 * Constants.MULTIPLIER, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE));
    }

}
