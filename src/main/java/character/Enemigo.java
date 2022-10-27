package character;

import lolo.Mapa;
import utils.Celda;

public abstract class Enemigo extends Character {

	private String nombre;
	
    public Enemigo(int x, int y, int vidas, String nombre) {
        super(x, y, vidas);
        this.nombre = nombre;
    }


    public abstract void atacar(Player player);

    public abstract void interactWith(Player p, int direccion, Mapa mapa);

    public boolean canInteractWith(Celda p) {
        return false;
    }

    public boolean canBeAttacked() {
        return false;
    }
}
