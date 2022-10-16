package character;

import Utils.Cell;
import lolo.GameMap;

public abstract class Enemy extends Character{
	String name;
	
	public Enemy(Cell pos, GameMap map, int lives, String name) {
    	super(pos, map, lives);
    	this.name = name;
    }
	
	public abstract void attack(Player player);
    
    public String getAlliance() {
    	return "ENEMY";
    }
}
