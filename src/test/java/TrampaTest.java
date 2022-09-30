import Utils.Celda;
import character.Enemigos.Trampa;
import character.Player;
import lolo.Mapa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TrampaTest {
    Mapa m;
    Player p;
    Trampa t;

    @Before
    public void setUp() {
        m = new Mapa("mapa.test.txt");
        p = m.getPlayer();
        t = new Trampa(new Celda(1, 1), m, 3);
    }

    @Test
    public void daniaJugador(){
        assertEquals(3,p.getVidas());
        t.atacar(p);
        assertEquals(2,p.getVidas());
    }

    @Test
    public void mataAlJugador(){
        assertEquals(3,p.getVidas());
        t.atacar(p);
        t.atacar(p);
        t.atacar(p);
        assertEquals(0,p.getVidas());
        assertFalse(p.isAlive());
    }
}