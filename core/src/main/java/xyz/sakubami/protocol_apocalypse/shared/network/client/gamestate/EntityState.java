package xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate;

import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;
import xyz.sakubami.protocol_apocalypse.shared.types.Type;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2f;

import java.util.UUID;

public class EntityState implements State {
    public UUID uuid;
    public boolean remove = false;
    public SerializedEntity entity;
    public String pos;

    public EntityState(boolean remove) {
        this.remove = remove;
    }

    public EntityState(SerializedEntity data) {
        this.uuid = data.uuid;
        this.pos = data.pos;
    }

    public EntityState(SerializedEntity data, boolean remove) {
        this.uuid = data.uuid;
        this.remove = remove;
        this.pos = data.pos;
    }

    @Override public String getPos() { return pos; }
    @Override public Type getType() { return entity.type; }
}
