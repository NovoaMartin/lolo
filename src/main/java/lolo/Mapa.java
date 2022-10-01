package lolo;


import Utils.Celda;
import character.Character;
import character.Enemigo;
import character.Enemigos.Pozo;
import character.Enemigos.Trampa;
import character.Player;
import enviroment.Enviroment;
import enviroment.MovableRock;
import enviroment.Wall;
import items.Item;
import items.Llave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Mapa {
    private int width;
    private int height;

    private final ArrayList<Player> players;
    private Item[][] items;
    private Enviroment[][] enviroments;

    private final ArrayList<Enemigo> enemigos;

    private Celda salida;


    public Mapa(String mapName) {
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

        if (character instanceof Player p) {
            for (Enemigo e : enemigos) {
                if (e.getPos().equals(target)) {
                    e.atacar(p);
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
        } else if (target.equals(this.salida)) {
            if (character instanceof Player p && ((Player) character).hasKey()) {
                p.setWinner();
                System.out.println("Ganaste!");
            }
        } else {
            character.setPos(target);
        }
    }

    public boolean tryMove(Enviroment enviroment, int direccion) {
        Celda from = enviroment.getPos();
        Celda target = enviroment.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height ||
                players.stream().anyMatch(c -> c.getPos().equals(target))
                || target.equals(this.salida)) {
            return false;
        }
        Optional<Enemigo> enemigo = enemigos.stream().filter(e -> e.getPos().equals(target)).findFirst();
        if (enemigo.isPresent())
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
        Scanner scanner = new Scanner(new File(mapName));
        this.width = scanner.nextInt();
        this.height = scanner.nextInt();
        this.salida = new Celda(scanner.nextInt(), scanner.nextInt());

        int numItems = scanner.nextInt();
        int numEnviroments = scanner.nextInt();
        int numEnemigos = scanner.nextInt();
        this.items = new Item[this.width][this.height];
        this.enviroments = new Enviroment[this.width][this.height];

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
        scanner.close();
        for (int i = 0; i < this.width; i++) {
            this.enviroments[i][0] = new Wall(new Celda(i, 0));
            this.enviroments[i][this.height - 1] = new Wall(new Celda(i, this.height - 1));
        }
        for (int i = 0; i < this.height; i++) {
            this.enviroments[0][i] = new Wall(new Celda(0, i));
            this.enviroments[this.width - 1][i] = new Wall(new Celda(this.width - 1, i));
        }
    }

    private void addItem(String type, int x, int y) {
        if (type.equals("items.Llave")) {
            this.items[x][y] = new Llave(new Celda(x, y));
        }
    }

    private void addEnvironment(String type, int x, int y) {
        if (type.equals("enviroment.Wall")) {
            this.enviroments[x][y] = new Wall(new Celda(x, y));
        } else if (type.equals("enviroment.MovableRock")) {
            this.enviroments[x][y] = new MovableRock(new Celda(x, y));
        }
    }

    private void addEnemigo(String type, int x, int y) {
        if (type.equals("Pozo")) {
            this.enemigos.add(new Pozo(new Celda(x, y), this, 1));
        } else if (type.equals("Trampa")) {
            this.enemigos.add(new Trampa(new Celda(x, y), this, 1));
        }
    }

    public void printMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int finalI = i;
                int finalJ = j;
                Optional<Enemigo> enemigo = enemigos.stream().filter(e -> e.getPos().equals(new Celda(finalJ, finalI))).findFirst();

                if (items[j][i] != null) {
                    System.out.print("I");
                } else if (enviroments[j][i] != null) {
                    if (enviroments[j][i] instanceof MovableRock) {
                        System.out.print("R");
                    } else {
                        System.out.print("#");
                    }
                } else if (players.get(0).getPos().x == j && players.get(0).getPos().y == i) {
                    System.out.print("J");
                } else if (salida.x == j && salida.y == i) {
                    System.out.print("S");
                } else if (enemigo.isPresent()) {
                    if (enemigo.get() instanceof Pozo)
                        System.out.print("P");
                    else if (enemigo.get() instanceof Trampa)
                        System.out.print("T");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }
}
