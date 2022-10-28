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
    private static final boolean TECLADO = false;

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

        if (!TECLADO) {
            GameRunner gameRunner = new GameRunner(mapaActual.getPlayer(), mapaActual);
            gameRunner.loadFromFile("runner1.txt");
//            gameRunner.move(Direccion.LEFT, 2);
//            gameRunner.move(Direccion.RIGHT, 4);
//            gameRunner.move(Direccion.DOWN, 4);
//            gameRunner.move(Direccion.RIGHT);
//            gameRunner.atacar();
//            gameRunner.move(Direccion.UP, 4);
//            gameRunner.move(Direccion.RIGHT, 5);
//            gameRunner.move(Direccion.DOWN);
//            gameRunner.move(Direccion.RIGHT, 3);
//            gameRunner.move(Direccion.DOWN, 4);
//            gameRunner.move(Direccion.LEFT);
//            gameRunner.move(Direccion.UP);
//            gameRunner.move(Direccion.LEFT);
//            gameRunner.move(Direccion.UP);
//            gameRunner.move(Direccion.RIGHT);
//            gameRunner.move(Direccion.UP, 2);
//            gameRunner.move(Direccion.LEFT, 2);
//            gameRunner.move(Direccion.DOWN);
//            gameRunner.move(Direccion.RIGHT, 2);
//            gameRunner.move(Direccion.LEFT, 2);
//            gameRunner.move(Direccion.UP);
//            gameRunner.move(Direccion.LEFT);
//            gameRunner.move(Direccion.UP);
//            gameRunner.move(Direccion.LEFT, 2);
//            gameRunner.move(Direccion.DOWN, 9);
//            gameRunner.move(Direccion.RIGHT, 7);
            gameRunner.start();
        }


    }


    Timer updateTimer;

    private void createUpdateTimer(Mapa m) {
        if (updateTimer != null)
            updateTimer.cancel();
        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                m.update();
            }
        }, 0, 1);
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
