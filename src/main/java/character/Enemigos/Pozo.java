package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Player;
import lolo.Mapa;

public class Pozo extends Enemigo {
    public Pozo(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas,"Pozo");
    }

    @Override
    public void atacar(Player player) {
        player.morir();
        System.out.println("Te has caido en un pozo");
    }
}