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

    int tickCount = 0;

    @Override
    public void update() {
        if (!isAlive()) return;
        tickCount = (tickCount + 1) % 1000;
        if (tickCount != 0) return;
        direccion = mapa.canMove(pos.translate(direccion), direccion) ? direccion : Direccion.reverse(direccion);
        tryMove(direccion);
    }

    @Override
    public void setPos(Celda pos) {
        super.setPos(pos);
        TranslateTransition tt = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        if (direccion == Direccion.RIGHT)
            tt.setByX(50);
        else if (direccion == Direccion.LEFT)
            tt.setByX(-50);
        else if (direccion == Direccion.UP)
            tt.setByY(-50);
        else if (direccion == Direccion.DOWN)
            tt.setByY(50);
        tt.play();
    }

    @Override
    public boolean canInteractWith(Celda p) {
        return alive && p.equals(pos);
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
