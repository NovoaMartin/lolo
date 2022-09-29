public abstract class Character {
    private Celda pos;
    private Mapa mapa;
    int vidas;

    public Character(Celda pos, Mapa mapa) {
        this.pos = pos;
        this.mapa = mapa;
        this.vidas = 3;
    }

    public void tryMove(int direccion){
        this.mapa.tryMove(this, direccion);
    }

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
    }
}
