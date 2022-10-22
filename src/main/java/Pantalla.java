import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lolo.Mapa;

public class Pantalla extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        Mapa m = new Mapa("mapa.2.txt");
        pane.setCenter(m.getRender());


        m.setEventListeners(pane);

        Scene sc = new Scene(pane);
        sc.setFill(Color.FLORALWHITE);
        stage.setScene(sc);
        stage.setTitle("Pantalla");
        pane.requestFocus();

        stage.show();


    }

    public static void startGame() {
        launch();
    }
}
