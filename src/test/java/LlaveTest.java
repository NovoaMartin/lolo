import character.Player;
import items.Key;
import lolo.Mapa;
import utils.Celda;
import utils.Direction;

import org.junit.Test;

import app.Pantalla;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LlaveTest {
    @Test
    public void testInteractWith() {
        new Thread(Pantalla::startGame).start();
        Mapa m = new Mapa("mapa.test.txt", new Pantalla());
        Key l = new Key(new Celda(1, 1));
        Player p = new Player(new Celda(2, 1), m, 3);
        l.interactWith(p, Direction.DOWN, m);
        assertTrue(p.hasKey());
        assertFalse(l.isValid());
    }
}
