package character;

import Utils.Celda;
import lolo.Mapa;

public class Jugador extends Character{

	private boolean tieneLlave;
	private boolean ganador = false;
	
    public Jugador(Celda pos, Mapa mapa, int vidas) {
    	super(pos, mapa, vidas);
    }
    
    public boolean tieneLlave() {
    	return tieneLlave;
    }
    
    public void agarrarLlave() {
    	tieneLlave = true;
    }
    
    public void ganar() {
    	ganador = true;
    }
    
    public boolean gano() {
    	return ganador;
    }
  
}
