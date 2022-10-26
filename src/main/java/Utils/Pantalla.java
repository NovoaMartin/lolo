package Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lolo.Mapa;

public class Pantalla extends Application {
    private static final boolean TECLADO = true;

    public static void main(String[] args) {
        launch();
    }

    private Stage stage;

    private final String originalMap = "mapa.1.txt";

    @Override
    public void start(Stage stage) {
        stage.setX(0);
        stage.setY(0);
        this.stage = stage;
        Mapa m = createView(originalMap);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, e->{
            if(e.getCode()== KeyCode.T){
                createView(originalMap);
            }
        });

        if (!TECLADO)
            new Thread(() -> {
                try {
                    int sleepTime = ((int) Constants.MOVEMENT_ANIMATION_DURATION.toMillis() + 50);
                    m.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    m.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    m.getPlayer().tryMove(Direccion.DOWN);
                    Thread.sleep(sleepTime);
                    m.getPlayer().tryMove(Direccion.DOWN);
                } catch (Exception e) {
                    System.exit(2);
                }
            }).start();

    }

    public Mapa createView(String mapFile) {
        BorderPane pane = new BorderPane();
        Mapa m = new Mapa(mapFile, this);
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
