package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.entities.Entity;

import java.util.Map;

public class ChunkBatch {
    public Map<String, SerializedChunk> chunks;
    public Map<String, Entity> entities;

    public ChunkBatch() {}
}
