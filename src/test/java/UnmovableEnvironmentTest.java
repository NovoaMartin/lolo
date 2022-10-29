import Utils.Celda;
import Utils.Direccion;
import Utils.Pantalla;
import character.Player;
import environment.UnmovableEnvironment;
import lolo.Mapa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnmovableEnvironmentTest {
    UnmovableEnvironment unmovableEnvironment;
    Mapa m;

    @Before
    public void setUp() {
        m = new Mapa("mapa.test.txt", new Pantalla(), 10, 10);
        unmovableEnvironment = new UnmovableEnvironment(new Celda(1, 1), Direccion.DOWN);
    }

    @Test
    public void testInteractWith() {
        Player p = new Player(new Celda(2, 1), m, 3);
        unmovableEnvironment.interactWith(p, Direccion.DOWN, m);
        assertEquals(p.getPos(), new Celda(2, 1));
        assertEquals(3, p.getVidas());
        assertEquals(unmovableEnvironment.getPos(), new Celda(1, 1));
    }

    @Test
    public void setPosTest() {
        unmovableEnvironment.setPos(new Celda(2, 2));
        assertEquals(unmovableEnvironment.getPos(), new Celda(2, 2));
    }

}
