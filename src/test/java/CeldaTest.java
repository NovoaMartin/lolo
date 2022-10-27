import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Celda;
import utils.Direction;

public class CeldaTest {
    Celda celda;

    @Before
    public void setUp() {
        celda = new Celda(0, 0);
    }

    @Test
    public void translateTest() {
        Celda up = celda.translate(Direction.UP);
        Celda down = celda.translate(Direction.DOWN);
        Celda left = celda.translate(Direction.LEFT);
        Celda right = celda.translate(Direction.RIGHT);
        Celda invalid = celda.translate(42);
        Assert.assertEquals(new Celda(0, -1), up);
        Assert.assertEquals(new Celda(0, 1), down);
        Assert.assertEquals(new Celda(-1, 0), left);
        Assert.assertEquals(new Celda(1, 0), right);
        Assert.assertNull(invalid);
    }
}
