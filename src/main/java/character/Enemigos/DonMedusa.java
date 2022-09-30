package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Jugador;
import lolo.Mapa;

public class DonMedusa extends Enemigo {
	public DonMedusa(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    	nombre = "DonMedusa";
    }
	
	String nombre;
	
	@Override 
	public void atacar(Jugador player) {
		player.recibirDanio();
		System.out.println("Atacando como DonMedusa...");
	}
}
