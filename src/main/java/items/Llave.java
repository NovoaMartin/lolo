package items;

import Utils.Celda;
import character.Character;
import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Llave extends Item {

    ImageView image;

    public Llave(Celda pos) {
        super(pos);
        image = new ImageView("file:src/main/resources/llave.png");
        image.setTranslateY(2.5 + pos.y * 50);
        image.setTranslateX(2.5 + pos.x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        if (character instanceof Player) {
            ((Player) character).takeKey();
            this.image.setOpacity(0.3);
        }
        this.valid = false;
    }

    @Override
    public Node getRender() {
        return image;
    }
}

