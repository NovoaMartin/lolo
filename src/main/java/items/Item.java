package items;

import Utils.Cell;
import character.Character;
import lolo.GameMap;

public abstract class Item {
    protected Cell pos;
    protected boolean valid = true;

    public Item(Cell pos) {
        this.pos = pos;
    }
    
    public abstract void interactWith(Character character, int direction, GameMap map);

    public boolean isValid() {
        return valid;
    }
}
