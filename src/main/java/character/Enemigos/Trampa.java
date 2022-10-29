package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Trampa extends Enemigo {
    ImageView image = new ImageView("file:src/main/resources/spike.png");

    public Trampa(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas, "Trampa");
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

    @Override
    public void atacar(Player player) {
        player.recibirDanio("Trampa");
    }

    @Override
    public void interactWith(Player p, int direccion, Mapa mapa) {
        atacar(p);
    }

    @Override
    public boolean canInteractWith(Celda p) {
        return p.equals(pos);
    }

    public Node getRender() {
        return image;
    }
}
