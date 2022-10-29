package lolo;

import java.util.ArrayList;

import character.Player;
import character.Snakey;
import enviroment.Door;
import enviroment.Enviroment;
import items.Key;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Celda;
import utils.Constants;
import utils.Direction;
import utils.Renderable;

public class Mapa implements Renderable {

	private Celda[][] map;
	private ArrayList<Player> players;
	private BorderPane root;
	private Celda door;
	private int keys = 0;

	public Mapa(Celda[][] map) {
		this.map = map;
		players = new ArrayList<Player>();
	}

	public void tryMove(Player p, int direccion) {
		Celda target = p.translate(direccion);
		if (map[target.i][target.j] != null && map[target.i][target.j] == door && map[target.i][target.j].canInteract()) {
			door.interactWith(p);
			showText("Ganaste");
		} else if (target.j < 1 || target.j >= map.length - 1 || target.i < 1 || target.i >= map.length - 1) {
			return;
		} else if (map[target.i][target.j] != null && map[target.i][target.j].canInteract()) {
			map[target.i][target.j].interactWith(p);
			if (!p.isAlive()) {
				showText("Perdiste");
			} else if (!map[target.i][target.j].isValid()) {
				map[target.i][target.j].visible(false);
				map[target.i][target.j] = null;
			}
			if (p.Keys() == keys) {
				((Door) door).open();
			}
		} else {
			p.tryMove(target, direccion);
		}
	}

	public Player getPlayer() {
		return players.get(0);
	}

	public void addElement(char ch, int i, int j) {
		switch (ch) {
		case '#': {
			if (i == 0) {
				map[i][j] = new Enviroment(i, j, 80, 0);
			} else if (i == map.length - 1) {
				map[i][j] = new Enviroment(i, j, 80, 48);
			} else if (j == map.length - 1) {
				map[i][j] = new Enviroment(i, j, 112, 32);
			} else {
				map[i][j] = new Enviroment(i, j, 64, 16);
			}
			break;
		}
		case '*': {
			if (i == 0 && j == 0) {
				map[i][j] = new Enviroment(i, j, 64, 0);
			} else if (i == 0 && j == map.length - 1) {
				map[i][j] = new Enviroment(i, j, 112, 0);
			} else if (i == map.length - 1 && j == 0) {
				map[i][j] = new Enviroment(i, j, 64, 48);
			} else {
				map[i][j] = new Enviroment(i, j, 112, 48);
			}
			break;
		}
		case 'P': {
			Player p = new Player(i, j, 3, 32, 0);
			players.add(p);
			map[i][j] = p;
			break;
		}
		case 'S': {
			map[i][j] = new Snakey(i, j, 0, 96);
			break;
		}
		case 'K': {
			map[i][j] = new Key(i, j, 16, 48);
			keys++;
			break;
		}
		case 'D': {
			if (i == 0) {
				map[i][j] = new Door(i, j, 0, 64, 64);
			} else if (i == map.length - 1) {
				map[i][j] = new Door(i, j, 2, 64, 64);
			} else if (j == map.length - 1) {
				map[i][j] = new Door(i, j, 1, 64, 64);
			} else {
				map[i][j] = new Door(i, j, 3, 64, 64);
			}
			door = map[i][j];
			break;
		}
		case 'T': {
			map[i][j] = new Enviroment(i, j, 15, 16);
			break;
		}
		case ' ': {
//			map[i][j] = new Enviroment(i, j, 15, 0);
			break;
		}
		}
	}

	public void removeElement(Celda target) {
		if (map[target.i][target.j].isValid()) {
			map[target.i][target.j].visible(false);
			map[target.i][target.j] = null;
		}
	}

	public Node getRender() {
		root = new BorderPane();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != null)
					root.getChildren().add(map[i][j].getRender());
			}
		}
		root.setPrefHeight(map.length * Constants.IMAGE_SIZE);
		root.setPrefWidth(map.length * Constants.IMAGE_SIZE);
		BackgroundImage floor = new BackgroundImage(
				new Image("file:src/main/resources/floor.png", Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, true, false),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(floor));
		return root;
	}

	public void setEventListeners(Stage node) {
		Player p1 = players.get(0);
		node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			switch (e.getCode()) {
			case W: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.UP);
					break;
				}
			}
			case S: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.DOWN);
					break;
				}
			}
			case A: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.LEFT);
					break;
				}
			}
			case D: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.RIGHT);
					break;
				}
			}
			case SPACE: {
				if (!p1.isMoving()) {
					Celda target = getAttackTarget(p1.i, p1.j, p1.facingDir());
					p1.atacar(target);
					map[target.i][target.j] = null;
					break;
				}
			}
			default:
				break;
			}
		});
	}

	public Celda getAttackTarget(int i, int j, int dir) {
		switch (dir) {
		case Direction.UP: {
			for (i--; i > 0; i--) {
				if (map[i][j] != null)
					return map[i][j];
			}
		}
			break;
		case Direction.DOWN: {
			for (i++; i < map.length; i++) {
				if (map[i][j] != null)
					return map[i][j];
			}
		}
			break;
		case Direction.LEFT: {
			for (j--; j >= 0; j--) {
				if (map[i][j] != null)
					return map[i][j];
			}
		}
			break;
		case Direction.RIGHT: {
			for (j++; j <= map.length; j++) {
				if (map[i][j] != null)
					return map[i][j];
			}
		}
			break;
		}

		return null;
	}

	public void showText(String txt) {
		Shape rect = new Rectangle(root.getPrefWidth(), root.getPrefHeight());
		rect.setFill(Color.gray(0, 0.5));
		root.getChildren().add(rect);
		Text text = new Text(txt + "!\n" + "Presiona R para reiniciar el nivel");
		text.setFont(new Font(50));

		if (txt.contains("Ganaste"))
			text.setFill(Color.GREEN);
		else
			text.setFill(Color.RED);

		text.setTextAlignment(TextAlignment.CENTER);
		root.setCenter(text);
	}

}
