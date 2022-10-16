import Utils.Cell;
import Utils.Direction;
import character.Player;
import enviroment.MovableRock;
import lolo.GameMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovableRockTest {
    MovableRock rock;
    GameMap m;

    @Before
    public void setUp() {
        m = new GameMap("mapa.test.txt");
        rock = new MovableRock(new Cell(3, 3));
    }

    @Test
    public void testInteractWith() {
        Player p = new Player(new Cell(3, 2), m, 3);
        rock = (MovableRock) m.getEnviroments()[3][3];
        rock.interactWith(p, Direction.DOWN, m);
        assertEquals(p.getPos(), new Cell(3, 3));
        assertEquals(rock.getPos(), new Cell(3, 4));
    }
}
