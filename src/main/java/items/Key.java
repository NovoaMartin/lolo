package items;

import Utils.Cell;
import character.Character;
import character.Player;
import lolo.GameMap;

public class Key extends Item{

	public Key(Cell pos) {
		super(pos);
	}

    @Override
    public void interactWith(Character character, int direction, GameMap map) {
        if (character.isPlayer()) {
            ((Player)character).takeKey();
        }
        this.valid = false;
    }
  
}

