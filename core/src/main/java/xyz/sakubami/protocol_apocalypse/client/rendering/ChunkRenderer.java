package xyz.sakubami.protocol_apocalypse.client.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.protocol_apocalypse.server.logic.objects.GameObject;
import xyz.sakubami.protocol_apocalypse.client.rendering.textures.TextureManager;
import xyz.sakubami.protocol_apocalypse.shared.types.TileType;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;
import xyz.sakubami.protocol_apocalypse.server.logic.chunks.Chunk;
import xyz.sakubami.protocol_apocalypse.server.logic.chunks.ChunkManager;

public record ChunkRenderer(SpriteBatch batch, ChunkManager chunkManager, int tileSize) {

    public void render() {
        for (Vector2i pos : chunkManager.getChunks().keySet()) {
            renderChunk(pos);
        }
    }

    private void renderChunk(Vector2i pos) {
        Chunk chunk = chunkManager.getChunks().get(pos);

        for (int y = 0; y < chunk.getSize(); y++) {
            for (int x = 0; x < chunk.getSize(); x++) {
                TileType tile = chunk.getTiles()[x + y * chunk.getSize()];

                int worldX = (pos.x() * chunk.getSize() + x) * tileSize;
                int worldY = (pos.y() * chunk.getSize() + y) * tileSize;

                batch.draw(TextureManager.get().getTileTexture(tile), worldX, worldY);
                GameObject object = chunk.getObjectAt(new Vector2i(x, y));
                if (object != null)
                    batch.draw(TextureManager.get().getObjectTexture(object.getType()), worldX, worldY);
            }
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
