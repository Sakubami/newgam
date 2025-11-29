package xyz.sakubami.firstgam.textures.tiles;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum TileTexture implements TexturePath {
    GRASS,
    SNOW,
    WATER;

    @Override
    public String getPath() {
        return this.name().toLowerCase();
    }
}
