import character.Hole;
import character.Player;
import lolo.Mapa;
import utils.Celda;

import org.junit.Before;
import org.junit.Test;

import app.Pantalla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PozoTest {
    Mapa m;
    Player p;
    Hole t;

    @Before
    public void setUp() {
        m = new Mapa("mapa.test.txt", new Pantalla());
        p = m.getPlayer();
        t = new Hole(new Celda(1, 1), m, 3);
    }

    @Test
    public void mataAlJugador() {
        assertEquals(3, p.getVidas());
        t.atacar(p);
        assertEquals(0, p.getVidas());
        assertFalse(p.isAlive());
    }

}
