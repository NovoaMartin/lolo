package environment;

import Utils.Celda;
import character.Character;
import graphics.Renderable;
import lolo.Mapa;

public abstract class Enviroment implements Renderable {
    protected Celda pos;

    public Enviroment(Celda pos) {
        this.pos = pos;
    }

    public abstract void interactWith(Character character, int direccion, Mapa mapa);

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
    }

    public boolean canMove(int direccion, Mapa mapa) {
        return false;
    }
}
