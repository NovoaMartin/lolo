package items;

import Utils.Celda;
import character.Character;
import character.Player;
import lolo.Mapa;

public class Llave extends Item {
    public Llave(Celda pos, Mapa mapa) {
        super(pos, mapa);
    }

    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        if (character instanceof Player) {
            ((Player) character).takeKey();
        }
        this.valid = false;
    }
}
