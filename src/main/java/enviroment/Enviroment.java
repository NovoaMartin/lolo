package enviroment;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;
import utils.Constants;

public class Enviroment extends Celda {

	public Enviroment(int x, int y, int from, int to) {
		super(x, y);
		
        this.image = new ImageView(new Image("file:src/main/resources/tileset.png", 128*Constants.MULTIPLIER, 80*Constants.MULTIPLIER, false, false));
        image.setViewport(new Rectangle2D(from*Constants.MULTIPLIER, to*Constants.MULTIPLIER, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE));
        this.image.setX(y * Constants.IMAGE_SIZE);
        this.image.setY(x * Constants.IMAGE_SIZE);
	}
	
	@Override
	public boolean canInteract() {
		return true;
	}

}
