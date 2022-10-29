import Utils.Celda;
import Utils.Direccion;
import Utils.MapLoader;
import Utils.Pantalla;
import character.Player;
import items.Llave;
import lolo.Mapa;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LlaveTest {
    @Test
    public void testInteractWith() throws InterruptedException {
        Util.initialize();
        Mapa m = MapLoader.loadFromFile("mapa.test.txt", new Pantalla());
        Llave l = new Llave(new Celda(1, 1));
        Player p = new Player(new Celda(2, 1), m, 3);
        l.interactWith(p, Direccion.DOWN, m);
        assertTrue(p.hasKey());
        assertFalse(l.isValid());
    }
}
