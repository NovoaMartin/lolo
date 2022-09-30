package items;

import Utils.Celda;
import character.Character;

public class Llave extends Item{

	public Llave(Celda pos) {
		super(pos);
	}
  
    @Override
    public void interactWith(Character character, int direccion, Mapa mapa) {
        if (character instanceof Player) {
            ((Player) character).takeKey();
        }
        this.valid = false;
    }
  
}

