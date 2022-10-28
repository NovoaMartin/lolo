package Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lolo.Mapa;

import java.util.Timer;
import java.util.TimerTask;

public class Pantalla extends Application {
    private static final boolean TECLADO = true;

    public static void main(String[] args) {
        launch();
    }

    private Stage stage;

    private final String originalMap = "mapa.1.txt";
    Mapa mapaActual = null;

    @Override
    public void start(Stage stage) {
        stage.setX(0);
        stage.setY(0);
        this.stage = stage;
        mapaActual = createView(originalMap);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.T) {
                mapaActual = createView(originalMap);
            }
        });
        stage.setOnCloseRequest(e -> System.exit(0));

        if (!TECLADO)
            new Thread(() -> {
                try {
                    int sleepTime = ((int) Constants.MOVEMENT_ANIMATION_DURATION.toMillis() + 50);
                    mapaActual.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    mapaActual.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    mapaActual.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    mapaActual.getPlayer().tryMove(Direccion.DOWN);
                } catch (Exception e) {
                    System.exit(2);
                }
            }).start();
    }


    Timer updateTimer;

    private void createUpdateTimer(Mapa m) {
        if(updateTimer != null)
            updateTimer.cancel();
        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                m.update();
            }
        }, 0, 1000);
    }

    public Mapa createView(String mapFile) {
        BorderPane pane = new BorderPane();
        Mapa m = MapLoader.loadFromFile(mapFile, this);
        pane.setCenter(m.getRender());
        m.setEventListeners(pane);
        Scene sc = new Scene(pane);
        sc.setFill(Color.FLORALWHITE);
        stage.setScene(sc);
        stage.setTitle("Pantalla");
        pane.requestFocus();
        createUpdateTimer(m);
        stage.show();
        return m;
    }

    public static void startGame() {
        launch();
    }
}
