package items;

import Utils.Celda;
import character.Character;
import character.Jugador;
import lolo.Mapa;

public class Llave extends Item{

	public Llave(Celda pos) {
		super(pos);
	}
	@Override
	public void interactWith(Character character, int direccion, Mapa mapa) {
		if(character.getClass().getName() == "character.Jugador") {
			((Jugador)character).agarrarLlave();
			mapa.removeElementPos(this.getPos(), this.getClass().getName());
			character.tryMove(direccion);
		}
	}

}
