public class Wall extends Enviroment {
    public Wall(Celda pos, Mapa mapa) {
        super(pos, mapa);
    }

    @Override
    public void interactWith(Character character, int direccion) {
        // Nothing
    }
}
