package xyz.sakubami.firstgam.entities.livingentity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.entities.Entity;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.entities.EntityTexture;

import java.io.Serial;
import java.util.UUID;

public abstract class LivingEntity extends Entity {
    public LivingEntity(EntityTexture texture, String id) {
        super(texture, id);
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = super.toData();
        return data;
    }
}
