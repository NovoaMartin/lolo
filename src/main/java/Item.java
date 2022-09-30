public abstract class Item {
    protected Celda pos;
    protected Mapa mapa;
    protected boolean valid = true;

    public Item(Celda pos, Mapa mapa) {
        this.pos = pos;
        this.mapa = mapa;
    }


    public abstract void interactWith(Character character, int direccion, Mapa mapa);

    public boolean isValid() {
        return valid;
    }
}
