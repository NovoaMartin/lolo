package character.Enemigos;

import Utils.Celda;
import Utils.Direccion;
import character.Enemigo;
import character.Player;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lolo.Mapa;

import java.util.Map;

public class FireThing extends Enemigo {
    ImageView image;
    ImageView fire;

    private static final Map<Integer, Image> images = Map.of(
            Direccion.UP, new Image("file:src/main/resources/dragonUp.png"),
            Direccion.DOWN, new Image("file:src/main/resources/dragonDown.png"),
            Direccion.LEFT, new Image("file:src/main/resources/dragonLeft.png"),
            Direccion.RIGHT, new Image("file:src/main/resources/dragonRight.png")
    );

    Map<Integer, Image> fireImages = Map.of(
            Direccion.UP, new Image("file:src/main/resources/fireUp.png"),
            Direccion.DOWN, new Image("file:src/main/resources/fireDown.png"),
            Direccion.LEFT, new Image("file:src/main/resources/fireLeft.png"),
            Direccion.RIGHT, new Image("file:src/main/resources/fireRight.png")
    );

    boolean isAwake = false;

    int orientacion;

    public FireThing(Celda pos, Mapa map, int vidas, int direccion) {
        super(pos, map, vidas, "FireThing");
        image = new ImageView(images.get(direccion));
        this.orientacion = direccion;
        fire = new ImageView(fireImages.get(direccion));
        fire.setVisible(false);
        image.setFitHeight(50);
        image.setFitWidth(50);
        image.setTranslateX(pos.x * 50);
        image.setTranslateY(pos.y * 50);
    }


    @Override
    public void atacar(Player player) {
        player.morir("Quemado");
    }

    @Override
    public void interactWith(Player p, int direccion, Mapa mapa) {
        if (canAttack(p.getPos().translate(direccion))) {
            atacar(p);
        }
    }

    @Override
    public boolean canInteractWith(Celda p) {
        return alive && p.equals(pos) || canAttack(p);
    }

    public boolean canAttack(Celda p) {
        boolean initialCondition = isAwake && isAlive();
        if (!initialCondition || nextThing == null) return false;
        if (orientacion == Direccion.DOWN) {
            return p.x == pos.x && p.y > pos.y && p.y <= nextThing.y;
        } else if (orientacion == Direccion.UP) {
            return p.x == pos.x && p.y < pos.y && p.y >= nextThing.y;
        } else if (orientacion == Direccion.LEFT) {
            return p.y == pos.y && p.x < pos.x && p.x >= nextThing.x;
        } else if (orientacion == Direccion.RIGHT) {
            return p.y == pos.y && p.x > pos.x && p.x <= nextThing.x;
        }
        return false;
    }

    @Override
    public void awaken() {
        isAwake = true;
        //red effect
        image.setEffect(new ColorAdjust(0, 1, -0.5, 0));
        updateFire();
        fire.setVisible(true);
    }

    Celda nextThing;

    private void updateFire() {
        nextThing = mapa.findNextOccupiedSpace(pos, orientacion);
        nextThing = nextThing.translate(Direccion.reverse(orientacion));
        if (orientacion == Direccion.RIGHT) {
            fire.setTranslateX(pos.x * 50 + 50);
            fire.setTranslateY(pos.y * 50 + 10);
            fire.setFitWidth(50 * (nextThing.x - pos.x));
            fire.setFitHeight(30);
        } else if (orientacion == Direccion.LEFT) {
            fire.setTranslateX(pos.x * 50 - 50 * (pos.x - nextThing.x));
            fire.setTranslateY(pos.y * 50 + 10);
            fire.setFitWidth(50 * (pos.x - nextThing.x));
            fire.setFitHeight(30);
        } else if (orientacion == Direccion.UP) {
            fire.setTranslateX(pos.x * 50 + 10);
            fire.setTranslateY(pos.y * 50 - 50 * (pos.y - nextThing.y));
            fire.setFitWidth(30);
            fire.setFitHeight(50 * (pos.y - nextThing.y));
        } else if (orientacion == Direccion.DOWN) {
            fire.setTranslateX(pos.x * 50 + 10);
            fire.setTranslateY(pos.y * 50 + 50);
            fire.setFitWidth(30);
            fire.setFitHeight(50 * (nextThing.y - pos.y));
        }
    }

    @Override
    public Node getRender() {
        return new Group(image, fire);
    }

    @Override
    public void update() {
        updateFire();
    }
}
