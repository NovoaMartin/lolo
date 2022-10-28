package character;

import utils.Celda;

public class Muse extends Character {

    public Muse(int x, int y, int from, int to) {
        super(x, y, 0, from, to, "Medusa");
    }

//    @Override
//    public void atacar(Player player) {
//        player.morir("Medusa");
//
//    }

    @Override
    public void morir(String enemigo) {
        super.morir(enemigo);
        image.setVisible(false);
    }

//    @Override
//    public void interactWith(Player p, int direccion, Mapa mapa) {
//        if (alive) atacar(p);
//    }

    public boolean canInteractWith(Celda p) {
        return alive && distance(p) <= 2;
    }

//    @Override
//    public boolean canBeAttacked() {
//        return true;
//    }
}
