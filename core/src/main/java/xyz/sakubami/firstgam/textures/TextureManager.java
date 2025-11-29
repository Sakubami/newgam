package xyz.sakubami.firstgam.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.textures.entities.EntityType;
import xyz.sakubami.firstgam.textures.items.ItemType;
import xyz.sakubami.firstgam.textures.objects.ObjectType;
import xyz.sakubami.firstgam.textures.tiles.TileTexture;

import java.util.EnumMap;

public class TextureManager {
    private static final TextureManager instance = new TextureManager();
    public static TextureManager get() { return instance; }

    private final TextureAtlas atlas;

    private final EnumMap<ItemType, TextureRegion> items = new EnumMap<>(ItemType.class);
    private final EnumMap<TileTexture, TextureRegion> tiles = new EnumMap<>(TileTexture.class);
    private final EnumMap<ObjectType, TextureRegion> objects = new EnumMap<>(ObjectType.class);
    private final EnumMap<EntityType, TextureRegion> entities = new EnumMap<>(EntityType.class);

    public TextureManager() {
        atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));

        loadEnum(ItemType.values(), items);
        loadEnum(ObjectType.values(), objects);
        loadEnum(TileTexture.values(), tiles);
        loadEnum(EntityType.values(), entities);
    }

    private <E extends Enum<E> & TexturePath> void loadEnum(E[] values, EnumMap<E, TextureRegion> map) {
        for (E type : values) {
            String path = type.getPath();
            TextureRegion region = atlas.findRegion(path);

            if (region == null) {
                Gdx.app.error("TextureManager", "Texture not found: " + path);
            }

            map.put(type, region);
        }
    }

    public TextureRegion getItemTexture(ItemType texture) { return this.items.get(texture); }
    public TextureRegion getTileTexture(TileTexture texture) { return this.tiles.get(texture); }
    public TextureRegion getObjectTexture(ObjectType texture) { return this.objects.get(texture); }
    public TextureRegion getEntityTexture(EntityType texture) { return this.entities.get(texture); }
}
