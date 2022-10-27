package enviroment;

import graphics.Renderable;
import utils.Celda;

public abstract class Enviroment extends Celda implements Renderable {

	public Enviroment(int x, int y) {
		super(x, y);
	}

}
