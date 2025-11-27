package xyz.sakubami.firstgam.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.textures.entities.EntityID;
import xyz.sakubami.firstgam.textures.entities.TallEntityID;
import xyz.sakubami.firstgam.textures.objects.ObjectID;
import xyz.sakubami.firstgam.textures.objects.TallObjectID;
import xyz.sakubami.firstgam.textures.tiles.TileID;

public class TextureManager {

    private static final TextureManager instance = new TextureManager();
    public static TextureManager get() { return instance; }

    private final Path[] paths = Path.values();
    private TextureRegion[] tiles;
    private TextureRegion[] objects_0;
    private TextureRegion[] objects_1;
    private TextureRegion[] entities_0;
    private TextureRegion[] entities_1;

    private TextureManager() {
        loadTextures();
    }

    private void loadTextures() {
        for (Path path : paths) {
            Texture texture = new Texture(path.getPath());
            TextureRegion[][] split = TextureRegion.split(texture, path.getWidth(), path.getHeight());

            int rows = split.length;
            int cols = split[0].length;
            TextureRegion[] textureRegions = new TextureRegion[rows * cols];

            for (int y = 0; y < rows; y++) {
                System.arraycopy(split[y], 0, textureRegions, y * cols, cols);
            }

            switch (path.getPath()) {
                case "tiles.png" : {
                    this.tiles = textureRegions;
                    break;
                }
                case "objects_0.png" : {
                    this.objects_0 = textureRegions;
                    break;
                }
                case "objects_1.png" : {
                    this.objects_1 = textureRegions;
                    break;
                }
                case "entities_0.png" : {
                    this.entities_0 = textureRegions;
                    break;
                }
                case "entities_1.png" : {
                    this.entities_1 = textureRegions;
                    break;
                }
            }
        }
    }

    public TextureRegion[] getTiles() { return this.tiles; }
    public TextureRegion[] getObjects() { return this.objects_0; }
    public TextureRegion[] getTallObjects() { return this.objects_1; }
    public TextureRegion[] getEntities() { return this.entities_0; }
    public TextureRegion[] getTallEntities() { return this.entities_1; }

    public TextureRegion getTile(TileID id) { return this.tiles[id.getID()]; }
    public TextureRegion getObject(ObjectID id) { return this.objects_0[id.getId()]; }
    public TextureRegion getTallObject(TallObjectID id) { return this.objects_1[id.getId()]; }
    public TextureRegion getEntity(EntityID id) { return entities_0[id.getId()]; }
    public TextureRegion getTallEntity(TallEntityID id) { return entities_1[id.getId()]; }
}
