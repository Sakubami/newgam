package xyz.sakubami.firstgam.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.entities.EntityType;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class Entity implements Serializable<SerializedEntity> {
    private float x;
    private float y;
    private final EntityType type;
    private final TextureRegion texture;
    private final String id;
    private UUID uuid;

    public Entity(EntityType textureT, String id) {
        this.x = 0;
        this.y = 0;
        this.type = textureT;
        this.texture = TextureManager.get().getEntityTexture(textureT);
        this.id = id;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = new SerializedEntity();
        data.x = x;
        data.y = y;
        data.type = type;
        data.id = id;
        data.uuid = uuid;
        return data;
    }

    @Override
    public void fromData(SerializedEntity data) {
        this.x = data.x;
        this.y = data.y;
        this.uuid = data.uuid;
    }

    private static final HashMap<EntityType, Supplier<? extends Entity>> registry = new HashMap<>();

    public static void registerType(EntityType type, Supplier<? extends Entity> constructor) {
        registry.put(type, constructor);
    }

    public static Entity createFromData(SerializedEntity data) {
        Supplier<? extends Entity> supplier = registry.get(data.type);
        if (supplier == null) {
            throw new RuntimeException("Unknown GameObject type: " + data.type);
        }
        Entity obj = supplier.get();   // concrete instance
        obj.fromData(data);                // populate fields
        return obj;
    }

    public UUID getUuid() { return this.uuid; }

    public float getX() { return this.x; }

    public void setX(float x) { this.x = x; }

    public float getY() { return this.y; }

    public void setY(float y) { this.y = y; }

    public TextureRegion getTexture() { return this.texture; }
}
