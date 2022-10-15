import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import lolo.Mapa;

public class Pantalla extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Mapa m = new Mapa("mapa.2.txt");
//        Canvas canvas = new Canvas(500, 500);
//        drawMap(canvas.getGraphicsContext2D());
//        pane.getChildren().add(canvas);
//        pane.getChildren().add(m.getRender());
//        setEventListener((Shape) m.getRender(), pane);
//
//
//        canvas.setOnMouseClicked(e -> {
//            System.out.println("Click en: [" + (int)e.getY() / 50 + "][" + (int)e.getX() / 50 + "]");
//        });

        pane.getChildren().add(m.getRender());

        m.setEventListeners(pane);
//
//        pane.setOnKeyPressed(e->{
//            if(e.getCode() == KeyCode.W){
//                System.out.println("W");
//            }
//        });

        Scene sc = new Scene(pane, 0, 0, Color.WHITE);

        stage.setScene(sc);
        stage.setTitle("Pantalla");
        stage.show();
        stage.requestFocus();
        pane.requestFocus();
    }


    private void setEventListener(Shape s, Pane c){
        c.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                s.setTranslateX(s.getTranslateX() + 50);
            } else if(e.getButton() == MouseButton.SECONDARY) {
                s.setTranslateY(s.getTranslateY() + 50);
            }
        });

        c.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                s.setTranslateY(s.getTranslateY() - 50);
            } else if(e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                s.setTranslateY(s.getTranslateY() + 50);
            } else if(e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                s.setTranslateX(s.getTranslateX() - 50);
            } else if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                s.setTranslateX(s.getTranslateX() + 50);
            }
        });
    }
    private void drawMap(GraphicsContext gc) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gc.strokeRect(i * 50, j * 50, 50, 50);
            }
        }
    }

    public static void startGame() {
        launch();
    }
}
