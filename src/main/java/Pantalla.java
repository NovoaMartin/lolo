import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import lolo.Mapa;

public class Pantalla extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox pane = new VBox();
        Mapa m = new Mapa("mapa.2.txt");
        pane.getChildren().add(m.getRender());

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
