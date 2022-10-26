package lolo;


import Utils.Celda;
import Utils.Direccion;
import Utils.Pantalla;
import character.Character;
import character.Enemigo;
import character.Enemigos.Medusa;
import character.Enemigos.Pozo;
import character.Enemigos.Trampa;
import character.Player;
import enviroment.Enviroment;
import enviroment.Exit;
import enviroment.MovableRock;
import enviroment.UnmovableEnvironment;
import graphics.Renderable;
import items.Item;
import items.Llave;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Mapa implements Renderable {
    private int width;
    private int height;

    private final ArrayList<Player> players;
    private Item[][] items;
    private Enviroment[][] enviroments;

    private final ArrayList<Enemigo> enemigos;

    private Exit exit;

    private final String mapFile;
    private String nextMap;
    Pantalla pantalla;

    public Mapa(String mapName, Pantalla pantalla) {
        this.mapFile = mapName;
        this.pantalla = pantalla;
        this.enemigos = new ArrayList<Enemigo>();
        this.players = new ArrayList<Player>();
        try {
            this.loadFromFile(mapName);
        } catch (Exception e) {
            System.out.println("Error al cargar el mapa");
            System.exit(1);
        }
    }


    public void tryMove(Character character, int direccion) {
        Celda target = character.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height || players.stream().anyMatch(c -> c.getPos().equals(target) && c != character)) {
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

    public boolean tryMove(Enviroment enviroment, int direccion) {
        Celda from = enviroment.getPos();
        Celda target = enviroment.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height ||
                players.stream().anyMatch(c -> c.getPos().equals(target))) {
            return false;
        }
        Optional<Enemigo> enemigo = enemigos.stream().filter(e -> e.getPos().equals(target)).findFirst();
        if (enemigo.isPresent() && enemigo.get().isAlive())
            return false;

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

    private void loadFromFile(String mapName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mapas/" + mapName));
        this.width = scanner.nextInt();
        this.height = scanner.nextInt();
        this.items = new Item[this.width][this.height];
        this.enviroments = new Enviroment[this.width][this.height];
        addEnvironment("Exit", scanner.nextInt(), scanner.nextInt());

        int numItems = scanner.nextInt();
        int numEnviroments = scanner.nextInt();
        int numEnemigos = scanner.nextInt();

        this.players.add(new Player(new Celda(scanner.nextInt(), scanner.nextInt()), this, scanner.nextInt()));

        for (int i = 0; i < numItems; i++) {
            String type = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            addItem(type, x, y);
        }
        for (int i = 0; i < numEnviroments; i++) {
            String type = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            addEnvironment(type, x, y);
        }
        for (int i = 0; i < numEnemigos; i++) {
            String type = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            addEnemigo(type, x, y);
        }

        for (int i = 0; i < this.width; i++) {
            this.enviroments[i][0] = new UnmovableEnvironment(new Celda(i, 0), Direccion.DOWN);
            this.enviroments[i][this.height - 1] = new UnmovableEnvironment(new Celda(i, this.height - 1), Direccion.UP);
        }
        for (int i = 0; i < this.height; i++) {
            this.enviroments[0][i] = new UnmovableEnvironment(new Celda(0, i), Direccion.RIGHT);
            this.enviroments[this.width - 1][i] = new UnmovableEnvironment(new Celda(this.width - 1, i), Direccion.LEFT);
        }

        if (scanner.hasNext()) {
            this.nextMap = scanner.next();
        }
        scanner.close();
    }

    private void addItem(String type, int x, int y) {
        if (type.equals("items.Llave")) {
            this.items[x][y] = new Llave(new Celda(x, y));
        }
    }

    private void addEnvironment(String type, int x, int y) {
        switch (type) {
            case "enviroment.Rock":
                this.enviroments[x][y] = new UnmovableEnvironment(new Celda(x, y), Direccion.DOWN, "Rock");
                break;
            case "enviroment.MovableRock":
                this.enviroments[x][y] = new MovableRock(new Celda(x, y));
                break;
            case "Exit":
                exit = new Exit(new Celda(x, y));
                this.enviroments[x][y] = exit;
                break;
        }
    }

    private void addEnemigo(String type, int x, int y) {
        switch (type) {
            case "Pozo":
                this.enemigos.add(new Pozo(new Celda(x, y), this, 1));
                break;
            case "Trampa":
                this.enemigos.add(new Trampa(new Celda(x, y), this, 1));
                break;
            case "Medusa":
                this.enemigos.add(new Medusa(new Celda(x, y), this, 1));
                break;
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
                if (e.getPos().y < from.y && e.getPos().x == from.x && e.getPos().y > min.getPos().y && e.canBeAttacked()) {
                    min = e;
                }
            }
            return min;
        } else if (orientacion == Direccion.DOWN) {
            Enemigo min = new Trampa(new Celda(from.x, height), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().y > from.y && e.getPos().x == from.x && e.getPos().y < min.getPos().y && e.canBeAttacked()) {
                    min = e;
                }
            }
            return min;
        } else if (orientacion == Direccion.LEFT) {
            Enemigo min = new Trampa(new Celda(-1, from.y), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().x < from.x && e.getPos().y == from.y && e.getPos().x > min.getPos().x && e.canBeAttacked()) {
                    min = e;
                }
            }
            return min;
        } else {
            Enemigo min = new Trampa(new Celda(width, from.y), this, 1);
            for (Enemigo e : enemigos) {
                if (e.getPos().x > from.x && e.getPos().y == from.y && e.getPos().x < min.getPos().x && e.canBeAttacked()) {
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
}
