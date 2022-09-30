package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Gol extends Enemigo {
	public Gol(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    	nombre = "Gol";
    }
	
	String nombre;
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como Gol...");
	}
}
