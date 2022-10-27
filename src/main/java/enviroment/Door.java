package enviroment;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;

public class Door extends Enviroment{

	private int keyCount = 0;
	private boolean isOpen = false;
	private static final Image open = new Image("file:src/main/resources/door_open.png");
	
	public Door(int x, int y, int dir) {
		super(x, y);
        image = new ImageView("file:src/main/resources/door.png");
        image.setRotate(90 * dir);
        image.setFitWidth(50);
        image.setFitHeight(50);
        image.setTranslateX(x * 50);
        image.setTranslateY(y * 50);
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
            image.setImage(open);
            isOpen = true;
        }
    }

	@Override
	public Node getRender() {
		return null;
	}

}
