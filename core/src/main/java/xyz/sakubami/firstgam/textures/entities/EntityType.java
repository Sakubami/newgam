package xyz.sakubami.firstgam.textures.entities;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum EntityType implements TexturePath {
    PLAYER;

    @Override
    public String getPath() {
        return this.name().toLowerCase();
    }
}
