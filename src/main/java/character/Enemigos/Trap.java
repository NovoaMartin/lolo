package character.Enemigos;

import Utils.Cell;
import character.Enemy;
import character.Player;
import lolo.GameMap;

public class Trap extends Enemy {
    public Trap(Cell pos, GameMap map, int lives) {
        super(pos, map, lives,"TRAP");
    }

    @Override
    public void attack(Player player) {
        player.hurt();
        //System.out.println("Te has caido en una trampa");
    }
}
