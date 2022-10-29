package character;

import java.util.Map;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Celda;
import utils.Constants;
import utils.Direction;
import utils.Interactable;

public class Player extends Character implements Interactable {

	private boolean isMoving = false;
	private boolean isAttacking = false;
	private int keys = 0;
	
	private int facingDir;
	private TranslateTransition animacion;
	private ImageView shot;

	private static final Map<Integer, Image[]> sprites = Map.of(Direction.UP,
			new Image[] { new Image("file:src/main/resources/character/up1.png"),
					new Image("file:src/main/resources/character/up2.png"),
					new Image("file:src/main/resources/character/up3.png"),
					new Image("file:src/main/resources/character/up4.png"),
					new Image("file:src/main/resources/character/up5.png"), },
			Direction.DOWN,
			new Image[] { new Image("file:src/main/resources/character/down1.png"),
					new Image("file:src/main/resources/character/down2.png"),
					new Image("file:src/main/resources/character/down3.png"),
					new Image("file:src/main/resources/character/down4.png"),
					new Image("file:src/main/resources/character/down5.png"), },
			Direction.LEFT,
			new Image[] { new Image("file:src/main/resources/character/left1.png"),
					new Image("file:src/main/resources/character/left2.png"),
					new Image("file:src/main/resources/character/left3.png"),
					new Image("file:src/main/resources/character/left4.png"),
					new Image("file:src/main/resources/character/left5.png"), },
			Direction.RIGHT,
			new Image[] { new Image("file:src/main/resources/character/right1.png"),
					new Image("file:src/main/resources/character/right2.png"),
					new Image("file:src/main/resources/character/right3.png"),
					new Image("file:src/main/resources/character/right4.png"),
					new Image("file:src/main/resources/character/right5.png"), });

	public Player(int i, int j, int vidas, int from, int to) {
		super(i, j, vidas, from, to, "lolo");
		image.toFront();
		shot = new ImageView(new Image("file:src/main/resources/Enemies&Objects.png", 128*Constants.MULTIPLIER, 592*Constants.MULTIPLIER, false, false));
		shot.setViewport(new Rectangle2D(32*Constants.MULTIPLIER, 64*Constants.MULTIPLIER, Constants.IMAGE_SIZE, Constants.IMAGE_SIZE));
        shot.setX(j * Constants.IMAGE_SIZE);
        shot.setY(i * Constants.IMAGE_SIZE);
		shot.setVisible(false);
	}

	public boolean isMoving() {
		return isMoving;
	}

	public int facingDir() {
		return facingDir;
	}

	@Override
	public void recibirDanio(String enemigo) {
		super.recibirDanio(enemigo);
		image.setEffect(new javafx.scene.effect.ColorAdjust(0, 0, -0.5, 0));
		new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			image.setEffect(null);
		}).start();
	}

	public void atacar(Celda target) {

		if (isAttacking) {
			return;
		}
		isAttacking = true;
		int distance = distance(target);
		shot.setX(j * Constants.IMAGE_SIZE);
        shot.setY(i * Constants.IMAGE_SIZE);
        shot.setVisible(true);
        TranslateTransition animation = new TranslateTransition(Constants.MAGIC_ANIMATION_DURATION.multiply(distance), shot);
        
		if (facingDir == Direction.UP) {
			animation.setByY(-(distance * Constants.IMAGE_SIZE));
		} else if (facingDir == Direction.DOWN) {
			animation.setByY((distance * Constants.IMAGE_SIZE));
		} else if (facingDir == Direction.LEFT) {
			animation.setByX(-(distance * Constants.IMAGE_SIZE));
		} else if (facingDir == Direction.RIGHT) {
			animation.setByX((distance * Constants.IMAGE_SIZE));
		}
        animation.play();
        animation.setOnFinished(e -> {
            shot.setVisible(false);
            target.visible(false);
            isAttacking = false;
        });
	}

	public void takeKey() {
		this.keys++;
	}

	public int Keys() {
		return keys;
	}

	@Override
	public Node getRender() {
		return new Group(shot, image);
	}

	@Override
	public void tryMove(Celda pos, int dir) {
		facingDir = dir;
		if(isMoving) {
			return;
		}
		isMoving = true;
		animacion = new TranslateTransition(Constants.MOVEMENT_ANIMATION_DURATION, image);
		Transition imageRotation = new Transition() {
			{
				setCycleDuration(Constants.MOVEMENT_ANIMATION_DURATION);
			}

			@Override
			protected void interpolate(double frac) {
//                image.setImage(sprites.get(orientacion)[i++ % 5]);
			}
		};
		imageRotation.setOnFinished(e -> image.setImage(image.getImage()));
		animacion.setOnFinished(e -> {
			isMoving = false;
			this.j = pos.j;
			this.i = pos.i;
		});
		if (dir == Direction.UP) {
			animacion.setByY(-Constants.IMAGE_SIZE);
		} else if (dir == Direction.DOWN) {
			animacion.setByY(Constants.IMAGE_SIZE);
		} else if (dir == Direction.LEFT) {
			animacion.setByX(-Constants.IMAGE_SIZE);
		} else if (dir == Direction.RIGHT) {
			animacion.setByX(Constants.IMAGE_SIZE);
		}
		animacion.play();
		imageRotation.play();
	}
}
