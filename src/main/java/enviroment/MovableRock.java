package enviroment;

import Utils.Celda;
import Utils.Direccion;
import character.Character;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import lolo.Mapa;

public class MovableRock extends Enviroment {
    Shape image;

    public MovableRock(Celda pos) {
        super(pos);
        image = new Rectangle(50, 50);
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
        image.setFill(Color.DARKGREEN);
    }

    private int orientacion = Direccion.RIGHT;

    protected boolean tryMove(int direccion, Mapa mapa) {
        this.orientacion = direccion;
        return mapa.tryMove(this, direccion);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        Celda inicial = this.getPos();
        boolean moved = this.tryMove(direccion, mapa);
        if (moved) {
            character.setPos(inicial);
        }
    }

    @Override
    public Node getRender() {
        return image;
    }

    @Override
    public void setPos(Celda pos) {
        super.setPos(pos);
        TranslateTransition animacion = new TranslateTransition(Duration.millis(500), image);
        animacion.setToX(2.5 + pos.x * 50);
        animacion.setToY(2.5 + pos.y * 50);
        animacion.play();
        animacion.setOnFinished(e -> {
            image.setTranslateX(2.5 + pos.x * 50);
            image.setTranslateY(2.5 + pos.y * 50);
        });
    }
}
