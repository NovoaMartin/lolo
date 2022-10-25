package lolo;


import Utils.Cell;
import character.Character;
import character.Enemy;
import character.Enemigos.Pit;
import character.Enemigos.Trap;
import character.Player;
import enviroment.Environment;
import enviroment.MovableRock;
import enviroment.Wall;
import items.Item;
import items.Key;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class GameMap {
    private int width;
    private int height;

    private final ArrayList<Player> players;
    private Item[][] items;
    private Environment[][] environments;

    private final ArrayList<Enemy> enemies;

    private Cell exit;


    public GameMap(String mapName) {
        this.enemies = new ArrayList<Enemy>();
        this.players = new ArrayList<Player>();
        try {
            this.loadFromFile(mapName);
        } catch (Exception e) {
            System.out.println("Error al cargar el mapa");
            System.exit(1);
        }
    }


    public void move(Character character, int direction) {
        Cell target = character.getPos().translate(direction);
        if (target.isOutsideMap(this) ||
        	players.stream().anyMatch(c -> c.getPos().equals(target) && c != character))
            return;

        if (character.isPlayer()) {
            for (Enemy e : enemies) {
                if (e.getPos().equals(target)) {
                    e.attack((Player)character);
                    return;
                }
            }
        }

        if (items[target.x][target.y] != null) {
            items[target.x][target.y].interactWith(character, direction, this);
            if (!items[target.x][target.y].isValid()) {
                items[target.x][target.y] = null;
            }
        } else if (environments[target.x][target.y] != null) {
            environments[target.x][target.y].interactWith(character, direction, this);
        } else if (target.equals(this.exit)) {
            if (character.isPlayer() && ((Player) character).hasKey()) {
                ((Player)character).setWinner();
                //System.out.println("Ganaste!");
            }
        } else {
            character.setPos(target);
        }
    }

    // Returns whether the environment can move to its target position or not
    public boolean move(Environment environment, int direction) {
        Cell from = environment.getPos();
        Cell to = environment.getPos().translate(direction);
        
        // If the target pos is outside the map, or
        // there are any players in the way, or
        // the target pos is the exit,
        // do not move.
        if (to.isOutsideMap(this) ||
            players.stream().anyMatch(c -> c.getPos().equals(to)) ||
            to.equals(this.exit)) {
            return false;
        }
        
        // If there's an enemy in the way,
        // do not move.
        Optional<Enemy> enemy = enemies.stream().filter(e -> e.getPos().equals(to)).findFirst();
        if (enemy.isPresent())
            return false;

        // If there's any items or other environments in the way,
        // do not move.
        if (items[to.x][to.y] != null || environments[to.x][to.y] != null)
        	return false;
        
        // Otherwise, move.
        environment.setPos(to);
        environments[from.x][from.y] = null;
        environments[to.x][to.y] = environment;
        return true;
    }

    public Player getPlayer() {
        return players.get(0);
    }

    public Environment[][] getEnviroments() {
        return environments;
    }

    private void loadFromFile(String mapName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("mapas/" + mapName));
        this.width = scanner.nextInt();
        this.height = scanner.nextInt();
        this.exit = new Cell(scanner.nextInt(), scanner.nextInt());

        int numItems = scanner.nextInt();
        int numEnviroments = scanner.nextInt();
        int numEnemigos = scanner.nextInt();
        this.items = new Item[this.width][this.height];
        this.environments = new Environment[this.width][this.height];

        this.players.add(new Player(new Cell(scanner.nextInt(), scanner.nextInt()), this, scanner.nextInt()));

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
        scanner.close();
        for (int i = 0; i < this.width; i++) {
            this.environments[i][0] = new Wall(new Cell(i, 0));
            this.environments[i][this.height - 1] = new Wall(new Cell(i, this.height - 1));
        }
        for (int i = 0; i < this.height; i++) {
            this.environments[0][i] = new Wall(new Cell(0, i));
            this.environments[this.width - 1][i] = new Wall(new Cell(this.width - 1, i));
        }
    }

    private void addItem(String type, int x, int y) {
        if (type.equals("items.Key")) {
            this.items[x][y] = new Key(new Cell(x, y));
        }
    }

    private void addEnvironment(String type, int x, int y) {
        if (type.equals("environment.Wall")) {
            this.environments[x][y] = new Wall(new Cell(x, y));
        } else if (type.equals("environment.MovableRock")) {
            this.environments[x][y] = new MovableRock(new Cell(x, y));
        }
    }

    private void addEnemigo(String type, int x, int y) {
        if (type.equals("Pit")) {
            this.enemies.add(new Pit(new Cell(x, y), this, 1));
        } else if (type.equals("Trap")) {
            this.enemies.add(new Trap(new Cell(x, y), this, 1));
        }
    }

    // Should be deleted on final hand-in.
    public void printMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int finalI = i;
                int finalJ = j;
                Optional<Enemy> enemigo = enemies.stream().filter(e -> e.getPos().equals(new Cell(finalJ, finalI))).findFirst();

                if (items[j][i] != null) {
                    System.out.print("I");
                } else if (environments[j][i] != null) {
                    if (environments[j][i] instanceof MovableRock) {
                        System.out.print("R");
                    } else {
                        System.out.print("#");
                    }
                } else if (players.get(0).getPos().x == j && players.get(0).getPos().y == i) {
                    System.out.print("J");
                } else if (exit.x == j && exit.y == i) {
                    System.out.print("S");
                } else if (enemigo.isPresent()) {
                    if (enemigo.get() instanceof Pit)
                        System.out.print("P");
                    else if (enemigo.get() instanceof Trap)
                        System.out.print("T");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
