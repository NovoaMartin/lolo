package character;

import Utils.Cell;
import lolo.GameMap;

public class Player extends Character {
    private boolean winner = false;
    private boolean key = false;

    public Player(Cell pos, GameMap map, int lives) {
        super(pos, map, lives);
    }

    public void move(int direction) {
        if (alive && !winner)
            super.move(direction);
    }

    public void takeKey() {
        this.key = true;
    }

    public boolean hasKey() {
        return this.key;
    }

    public void setWinner() {
        this.winner = true;
    }

    public boolean isWinner() {
        return this.winner;
    }
    
    public boolean isPlayer() {
    	return true;
    }
}
