import Utils.Cell;
import Utils.Direction;
import character.Player;
import enviroment.MovableRock;
import enviroment.Wall;
import lolo.GameMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    GameMap map;
    Player player;

    @Before
    public void setUp() {
        map = new GameMap("mapa.test.txt");
        player = map.getPlayer();
    }

    @Test
    public void playerCanMoveToEmptyCell() {
        Cell posicionInicial = player.getPos();
        map.move(player, Direction.DOWN);
        assertEquals(posicionInicial.translate(Direction.DOWN), player.getPos());
    }

    @Test
    public void playerCannotMoveToOccupiedCell() {
        Cell posicionInicial = player.getPos();
        map.move(player, Direction.UP);
        assertEquals(posicionInicial, player.getPos());
    }

    @Test
    public void playerCanGetKeyOnContact() {
        player.setPos(new Cell(1, 2));
        assertFalse(player.hasKey());
        player.move(Direction.DOWN);
        assertTrue(player.hasKey());
    }

    @Test
    public void playerCannotExitWithoutKey() {
        player.setPos(new Cell(3, 4));
        assertFalse(player.isWinner());
        player.move(Direction.RIGHT);
        assertFalse(player.isWinner());
        assertEquals(new Cell(3, 4), player.getPos());
    }

    @Test
    public void playerWinsOnExitSuccess() {
        player.setPos(new Cell(3, 4));
        player.takeKey();
        assertFalse(player.isWinner());
        player.move(Direction.RIGHT);
        assertTrue(player.isWinner());
    }

    @Test
    public void playerCanMoveMovableEnvironments() {
        MovableRock r = (MovableRock) map.getEnviroments()[3][3];
        player.setPos(new Cell(3, 2));
        player.move(Direction.DOWN);
        assertEquals(new Cell(3, 4), r.getPos());
        assertEquals(new Cell(3, 3), player.getPos());
    }
    
    @Test
    public void cannotMoveEnvironmentsOverEachOther() {
        MovableRock r = (MovableRock) map.getEnviroments()[4][5];
        Wall wall = (Wall) map.getEnviroments()[5][5];
        player.setPos(new Cell(3, 5));
        player.move(Direction.RIGHT);
        assertEquals(new Cell(4, 5), r.getPos());
        assertEquals(new Cell(3, 5), player.getPos());
        assertEquals(new Cell(5, 5), wall.getPos());
    }

    @Test
    public void cannotBlockExit() {
        MovableRock r = (MovableRock) map.getEnviroments()[4][5];
        player.setPos(new Cell(4, 6));
        player.move(Direction.UP);
        assertEquals(new Cell(4, 5), r.getPos());
        assertEquals(new Cell(4, 6), player.getPos());
    }

    @Test
    public void cannotMoveOverEnemy() {
        player.setPos(new Cell(5, 2));
        player.move(Direction.UP);
        assertEquals(new Cell(5, 2), player.getPos());
    }

    @Test
    public void hurtOnContactWithEnemy() {
        player.setPos(new Cell(5, 2));
        player.move(Direction.UP);
        assertEquals(2, player.getLives());
        assertTrue(player.isAlive());
    }

    @Test
    public void playerDiesUponSteppingOverPit() {
        player.setPos(new Cell(6, 2));
        player.move(Direction.UP);
        assertEquals(0, player.getLives());
        assertFalse(player.isAlive());
    }

    @Test
    public void cannotMoveEnvironmentOverEnemy() {
        MovableRock r = (MovableRock) map.getEnviroments()[6][4];
        player.setPos(new Cell(5, 4));
        player.move(Direction.RIGHT);
        assertEquals(new Cell(6, 4), r.getPos());
        assertEquals(new Cell(5, 4), player.getPos());
    }

}
