import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    private int width;
    private int height;

    private final ArrayList<Player> players;
    private Item[][] items;
    private Enviroment[][] enviroments;

    private Celda salida;

    public static void main(String[] args) {
        Mapa m = new Mapa("mapa.txt");
        m.printMap();
        Player p = m.getPlayer();

        //Loop gameinput
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            String input = sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch (input) {
                case "w":
                    p.tryMove(Direccion.UP);
                    break;
                case "a":
                    p.tryMove(Direccion.LEFT);
                    break;
                case "s":
                    p.tryMove(Direccion.DOWN);
                    break;
                case "d":
                    p.tryMove(Direccion.RIGHT);
                    break;
                case "q":
                    break label;
            }
            m.printMap();
        }
    }

    public void printMap() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (items[j][i] != null) {
                    System.out.print("I");
                } else if (enviroments[j][i] != null) {
                    if (enviroments[j][i] instanceof MovableRock) {
                        System.out.print("R");
                    } else {
                        System.out.print("#");
                    }
                } else if (players.get(0).getPos().x == j && players.get(0).getPos().y == i) {
                    System.out.print("P");
                } else if (salida.x == j && salida.y == i) {
                    System.out.print("S");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }


    public Mapa(String mapName) {
        this.players = new ArrayList<Player>();
        try {
            this.loadFromFile(mapName);
        } catch (Exception e) {
            System.out.println("Error al cargar el mapa");
            System.exit(1);
        }
    }

    private void loadFromFile(String mapName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapName));
        this.width = scanner.nextInt();
        this.height = scanner.nextInt();
        this.salida = new Celda(scanner.nextInt(), scanner.nextInt());

        int numItems = scanner.nextInt();
        int numEnviroments = scanner.nextInt();
        this.items = new Item[this.width][this.height];
        this.enviroments = new Enviroment[this.width][this.height];

        this.players.add(new Player(new Celda(scanner.nextInt(), scanner.nextInt()), this, scanner.nextInt()));

        for (int i = 0; i < numItems; i++) {
            String type = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (type.equals("Llave")) {
                this.items[x][y] = new Llave(new Celda(x, y), this);
            }
        }

        for (int i = 0; i < numEnviroments; i++) {
            String type = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (type.equals("Wall")) {
                this.enviroments[x][y] = new Wall(new Celda(x, y), this);
            } else if (type.equals("MovableRock")) {
                this.enviroments[x][y] = new MovableRock(new Celda(x, y), this);
            }
        }

        scanner.close();

        //Fill all borders of enviroment with a wall
        for (int i = 0; i < this.width; i++) {
            this.enviroments[i][0] = new Wall(new Celda(i, 0), this);
            this.enviroments[i][this.height - 1] = new Wall(new Celda(i, this.height - 1), this);
        }
        for (int i = 0; i < this.height; i++) {
            this.enviroments[0][i] = new Wall(new Celda(0, i), this);
            this.enviroments[this.width - 1][i] = new Wall(new Celda(this.width - 1, i), this);
        }


    }

    public void tryMove(Character character, int direccion) {
        Celda target = character.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height || players.stream().anyMatch(c -> c.getPos().equals(target) && c != character)) {
            return;
        }
        if (items[target.x][target.y] != null) {
            items[target.x][target.y].interactWith(character, direccion, this);
            if (!items[target.x][target.y].isValid()) {
                items[target.x][target.y] = null;
            }
        } else if (enviroments[target.x][target.y] != null) {
            enviroments[target.x][target.y].interactWith(character, direccion);
        } else if (target.equals(this.salida) && character.getClass() == Player.class && ((Player) character).hasKey()) {
            System.out.println("You win!");
            System.exit(0);
        } else {
            character.setPos(target);
        }
    }

    public boolean tryMove(Enviroment enviroment, int direccion) {
        Celda from = enviroment.getPos();
        Celda target = enviroment.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height || players.stream().anyMatch(c -> c.getPos().equals(target))) {
            return false;
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
}
