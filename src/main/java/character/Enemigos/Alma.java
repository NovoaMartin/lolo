package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Alma extends Enemigo {
	public Alma(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas,"Alma");
    	
    }
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como Alma...");
	}
}