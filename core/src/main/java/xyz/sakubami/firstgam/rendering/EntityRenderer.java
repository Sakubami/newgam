package xyz.sakubami.firstgam.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.firstgam.chunks.ChunkManager;
import xyz.sakubami.firstgam.entities.Entity;
import xyz.sakubami.firstgam.entities.livingentity.LivingEntity;
import xyz.sakubami.firstgam.entities.livingentity.Player;
import xyz.sakubami.firstgam.world.WorldManager;

import java.util.*;

public record EntityRenderer(SpriteBatch batch, ChunkManager chunkmanager, int tileSize) {
    public void render() {
        renderEntities();
    }

    private void renderEntities() {
        Map<UUID, Entity> v = new HashMap<>(chunkmanager.getEntities());

        for(Player player : WorldManager.get().getCurrentWorld().getOnlinePlayers()) {
            v.put(player.getUuid(), player);
        }

        List<Entity> sorted = v.values().stream()
            .sorted(Comparator.comparingDouble(Entity::getY).reversed())
            .toList();

        for (Entity entity : sorted) {
            batch.draw(entity.getTexture(), entity.getX(), entity.getY());
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
