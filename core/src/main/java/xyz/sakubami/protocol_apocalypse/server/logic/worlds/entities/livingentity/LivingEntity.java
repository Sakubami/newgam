package xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity;

import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.Entity;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

public abstract class LivingEntity extends Entity {
    private String displayName = "NULL";

    public LivingEntity(EntityType textureT, String id) {
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
    public Vector2i getTilePos() {
        int x = (int) Math.floor(super.getX());
        int y = (int) Math.floor(super.getY());
        return new Vector2i(x / 32, y / 32);
    }
}
