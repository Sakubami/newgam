package xyz.sakubami.protocol_apocalypse.client.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import xyz.sakubami.protocol_apocalypse.client.logic.ClientWorld;
import xyz.sakubami.protocol_apocalypse.client.rendering.textures.TextureManager;
import xyz.sakubami.protocol_apocalypse.server.logic.chunks.ChunkManager;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.Entity;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity.Player;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.WorldManager;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.EntityState;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2f;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

import java.util.*;

public record EntityRenderer(SpriteBatch batch, ClientWorld world) {

    public void render() {
        renderEntities();
    }

    private void renderEntities() {
        Map<UUID, EntityState> v = new HashMap<>(world.getEntities());

        List<EntityState> sorted = v.values().stream()
            .sorted(Comparator.comparing((EntityState e) -> Vector2f.fromString(e.entity.pos).y()).reversed())
            .toList();

        for (EntityState entity : sorted) {
            Vector2f pos = Vector2f.fromString(entity.entity.pos);
            batch.draw(TextureManager.get().getEntityTexture(entity.type), pos.x(), pos.y());
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
