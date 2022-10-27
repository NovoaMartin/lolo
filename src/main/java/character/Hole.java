package character;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Hole extends Enemigo {
    private ImageView image = new ImageView("file:src/main/resources/hole.png");

    public Hole(int x, int y, int vidas) {
        super(x, y, vidas, "Pozo");
        image.setTranslateY(2.5 + y * 50);
        image.setTranslateX(2.5 + x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

    @Override
    public void atacar(Player player) {
//        player.setPos(this.pos);
        player.morir("Pozo");
        System.out.println("Te has caido en un pozo");
    }

    @Override
    public void interactWith(Player p, int direccion, Mapa mapa) {
        atacar(p);
    }

    @Override
    public Node getRender() {
        return image;
    }
}