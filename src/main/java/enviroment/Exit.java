package enviroment;

import Utils.Celda;
import character.Character;
import character.Enemigo;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lolo.Mapa;

public class Exit extends Enviroment {
    private static final Image closed = new Image("file:src/main/resources/door.png");
    private static final Image open = new Image("file:src/main/resources/door_open.png");

    ImageView image;

    private int keyCount = 0;

    private boolean isOpen = false;
    private final Mapa map;

    public Exit(Celda pos, Mapa map) {
        super(pos);
        this.map = map;
        image = new ImageView();
        image.setImage(closed);
        image.setTranslateY(pos.y * 50);
        image.setTranslateX(pos.x * 50);
        image.setFitHeight(50);
        image.setFitWidth(50);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        if (character.canWin() && character.hasWinCondition() && isOpen) {
            character.setPos(pos);
            character.win();
        }
    }

    public void increaseKeyCount() {
        keyCount++;
        if (keyCount == 1) {
            image.setImage(open);
            for(Enemigo e : map.getEnemigos()) {
                e.awaken();
            }
            isOpen = true;
        }
    }

    @Override
    public Node getRender() {
        return image;
    }
}
