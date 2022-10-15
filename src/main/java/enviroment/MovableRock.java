package enviroment;

import Utils.Celda;
import Utils.Direccion;
import character.Character;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;

public class MovableRock extends Enviroment {
    Shape shape;

    public MovableRock(Celda pos) {
        super(pos);
        shape = new Rectangle(50, 50);
        shape.setTranslateY(2.5 + pos.y * 50);
        shape.setTranslateX(2.5 + pos.x * 50);
        shape.setFill(Color.DARKGREEN);
    }

    private int orientacion = Direccion.RIGHT;

    protected boolean tryMove(int direccion, Mapa mapa) {
        this.orientacion = direccion;
        return mapa.tryMove(this, direccion);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        boolean moved = this.tryMove(direccion, mapa);
        if (moved) {
            character.tryMove(direccion);
        }
    }

    @Override
    public Node getRender() {
        return shape;
    }

    @Override
    public void setPos(Celda pos) {
        super.setPos(pos);
        shape.setTranslateY(2.5 + pos.y * 50);
        shape.setTranslateX(2.5 + pos.x * 50);
    }
}
