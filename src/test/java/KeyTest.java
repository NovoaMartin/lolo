import Utils.Cell;
import Utils.Direction;
import character.Player;
import items.Key;
import lolo.GameMap;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KeyTest {
    @Test
    public void testInteractWith() {
        GameMap m = new GameMap("mapa.test.txt");
        Key l = new Key(new Cell(1, 1));
        Player p = new Player(new Cell(2, 1), m, 3);
        l.interactWith(p, Direction.DOWN, m);
        assertTrue(p.hasKey());
        assertFalse(l.isValid());
    }
}
