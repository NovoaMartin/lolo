import Utils.Celda;
import Utils.Direccion;
import Utils.Pantalla;
import character.Player;
import enviroment.MovableRock;
import lolo.Mapa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovableRockTest {
    MovableRock rock;
    Mapa m;

    @Before
    public void setUp() {
        m = new Mapa("mapa.test.txt", new Pantalla(), 10, 10);
        rock = new MovableRock(new Celda(3, 3));
    }

    @Test
    public void testInteractWith() {
        Player p = new Player(new Celda(3, 2), m, 3);
        rock = (MovableRock) m.getEnviroments()[3][3];
        rock.interactWith(p, Direccion.DOWN, m);
        assertEquals(p.getPos(), new Celda(3, 3));
        assertEquals(rock.getPos(), new Celda(3, 4));
    }
}
