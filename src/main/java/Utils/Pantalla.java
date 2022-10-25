package Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lolo.Mapa;

public class Pantalla extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws InterruptedException {
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

		new Thread() {
			@Override
			public void run() {
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
			}
		}.start();

	}

	public static void startGame() {
		launch();
	}
}
