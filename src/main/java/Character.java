public abstract class Character {
    private Celda pos;
    private final Mapa mapa;
    int vidas;
    protected boolean alive = true;

    public Character(Celda pos, Mapa mapa, int vidas) {
        this.pos = pos;
        this.mapa = mapa;
        this.vidas = vidas;
    }

    public void tryMove(int direccion) {
        this.mapa.tryMove(this, direccion);
    }

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
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
}
