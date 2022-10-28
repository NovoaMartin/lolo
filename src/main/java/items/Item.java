package items;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;
import utils.Constants;

public class Item extends Celda {

    protected boolean valid = true;

    public Item(int x, int y, int from, int to) {
        super(x, y);
        
        this.image = new ImageView(new Image("file:src/main/resources/tileset.png", 128*5, 80*5, false, false));
        image.setViewport(new Rectangle2D(from*5, to*5, Constants.imageSize * 5, Constants.imageSize * 5));
        this.image.setX(y * Constants.imageSize * 5);
        this.image.setY(x * Constants.imageSize * 5);
    }

    public boolean isValid() {
        return valid;
    }
}
