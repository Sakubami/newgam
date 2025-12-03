package xyz.sakubami.firstgam.saving;

import java.util.Map;

public class SerializedWorld {
    public long seed;
    public String name;
    public Map<String, SerializedChunk> savedChunks;
}
