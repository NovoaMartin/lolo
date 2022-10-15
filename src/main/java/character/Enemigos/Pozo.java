package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Pozo extends Enemigo {
    ImageView image = new ImageView("file:src/main/resources/hole.png");

    public Pozo(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas, "Pozo");
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

    @Override
    public void atacar(Player player) {
        player.morir();
        System.out.println("Te has caido en un pozo");
    }

    @Override
    public Node getRender() {
        return image;
    }
}