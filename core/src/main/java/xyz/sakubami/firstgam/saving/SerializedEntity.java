package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.textures.entities.EntityType;

import java.util.UUID;

public class SerializedEntity {
    public float x;
    public float y;
    public EntityType texture;
    public String id;
    public UUID uuid;

    public String displayName;

    public SerializedEntity() {}
}
