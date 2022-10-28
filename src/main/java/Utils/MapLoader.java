package Utils;

import character.Enemigos.Medusa;
import character.Enemigos.MovingThing;
import character.Enemigos.Pozo;
import character.Enemigos.Trampa;
import character.Player;
import enviroment.Exit;
import enviroment.MovableRock;
import enviroment.UnmovableEnvironment;
import items.Llave;
import lolo.Mapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoader {
    public static Mapa loadFromFile(String path, Pantalla p) {
        try {
            Scanner scanner = new Scanner(new File("mapas/" + path));
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            Mapa map = new Mapa(path, p, width, height);
            Exit exit = new Exit(new Celda(scanner.nextInt(), scanner.nextInt()));
            int numItems = scanner.nextInt();
            int numEnviroments = scanner.nextInt();
            int numEnemies = scanner.nextInt();
            map.setPlayer(new Player(new Celda(scanner.nextInt(), scanner.nextInt()), map, scanner.nextInt()));
            for (int i = 0; i < numItems; i++) {
                addItem(map, scanner.next(), scanner.nextInt(), scanner.nextInt());
            }
            for (int i = 0; i < numEnviroments; i++) {
                addEnvironment(map, scanner.next(), scanner.nextInt(), scanner.nextInt());
            }
            for (int i = 0; i < numEnemies; i++) {
                addEnemigo(map, scanner.next(), scanner.nextInt(), scanner.nextInt());
            }
            map.setExit(exit);
            map.addWalls();
            if (scanner.hasNext()) {
                map.setNextMap(scanner.next());
            }
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addItem(Mapa map, String type, int x, int y) {
        if (type.equals("items.Llave")) {
            map.addItem(new Llave(new Celda(x, y)));
        }
    }

    public static void addEnvironment(Mapa map, String type, int x, int y) {
        switch (type) {
            case "enviroment.Rock":
                map.addEnvironment(new UnmovableEnvironment(new Celda(x, y), Direccion.DOWN, "Rock"));
                break;
            case "enviroment.MovableRock":
                map.addEnvironment(new MovableRock(new Celda(x, y)));
                break;
            case "Exit":
                map.setExit(new Exit(new Celda(x, y)));
                break;
        }
    }

    public static void addEnemigo(Mapa map, String type, int x, int y) {
        switch (type) {
            case "Pozo":
                map.addEnemigo(new Pozo(new Celda(x, y), map, 1));
                break;
            case "Trampa":
                map.addEnemigo(new Trampa(new Celda(x, y), map, 1));
                break;
            case "Medusa":
                map.addEnemigo(new Medusa(new Celda(x, y), map, 1));
                break;
            case "MovingThing":
                map.addEnemigo(new MovingThing(new Celda(x, y), map, 1));
                break;
        }
    }
}