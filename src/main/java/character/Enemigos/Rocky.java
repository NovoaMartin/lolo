package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Rocky extends Enemigo{
	public Rocky(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    	nombre = "Rocky";
    }
	
	String nombre;
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como Rocky...");
	}
}
