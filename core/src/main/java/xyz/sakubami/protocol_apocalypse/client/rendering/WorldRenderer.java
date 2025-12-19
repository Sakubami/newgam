package xyz.sakubami.protocol_apocalypse.client.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.protocol_apocalypse.client.logic.ClientWorld;
import xyz.sakubami.protocol_apocalypse.client.rendering.textures.TextureManager;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.ChunkState;
import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;
import xyz.sakubami.protocol_apocalypse.shared.types.ObjectType;
import xyz.sakubami.protocol_apocalypse.shared.network.client.gamestate.State;
import xyz.sakubami.protocol_apocalypse.shared.types.Type;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2f;
import xyz.sakubami.protocol_apocalypse.shared.utils.Vector2i;

import java.util.*;

public record WorldRenderer(SpriteBatch batch, ClientWorld world) {

    public void render() {


        List<State> states = new ArrayList<>(world.getEntities().values());

        for (ChunkState state : world.getChunks().values()) {

        }

        List<State> sorted = states.stream()
            .sorted(Comparator.comparing((State state) -> Vector2i.fromString(state.getPos()).y()).reversed())
            .toList();

        for (State state : sorted) {
            Vector2f pos = Vector2f.fromString(state.getPos());
            TextureRegion texture;
            Type type = state.getType();

            if (type instanceof ObjectType)
                texture = TextureManager.get().getObjectTexture((ObjectType) state.getType());
            else
                texture = TextureManager.get().getEntityTexture((EntityType) state.getType());
            batch.draw(texture, pos.x(), pos.y());
        }
    }
}
