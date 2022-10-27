package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lolo.Mapa;
import utils.Constants;
import utils.Direction;
import utils.FileMap;

public class Pantalla extends Application {
    private static final boolean TECLADO = true;

    public static void main(String[] args) {
        launch();
    }

    private Stage stage;

    private final String originalMap = "Dibujo.txt";

    @Override
    public void start(Stage stage) {
        stage.setX(0);
        stage.setY(0);
        this.stage = stage;
        Mapa m = createView();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, e->{
            if(e.getCode()== KeyCode.T){
                createView();
            }
        });

        if (!TECLADO)
            new Thread(() -> {
                try {
                    int sleepTime = ((int) Constants.MOVEMENT_ANIMATION_DURATION.toMillis() + 50);
                    m.tryMove(m.getPlayer(), Direction.DOWN);
                    Thread.sleep(sleepTime);
                    m.tryMove(m.getPlayer(), Direction.DOWN);
                    Thread.sleep(sleepTime);
                    m.tryMove(m.getPlayer(), Direction.DOWN);
                    Thread.sleep(sleepTime);
                    m.tryMove(m.getPlayer(), Direction.DOWN);
                } catch (Exception e) {
                    System.exit(2);
                }
            }).start();

    }

    public Mapa createView() {
        BorderPane pane = new BorderPane();
        Mapa m = FileMap.loadMap(originalMap);
        System.out.println(m);
        pane.setCenter(m.getRender());

        m.setEventListeners(pane);

        Scene sc = new Scene(pane);
        sc.setFill(Color.FLORALWHITE);
        stage.setScene(sc);
        stage.setTitle("Pantalla");
        pane.requestFocus();

        stage.show();
        return m;
    }

    public static void startGame() {
        launch();
    }
}
