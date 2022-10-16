import Utils.Direction;
import character.Player;
import lolo.GameMap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameMap m = new GameMap("mapa.2.txt");
        m.printMap();
        Player p = m.getPlayer();

        Scanner sc = new Scanner(System.in);
        label:
        while (p.isAlive() && !p.isWinner()) {
            String input = sc.nextLine();
            System.out.print("\033[H\033[2J");
            switch (input.toLowerCase()) {
                case "w":
                    p.move(Direction.UP);
                    break;
                case "a":
                    p.move(Direction.LEFT);
                    break;
                case "s":
                    p.move(Direction.DOWN);
                    break;
                case "d":
                    p.move(Direction.RIGHT);
                    break;
                case "q":
                    break label;
            }
            m.printMap();
        }
    }
}
