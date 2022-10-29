package items;

import character.Player;

public class Key extends Item{

    public Key(int x, int y, int from, int to) {
        super(x, y, from, to);
    }

    @Override
    public void interactWith(Player p) {
    	p.takeKey();
    	valid = false;
    }
    
    @Override
    public boolean canInteract() {
    	return true;
    }

}

