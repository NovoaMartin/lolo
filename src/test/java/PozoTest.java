import Utils.Celda;
import Utils.MapLoader;
import Utils.Pantalla;
import character.Enemigos.Pozo;
import character.Player;
import lolo.Mapa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PozoTest {
    Mapa m;
    Player p;
    Pozo t;

    @Before
    public void setUp() throws InterruptedException {
        Util.initialize();
        m = MapLoader.loadFromFile("mapa.test.txt", new Pantalla());
        p = m.getPlayer();
        t = new Pozo(new Celda(1, 1), m, 3);
    }

    @Test
    public void mataAlJugador() {
        assertEquals(3, p.getVidas());
        t.atacar(p);
        assertEquals(0, p.getVidas());
        assertFalse(p.isAlive());
    }

}
