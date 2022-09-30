package character;

import Utils.Celda;
import lolo.Mapa;

public class Enemigo extends Character{
	public Enemigo(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    }
	
	public void atacar(Jugador player) {
		player.recibirDanio();
	}
}
