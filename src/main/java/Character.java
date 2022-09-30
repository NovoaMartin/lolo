public abstract class Character {
    private Celda pos;
    private final Mapa mapa;
    int vidas;

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
}
