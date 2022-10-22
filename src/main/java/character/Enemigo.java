package character;

import Utils.Celda;
import lolo.Mapa;

public abstract class Enemigo extends Character {
    public Enemigo(Celda pos, Mapa mapa, int vidas, String nombre) {
        super(pos, mapa, vidas);
        this.nombre = nombre;
    }

    String nombre;

    public abstract void atacar(Player player);

    public abstract void interactWith(Player p, int direccion, Mapa mapa);

    public boolean canInteractWith(Celda p) {
        return false;
    }

    public boolean canBeAttacked() {
        return false;
    }
}
