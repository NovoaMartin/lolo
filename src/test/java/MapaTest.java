import Utils.Celda;
import Utils.Direccion;
import character.Player;
import enviroment.MovableRock;
import enviroment.Wall;
import lolo.Mapa;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapaTest {
    Mapa mapa;
    Player player;

    @Before
    public void setUp() {
        mapa = new Mapa("mapa.test.txt");
        player = mapa.getPlayer();
    }

    @Test
    public void elJugadorSeMueveEnCeldasVacias() {
        Celda posicionInicial = player.getPos();
        mapa.tryMove(player, Direccion.DOWN);
        assertEquals(posicionInicial.translate(Direccion.DOWN), player.getPos());
    }

    @Test
    public void elJugadorNoSeMueveEnCeldasOcupadas() {
        Celda posicionInicial = player.getPos();
        mapa.tryMove(player, Direccion.UP);
        assertEquals(posicionInicial, player.getPos());
    }

    @Test
    public void elJugadorObtieneLaLlaveAlMoverse() {
        player.setPos(new Celda(1, 2));
        assertFalse(player.hasKey());
        player.tryMove(Direccion.DOWN);
        assertTrue(player.hasKey());
    }

    @Test
    public void elJugadorNoPuedeEntrarASalidaSinLlave() {
        player.setPos(new Celda(3, 4));
        assertFalse(player.isWinner());
        player.tryMove(Direccion.RIGHT);
        assertFalse(player.isWinner());
        assertEquals(new Celda(3, 4), player.getPos());
    }

    @Test
    public void elJugadorGanaAlEntrarALaSalida() {
        player.setPos(new Celda(3, 4));
        player.takeKey();
        assertFalse(player.isWinner());
        player.tryMove(Direccion.RIGHT);
        assertTrue(player.isWinner());
    }

    @Test
    public void elJugadorPuedeMoverObstaculos() {
        MovableRock r = (MovableRock) mapa.getEnviroments()[3][3];
        player.setPos(new Celda(3, 2));
        player.tryMove(Direccion.DOWN);
        assertEquals(new Celda(3, 4), r.getPos());
        assertEquals(new Celda(3, 3), player.getPos());
    }

    @Test
    public void noSePuedenMoverObstaculosSobreOtroObstaculo() {
        MovableRock r = (MovableRock) mapa.getEnviroments()[4][5];
        Wall wall = (Wall) mapa.getEnviroments()[5][5];
        player.setPos(new Celda(3, 5));
        player.tryMove(Direccion.RIGHT);
        assertEquals(new Celda(4, 5), r.getPos());
        assertEquals(new Celda(3, 5), player.getPos());
        assertEquals(new Celda(5, 5), wall.getPos());
    }

    @Test
    public void noSePuedeBloquearLaSalida() {
        MovableRock r = (MovableRock) mapa.getEnviroments()[4][5];
        player.setPos(new Celda(4, 6));
        player.tryMove(Direccion.UP);
        assertEquals(new Celda(4, 5), r.getPos());
        assertEquals(new Celda(4, 6), player.getPos());
    }

    @Test
    public void noSePuedeMoverSobreUnEnemigo() {
        player.setPos(new Celda(5, 2));
        player.tryMove(Direccion.UP);
        assertEquals(new Celda(5, 2), player.getPos());
    }

    @Test
    public void recibeDanioAlPisarTrampa() {
        player.setPos(new Celda(5, 2));
        player.tryMove(Direccion.UP);
        assertEquals(2, player.getVidas());
        assertTrue(player.isAlive());
    }

    @Test
    public void muereAlPisarPozo() {
        player.setPos(new Celda(6, 2));
        player.tryMove(Direccion.UP);
        assertEquals(0, player.getVidas());
        assertFalse(player.isAlive());
    }

    @Test
    public void noSePuedeMoverUnEnviromentSobreEnemigo() {
        MovableRock r = (MovableRock) mapa.getEnviroments()[6][4];
        player.setPos(new Celda(5, 4));
        player.tryMove(Direccion.RIGHT);
        assertEquals(new Celda(6, 4), r.getPos());
        assertEquals(new Celda(5, 4), player.getPos());
    }

}
