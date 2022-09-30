package items;

import Utils.Celda;
import character.Character;
import lolo.Mapa;

public abstract class Item {
	
	private  Celda pos;
    protected boolean valid = true;

    public Item(Celda pos) {
    	this.pos = pos;
    }
    
    public Celda getPos() {
    	return pos;
    }
    
    public abstract void interactWith(Character character, int direccion, Mapa mapa);

    public boolean isValid() {
        return valid;
    }
}
