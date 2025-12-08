package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.entities.livingentity.LivingEntity;
import xyz.sakubami.firstgam.textures.tiles.TileTexture;
import xyz.sakubami.firstgam.utils.Vector2i;

import java.util.Map;

public class SerializedChunk {
    public TileTexture[] tiles;
    public int size;
    public Map<String, SerializedObject> objects;

    public SerializedChunk() {}
}
