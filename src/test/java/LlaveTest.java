import Utils.Celda;
import Utils.Direccion;
import Utils.Pantalla;
import character.Player;
import items.Llave;
import lolo.Mapa;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LlaveTest {
    @Test
    public void testInteractWith() {
        new Thread(Pantalla::startGame).start();
        Mapa m = new Mapa("mapa.test.txt", new Pantalla(), 10, 10);
        Llave l = new Llave(new Celda(1, 1));
        Player p = new Player(new Celda(2, 1), m, 3);
        l.interactWith(p, Direccion.DOWN, m);
        assertTrue(p.hasKey());
        assertFalse(l.isValid());
    }
}
