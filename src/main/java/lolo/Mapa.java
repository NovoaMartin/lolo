package lolo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import app.Pantalla;
import character.Muse;
import character.Player;
import enviroment.Door;
import enviroment.Enviroment;
import graphics.Renderable;
import items.Key;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Celda;
import utils.Constants;
import utils.Direction;

public class Mapa implements Renderable {

	private Celda[][] map;

	private String nextMap;
	private Pantalla pantalla;

	private ArrayList<Player> players;

	public Mapa(Celda[][] map) {
		this.map = map;
		players = new ArrayList<Player>();
	}

	public void tryMove(Player pos, int direccion) {
		Celda target = pos.translate(direccion);
		if (target.x < 0 || target.x >= map.length || target.y < 0 || target.y >= map.length) {
			return;
		} else if (map[target.x][target.y].canInteract() ) {
			pos.interactWith(target);
		} else {
			pos.setPos(target);
		}
	}

	public Player getPlayer() {
		return players.get(0);
	}

//		if (character instanceof Player) {
//			Player p = (Player) character;
//			for (Enemigo e : enemigos) {
//				if (e.isAlive() && e.getPos().equals(target) || e.canInteractWith(target)) {
//					e.interactWith(p, direccion, this);
//					return;
//				}
//			}
//		}
//
//		if (items[target.x][target.y] != null) {
//			items[target.x][target.y].interactWith(character, direccion, this);
//			if (!items[target.x][target.y].isValid()) {
//				items[target.x][target.y] = null;
//			}
//		} else if (enviroments[target.x][target.y] != null) {
//			enviroments[target.x][target.y].interactWith(character, direccion, this);
//		} else {
//			character.setPos(target);
//		}
//	}

	public boolean tryMove(Enviroment enviroment, int direccion) {
//		Celda from = enviroment.getPos();
//		Celda target = enviroment.getPos().translate(direccion);
//		if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height
//				|| players.stream().anyMatch(c -> c.getPos().equals(target))) {
//			return false;
//		}
//		Optional<Enemigo> enemigo = enemigos.stream().filter(e -> e.getPos().equals(target)).findFirst();
//		if (enemigo.isPresent() && enemigo.get().isAlive())
//			return false;
//
//		if (items[target.x][target.y] == null && enviroments[target.x][target.y] == null) {
//			enviroment.setPos(target);
//			enviroments[from.x][from.y] = null;
//			enviroments[target.x][target.y] = enviroment;
//			return true;
//		}
		return false;
	}

	private void loadFromFile(String mapName) throws FileNotFoundException {
//		Scanner scanner = new Scanner(new File("mapas/" + mapName));
//		this.width = scanner.nextInt();
//		this.height = scanner.nextInt();
//		this.items = new Item[this.width][this.height];
//		this.enviroments = new Enviroment[this.width][this.height];
//		addEnvironment("Exit", scanner.nextInt(), scanner.nextInt());
//
//		int numItems = scanner.nextInt();
//		int numEnviroments = scanner.nextInt();
//		int numEnemigos = scanner.nextInt();
//
//		this.players.add(new Player(new Celda(scanner.nextInt(), scanner.nextInt()), this, scanner.nextInt()));
//
//		for (int i = 0; i < numItems; i++) {
//			String type = scanner.next();
//			int x = scanner.nextInt();
//			int y = scanner.nextInt();
//			addItem(type, x, y);
//		}
//		for (int i = 0; i < numEnviroments; i++) {
//			String type = scanner.next();
//			int x = scanner.nextInt();
//			int y = scanner.nextInt();
//			addEnvironment(type, x, y);
//		}
//		for (int i = 0; i < numEnemigos; i++) {
//			String type = scanner.next();
//			int x = scanner.nextInt();
//			int y = scanner.nextInt();
//			addEnemigo(type, x, y);
//		}
//
//		for (int i = 0; i < this.width; i++) {
//			this.enviroments[i][0] = new UnmovableEnvironment(new Celda(i, 0), Direccion.DOWN);
//			this.enviroments[i][this.height - 1] = new UnmovableEnvironment(new Celda(i, this.height - 1),
//					Direccion.UP);
//		}
//		for (int i = 0; i < this.height; i++) {
//			this.enviroments[0][i] = new UnmovableEnvironment(new Celda(0, i), Direccion.RIGHT);
//			this.enviroments[this.width - 1][i] = new UnmovableEnvironment(new Celda(this.width - 1, i),
//					Direccion.LEFT);
//		}
//
//		if (scanner.hasNext()) {
//			this.nextMap = scanner.next();
//		}
//		scanner.close();
	}

	public void addElement(char ch, int i, int j) {
		switch (ch) {
		case '#': {
			if (i == 0) {
				map[i][j] = new Enviroment(i, j, 80, 0);
			} else if (i == map.length-1) {
				map[i][j] = new Enviroment(i, j, 80, 48);
			} else if (j == map.length-1) {
				map[i][j] = new Enviroment(i, j, 112, 32);
			} else {
				map[i][j] = new Enviroment(i, j, 64, 16);
			}
			break;
		}
		case '*': {
			if (i == 0 && j == 0) {
				map[i][j] = new Enviroment(i, j, 64, 0);
			} else if (i == 0 && j == map.length-1) {
				map[i][j] = new Enviroment(i, j, 112, 0);
			} else if (i == map.length-1 && j == 0) {
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
		case 'M': {
			map[i][j] = new Muse(i, j, 0, 96);
			break;
		}
		case 'K': {
			map[i][j] = new Key(i, j, 16, 48);
			break;
		}
		case 'D': {
			if (i == 0) {
				map[i][j] = new Door(i, j, 1, 64, 64);
			} else if (i == map.length-1) {
				map[i][j] = new Door(i, j, 1, 64, 64);
			} else if (j == map.length-1) {
				map[i][j] = new Door(i, j, 1, 64, 64);
			} else {
				map[i][j] = new Door(i, j, 1, 64, 64);
			}
			break;
		}
		case 'T': {
			map[i][j] = new Enviroment(i, j, 15, 16);
			break;
		}
		case ' ': {
			map[i][j] = new Enviroment(i, j, 15, 0);
			break;
		}
		}
	}

	BorderPane root = new BorderPane();

	public Node getRender() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				root.getChildren().add(map[i][j].getRender());
			}
		}

		root.setPrefHeight(map.length * Constants.imageSize * 5);
		root.setPrefWidth(map.length * Constants.imageSize * 5);

//		BackgroundImage floor = new BackgroundImage(new Image("file:src/main/resources/floor.png"),
//				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//
//		root.setBackground(new Background(floor));
		return root;
	}

	public void setEventListeners(Stage node) {
		Player p1 = players.get(0);
		node.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			} else if (e.getCode() == KeyCode.R) {
				pantalla.createView();
			}
			switch (e.getCode()) {
			case W: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.UP);
					break;
				}
			}
			case S: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.UP);
					break;
				}
			}
			case A: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.UP);
					break;
				}
			}
			case D: {
				if (!p1.isMoving()) {
					tryMove(p1, Direction.UP);
					break;
				}
			}
			case SPACE: {
				if (!p1.isMoving()) {
					p1.atacar(this);
					break;
				}
			}
			default:
				break;
			}
		});
	}

	public Celda getAttackTarget(Celda from, int orientacion) {
		Celda target = null;
		if (orientacion == Direction.UP) {
			for (int i = from.y; i > 0; i--) {
				if (map[i][from.x] != null) {
					target = map[i][from.x];
				}
			}
		} else if (orientacion == Direction.DOWN) {
			for (int i = from.y; i > 0; i++) {
				if (map[i][from.x] != null) {
					target = map[i][from.x];
				}
			}
		} else if (orientacion == Direction.LEFT) {
			for (int i = from.y; i > 0; i--) {
				if (map[from.y][i] != null) {
					target = map[from.y][i];
				}
			}
		} else {
			for (int i = from.y; i > 0; i++) {
				if (map[from.y][i] != null) {
					target = map[from.y][i];
				}
			}
		}
		return target;
	}

	public void win() {
		if (nextMap != null) {
			pantalla.createView();
			return;
		}
		this.root.getChildren().clear();
		Text text = new Text("Victoria!");
		text.setFont(new Font(50));
		text.setFill(Color.WHITE);
		this.root.setCenter(text);
		root.setOnKeyPressed(e -> System.exit(0));
	}

	public void lose(String enemigo) {
		Shape rect = new Rectangle(root.getPrefWidth(), root.getPrefHeight());
		rect.setFill(Color.gray(0, 0.5));
		this.root.getChildren().add(rect);
		Text text = new Text("Perdiste!\nMoriste por: " + enemigo + "\nPresiona R para reiniciar el nivel");
		text.setFont(new Font(50));
		text.setFill(Color.RED);
		root.setCenter(text);
		root.setOnKeyPressed(e -> System.exit(0));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}

		return sb.toString();

	}
}
