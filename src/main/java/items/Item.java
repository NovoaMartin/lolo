package items;

import graphics.Renderable;
import utils.Celda;

public abstract class Item extends Celda implements Renderable {

    protected boolean valid = true;

    public Item(int x, int y) {
        super(x, y);
    }

    public boolean isValid() {
        return valid;
    }
}
