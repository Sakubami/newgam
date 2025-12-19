package xyz.sakubami.protocol_apocalypse.server.logic.worlds;

import xyz.sakubami.protocol_apocalypse.server.logic.chunks.ChunkManager;
import xyz.sakubami.protocol_apocalypse.server.logic.chunks.WorldGenerator;
import xyz.sakubami.protocol_apocalypse.server.saving.data.Serializable;
import xyz.sakubami.protocol_apocalypse.server.saving.data.WorldData;

import java.util.*;

public class World implements Serializable<WorldData> {
    private long seed;
    private UUID uuid;
    private String name;

    private final ChunkManager chunkManager;

    public World(String name) {
        this.name = name;
        this.seed = System.currentTimeMillis();
        this.uuid = UUID.randomUUID();
        System.out.println(uuid + "UUID LOL");

        chunkManager = new ChunkManager(new WorldGenerator(seed));
    }

    public void tick() {
        chunkManager.handleBatches(this);
    }

    @Override
    public WorldData toData() {
        return null;
    }

    @Override
    public void readData(WorldData data) {
        this.seed = data.seed;
        this.uuid = data.uuid;
        this.name = data.name;
    }

    public UUID getUuid() { return this.uuid; }
    public String getName() { return this.name; }
    public String getDirectory() { return "worlds/" + name; }
    public void setName(String name) { this.name = name; }
    public ChunkManager getChunkManager() { return chunkManager; }
}
