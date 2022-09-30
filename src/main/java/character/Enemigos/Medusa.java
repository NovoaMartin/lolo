package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Medusa extends Enemigo{
	public Medusa(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    	nombre = "Medusa";
    }
	
	String nombre;
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como Medusa...");
	}
}
