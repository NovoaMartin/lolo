package character;

import java.util.Map;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;
import utils.Celda;
import utils.Constants;
import utils.Direction;

public class Player extends Character {

	private boolean winner = false;
    private boolean key = false;

    private TranslateTransition animacion;
    private Shape magicalShot;

    private static final Map<Integer, Image[]> sprites = Map.of(
            Direction.UP, new Image[]{
                    new Image("file:src/main/resources/character/up1.png"),
                    new Image("file:src/main/resources/character/up2.png"),
                    new Image("file:src/main/resources/character/up3.png"),
                    new Image("file:src/main/resources/character/up4.png"),
                    new Image("file:src/main/resources/character/up5.png"),
            },
            Direction.DOWN, new Image[]{
                    new Image("file:src/main/resources/character/down1.png"),
                    new Image("file:src/main/resources/character/down2.png"),
                    new Image("file:src/main/resources/character/down3.png"),
                    new Image("file:src/main/resources/character/down4.png"),
                    new Image("file:src/main/resources/character/down5.png"),
            },
            Direction.LEFT, new Image[]{
                    new Image("file:src/main/resources/character/left1.png"),
                    new Image("file:src/main/resources/character/left2.png"),
                    new Image("file:src/main/resources/character/left3.png"),
                    new Image("file:src/main/resources/character/left4.png"),
                    new Image("file:src/main/resources/character/left5.png"),
            },
            Direction.RIGHT, new Image[]{
                    new Image("file:src/main/resources/character/right1.png"),
                    new Image("file:src/main/resources/character/right2.png"),
                    new Image("file:src/main/resources/character/right3.png"),
                    new Image("file:src/main/resources/character/right4.png"),
                    new Image("file:src/main/resources/character/right5.png"),
            }
    );

    public Player(int x, int y, int vidas, int from, int to) {
    	super(x, y, vidas, from, to, "lolo");
        magicalShot = new Rectangle(0, 0, 5, 5);
        magicalShot.setFill(Color.BLUE);
        magicalShot.setVisible(false);
    }

    private boolean moving = false;

//    public void tryMove(int direccion) {
//        if (alive && !winner) {
//            this.orientacion = direccion;
//            image.setImage(sprites.get(direccion)[2]);
//            mapa.tryMove(this, direccion);
//        }
//    }

//    public void morir(String enemigo) {
//        super.morir(enemigo);
//        if (enemigo.equals("Medusa")) {
//            image.setEffect(new ColorAdjust(0, 0, -0.5, 0));
//        } else {
//            FadeTransition ft = new FadeTransition(Constants.DEAD_ANIMATION_DURATION, image);
//            ft.setFromValue(1.0);
//            ft.setToValue(0.0);
//            ft.play();
//        }
//        Transition t = new Transition() {
//            @Override
//            protected void interpolate(double frac) {
//            }
//            {
//                setCycleDuration(Constants.DEAD_ANIMATION_DURATION.multiply(3));
//            }
//        };
//        t.play();
//        t.setOnFinished(e -> mapa.lose(enemigo));
//    }

    @Override
    public void recibirDanio(String enemigo) {
        super.recibirDanio(enemigo);
        image.setEffect(new javafx.scene.effect.ColorAdjust(0, 0, -0.5, 0));
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            image.setEffect(null);
        }).start();
    }

    private boolean attacking = false;

    public void atacar(Mapa mapa) {
    	
        if (attacking) {
        	return;
        }
        attacking = true;
        
        Celda target = mapa.getAttackTarget(this, orientacion);
        int distance = distance(target);
        
        magicalShot.setTranslateY(y * 50 + 25);
        magicalShot.setTranslateX(x * 50 + 25);
        
        magicalShot.setVisible(true);
        
        TranslateTransition animation = new TranslateTransition(Constants.MAGIC_ANIMATION_DURATION.multiply(distance), magicalShot);
        
        if (orientacion == Direction.UP) {
            animation.setByY(-distance * 50);
        } else if (orientacion == Direction.DOWN) {
            animation.setByY(distance * 50);
        } else if (orientacion == Direction.LEFT) {
            animation.setByX(-distance * 50);
        } else if (orientacion == Direction.RIGHT) {
            animation.setByX(distance * 50);
        }
        
        animation.play();
        animation.setOnFinished(e -> {
            magicalShot.setVisible(false);
            target.interactWith(this);
            attacking = false;
        });
    }

    public void takeKey() {
        this.key = true;
    }

    @Override
    public boolean canWin() {
        return true;
    }

    @Override
    public boolean hasWinCondition() {
        return this.hasKey();
    }

    public boolean hasKey() {
        return this.key;
    }

    public boolean isMoving() {
    	return moving;
    }

    public boolean isWinner() {
        return this.winner;
    }


    @Override
    public Node getRender() {
        return new Group(image, magicalShot);
    }


    private int orientacion = Direction.DOWN;

    @Override
    public void setPos(Celda pos) {
        moving = true;
        animacion = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
        Transition imageRotation = new Transition() {
            {
                setCycleDuration(Constants.MOVEMENT_ANIMATION_DURATION);
            }

            int i = 0;

            @Override
            protected void interpolate(double frac) {
//                image.setImage(sprites.get(orientacion)[i++ % 5]);
            }
        };
        imageRotation.setOnFinished(e -> image.setImage(image.getImage()));
        animacion.setOnFinished(e -> {
            moving = false;
            this.x = pos.x;
            this.y = pos.y;
        });
        if (orientacion == Direction.UP) {
            animacion.setByY(-50);
            animacion.play();
            image.setTranslateY(2.5 + pos.y * 50);
        } else if (orientacion == Direction.DOWN) {
            animacion.setByY(Constants.imageSize * 5);
            animacion.play();
            this.x--;
            image.setY(x * Constants.imageSize * 5 );
        } else if (orientacion == Direction.LEFT) {
            animacion.setByX(-50);
            animacion.play();
            image.setTranslateX(2.5 + pos.x * 50);
        } else if (orientacion == Direction.RIGHT) {
            animacion.setByX(50);
            animacion.play();
            image.setTranslateX(2.5 + pos.x * 50);
        }
        imageRotation.play();
    }
}
