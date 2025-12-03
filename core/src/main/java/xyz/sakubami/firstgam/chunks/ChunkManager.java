package xyz.sakubami.firstgam.chunks;

import com.google.gson.Gson;
import xyz.sakubami.firstgam.entities.livingentity.Player;
import xyz.sakubami.firstgam.objects.container.interfaces.Chest;
import xyz.sakubami.firstgam.saving.ChunkBatch;
import xyz.sakubami.firstgam.utils.Vector2i;
import xyz.sakubami.firstgam.world.World;
import xyz.sakubami.firstgam.world.WorldManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChunkManager {
    private final WorldGenerator generator;
    private final int CHUNK_SIZE = 16;
    private final int BATCH_SIZE = 3;
    private World world;

    private final Map<Vector2i, Chunk> loadedChunks;
    private final Map<Vector2i, ChunkBatch> loadedBatches;

    public ChunkManager(WorldGenerator generator) {
        System.out.println("ChunkManagerInit: " + System.currentTimeMillis());
        this.generator = generator;
        this.loadedChunks = new HashMap<>();
        this.loadedBatches = new HashMap<>();
        this.world = WorldManager.get().getCurrentWorld();
        loadChunks();
    }

    public void update() {
        handleBatches();
    }

    private void loadChunks() {
        Chunk chunk1 = new Chunk(CHUNK_SIZE, new Vector2i(0, 1), generator, false);
        Chest chest = new Chest();
        chunk1.addObject(new Vector2i(7,13), chest);
        loadedChunks.put(new Vector2i(0, 1), chunk1);
        loadedChunks.put(new Vector2i(0,0), new Chunk(CHUNK_SIZE, new Vector2i(0,0), generator, false));
        loadedChunks.put(new Vector2i(1,0), new Chunk(CHUNK_SIZE, new Vector2i(1,0), generator, false));
        loadedChunks.put(new Vector2i(2,0), new Chunk(CHUNK_SIZE, new Vector2i(2,0), generator, false));
        loadedChunks.put(new Vector2i(2,1), new Chunk(CHUNK_SIZE, new Vector2i(2,1), generator, false));
        loadedChunks.put(new Vector2i(1,1), new Chunk(CHUNK_SIZE, new Vector2i(1,1), generator, false));
    }

    private void updateChunksAroundPlayers() {

    }

    private void handleBatches() {
        List<Player> players = world.getOnlinePlayers();
        for(Player player : players) {
            int batchX = (int) Math.floor(((player.getX() / world.getTileSize()) / CHUNK_SIZE) / BATCH_SIZE);
            int batchY = (int) Math.floor(((player.getY() / world.getTileSize()) / CHUNK_SIZE) / BATCH_SIZE);

            Vector2i batchLocation = new Vector2i(batchX, batchY);

            if (!loadedBatches.containsKey(batchLocation)) {
                loadBatch(batchLocation);
            }

            int i = 0;
            for (Vector2i loc : loadedBatches.keySet()) {
                System.out.println(loc.x() + " <- X and Y -> " + loc.y() + " increment: " + i);
                i++;
                if (loc.x() - batchX > 1 || loc.y() - batchY > 1) {
                    unloadBatch(loc);
                }
            }
        }
    }

    private void loadBatch(Vector2i location) {
        
    }

    private void unloadBatch(Vector2i loc) {
        Path path = Paths.get("batches");

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        try(Writer writer = new FileWriter("batches/" + loc.x() + "%" + loc.y())) {
            gson.toJson(loadedBatches.get(loc), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadedBatches.remove(loc);
    }

    public Map<Vector2i, Chunk> getLoadedChunks() {
        return loadedChunks;
    }

    public Chunk getChunkAbsolute(Vector2i vector) {
        return loadedChunks.get(vector);
    }
}


// handle player position
// load chunks from saved file
// generate chunk if non-existent on loading stage
