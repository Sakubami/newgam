package xyz.sakubami.protocol_apocalypse.server.logic.chunks;

import com.google.gson.Gson;
import xyz.sakubami.protocol_apocalypse.server.saving.Saviour;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.Entity;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity.Player;
import xyz.sakubami.protocol_apocalypse.server.saving.data.ChunkBatch;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedChunk;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.shared.utils.Coordinates;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.World;

import java.util.*;

/**
 * handles chunk loading, updating and unloading
 */
public class ChunkManager {
    private final Gson gson = new Gson();
    private final WorldGenerator generator;
    private final int CHUNK_SIZE = 16;
    private final int BATCH_SIZE = 3;

    private final Map<Vector2i, Chunk> chunks;
    private final Map<Vector2i, ChunkBatch> batches;
    private final Map<UUID, Entity> entities;

    public ChunkManager(WorldGenerator generator) {
        this.generator = generator;
        this.chunks = new HashMap<>();
        this.batches = new HashMap<>();
        this.entities = new HashMap<>();
    }

    public void shutdown() {
        for(Vector2i v : batches.keySet()) {
            unloadBatch(v);
            System.out.println("SHUTDOWN");
        }
    }

    /**
     * should load the chunkbatches around every player connected to the world
     */
    public void handleBatches(World world) {
        Set<Vector2i> loadQueue = new HashSet<>();
        Set<Vector2i> unloadQueue = new HashSet<>(batches.keySet());
        List<Player> players = world.getOnlinePlayers();

        Set<Vector2i> occupiedBatches = new HashSet<>();
        for (Player player : players) {
            occupiedBatches.add(Coordinates.getChunkBatch(player));
        }

        for(Vector2i currentBatch : occupiedBatches) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    Vector2i neighbor = new Vector2i(currentBatch.x() + dx, currentBatch.y() + dy);
                    loadQueue.add(neighbor);
                    unloadQueue.remove(neighbor);
                }
            }
        }

        for (Vector2i pos : loadQueue) {
            if (!batches.containsKey(pos)) {
                System.out.println("loading + " + pos);
                loadBatch(world, pos);
            }
        }

        for (Vector2i pos : unloadQueue) {
            unloadBatch(pos);
        }
    }

    /**
     * just loads chunkBatches with the given location ---> name
     * puts empty chunks inside the batch on generation until saving for performace reasons
     * @param loc
     */
    private void loadBatch(World world, Vector2i loc) {
        String path = world.getDirectory() + "/batches/" + loc.toString();

        ChunkBatch batch = Saviour.get().loadData(path, ChunkBatch.class);
        if (batch == null)
            batch = new ChunkBatch();

        if (batch.chunks.isEmpty()) {
            int chunkX = loc.x() * 3;
            int chunkY = loc.y() * 3;
            for(int i = chunkX; i <= chunkX + 2; i++) {
                for(int i2 = chunkY; i2 <= chunkY + 2; i2++) {
                    Vector2i v = new Vector2i(i, i2);
                    Chunk chunk = new Chunk(CHUNK_SIZE);
                    batch.chunks.put(v.toString(), chunk.toData());
                    chunk.generateTiles(generator, v);
                    this.chunks.put(v, chunk);
                }
            }
        } else {
            for (Map.Entry<String, SerializedChunk> entry : batch.chunks.entrySet()) {
                Vector2i chunkPos = Vector2i.fromString(entry.getKey());
                Chunk chunk = new Chunk(CHUNK_SIZE);
                chunk.readData(entry.getValue());
                this.chunks.put(chunkPos, chunk);
            }
        }

        if (!batch.entities.isEmpty())
            for (SerializedEntity data : batch.entities.values()) {
                Entity entity = Entity.createFromData(data);
                entities.put(entity.getUuid(), entity);
            }


        batch.entities = new HashMap<>();
        this.batches.put(loc, batch);
    }

    /**
     * should handle the unloading and saving of the chunks correctly
     * im keeping the loadedchunks in a separate list for uhm reasons
     * @param loc position of the batch
     */
    private void unloadBatch(Vector2i loc) {
        ChunkBatch newBatch = new ChunkBatch();
        newBatch.chunks = new HashMap<>();
        newBatch.entities = new HashMap<>();
        newBatch.name = loc.toString();

        for (String v : batches.get(loc).chunks.keySet()) {
            Vector2i chunkPos = Vector2i.fromString(v);
            Chunk chunk = chunks.get(chunkPos);
            newBatch.chunks.put(chunkPos.toString(), chunk.toData());
            chunks.remove(chunkPos);
        }

        for (Map.Entry<UUID, Entity> entry : entities.entrySet()) {
            Vector2i pos = Coordinates.getChunkBatch(entry.getValue());
            Vector2i distance = pos.subtract(loc);
            if  (distance.x() > 0 || distance.y() > 0)
                continue;
            newBatch.entities.put(entry.getKey(), entry.getValue().toData());
            entities.remove(entry.getKey());
        }

        Saviour.get().saveWorldData(newBatch);
        batches.remove(loc);
    }

    public Map<Vector2i, Chunk> getChunks() { return chunks; }
    public Map<UUID, Entity> getEntities() { return this.entities; }

}


// handle player position
// load chunks from saved file
// generate chunk if non-existent on loading stage
