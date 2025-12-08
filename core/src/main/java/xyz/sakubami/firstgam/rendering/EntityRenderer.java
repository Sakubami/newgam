package xyz.sakubami.firstgam.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.firstgam.chunks.ChunkManager;
import xyz.sakubami.firstgam.entities.Entity;
import xyz.sakubami.firstgam.entities.livingentity.LivingEntity;

import java.util.Map;
import java.util.UUID;

public record EntityRenderer(SpriteBatch batch, ChunkManager chunkmanager, int tileSize) {
    public void render() {
        renderEntities();
    }

    private void renderEntities() {
        for (Map.Entry<UUID, Entity> entry : chunkmanager.getEntities().entrySet()) {
            Entity entity = entry.getValue();
            batch.draw(entity.getTexture(), entity.getX(), entity.getY());
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
