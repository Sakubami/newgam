package xyz.sakubami.firstgam.textures;

import xyz.sakubami.firstgam.textures.entities.TallEntityID;
import xyz.sakubami.firstgam.textures.entities.EntityID;
import xyz.sakubami.firstgam.textures.objects.ObjectID;
import xyz.sakubami.firstgam.textures.objects.TallObjectID;
import xyz.sakubami.firstgam.textures.tiles.TileID;

public enum Path {
    ENTITIES_0(EntityID.class, "entities_0.png", 32, 32),
    ENTITIES_1(TallEntityID.class,"entities_1.png", 32, 64),
    OBJECTS_0(ObjectID.class, "objects_0.png", 32, 32),
    OBJECTS_1(TallObjectID.class, "objects_1.png", 32, 64),
    TILES(TileID.class,"tiles.png", 32, 32);

    private final Class<? extends Enum<?>> IDs;
    private final String path;
    private final int width;
    private final int height;

    Path(Class<? extends Enum<?>> IDs, String path, int width, int height) {
        this.IDs = IDs;
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public String getPath() { return this.path; }
    public Class<? extends Enum<?>> getIDs() { return IDs; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
