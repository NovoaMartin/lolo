package character;

import Utils.Celda;
import lolo.Mapa;

public class Character {
	
    private Celda pos;
    private Mapa mapa;
    private int vidas;
    
    public Character(Celda pos, Mapa mapa, int vidas) {
    	this.pos = pos;
    	this.mapa = mapa;
    	this.vidas = vidas;
    }
    
    public void tryMove(int direccion){
        this.mapa.tryMove(this, direccion);
    }

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
    }
    
    public int getVidas() {
		return vidas;
	}
    
    public void recibirDanio() {
    	this.vidas--;
    	if(this.vidas < 0) {
    		morir();
    	}
    }
    
    public void morir() {
    	System.out.println("He muerto");
    }
}
