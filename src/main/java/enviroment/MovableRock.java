package enviroment;

import Utils.Celda;
import character.Character;
import lolo.Mapa;

public class MovableRock extends Enviroment {

    public MovableRock(Celda pos, Mapa mapa) {
        super(pos, mapa);
    }

    protected boolean tryMove(int direccion) {
        return this.mapa.tryMove(this, direccion);
    }

    @Override
    public void interactWith(Character character, int direccion) {
        boolean moved = this.tryMove(direccion);
        if (moved) {
            character.tryMove(direccion);
        }
    }

}
