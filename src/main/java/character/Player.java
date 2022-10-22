package character;

import Utils.Celda;
import Utils.Constants;
import Utils.Direccion;
import graphics.Renderable;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lolo.Mapa;

public class Player extends Character implements Renderable {
    private boolean winner = false;
    private boolean key = false;

    private final ImageView image;

    TranslateTransition animacion;

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
        image = new ImageView("file:src/main/resources/lolo.png");
        image.setFitWidth(45);
        image.setFitHeight(45);
        image.setTranslateX(pos.x * 50 + 2.5);
        image.setTranslateY(pos.y * 50 + 2.5);
    }

    private boolean moving = false;

    public void tryMove(int direccion) {
        this.orientacion = direccion;
        image.setRotate(Direccion.getRotation(direccion));
        if (alive && !winner)
            super.tryMove(direccion);
    }

    public void morir() {
        super.morir();
        FadeTransition ft = new FadeTransition(Constants.DEAD_ANIMATION_DURATION, image);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
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


    @Override
    public Node getRender() {
        return image;
    }

    public void setEventListeners(Node node) {
        node.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> {
                    if (!moving) {
                        tryMove(Direccion.UP);
                    }
                }
                case S -> {
                    if (!moving) {
                        tryMove(Direccion.DOWN);
                    }
                }
                case A -> {
                    if (!moving) {
                        tryMove(Direccion.LEFT);
                    }
                }
                case D -> {
                    if (!moving) {
                        tryMove(Direccion.RIGHT);
                    }
                }
            }
        });
    }

    private int orientacion = Direccion.DOWN;

    @Override
    public void setPos(Celda pos) {
        moving=true;
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
