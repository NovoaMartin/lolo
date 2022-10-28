package character;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;
import utils.Constants;

public class Character extends Celda {

    private int vidas;
    private String nombre;
    protected boolean alive = true;
    
    public Character(int x, int y, int vidas, int from, int to, String nombre) {
    	super(x, y);
        this.vidas = vidas;
        this.nombre = nombre;
        
        if(nombre.equals("lolo")) {
        	image = new ImageView(new Image("file:src/main/resources/Lolo&Lala.png", 272*5, 192*5, false, false));
        } else {
        	image = new ImageView(new Image("file:src/main/resources/Enemies & Objects.png", 128*5, 592*5, false, false));
        }
        
        image.setViewport(new Rectangle2D(from*5, to*5, Constants.imageSize * 5, Constants.imageSize * 5));
        this.image.setX(y * Constants.imageSize * 5);
        this.image.setY(x * Constants.imageSize * 5);
    }

    public void recibirDanio(String enemigo) {
        this.vidas--;
        if (this.vidas <= 0) {
            morir(enemigo);
        }
    }

    public boolean canWin() {
        return false;
    }

    public boolean hasWinCondition(){
        return false;
    }

    public boolean win(){
        return false;
    }

    public void morir(String enemigo) {
        this.vidas = 0;
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getVidas() {
        return vidas;
    }


}
