public class Player extends Character {
    private boolean key = false;
    private boolean alive = true;

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
    }

    public void takeKey() {
        this.key = true;
    }

    public void takeDamage() {
        this.vidas--;
        if (this.vidas == 0) {
            this.alive = false;
        }
    }

    public void morir() {
        this.vidas = 0;
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean hasKey() {
        return this.key;
    }


}
