package character.Enemigos;

import Utils.Celda;
import character.Enemigo;
import character.Player;
import lolo.Mapa;

public class Trampa extends Enemigo {
    public Trampa(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas,"Trampa");
    }

    @Override
    public void atacar(Player player) {
        player.recibirDanio();
        System.out.println("Te has caido en una trampa");
    }
}
