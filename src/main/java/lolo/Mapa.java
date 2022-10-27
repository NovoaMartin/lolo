package lolo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import app.Pantalla;
import character.Hole;
import character.Player;
import enviroment.Door;
import enviroment.Enviroment;
import enviroment.Wall;
import graphics.Renderable;
import items.Key;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
import utils.Celda;
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

	public void tryMove(Celda pos, int direccion) {
		Celda target = pos.translate(direccion);
		if (target.x < 0 || target.x >= map.length || target.y < 0 || target.y >= map.length) {
			return;
		} else if(map[target.x][target.y] != null){
			pos.interactWith(target);
		} else {
			map[target.x][target.y].setPos(target);
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

	public void addElement(char ch, int x, int y) {
		switch (ch) {
		case '#': {
			if (x == 0) {	
				map[x][y] = new Wall(x, y, Direction.DOWN);
			} else if (x == map.length) {
				map[x][y] = new Wall(x, y, Direction.UP);
			} else if (y == map.length) {
				map[x][y] = new Wall(x, y, Direction.LEFT);
			} else {
				map[x][y] = new Wall(x, y, Direction.LEFT);
			}
			break;
		}
		case 'P': {
			Player p = new Player(x, y, 3);
			players.add(p);
			map[x][y] = p;
			break;
		}
		case 'H': {
			map[x][y] = new Hole(x, y, 0);
			break;
		}
		case 'K': {
			map[x][y] = new Key(x, y);
			break;
		}
		case 'D': {
			if (x == 0) {	
				map[x][y] = new Door(x, y, Direction.DOWN);
			} else if (x == map.length) {
				map[x][y] = new Door(x, y, Direction.UP);
			} else if (y == map.length) {
				map[x][y] = new Door(x, y, Direction.LEFT);
			} else {
				map[x][y] = new Door(x, y, Direction.LEFT);
			}
		}
		default:
			
		}
	}
	

	BorderPane root = new BorderPane();

	public Node getRender() {
		
		for(Celda[] celdas : map) {
			for(Celda celda : celdas) {
				root.getChildren().add(celda.getRender());
			}
		}

		root.setPrefHeight(map.length * 16);
		root.setPrefWidth(map.length * 50);

		BackgroundImage floor = new BackgroundImage(new Image("file:src/main/resources/floor.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		root.setBackground(new Background(floor));
		return root;
	}
	
	public void setEventListeners(Node node) {
		Player p1 = players.get(0);
		node.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				System.exit(0);
			} else if (event.getCode() == KeyCode.R) {
				pantalla.createView();
			}
		});
        node.setOnKeyPressed(e -> {
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
				if(map[i][from.x] != null) {
					target = map[i][from.x];
				}
			}
		} else if (orientacion == Direction.DOWN) {
			for (int i = from.y; i > 0; i++) {
				if(map[i][from.x] != null) {
					target = map[i][from.x];
				}
			}
		} else if (orientacion == Direction.LEFT) {
			for (int i = from.y; i > 0; i--) {
				if(map[from.y][i] != null) {
					target = map[from.y][i];
				}
			}
		} else {
			for (int i = from.y; i > 0; i++) {
				if(map[from.y][i] != null) {
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
		for(Celda[] celdas : map) {
			for(Celda celda : celdas) {
				sb.append(celda + " ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
		
	}
}
