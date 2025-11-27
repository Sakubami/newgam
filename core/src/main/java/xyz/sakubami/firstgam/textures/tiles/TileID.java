package xyz.sakubami.firstgam.textures.tiles;

public enum TileID {
    GRASS(0),
    DIRT(1),
    STONE(2);

    private final int id;

    TileID(int id) {
        this.id = id;
    }

    public int getID() { return id; }
}
