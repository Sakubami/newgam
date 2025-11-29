package xyz.sakubami.firstgam.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedObject;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.objects.ObjectTexture;

public abstract class GameObject implements Serializable<SerializedObject> {
    private int x;
    private int y;
    private final float width;
    private final float height;
    private final ObjectTexture textureT;
    private final TextureRegion texture;
    private final String id;

    public GameObject(ObjectTexture textureT, String id) {
        this.x = 0;
        this.y = 0;
        this.id = id;
        this.textureT = textureT;
        this.texture = TextureManager.get().getObjectTexture(textureT);
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
    }

    @Override
    public SerializedObject toData() {
        SerializedObject data = new SerializedObject();
        data.x = x;
        data.y = y;
        data.texture = textureT;
        data.id = id;
        return data;
    }

    @Override
    public void fromData(SerializedObject data) {
        this.x = data.x;
        this.y = data.y;
    }

    public float getX() { return x; }

    public float getY() { return y; }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public TextureRegion getTexture() { return texture; }

    public Rectangle getBoundingBox() { return new Rectangle(x, y, width, height); }

    public void update(float deltaT) {}
}
