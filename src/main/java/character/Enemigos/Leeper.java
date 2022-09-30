package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Leeper extends Enemigo{
	public Leeper(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    	nombre = "Leeper";
    }
	
	String nombre;
	
	@Override 
	public void atacar(Jugador player) {
		System.out.println("Atacando como Leeper...");
	}
}
