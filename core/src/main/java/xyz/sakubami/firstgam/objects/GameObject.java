package xyz.sakubami.firstgam.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedObject;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.objects.ObjectType;

import java.util.HashMap;
import java.util.function.Supplier;

public abstract class GameObject implements Serializable<SerializedObject> {
    private int x;
    private int y;
    private final float width;
    private final float height;
    private final ObjectType type;
    private final TextureRegion texture;
    private final String id;

    public GameObject(ObjectType textureT, String id) {
        this.x = 0;
        this.y = 0;
        this.id = id;
        this.type = textureT;
        this.texture = TextureManager.get().getObjectTexture(textureT);
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
    }

    @Override
    public SerializedObject toData() {
        SerializedObject data = new SerializedObject();
        data.x = x;
        data.y = y;
        data.type = type;
        data.id = id;
        return data;
    }

    @Override
    public void fromData(SerializedObject data) {
        this.x = data.x;
        this.y = data.y;
    }

    private static final HashMap<ObjectType, Supplier<? extends GameObject>> registry = new HashMap<>();

    public static void registerType(ObjectType type, Supplier<? extends GameObject> constructor) {
        registry.put(type, constructor);
    }

    public static GameObject createFromData(SerializedObject data) {
        Supplier<? extends GameObject> supplier = registry.get(data.type);
        if (supplier == null) {
            throw new RuntimeException("Unknown GameObject type: " + data.type);
        }
        GameObject obj = supplier.get();   // concrete instance
        obj.fromData(data);                // populate fields
        return obj;
    }

    public float getX() { return x; }

    public float getY() { return y; }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public TextureRegion getTexture() { return texture; }

    public Rectangle getBoundingBox() { return new Rectangle(x, y, width, height); }

    public void update(float deltaT) {}
}
