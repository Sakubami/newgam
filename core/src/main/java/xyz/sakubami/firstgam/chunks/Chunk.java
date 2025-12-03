package xyz.sakubami.firstgam.chunks;

import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedChunk;
import xyz.sakubami.firstgam.textures.tiles.TileTexture;
import xyz.sakubami.firstgam.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class Chunk implements Serializable<SerializedChunk> {
    private final int chunkX, chunkY;
    private TileTexture[] tiles;
    private final WorldGenerator generator;
    private final int size;
    private final Map<Vector2i, GameObject> objects;

    public Chunk(int size, Vector2i vector, WorldGenerator generator, boolean skipGeneration) {
        this.chunkX = vector.x();
        this.chunkY = vector.y();
        this.generator = generator;
        this.size = size;
        if (!skipGeneration)
            generateTiles();
        this.objects = new HashMap<>();
    }

    @Override
    public SerializedChunk toData() {
        SerializedChunk data = new SerializedChunk();
        data.chunkX = chunkX;
        data.chunkY = chunkY;
        data.size = size;
        data.tiles = tiles;
        for(Vector2i loc : objects.keySet()) {
            data.objects.put(Vector2i.toString(loc), objects.get(loc).toData());
        }
        return data;
    }

    @Override
    public void fromData(SerializedChunk data) {
        for(String key : data.objects.keySet()) {
            String[] split = key.split("%");
            objects.put(Vector2i.fromString(key), GameObject.createFromData(data.objects.get(key)));
        }
        this.tiles = data.tiles;
    }

    public void generateTiles() {
        TileTexture[] tiles1D = new TileTexture[size * size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int worldX = chunkX * size + x;
                int worldY = chunkY * size + y;
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
    public int getChunkX() { return this.chunkX; }
    public int getChunkY() { return this.chunkY; }
}
