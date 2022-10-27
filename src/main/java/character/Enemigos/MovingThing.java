package character.Enemigos;

import Utils.Constants;
import Utils.Direccion;
import character.Enemigo;
import character.Player;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lolo.Mapa;
import Utils.Celda;

public class MovingThing extends Enemigo {

    ImageView image = new ImageView(new Image("file:src/main/resources/medusa.png"));
    private int direccion = Direccion.DOWN;

    public MovingThing(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas, "MovingThing");
        image.setFitHeight(50);
        image.setFitWidth(50);
        image.setTranslateX(pos.x * 50);
        image.setTranslateY(pos.y * 50);
    }

    @Override
    public void atacar(Player player) {
        player.recibirDanio("Moving");
    }

    @Override
    public void interactWith(Player p, int direccion, Mapa mapa) {
        atacar(p);
    }

    @Override
    public Node getRender() {
        return image;
    }

    private boolean moving = false;

    @Override
    public void update() {
        moving = true;
        tryMove(direccion);
    }

    @Override
    public void setPos(Celda pos) {
        if (!moving)
            moving = true;
        super.setPos(pos);
        TranslateTransition tt = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        tt.setByY(50);
        tt.setOnFinished(e -> moving = false);
        tt.play();
    }

    @Override
    public boolean canBeAttacked() {
        return true;
    }

    @Override
    public void morir(String enemigo) {
        super.morir(enemigo);
        image.setVisible(false);
    }
}
