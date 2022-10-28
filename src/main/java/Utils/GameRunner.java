package Utils;

import character.Player;
import javafx.application.Platform;
import lolo.Mapa;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {
    Queue<Integer> acciones = new ArrayDeque<>();
    Player player;
    Timer timer = new Timer();
    Mapa mapaActual;

    public GameRunner(Player p, Mapa mapaActual) {
        super();
        player = p;
        this.mapaActual = mapaActual;
    }

    public void move(int direccion) {
        acciones.add(direccion);
    }

    public void move(int direccion, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            acciones.add(direccion);
        }
    }

    public void atacar() {
        acciones.add(0);
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (mapaActual.getNextActiveMap() != null) {
                        mapaActual = mapaActual.getNextActiveMap();
                        player = mapaActual.getPlayer();
                    }
                    if (acciones.isEmpty()) {
                        cancel();
                        return;
                    }
                    int accion = acciones.poll();
                    if (accion == 0) {
                        player.atacar();
                    } else {
                        player.tryMove(accion);
                    }
                });
            }
        }, 0, (long) Constants.MOVEMENT_ANIMATION_DURATION.toMillis() + 50);
    }
}
