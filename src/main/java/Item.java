public abstract class Item {
    private boolean valid = true;

    public abstract void interactWith(Character character, int direccion, Mapa mapa);

    public boolean isValid() {
        return valid;
    }
}
