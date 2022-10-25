package enviroment;

import Utils.Celda;
import Utils.Direccion;
import character.Character;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class UnmovableEnvironment extends Enviroment {
    ImageView image;

    public UnmovableEnvironment(Celda pos, int orientacion) {
        super(pos);
        image = new ImageView("file:src/main/resources/wall.png");
        image.setRotate(Direccion.getRotation(orientacion));
        image.setFitWidth(50);
        image.setFitHeight(50);
        image.setTranslateX(pos.x * 50);
        image.setTranslateY(pos.y * 50);
    }

    public UnmovableEnvironment(Celda pos, int orientacion, String type) {
        super(pos);
        image = new ImageView("file:src/main/resources/rock.png");
        image.setTranslateY(pos.y * 50);
        image.setTranslateX(pos.x * 50);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa m) {
        // Nothing
    }

    @Override
    public Node getRender() {
        return image;
    }
}
