package xyz.sakubami.firstgam.world;

import java.util.List;

public class WorldManager {
    private static final WorldManager instance = new WorldManager();
    public static WorldManager get() { return instance; }

    private World currentWorld;
    private List<World> savedWorlds;

    public WorldManager() {

    }

    public void loadWorld() {
        this.currentWorld = new World();
    }

    public void createNewWorld() {
        this.currentWorld = new World();
    }

    public World getCurrentWorld() {
        return this.currentWorld;
    }
}
