package character;

import Utils.Celda;
import lolo.Mapa;

public class Enemigo extends Character{
	public Enemigo(Celda pos, Mapa mapa, int vidas, String nombre) {
    	super(pos, mapa, vidas);
    	this.nombre = nombre;
    }
	
	String nombre;
	
	public abstract void atacar(Jugador player);
	
	public String getNombre() {
		return nombre;
	}
}
