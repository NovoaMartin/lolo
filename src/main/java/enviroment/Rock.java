package enviroment;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Rock extends Enviroment {
    private ImageView image;

    public Rock(int x, int y) {
        super(x, y);
        image = new ImageView("file:src/main/resources/rock.png");
        image.setTranslateX(x * 50);
        image.setTranslateY(y * 50);
    }
    
    @Override
    public Node getRender() {
        return image;
    }
}
