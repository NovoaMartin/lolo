import Utils.Cell;
import Utils.Direction;
import character.Player;
import enviroment.Wall;
import lolo.GameMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallTest {
    Wall wall;
    GameMap m;

    @Before
    public void setUp() {
        m = new GameMap("mapa.test.txt");
        wall = new Wall(new Cell(1, 1));
    }

    @Test
    public void testInteractWith() {
        Player p = new Player(new Cell(2, 1), m, 3);
        wall.interactWith(p, Direction.DOWN, m);
        assertEquals(p.getPos(), new Cell(2, 1));
        assertEquals(3, p.getLives());
        assertEquals(wall.getPos(), new Cell(1, 1));
    }

    @Test
    public void setPosTest() {
        wall.setPos(new Cell(2, 2));
        assertEquals(wall.getPos(), new Cell(2, 2));
    }

}
