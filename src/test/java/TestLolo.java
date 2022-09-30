import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Utils.Celda;
import Utils.Direccion;
import character.Jugador;
import enviroment.Enviroment;
import enviroment.MovableRock;
import items.Cofre;
import items.Corazon;
import items.Item;
import lolo.Mapa;

public class TestLolo {

	Mapa mapa;
	Jugador jugador;
	Item item;
	Enviroment enviroment;
	
	@Before
	public void init() {
		mapa = new Mapa(10,10, new Cofre(new Celda(3,3)), new Celda(1,1));
		jugador = new Jugador(new Celda(10/2,10/2), mapa, 3);
		item = new Corazon(new Celda(4,5));
		enviroment = new MovableRock(new Celda(6,5), mapa);
		mapa.addCharacter(jugador);
		mapa.addItem(item);
		mapa.addEnviroment(enviroment);
	}
	
	@Test
	public void MovePlayerTest() {
		jugador.tryMove(Direccion.UP);
		assertEquals(4, jugador.getPos().y);
		jugador.tryMove(Direccion.DOWN);
		assertEquals(5, jugador.getPos().y);
		jugador.tryMove(Direccion.LEFT);
		assertEquals(4, jugador.getPos().x);
		jugador.tryMove(Direccion.RIGHT);
		assertEquals(5, jugador.getPos().y);
	}
	
	@Test
	public void MovePlayerAndRockTest() {
		jugador.tryMove(Direccion.RIGHT);
		assertEquals(6, jugador.getPos().x);
		assertEquals(7, enviroment.getPos().x);
	}
	
	@Test
	public void GetCorazonTest() {
		jugador.tryMove(Direccion.LEFT);
		assertEquals(4, jugador.getPos().x);
		assertEquals(0, mapa.corazonesRestantes());
		assertTrue(mapa.cofreAbierto());
	}
	
	@Test
	public void OpenDoorTest() {
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.UP);
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.UP);
		assertTrue(mapa.cofreAbierto());
		assertTrue(mapa.puertaAbierta());
	}
	
	@Test
	public void ganarTest() {
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.UP);
		jugador.tryMove(Direccion.UP);
		assertTrue(mapa.cofreAbierto());
		assertTrue(mapa.puertaAbierta());
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.LEFT);
		jugador.tryMove(Direccion.UP);
		jugador.tryMove(Direccion.UP);
		jugador.tryMove(Direccion.UP);
		assertTrue(jugador.gano());
	}
	
}
