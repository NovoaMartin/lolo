package enviroment;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import utils.Celda;
import utils.Constants;

public class Door extends Enviroment{

	private int keyCount = 0;
	private boolean isOpen = false;
	private static final Image open = new Image("file:src/main/resources/door_open.png");
	
	public Door(int x, int y, int dir, int from, int to) {
		super(x, y, from, to);
        image.setRotate(90 * dir);
	}
	
    @Override
    public void interactWith(Celda target) {
//        if (character.canWin() && character.hasWinCondition() && isOpen) {
//            character.setPos(pos);
//            character.win();
//        }
    }
	
    public void increaseKeyCount(){
        keyCount++;
        if (keyCount == 1) {
            image.setViewport(new Rectangle2D(80, 64, Constants.imageSize, Constants.imageSize));
            isOpen = true;
        }
    }

}
