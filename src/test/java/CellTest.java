import Utils.Cell;
import Utils.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {
    Cell celda;

    @Before
    public void setUp() {
        celda = new Cell(0, 0);
    }

    @Test
    public void translateTest() {
        Cell up = celda.translate(Direction.UP);
        Cell down = celda.translate(Direction.DOWN);
        Cell left = celda.translate(Direction.LEFT);
        Cell right = celda.translate(Direction.RIGHT);
        Cell invalid = celda.translate(42);
        Assert.assertEquals(new Cell(0, -1), up);
        Assert.assertEquals(new Cell(0, 1), down);
        Assert.assertEquals(new Cell(-1, 0), left);
        Assert.assertEquals(new Cell(1, 0), right);
        Assert.assertNull(invalid);
    }
}
