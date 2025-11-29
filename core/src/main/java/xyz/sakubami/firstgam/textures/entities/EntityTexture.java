package xyz.sakubami.firstgam.textures.entities;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum EntityTexture implements TexturePath {
    PLAYER;

    @Override
    public String getPath() {
        return "entities/" + this.name().toLowerCase();
    }
}
