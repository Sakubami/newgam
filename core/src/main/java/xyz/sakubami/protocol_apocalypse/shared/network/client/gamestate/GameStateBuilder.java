package xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate;

import com.badlogic.gdx.Game;
import xyz.sakubami.protocol_apocalypse.server.logic.chunks.Chunk;
import xyz.sakubami.protocol_apocalypse.server.logic.objects.GameObject;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.Entity;
import xyz.sakubami.protocol_apocalypse.server.saving.data.ChunkBatch;
import xyz.sakubami.protocol_apocalypse.shared.utils.Coordinates;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameStateBuilder {
    private final Map<String, ChunkState> chunks = new HashMap<>();
    private final Map<UUID, EntityState> entities = new HashMap<>();

    public void updateObject(ObjectState object) {
        ChunkState state = chunks.computeIfAbsent(object.pos, k -> new ChunkState());
    }

    public void updateEntity(EntityState entity) {
        entities.put(entity.uuid, entity);
    }

    public GameState build() {
        return new GameState(entities, chunks);
    }
}
