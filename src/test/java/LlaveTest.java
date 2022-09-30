import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LlaveTest {
    @Test
    public void testInteractWith() {
        Mapa m = new Mapa("mapa.test.txt");
        Llave l = new Llave(new Celda(1, 1), m);
        Player p = new Player(new Celda(2, 1), m, 3);
        l.interactWith(p, Direccion.DOWN, m);
        assertTrue(p.hasKey());
        assertFalse(l.isValid());
    }
}
