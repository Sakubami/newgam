package xyz.sakubami.firstgam.textures.objects;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum ObjectTexture implements TexturePath {
    CHEST,
    TREE;

    @Override
    public String getPath() {
        return "objects/" + this.name().toLowerCase();
    }
}
