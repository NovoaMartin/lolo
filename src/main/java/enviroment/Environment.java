package enviroment;

import Utils.Cell;
import character.Character;
import lolo.GameMap;

public abstract class Environment {
    protected Cell pos;
    protected GameMap mapa;

    public Environment(Cell pos) {
        this.pos = pos;
    }

    public abstract void interactWith(Character character, int direction, GameMap map);

    public void setPos(Cell pos) {
        this.pos = pos;
    }

    public Cell getPos() {
        return pos;
    }
}
