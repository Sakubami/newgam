package xyz.sakubami.firstgam.utils;

import xyz.sakubami.firstgam.chunks.Chunk;
import xyz.sakubami.firstgam.entities.Entity;

public class Coordinates {
    private static final int TILE_SIZE = 32;
    private static final int CHUNK_SIZE = 16;
    private static final int BATCH_SIZE = 3;

    public static Vector2i getChunkBatch(Entity entity) {
        int batchX = (int) Math.floor(((entity.getX() / TILE_SIZE) / CHUNK_SIZE) / BATCH_SIZE);
        int batchY = (int) Math.floor(((entity.getY() / TILE_SIZE) / CHUNK_SIZE) / BATCH_SIZE);
        return new Vector2i(batchX, batchY);
    }
}
