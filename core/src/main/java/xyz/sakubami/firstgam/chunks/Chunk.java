package xyz.sakubami.firstgam.chunks;

import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedChunk;
import xyz.sakubami.firstgam.textures.tiles.TileTexture;
import xyz.sakubami.firstgam.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Chunk implements Serializable<SerializedChunk> {
    private TileTexture[] tiles;
    private final int size;
    private final Map<Vector2i, GameObject> objects;

    public Chunk(int size) {
        this.size = size;
        this.objects = new HashMap<>();
    }

    @Override
    public SerializedChunk toData() {
        SerializedChunk data = new SerializedChunk();
        data.size = size;
        data.tiles = tiles;
        for(Vector2i loc : objects.keySet()) {
            data.objects.put(loc.toString(), objects.get(loc).toData());
        }
        return data;
    }

    @Override
    public void fromData(SerializedChunk data) {
        this.tiles = data.tiles;
        if (data.objects == null)
            return;
        for(String key : data.objects.keySet()) {
            String[] split = key.split("%");
            objects.put(Vector2i.fromString(key), GameObject.createFromData(data.objects.get(key)));
        }

    }

    public void generateTiles(WorldGenerator generator, Vector2i pos) {
        TileTexture[] tiles1D = new TileTexture[size * size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int worldX = pos.x() * size + x;
                int worldY = pos.y() * size + y;
                double value = generator.getNoise(worldX * 0.8, worldY * 0.8);
                TileTexture tile;
                if (value < 0) tile = TileTexture.WATER;
                else if (value < 0.2) tile = TileTexture.GRASS;
                else tile = TileTexture.SNOW;
                tiles1D[x + y * size] = tile;
            }
        }
        this.tiles = tiles1D;
    }

    public void addObject(Vector2i pos, GameObject object) {
        this.objects.put(pos, object);
    }

    public void removeObject(Vector2i pos) {
        this.objects.remove(pos);
    }

    public GameObject getObjectAt(Vector2i pos) {
        return this.objects.get(pos);
    }

    public Map<Vector2i, GameObject> getObjects() {
        return this.objects;
    }

    public TileTexture[] getTiles() { return this.tiles; }
    public int getSize() { return this.size; }
}
