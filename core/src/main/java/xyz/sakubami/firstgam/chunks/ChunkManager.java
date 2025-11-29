package xyz.sakubami.firstgam.chunks;

import xyz.sakubami.firstgam.objects.container.interfaces.Chest;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class ChunkManager {
    private final WorldGenerator generator;
    private final int SIZE = 16;

    private final Map<Vector2i, Chunk> loadedChunks;

    public ChunkManager(WorldGenerator generator) {
        this.generator = generator;
        this.loadedChunks = new HashMap<>();
        loadChunks();
    }

    public Chunk getChunk(int chunkX, int chunkY) {
        Vector2i key = new Vector2i(chunkX, chunkY);
        if (!loadedChunks.containsKey(key)) {
            Chunk chunk = new Chunk(SIZE, key, generator, false);
            loadedChunks.put(key, chunk);
        }
        return loadedChunks.get(key);
    }

    public void loadChunks() {
        Chunk chunk1 = new Chunk(SIZE, new Vector2i(0, 1), generator, false);
        Chest chest = new Chest();
        chunk1.addObject(new Vector2i(7,13), chest);
        loadedChunks.put(new Vector2i(0, 1), chunk1);
        loadedChunks.put(new Vector2i(0,0), new Chunk(SIZE, new Vector2i(0,0), generator, false));
        loadedChunks.put(new Vector2i(1,0), new Chunk(SIZE, new Vector2i(1,0), generator, false));
        loadedChunks.put(new Vector2i(2,0), new Chunk(SIZE, new Vector2i(2,0), generator, false));
        loadedChunks.put(new Vector2i(2,1), new Chunk(SIZE, new Vector2i(2,1), generator, false));
        loadedChunks.put(new Vector2i(1,1), new Chunk(SIZE, new Vector2i(1,1), generator, false));
    }

    public Map<Vector2i, Chunk> getLoadedChunks() {
        return loadedChunks;
    }

    public Chunk getChunkAbsolute(Vector2i vector) {
        return loadedChunks.get(vector);
    }

    /*
    public Chunk getChunkRelative(GameObject object) {
        int tileX = (int)Math.floor(object.x / 32f);
        int tileY = (int)Math.floor(object.y / 32f);

        Vector2i vector = new Vector2i((int)Math.floor(tileX / 16f), (int)Math.floor(tileY / 16f));
        return chunks.get(vector);
    }

     */
}


// handle player position
// load chunks from saved file
// generate chunk if non-existent on loading stage
