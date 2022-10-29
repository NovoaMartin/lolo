package character;

import Utils.Celda;
import graphics.Updatable;
import lolo.Mapa;

public abstract class Enemigo extends Character implements Updatable {
    public Enemigo(Celda pos, Mapa mapa, int vidas, String nombre) {
        super(pos, mapa, vidas);
        this.nombre = nombre;
    }

    String nombre;

    @Override
    public void tryMove(int direccion) {
        mapa.tryMove(this, direccion);
    }

    public abstract void atacar(Player player);

    public abstract void interactWith(Player p, int direccion, Mapa mapa);

    public boolean canInteractWith(Celda p) {
        return false;
    }

    public boolean canBeAttacked() {
        return false;
    }

    public void awaken() {

    }

    public void update() {
    }
}
