package lolo;


import Utils.Celda;
import Utils.Direccion;
import Utils.Pantalla;
import character.Character;
import character.Enemigo;
import character.Enemigos.Trampa;
import character.Player;
import enviroment.Enviroment;
import enviroment.Exit;
import enviroment.UnmovableEnvironment;
import graphics.Renderable;
import graphics.Updatable;
import items.Item;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Optional;

public class Mapa implements Renderable, Updatable {
    private final int width;
    private final int height;

    private final ArrayList<Player> players;
    private final Item[][] items;
    private final Enviroment[][] enviroments;

    private final ArrayList<Enemigo> enemigos;

    private Exit exit;

    private final String mapFile;
    private String nextMap;
    Pantalla pantalla;

    public Mapa(String mapName, Pantalla pantalla, int width, int height) {
        this.mapFile = mapName;
        this.pantalla = pantalla;
        this.enemigos = new ArrayList<Enemigo>();
        this.players = new ArrayList<Player>();
        this.width = width;
        this.height = height;
        this.items = new Item[width][height];
        this.enviroments = new Enviroment[width][height];
    }


    public void tryMove(Character character, int direccion) {
        Celda target = character.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height) {
            return;
        }

        if (character instanceof Player) {
            Player p = (Player) character;
            for (Enemigo e : enemigos) {
                if (e.isAlive() && e.getPos().equals(target) || e.canInteractWith(target)) {
                    e.interactWith(p, direccion, this);
                    return;
                }
            }
        } else {
            Enemigo e = (Enemigo) character;
            for (Player p : players) {
                if (p.getPos().equals(target)) {
                    e.interactWith(p, direccion, this);
                    return;
                }
            }
        }

        if (items[target.x][target.y] != null) {
            items[target.x][target.y].interactWith(character, direccion, this);
            if (!items[target.x][target.y].isValid()) {
                items[target.x][target.y] = null;
            }
        } else if (enviroments[target.x][target.y] != null) {
            enviroments[target.x][target.y].interactWith(character, direccion, this);
        } else {
            character.setPos(target);
        }
    }

    public boolean canMove(Celda target, int direccion) {
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height) {
            return false;
        }
        for (Enemigo e : enemigos) {
            if (e.getPos().equals(target)) {
                return false;
            }
        }
        if (items[target.x][target.y] != null) {
            return false;
        } else if (enviroments[target.x][target.y] != null) {
            return enviroments[target.x][target.y].canMove(direccion, this);
        }
        return true;
    }

    public boolean tryMove(Enviroment enviroment, int direccion) {
        Celda from = enviroment.getPos();
        Celda target = enviroment.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height) {
            return false;
        }
        Optional<Enemigo> enemigo = enemigos.stream().filter(e -> e.getPos().equals(target)).findFirst();
        if (enemigo.isPresent() && enemigo.get().isAlive()) return false;
        if (getPlayer().getPos().equals(target)) {
            if (canMove(target.translate(direccion), direccion)) {
                getPlayer().tryMove(direccion);
                enviroment.setPos(target);
                enviroments[from.x][from.y] = null;
                enviroments[target.x][target.y] = enviroment;
                return true;
            } else {
                getPlayer().recibirDanio("Aplastado");
                return false;
            }
        }

        if (items[target.x][target.y] == null && enviroments[target.x][target.y] == null) {
            enviroment.setPos(target);
            enviroments[from.x][from.y] = null;
            enviroments[target.x][target.y] = enviroment;
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return players.get(0);
    }

    public Enviroment[][] getEnviroments() {
        return enviroments;
    }

    public void addItem(Item item) {
        items[item.getPos().x][item.getPos().y] = item;
    }

    public void addEnvironment(Enviroment enviroment) {
        this.enviroments[enviroment.getPos().x][enviroment.getPos().y] = enviroment;
    }

    public void addEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
    }

    public void addWalls() {
        for (int i = 0; i < this.width; i++) {
            this.enviroments[i][0] = new UnmovableEnvironment(new Celda(i, 0), Direccion.DOWN);
            this.enviroments[i][this.height - 1] = new UnmovableEnvironment(new Celda(i, this.height - 1), Direccion.UP);
        }
        for (int i = 0; i < this.height; i++) {
            this.enviroments[0][i] = new UnmovableEnvironment(new Celda(0, i), Direccion.RIGHT);
            this.enviroments[this.width - 1][i] = new UnmovableEnvironment(new Celda(this.width - 1, i), Direccion.LEFT);
        }
    }

    BorderPane root = new BorderPane();

    public Node getRender() {
        for (Item[] items : this.items) {
            for (Item item : items) {
                if (item != null) {
                    root.getChildren().add(item.getRender());
                }
            }
        }
        for (Enviroment[] enviroments : this.enviroments) {
            for (Enviroment enviroment : enviroments) {
                if (enviroment != null) {
                    root.getChildren().add(enviroment.getRender());
                }
            }
        }
        for (Enemigo enemigo : this.enemigos) {
            root.getChildren().add(enemigo.getRender());
        }
        root.getChildren().add(players.get(0).getRender());
        root.setPrefHeight(height * 50);
        root.setPrefWidth(width * 50);

        BackgroundImage floor = new BackgroundImage(new Image("file:src/main/resources/floor.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        root.setBackground(new Background(floor));
        return root;
    }

    public Exit getExit() {
        return exit;
    }

    public void setEventListeners(Node node) {
        players.get(0).setEventListeners(node);
        //Exit on ESC
        node.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            } else if (event.getCode() == KeyCode.R) {
                pantalla.createView(this.mapFile);
            }
        });
    }

    public Enemigo getAttackTarget(Player player, int orientacion) {
        Celda from = player.getPos();
        if (orientacion == Direccion.UP) {
            Enemigo min = new Trampa(new Celda(from.x, -1), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().y < from.y && e.getPos().x == from.x && e.getPos().y > min.getPos().y && e.canBeAttacked() && e.isAlive()) {
                    min = e;
                }
            }
            return min;
        } else if (orientacion == Direccion.DOWN) {
            Enemigo min = new Trampa(new Celda(from.x, height), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().y > from.y && e.getPos().x == from.x && e.getPos().y < min.getPos().y && e.canBeAttacked() && e.isAlive()) {
                    min = e;
                }
            }
            return min;
        } else if (orientacion == Direccion.LEFT) {
            Enemigo min = new Trampa(new Celda(-1, from.y), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().x < from.x && e.getPos().y == from.y && e.getPos().x > min.getPos().x && e.canBeAttacked() && e.isAlive()) {
                    min = e;
                }
            }
            return min;
        } else {
            Enemigo min = new Trampa(new Celda(width, from.y), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().x > from.x && e.getPos().y == from.y && e.getPos().x < min.getPos().x && e.canBeAttacked() && e.isAlive()) {
                    min = e;
                }
            }
            return min;
        }
    }

    public void win() {
        if (nextMap != null) {
            pantalla.createView(nextMap);
            return;
        }
        this.root.getChildren().clear();
        Text text = new Text("Victoria!");
        text.setFont(new Font(50));
        text.setFill(Color.WHITE);
        this.root.setCenter(text);
        root.setOnKeyPressed(e -> System.exit(0));
    }

    public void lose(String enemigo) {
        Shape rect = new Rectangle(root.getPrefWidth(), root.getPrefHeight());
        rect.setFill(Color.gray(0, 0.5));
        this.root.getChildren().add(rect);
        Text text = new Text("Perdiste!\nMoriste por: " + enemigo + "\nPresiona R para reiniciar el nivel");
        text.setFont(new Font(50));
        text.setFill(Color.RED);
        root.setCenter(text);
        root.setOnKeyPressed(e -> System.exit(0));
    }

    @Override
    public void update() {
        for (Enemigo enemigo : enemigos) {
            enemigo.update();
        }
    }

    public void setPlayer(Player player) {
        this.players.add(player);
    }

    public void setExit(Exit exit) {
        this.exit = exit;
        addEnvironment(exit);
    }

    public void setNextMap(String nextMap) {
        this.nextMap = nextMap;
    }
}
