package xyz.sakubami.firstgam.entities.livingentity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.entities.Entity;

public abstract class LivingEntity extends Entity {
    public LivingEntity(TextureRegion texture) {
        super(texture);
    }
}
