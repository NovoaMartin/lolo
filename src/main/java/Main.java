import Utils.Direccion;
import character.Player;
import lolo.Mapa;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mapa m = new Mapa("mapa.2.txt");
        m.printMap();
        Player p = m.getPlayer();

        Scanner sc = new Scanner(System.in);
        label:
        while (p.isAlive() && !p.isWinner()) {
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
}
