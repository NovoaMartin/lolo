package character.Enemigos;

import Utils.Cell;
import character.Enemy;
import character.Player;
import lolo.GameMap;

public class Pit extends Enemy {
    public Pit(Cell pos, GameMap mapa, int vidas) {
        super(pos, mapa, vidas,"PIT");
    }

    @Override
    public void attack(Player player) {
        player.kill();
        //System.out.println("Te has caido en un pozo");
    }
}