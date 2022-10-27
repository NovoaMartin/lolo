package enviroment;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Wall extends Enviroment{
	
	public Wall(int x, int y, int dir) {
		super(x, y);
        image = new ImageView("file:src/main/resources/wall2.png");
        image.setRotate(90 * dir);
        image.setFitWidth(50);
        image.setFitHeight(50);
        image.setTranslateX(x * 50);
        image.setTranslateY(y * 50);
	}

	@Override
	public Node getRender() {
		return null;
	}

}
