package character;

import Utils.Celda;
import Utils.Constants;
import Utils.Direccion;
import graphics.Renderable;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;

public class Player extends Character implements Renderable {
    private boolean winner = false;
    private boolean key = false;

    private final ImageView image;

    TranslateTransition animacion;

    Shape magicalShot;

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
        image = new ImageView("file:src/main/resources/lolo.png");
        image.setFitWidth(45);
        image.setFitHeight(45);
        image.setTranslateX(pos.x * 50 + 2.5);
        image.setTranslateY(pos.y * 50 + 2.5);
        magicalShot = new Rectangle(0, 0, 5, 5);
        magicalShot.setVisible(false);
    }

    private boolean moving = false;

    public void tryMove(int direccion) {
        if (alive && !winner) {
            this.orientacion = direccion;
            image.setRotate(Direccion.getRotation(direccion));
            super.tryMove(direccion);
        }
    }

    public void morir(String enemigo) {
        super.morir(enemigo);
        if (enemigo.equals("Medusa")) {
            image.setEffect(new javafx.scene.effect.ColorAdjust(0, 0, -0.5, 0));
        } else {
            FadeTransition ft = new FadeTransition(Constants.DEAD_ANIMATION_DURATION, image);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
        }
    }

    private boolean attacking = false;

    public void atacar() {
        if(attacking)return;
        attacking = true;
        Enemigo enemigo = this.mapa.getAttackTarget(this, orientacion);
        Celda target = enemigo.getPos();
        int distance = target.distance(this.pos);
        magicalShot.setTranslateY(this.pos.y * 50 + 2.5);
        magicalShot.setTranslateX(this.pos.x * 50 + 2.5);
        magicalShot.setVisible(true);
        TranslateTransition animation = new TranslateTransition(Constants.MAGIC_ANIMATION_DURATION.multiply(distance), magicalShot);
        if (orientacion == Direccion.UP) {
            animation.setByY(-distance * 50);
        } else if (orientacion == Direccion.DOWN) {
            animation.setByY(distance * 50);
        } else if (orientacion == Direccion.LEFT) {
            animation.setByX(-distance * 50);
        } else if (orientacion == Direccion.RIGHT) {
            animation.setByX(distance * 50);
        }
        animation.play();
        animation.setOnFinished(e -> {
            magicalShot.setVisible(false);
            enemigo.morir("Lolo");
            attacking = false;
        });
    }

    public void takeKey() {
        this.key = true;
    }

    public boolean hasKey() {
        return this.key;
    }

    public boolean win() {
        this.winner = true;
        this.mapa.win();
        return true;
    }

    @Override
    public boolean canWin() {
        return true;
    }

    @Override
    public boolean hasWinCondition() {
        return this.hasKey();
    }

    public boolean isWinner() {
        return this.winner;
    }


    @Override
    public Node getRender() {
        return new Group(image, magicalShot);
    }

    public void setEventListeners(Node node) {
    	node.setOnKeyPressed(e->{
    		switch(e.getCode()) {
    		case W: {
    			if(!moving) {
    				tryMove(Direccion.UP);
    				break;
    			}
    		}
    		case S:{
    			if(!moving) {
    				tryMove(Direccion.DOWN);
    				break;
    			}
    		}
    		case A:{
    			if(!moving) {
    				tryMove(Direccion.LEFT);
    				break;
    			}
    		}
    		case D:{
    			if(!moving) {
    				tryMove(Direccion.RIGHT);
    				break;
    			}
    		}
    		case SPACE:{
    			if(!moving) {
    				atacar();
    				break;
    			}
    		}
			default:
				break;
    		}
    	});
    }

    private int orientacion = Direccion.DOWN;

    @Override
    public void setPos(Celda pos) {
        moving = true;
        animacion = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        animacion.setOnFinished(e -> {
            moving = false;
            this.pos = pos;
        });
        if (orientacion == Direccion.UP) {
            animacion.setByY(-50);
            animacion.play();
            image.setTranslateY(2.5 + pos.y * 50);
        } else if (orientacion == Direccion.DOWN) {
            animacion.setByY(50);
            animacion.play();
            image.setTranslateY(2.5 + pos.y * 50);
        } else if (orientacion == Direccion.LEFT) {
            animacion.setByX(-50);
            animacion.play();
            image.setTranslateX(2.5 + pos.x * 50);
        } else if (orientacion == Direccion.RIGHT) {
            animacion.setByX(50);
            animacion.play();
            image.setTranslateX(2.5 + pos.x * 50);
        }
    }
}
