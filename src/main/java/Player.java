public class Player extends Character {
    private boolean winner = false;
    private boolean key = false;

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
    }

    public void tryMove(int direccion) {
        if (alive && !winner)
            super.tryMove(direccion);
    }

    public void takeKey() {
        this.key = true;
    }

    public boolean hasKey() {
        return this.key;
    }

    public void setWinner() {
        this.winner = true;
    }

    public boolean isWinner() {
        return this.winner;
    }
}
