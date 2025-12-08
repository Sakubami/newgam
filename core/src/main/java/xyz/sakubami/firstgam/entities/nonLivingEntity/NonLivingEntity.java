package xyz.sakubami.firstgam.entities.nonLivingEntity;

import xyz.sakubami.firstgam.entities.Entity;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.entities.EntityType;

public abstract class NonLivingEntity extends Entity {
    private String displayName = "NULL";

    public NonLivingEntity(EntityType textureT, String id) {
        super(textureT, id);
    }

    @Override
    public SerializedEntity toData() {
        SerializedEntity data = super.toData();
        data.displayName = displayName;
        return data;
    }

    public void setName(String displayName) { this.displayName = displayName; }
    public String getName() { return this.displayName; }
}
