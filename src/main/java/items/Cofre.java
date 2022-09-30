package items;

import Utils.Celda;
import character.Character;
import character.Jugador;
import lolo.Mapa;

public class Cofre extends Item {

	private boolean cofreAbierto = false;
	public Cofre(Celda pos) {
		super(pos);
	}

	@Override
	public void interactWith(Character character, int direccion, Mapa mapa) {
		((Jugador)character).agarrarLlave();
		mapa.abrirPuerta();
	}
	
	public void abrirCofre() {
		cofreAbierto = true;
	}
	
	public boolean cofreAbierto() {
		return cofreAbierto;
	}

}
