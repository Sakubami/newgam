package xyz.sakubami.protocol_apocalypse.client.logic;

import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.ChunkState;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.EntityState;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.GameState;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientWorld {
    private final Map<UUID, EntityState> entities = new HashMap<>();
    private final Map<Vector2i, ChunkState> chunks = new HashMap<>();

    public void applyState(GameState state) {
        for (Map.Entry<UUID, EntityState> entry : state.entities.entrySet()) {
            if (entry.getValue().remove) {
                entities.remove(entry.getKey());
                continue;
            }
            entities.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, ChunkState> entry : state.chunks.entrySet()) {
            Vector2i pos = Vector2i.fromString(entry.getKey());
            if (entry.getValue().remove) {
                chunks.remove(pos);
                continue;
            }
            chunks.put(pos, entry.getValue());
        }
    }

    public Map<UUID, EntityState> getEntities() { return  entities; }
    public Map<Vector2i, ChunkState> getChunks() { return chunks; }
}
