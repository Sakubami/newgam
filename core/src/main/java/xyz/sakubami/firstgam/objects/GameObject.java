package xyz.sakubami.firstgam.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final TextureRegion texture;

    public GameObject(float width, float height, TextureRegion texture) {
        this.x = 0f;
        this.y = 0f;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    public float getX() { return x; }

    public float getY() { return y; }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public TextureRegion getTexture() { return texture; }

    public Rectangle getBoundingBox() { return new Rectangle(x, y, width, height); }

    public void update(float deltaT) {}
}
