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

    int imageNumber = 0;

    @Override
    protected void interpolate(double frac) {
        if (i++ % 2 == 0) {
            imageNumber = (imageNumber + 1) % sprites.length;
            this.image.setImage(sprites[imageNumber]);
        }
    }
}
