package character;

import graphics.Renderable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;
import utils.Celda;

public class Medusa extends Enemigo implements Renderable {
    ImageView image = new ImageView("file:src/main/resources/medusa.png");

    public Medusa(int x, int y, Mapa mapa, int vidas) {
        super(x, y, vidas, "Medusa");
        image.setTranslateX(2.5 + x * 50);
        image.setTranslateY(2.5 + y * 50);
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
        return alive && distance(p) <= 2;
    }

    @Override
    public boolean canBeAttacked() {
        return true;
    }
}
