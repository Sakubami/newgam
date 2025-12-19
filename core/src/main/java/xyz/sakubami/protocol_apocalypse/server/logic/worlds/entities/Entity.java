package xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.protocol_apocalypse.server.saving.data.Serializable;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.client.rendering.textures.TextureManager;
import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2f;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class Entity implements Serializable<SerializedEntity> {
    private Vector2f pos;
    private final EntityType type;
    private final TextureRegion texture;
    private final String id;
    private UUID uuid;

    public Entity(EntityType textureT, String id) {
        this.pos = new Vector2f(0, 0);
        this.type = textureT;
        this.texture = TextureManager.get().getEntityTexture(textureT);
        this.id = id;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = new SerializedEntity();
        data.pos = pos.toString();
        data.type = type;
        data.id = id;
        data.uuid = uuid;
        return data;
    }

    @Override
    public void readData(SerializedEntity data) {
        this.pos = Vector2f.fromString(data.pos);
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
        obj.readData(data);                // populate fields
        return obj;
    }

    public UUID getUuid() { return this.uuid; }
    public Vector2f getPos() { return pos; }
    public void setPos(Vector2f pos) { this.pos = pos; }
    public TextureRegion getTexture() { return this.texture; }
}
