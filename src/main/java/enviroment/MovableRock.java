package enviroment;

import Utils.Celda;
import Utils.Constants;
import character.Character;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class MovableRock extends Enviroment {
    ImageView image;

    public MovableRock(Celda pos) {
        super(pos);
        image = new ImageView(new Image("file:src/main/resources/movableRock.png"));
        image.setFitWidth(50);
        image.setFitHeight(50);
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
    }

    protected boolean tryMove(int direccion, Mapa mapa) {
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
        TranslateTransition animacion = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        animacion.setToX(2.5 + pos.x * 50);
        animacion.setToY(2.5 + pos.y * 50);
        animacion.play();
    }

    @Override
    public boolean canMove(int direccion, Mapa mapa) {
        return mapa.canMove(pos.translate(direccion), direccion);
    }
}
