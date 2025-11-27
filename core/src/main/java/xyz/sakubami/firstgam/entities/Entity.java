package xyz.sakubami.firstgam.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.rendering.EntityRenderer;

import java.util.UUID;

public abstract class Entity {
    private final float x;
    private final float y;
    private final TextureRegion texture;
    private final UUID uuid;

    public Entity(TextureRegion texture) {
        this.x = 0;
        this.y = 0;
        this.texture = texture;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() { return this.uuid; }

    public float getX() { return this.x; }

    public float getY() { return this.y; }

    public TextureRegion getTexture() { return this.texture; }
}
