import Utils.Celda;
import Utils.Direccion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CeldaTest {
    Celda celda;

    @Before
    public void setUp() {
        celda = new Celda(0, 0);
    }

    @Test
    public void translateTest() {
        Celda up = celda.translate(Direccion.UP);
        Celda down = celda.translate(Direccion.DOWN);
        Celda left = celda.translate(Direccion.LEFT);
        Celda right = celda.translate(Direccion.RIGHT);
        Celda invalid = celda.translate(42);
        Assert.assertEquals(new Celda(0, -1), up);
        Assert.assertEquals(new Celda(0, 1), down);
        Assert.assertEquals(new Celda(-1, 0), left);
        Assert.assertEquals(new Celda(1, 0), right);
        Assert.assertNull(invalid);
    }
}
