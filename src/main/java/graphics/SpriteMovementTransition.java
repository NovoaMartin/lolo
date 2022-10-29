package graphics;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteMovementTransition {
    TranslateTransition movement;
    SpriteCycleTransition cycle;

    public SpriteMovementTransition(ImageView render, Duration duration, Image[] sprites) {
        movement = new TranslateTransition(duration, render);
        cycle = new SpriteCycleTransition(duration, render, sprites);
    }

    public void setByX(double x) {
        movement.setByX(x);
    }

    public void setByY(double y) {
        movement.setByY(y);
    }

    public void play() {
        movement.play();
        cycle.play();
    }

    public void setOnFinished(EventHandler<ActionEvent> e) {
        movement.setOnFinished(e);
    }
}
