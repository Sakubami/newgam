package xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate;

import xyz.sakubami.protocol_apocalypse.server.logic.chunks.Chunk;
import xyz.sakubami.protocol_apocalypse.server.logic.objects.GameObject;
import xyz.sakubami.protocol_apocalypse.server.saving.data.ChunkBatch;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedChunk;
import xyz.sakubami.protocol_apocalypse.shared.types.TileType;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class ChunkState {
    public boolean remove;
    private TileType[] tiles;
    private Map<String, ObjectState> objects = new HashMap<>();

    public ChunkState(Chunk chunk) {
        this.tiles = chunk.getTiles();
        for (Map.Entry<Vector2i, GameObject> entry : chunk.getObjects().entrySet()) {
            this.objects.put(entry.getKey().toString(), new ObjectState(entry.getValue()));
        }
    }

    public ChunkState(Chunk chunk, boolean remove) {
        this.tiles = chunk.getTiles();
        this.remove = remove;
        for (Map.Entry<Vector2i, GameObject> entry : chunk.getObjects().entrySet()) {
            this.objects.put(entry.getKey().toString(), new ObjectState(entry.getValue(), remove));
        }
    }

    public ChunkState() {

    }
}
