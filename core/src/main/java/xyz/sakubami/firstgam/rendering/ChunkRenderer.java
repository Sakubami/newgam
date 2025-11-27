package xyz.sakubami.firstgam.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.utils.Vector2i;
import xyz.sakubami.firstgam.chunks.Chunk;
import xyz.sakubami.firstgam.chunks.ChunkManager;

public record ChunkRenderer(SpriteBatch batch, ChunkManager chunkLoader, int tileSize) {

    public void render() {
        for (Chunk chunk : chunkLoader.getLoadedChunks().values()) {
            renderChunk(chunk);
        }
    }

    private void renderChunk(Chunk chunk) {
        int[] tiles = chunk.getTiles();

        for (int y = 0; y < chunk.getSize(); y++) {
            for (int x = 0; x < chunk.getSize(); x++) {
                int tileID = tiles[x + y * chunk.getSize()];

                float worldX = (chunk.getChunkX() * chunk.getSize() + x) * tileSize;
                float worldY = (chunk.getChunkY() * chunk.getSize() + y) * tileSize;

                batch.draw(TextureManager.get().getTiles()[tileID], worldX, worldY);
                GameObject object = chunk.getObjectAt(new Vector2i(x, y));
                if (object != null)
                    batch.draw(object.getTexture(), worldX, worldY);
            }
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
