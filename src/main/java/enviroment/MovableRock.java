package enviroment;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Shape;
import utils.Celda;
import utils.Constants;
import utils.Direction;

public class MovableRock extends Enviroment{
    private Shape image;

    public MovableRock(int x, int y) {
        super(x, y);
//        image = new Rectangle(50, 50);
//        image.setTranslateX(2.5 + x * 50);
//        image.setTranslateY(2.5 + y * 50);
//        image.setFill(Color.DARKGREEN);
    }

    private int orientacion = Direction.RIGHT;

//    protected boolean tryMove(int direccion, Mapa mapa) {
//        this.orientacion = direccion;
//        return mapa.tryMove(this, direccion);
//    }

//    @Override
//    public void interactWith(Character character, int direccion, Mapa mapa) {
//        Celda inicial = this.getPos();
//        boolean moved = this.tryMove(direccion, mapa);
//        if (moved) {
//            character.setPos(inicial);
//        }
//    }

	@Override
	public void setPos(Celda pos) {
        super.setPos(pos);
        TranslateTransition animacion = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        animacion.setToX(2.5 + pos.x * 50);
        animacion.setToY(2.5 + pos.y * 50);
        animacion.play();
        animacion.setOnFinished(e -> {
            image.setTranslateX(2.5 + pos.x * 50);
            image.setTranslateY(2.5 + pos.y * 50);
        });
	}
}
