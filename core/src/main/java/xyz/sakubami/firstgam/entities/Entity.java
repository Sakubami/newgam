package xyz.sakubami.firstgam.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.rendering.EntityRenderer;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.entities.EntityTexture;

import java.util.UUID;

public abstract class Entity implements Serializable<SerializedEntity> {
    private float x;
    private float y;
    private final EntityTexture textureT;
    private final TextureRegion texture;
    private final String id;
    private final UUID uuid;

    public Entity(EntityTexture textureT, String id) {
        this.x = 0;
        this.y = 0;
        this.textureT = textureT;
        this.texture = TextureManager.get().getEntityTexture(textureT);
        this.id = id;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = new SerializedEntity();
        data.x = x;
        data.y = y;
        data.texture = textureT;
        data.id = id;
        data.uuid = uuid;
        return data;
    }

    @Override
    public void fromData(SerializedEntity data) {
        this.x = data.x;
        this.y = data.y;
    }

    public UUID getUuid() { return this.uuid; }

    public float getX() { return this.x; }

    public float getY() { return this.y; }

    public TextureRegion getTexture() { return this.texture; }
}
