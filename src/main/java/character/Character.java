package character;

import Utils.*;
import lolo.GameMap;

public abstract class Character {
    private Cell pos;
    private final GameMap gameMap;
    private int lives;
    protected boolean alive = true;

    public Character(Cell pos, GameMap map, int lives) {
        this.pos = pos;
        this.gameMap = map;
        this.lives = lives;
    }

    public void move(int direction) {
        this.gameMap.move(this, direction);
    }

    public void setPos(Cell pos) {
        this.pos = pos;
    }

    public Cell getPos() {
        return pos;
    }
    
    public void hurt() {
    	this.lives--;
    	if(this.lives == 0) {
    		kill();
    	}
    }
    
    public void kill() {
        this.lives = 0;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public int getLives() {
        return lives;
    }
    
    // TODO: Hacerlo un enum?
    abstract public String getAlliance();
}
