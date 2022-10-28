package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Medusa extends Enemigo {
    ImageView image = new ImageView("file:src/main/resources/medusa.png");

    public Medusa(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas, "Medusa");
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

    @Override
    public void atacar(Player player) {
        player.morir("Medusa");

    }

    @Override
    public void morir(String enemigo) {
        super.morir(enemigo);
        image.setVisible(false);
    }

    @Override
    public void interactWith(Player p, int direccion, Mapa mapa) {
        if (alive) atacar(p);
    }

    @Override
    public Node getRender() {
        return image;
    }

    public boolean canInteractWith(Celda p) {
        return alive && p.distance(this.pos) <= 2;
    }

    @Override
    public boolean canBeAttacked() {
        return true;
    }
}
