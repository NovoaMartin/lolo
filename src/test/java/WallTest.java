import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallTest {
    Wall wall;
    Mapa m;

    @Before
    public void setUp() {
        m = new Mapa("mapa.test.txt");
        wall = new Wall(new Celda(1, 1), m);
    }

    @Test
    public void testInteractWith() {
        Player p = new Player(new Celda(2, 1), m, 3);
        wall.interactWith(p, Direccion.DOWN);
        assertEquals(p.getPos(), new Celda(2, 1));
        assertEquals(3, p.vidas);
        assertEquals(wall.getPos(), new Celda(1, 1));
    }

    @Test
    public void setPosTest() {
        wall.setPos(new Celda(2, 2));
        assertEquals(wall.getPos(), new Celda(2, 2));
    }

}
