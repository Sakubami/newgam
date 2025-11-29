package xyz.sakubami.firstgam.entities.livingentity;

import xyz.sakubami.firstgam.entities.Entity;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.entities.EntityType;

public abstract class LivingEntity extends Entity {
    public LivingEntity(EntityType texture, String id) {
        super(texture, id);
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = super.toData();
        return data;
    }
}
