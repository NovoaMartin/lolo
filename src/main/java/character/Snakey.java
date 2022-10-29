package character;

public class Snakey extends Character {

    public Snakey(int x, int y, int from, int to) {
        super(x, y, 0, from, to, "Medusa");
    }

    public void atacar(Player p) {
        p.morir("Medusa");

    }

//    @Override
//    public void morir() {
//        super.morir();
//        image.setVisible(false);
//    }

    @Override
    public void interactWith(Player p) {
        if (isAlive()) 
        	atacar(p);
    }
    
	@Override
	public boolean canInteract() {
		return true;
	}

}
