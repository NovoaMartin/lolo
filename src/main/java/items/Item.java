package items;

import Utils.Celda;
import character.Character;
import graphics.Renderable;
import lolo.Mapa;

public abstract class Item implements Renderable {
    protected Celda pos;
    protected boolean valid = true;

    public Item(Celda pos) {
        this.pos = pos;
    }
    
    public abstract void interactWith(Character character, int direccion, Mapa mapa);

    public boolean isValid() {
        return valid;
    }
}
