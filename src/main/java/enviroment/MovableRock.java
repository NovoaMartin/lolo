package enviroment;

import Utils.Celda;
import character.Character;
import lolo.Mapa;

public class MovableRock extends Enviroment {

    public MovableRock(Celda pos) {
        super(pos);
    }
    
    protected boolean tryMove(int direccion, Mapa mapa) {
        return mapa.tryMove(this, direccion);
    }
    
    @Override
    public void interactWith(Character character, int direccion) {
        boolean moved = this.tryMove(direccion);
        if (moved) {
            character.tryMove(direccion);
        }
    }
}
