import Utils.Cell;
import character.Enemigos.Pit;
import character.Enemigos.Trap;
import character.Player;
import lolo.GameMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PitTest {
    GameMap m;
    Player p;
    Pit t;

    @Before
    public void setUp() {
        m = new GameMap("mapa.test.txt");
        p = m.getPlayer();
        t = new Pit(new Cell(1, 1), m, 3);
    }

    @Test
    public void killsPlayer() {
        assertEquals(3, p.getLives());
        t.attack(p);
        assertEquals(0, p.getLives());
        assertFalse(p.isAlive());
    }

}
