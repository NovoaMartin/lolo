package enviroment;

import Utils.Cell;
import character.Character;
import lolo.GameMap;

public class Wall extends Environment {
    public Wall(Cell pos) {
        super(pos);
    }

    @Override
    public void interactWith(Character character, int direction, GameMap m) {
        // Nothing
    }
}
