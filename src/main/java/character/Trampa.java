package character;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Trampa extends Enemigo {
    private ImageView image = new ImageView("file:src/main/resources/spike.png");
    
    public Trampa(int x, int y, Mapa mapa, int vidas) {
        super(x, y, vidas, "Trampa");
        image.setTranslateX(2.5 + x * 50);
        image.setTranslateY(2.5 + y * 50);
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

    public Node getRender() {
        return image;
    }
}
