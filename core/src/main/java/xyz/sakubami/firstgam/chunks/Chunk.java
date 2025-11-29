package xyz.sakubami.firstgam.chunks;

import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.utils.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class Chunk {
    private final int chunkX, chunkY;
    private final int[] tiles;
    private final WorldGenerator generator;
    private final int size;
    private final Map<Vector2i, GameObject> objects = new HashMap<>();

    public Chunk(int size, Vector2i vector, WorldGenerator generator) {
        this.chunkX = vector.x();
        this.chunkY = vector.y();
        this.generator = generator;
        this.size = size;
        this.tiles = generateTiles();
    }

    public int[] generateTiles() {
        int[] tiles1D = new int[size * size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int worldX = chunkX * size + x;
                int worldY = chunkY * size + y;
                double value = generator.getNoise(worldX * 0.8, worldY * 0.8);
                TileID tile;
                if (value < 0) tile = TileID.STONE;
                else if (value < 0.2) tile = TileID.GRASS;
                else tile = TileID.DIRT;
                tiles1D[x + y * size] = tile.getID();
            }
        }
        return tiles1D;
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

    public int[] getTiles() { return this.tiles; }
    public int getSize() { return this.size; }
    public int getChunkX() { return this.chunkX; }
    public int getChunkY() { return this.chunkY; }
}
