package xyz.sakubami.firstgam.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedObject;

public abstract class GameObject implements Serializable<SerializedObject> {
    private int x;
    private int y;
    private float width;
    private float height;
    private final TextureRegion texture;

    public GameObject(float width, float height, TextureRegion texture) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    @Override
    public SerializedObject toData() {
        SerializedObject data = new SerializedObject();
        data.x = x;
        data.y = y;
        data.width = width;
        data.height = height;
        data.type = this.getClass().getSimpleName();
        return data;
    }

    @Override
    public void fromData(SerializedObject data) {
        this.x = data.x;
        this.y = data.y;
        this.width = data.width;
        this.height = data.height;
    }

    public float getX() { return x; }

    public float getY() { return y; }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public TextureRegion getTexture() { return texture; }

    public Rectangle getBoundingBox() { return new Rectangle(x, y, width, height); }

    public void update(float deltaT) {}
}
