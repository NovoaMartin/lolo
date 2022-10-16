package enviroment;

import Utils.Cell;
import character.Character;
import lolo.GameMap;

public class MovableRock extends Environment {

    public MovableRock(Cell pos) {
        super(pos);
    }
    
    protected boolean move(int direction, GameMap map) {
        return map.move(this, direction);
    }
    
    @Override
    public void interactWith(Character character, int direction, GameMap map) {
        boolean moved = this.move(direction, map);
        if (moved) {
            character.move(direction);
        }
    }
}
