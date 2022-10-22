package character;

import Utils.*;
import graphics.Renderable;
import lolo.Mapa;

public abstract class Character implements Renderable {
    protected Celda pos;
    private final Mapa mapa;
    private int vidas;
    protected boolean alive = true;

    public Character(Celda pos, Mapa mapa, int vidas) {
        this.pos = pos;
        this.mapa = mapa;
        this.vidas = vidas;
    }

    public void tryMove(int direccion) {
        this.mapa.tryMove(this, direccion);
    }

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
    }

    public void recibirDanio(String enemigo) {
        this.vidas--;
        if (this.vidas <= 0) {
            morir(enemigo);
        }
    }

    public void morir(String enemigo) {
        this.vidas = 0;
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getVidas() {
        return vidas;
    }


}
