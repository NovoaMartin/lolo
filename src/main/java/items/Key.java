package items;

import character.Character;
import character.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import lolo.Mapa;
import utils.Celda;

public class Key extends Item{

    ImageView image;

    public Key(int x, int y) {
        super(x, y);
        image = new ImageView("file:src/main/resources/llave2.png");
        image.setTranslateY(2.5 + y * 50);
        image.setTranslateX(2.5 + x * 50);
        image.setFitHeight(45);
        image.setFitWidth(45);
    }

//    @Override
//    public void interactWith(int direccion) {
//        if (character instanceof Player) {
//            ((Player) character).takeKey();
//            this.image.setOpacity(0.3);
//            mapa.getDoor().increaseKeyCount();
//        }
//        this.valid = false;
//    }

    @Override
    public Node getRender() {
        return image;
    }

}

