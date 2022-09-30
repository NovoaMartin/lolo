import Utils.Celda;
import character.Enemigos.Pozo;
import character.Enemigos.Trampa;
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
    public void setUp() {
        m = new Mapa("mapa.test.txt");
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
