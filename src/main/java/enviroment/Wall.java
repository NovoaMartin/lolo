package enviroment;

import Utils.Celda;
import character.Character;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;

public class Wall extends Enviroment {
    Shape shape;
    public Wall(Celda pos) {
        super(pos);
        shape = new Rectangle(50, 50);
        shape.setTranslateY(2.5 + pos.y * 50);
        shape.setTranslateX(2.5 + pos.x * 50);
        shape.setFill(javafx.scene.paint.Color.BROWN);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa m) {
        // Nothing
    }

    @Override
    public Node getRender() {
        return shape;
    }
}
