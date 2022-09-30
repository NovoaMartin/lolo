package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class Snakey extends Enemigo {
	public Snakey(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas,"Snakey");
    }
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como Snakey...");
	}
}
