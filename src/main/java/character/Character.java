package character;

import graphics.Renderable;
import utils.Celda;

public abstract class Character extends Celda implements Renderable {

    private int vidas;
    protected boolean alive = true;

    public Character(int x, int y, int vidas) {
    	super(x, y);
        this.vidas = vidas;
    }

    public void recibirDanio(String enemigo) {
        this.vidas--;
        if (this.vidas <= 0) {
            morir(enemigo);
        }
    }

    public boolean canWin() {
        return false;
    }

    public boolean hasWinCondition(){
        return false;
    }

    public boolean win(){
        return false;
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
