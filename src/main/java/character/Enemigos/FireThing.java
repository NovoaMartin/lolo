package character.Enemigos;

import Utils.Celda;
import Utils.Direccion;
import character.Enemigo;
import character.Player;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lolo.Mapa;

public class FireThing extends Enemigo {
    ImageView image = new ImageView("file:src/main/resources/medusa.png");
    Rectangle fire = new Rectangle(0, 0);

    boolean isAwake = false;

    int orientacion;

    public FireThing(Celda pos, Mapa map, int vidas, int direccion) {
        super(pos, map, vidas, "FireThing");
        this.orientacion = direccion;
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
        updateFire();
        fire.setVisible(true);
    }

    Celda nextThing;

    private void updateFire() {
        nextThing = mapa.findNextOccupiedSpace(pos, orientacion);
        nextThing = nextThing.translate(Direccion.reverse(orientacion));
        if (orientacion == Direccion.RIGHT) {
            fire.setX(pos.x * 50 + 50);
            fire.setY(pos.y * 50 + 10);
            fire.setWidth(50 * (nextThing.x - pos.x));
            fire.setHeight(30);
        } else if (orientacion == Direccion.LEFT) {
            fire.setX(pos.x * 50 - 50 * (pos.x - nextThing.x));
            fire.setY(pos.y * 50 + 10);
            fire.setWidth(50 * (pos.x - nextThing.x));
            fire.setHeight(30);
        } else if (orientacion == Direccion.UP) {
            fire.setX(pos.x * 50 + 10);
            fire.setY(pos.y * 50 - 50 * (pos.y - nextThing.y));
            fire.setWidth(30);
            fire.setHeight(50 * (pos.y - nextThing.y));
        } else if (orientacion == Direccion.DOWN) {
            fire.setX(pos.x * 50 + 10);
            fire.setY(pos.y * 50 + 50);
            fire.setWidth(30);
            fire.setHeight(50 * (nextThing.y - pos.y));
        }
        fire.setFill(Color.RED);
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
