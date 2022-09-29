public abstract class Enviroment {
    protected Celda pos;
    protected Mapa mapa;

    public Enviroment(Celda pos, Mapa mapa) {
        this.pos = pos;
        this.mapa = mapa;
    }


    public abstract void interactWith(Character character, int direccion);

    public void setPos(Celda pos) {
        this.pos = pos;
    }

    public Celda getPos() {
        return pos;
    }
}
