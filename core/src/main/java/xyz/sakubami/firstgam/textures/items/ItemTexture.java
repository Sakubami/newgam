package xyz.sakubami.firstgam.textures.items;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum ItemTexture implements TexturePath {
    SWORD;

    @Override
    public String getPath() {
        return "items/" + this.name().toLowerCase();
    }
}
