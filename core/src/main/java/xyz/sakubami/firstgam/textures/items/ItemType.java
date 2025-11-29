package xyz.sakubami.firstgam.textures.items;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum ItemType implements TexturePath {
    SWORD;

    @Override
    public String getPath() {
        return this.name().toLowerCase();
    }
}
