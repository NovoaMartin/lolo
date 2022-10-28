package graphics;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteCycleTransition extends Transition {
    int i = 0;
    Image[] sprites;
    ImageView image;

    public SpriteCycleTransition(Duration duration, ImageView image, Image[] sprites) {
        setCycleDuration(duration);
        this.image = image;
        this.sprites = sprites;
    }

    @Override
    protected void interpolate(double frac) {
        image.setImage(sprites[i++ % sprites.length]);
    }
}
