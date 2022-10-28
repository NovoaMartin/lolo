package character;

import Utils.Celda;
import Utils.Constants;
import Utils.Direccion;
import graphics.SpriteMovementTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;

import java.util.Map;

public class Player extends Character {
    private boolean winner = false;
    private boolean key = false;

    private final ImageView image;
    Shape magicalShot;

    private static final Map<Integer, Image[]> sprites = Map.of(
            Direccion.UP, new Image[]{
                    new Image("file:src/main/resources/character/up1.png"),
                    new Image("file:src/main/resources/character/up2.png"),
                    new Image("file:src/main/resources/character/up3.png"),
                    new Image("file:src/main/resources/character/up4.png"),
                    new Image("file:src/main/resources/character/up5.png"),
            },
            Direccion.DOWN, new Image[]{
                    new Image("file:src/main/resources/character/down1.png"),
                    new Image("file:src/main/resources/character/down2.png"),
                    new Image("file:src/main/resources/character/down3.png"),
                    new Image("file:src/main/resources/character/down4.png"),
                    new Image("file:src/main/resources/character/down5.png"),
            },
            Direccion.LEFT, new Image[]{
                    new Image("file:src/main/resources/character/left1.png"),
                    new Image("file:src/main/resources/character/left2.png"),
                    new Image("file:src/main/resources/character/left3.png"),
                    new Image("file:src/main/resources/character/left4.png"),
                    new Image("file:src/main/resources/character/left5.png"),
            },
            Direccion.RIGHT, new Image[]{
                    new Image("file:src/main/resources/character/right1.png"),
                    new Image("file:src/main/resources/character/right2.png"),
                    new Image("file:src/main/resources/character/right3.png"),
                    new Image("file:src/main/resources/character/right4.png"),
                    new Image("file:src/main/resources/character/right5.png"),
            }
    );

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
        image = new ImageView("file:src/main/resources/lolo.png");
        image.setFitWidth(45);
        image.setFitHeight(45);
        image.setTranslateX(pos.x * 50 + 2.5);
        image.setTranslateY(pos.y * 50 + 2.5);
        magicalShot = new Rectangle(0, 0, 5, 5);
        magicalShot.setFill(Color.BLUE);
        magicalShot.setVisible(false);
    }

    private boolean moving = false;

    public void tryMove(int direccion) {
        if (alive && !winner) {
            this.orientacion = direccion;
            image.setImage(sprites.get(direccion)[2]);
            mapa.tryMove(this, direccion);
        }
    }

    public void morir(String enemigo) {
        super.morir(enemigo);
        if (enemigo.equals("Medusa")) {
            image.setEffect(new ColorAdjust(0, 0, -0.9, 0));
        } else {
            FadeTransition ft = new FadeTransition(Constants.DEAD_ANIMATION_DURATION, image);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.play();
        }
        Transition t = new Transition() {
            @Override
            protected void interpolate(double frac) {
            }

            {
                setCycleDuration(Constants.DEAD_ANIMATION_DURATION.multiply(3));
            }
        };
        t.play();
        t.setOnFinished(e -> mapa.lose(enemigo));
    }

    @Override
    public void recibirDanio(String enemigo) {
        if (!alive) return;
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

    public void atacar() {
        if (attacking) return;
        attacking = true;
        Enemigo enemigo = this.mapa.getAttackTarget(this, orientacion);
        Celda target = enemigo.getPos();
        int distance = target.distance(this.pos);
        magicalShot.setTranslateY(this.pos.y * 50 + 25);
        magicalShot.setTranslateX(this.pos.x * 50 + 25);
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
        node.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W: {
                    if (!moving) {
                        tryMove(Direccion.UP);
                        break;
                    }
                }
                case S: {
                    if (!moving) {
                        tryMove(Direccion.DOWN);
                        break;
                    }
                }
                case A: {
                    if (!moving) {
                        tryMove(Direccion.LEFT);
                        break;
                    }
                }
                case D: {
                    if (!moving) {
                        tryMove(Direccion.RIGHT);
                        break;
                    }
                }
                case SPACE: {
                    if (!moving) {
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
        SpriteMovementTransition animacion = new SpriteMovementTransition(image, Constants.MOVEMENT_ANIMATION_DURATION, sprites.get(orientacion));

        super.setPos(pos);
        animacion.setOnFinished(e -> {
            moving = false;
            image.setImage(sprites.get(orientacion)[2]);
        });
        if (orientacion == Direccion.UP) {
            animacion.setByY(-50);
        } else if (orientacion == Direccion.DOWN) {
            animacion.setByY(50);
        } else if (orientacion == Direccion.LEFT) {
            animacion.setByX(-50);
        } else if (orientacion == Direccion.RIGHT) {
            animacion.setByX(50);
        }
        animacion.play();
    }
}
