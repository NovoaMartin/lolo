package enviroment;

import Utils.Celda;
import character.Character;
import lolo.Mapa;

public class Wall extends Enviroment {
    public Wall(Celda pos) {
        super(pos);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa m) {
        // Nothing
    }
}
