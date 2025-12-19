package xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameState {
    public Map<UUID, EntityState> entities = new HashMap<>();
    public Map<String, ChunkState> chunks = new HashMap<>();

    public GameState(Map<UUID, EntityState> entities, Map<String, ChunkState> chunks) {
        entities = entities;
        chunks = chunks;
    }
}
