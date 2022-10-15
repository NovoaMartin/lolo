package character;

import Utils.Celda;
import Utils.Direccion;
import graphics.Renderable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lolo.Mapa;

public class Player extends Character implements Renderable {
    private boolean winner = false;
    private boolean key = false;

    public Player(Celda pos, Mapa mapa, int vidas) {
        super(pos, mapa, vidas);
        shape = new Rectangle(45, 45);
        shape.setTranslateY(2.5 + pos.y * 50);
        shape.setTranslateX(2.5 + pos.x * 50);
        shape.setFill(Color.GRAY);
    }

    public void tryMove(int direccion) {
        this.orientacion = direccion;
        if (alive && !winner)
            super.tryMove(direccion);
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

    Shape shape;

    @Override
    public Node getRender() {
        return shape;
    }

    public void setEventListeners(Node node) {
        node.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W -> {
                    System.out.println("W");
                    tryMove(Direccion.UP);
                }
                case D -> tryMove(Direccion.RIGHT);
                case S -> tryMove(Direccion.DOWN);
                case A -> tryMove(Direccion.LEFT);
            }
        });
    }

    private int orientacion = Direccion.DOWN;

    @Override
    public void setPos(Celda pos) {
        if (orientacion == Direccion.UP) {
            shape.setTranslateY(2.5 + pos.y * 50);
        } else if (orientacion == Direccion.DOWN) {
            shape.setTranslateY(2.5 + pos.y * 50);
        } else if (orientacion == Direccion.LEFT) {
            shape.setTranslateX(2.5 + pos.x * 50);
        } else if (orientacion == Direccion.RIGHT) {
            shape.setTranslateX(2.5 + pos.x * 50);
        }
        this.pos = pos;
    }
}
