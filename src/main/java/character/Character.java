package character;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;
import utils.Constants;

public class Character extends Celda {

    private int vidas;
    
    public Character(int i, int j, int vidas, int from, int to, String nombre) {
    	super(i, j);
        this.vidas = vidas;
        
        if(nombre.equals("lolo")) {
        	image = new ImageView(new Image("file:src/main/resources/Lolo & Lala.png", 272*Constants.MULTIPLIER, 192*Constants.MULTIPLIER, false, false));
        } else {
        	image = new ImageView(new Image("file:src/main/resources/Enemies&Objects.png", 128*Constants.MULTIPLIER, 592*Constants.MULTIPLIER, false, false));
        }
        
        image.setViewport(new Rectangle2D(from*Constants.MULTIPLIER, to*Constants.MULTIPLIER, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE));
        this.image.setX(j * Constants.IMAGE_SIZE);
        this.image.setY(i * Constants.IMAGE_SIZE);
    }
    
	public void morir(String enemigo) {
		this.vidas = 0;
		FadeTransition ft = new FadeTransition(Constants.DEAD_ANIMATION_DURATION, image);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();

		Transition t = new Transition() {
			@Override
			protected void interpolate(double frac) {
			}

			{
				setCycleDuration(Constants.DEAD_ANIMATION_DURATION.multiply(3));
			}
		};
		t.play();
	}

    public void recibirDanio(String enemigo) {
        this.vidas--;
        if (this.vidas <= 0) {
            morir(enemigo);
        }
    }

    public boolean isAlive() {
        return vidas > 0;
    }

    public int getVidas() {
        return vidas;
    }


}
