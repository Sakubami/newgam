package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.textures.entities.EntityTexture;

import java.util.UUID;

public class SerializedEntity {
    public float x;
    public float y;
    public EntityTexture texture;
    public String id;
    public UUID uuid;

    public SerializedEntity() {}
}
