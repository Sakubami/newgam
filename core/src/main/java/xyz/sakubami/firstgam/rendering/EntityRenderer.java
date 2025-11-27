package xyz.sakubami.firstgam.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.firstgam.chunks.Chunk;
import xyz.sakubami.firstgam.chunks.ChunkManager;

public record EntityRenderer(SpriteBatch batch, ChunkManager chunkLoader, int tileSize) {

    public void render() {
        for (Chunk chunk : chunkLoader.getLoadedChunks().values()) {
            renderChunk(chunk);
        }
    }

    private void renderChunk(Chunk chunk) {

    }

    public void dispose() {
        batch.dispose();
    }
}
