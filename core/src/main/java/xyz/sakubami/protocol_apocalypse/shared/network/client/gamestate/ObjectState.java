package xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate;

import xyz.sakubami.protocol_apocalypse.server.logic.objects.GameObject;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedObject;
import xyz.sakubami.protocol_apocalypse.shared.types.Type;

import java.util.UUID;

public class ObjectState implements State{
    public SerializedObject object;
    public boolean remove = false;
    public String pos;

    public ObjectState(GameObject data) {
        this.pos = data.getPos().toString();
    }

    public ObjectState(GameObject data, boolean remove) {
        this.remove = remove;
        this.pos = data.getPos().toString();
    }

    @Override public String getPos() { return pos; }
    @Override public Type getType() { return object.type; }
}
