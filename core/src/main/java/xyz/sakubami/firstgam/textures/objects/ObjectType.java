package xyz.sakubami.firstgam.textures.objects;

import xyz.sakubami.firstgam.textures.TexturePath;

public enum ObjectType implements TexturePath {
    CHEST,
    TREE;

    @Override
    public String getPath() {
        return this.name().toLowerCase();
    }
}
