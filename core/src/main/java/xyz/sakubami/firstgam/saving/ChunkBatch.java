package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.entities.Entity;

import java.util.Map;
import java.util.UUID;

public class ChunkBatch {
    public Map<String, SerializedChunk> chunks;
    public Map<UUID, SerializedEntity> entities;

    public ChunkBatch() {}
}
