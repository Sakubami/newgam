package xyz.sakubami.firstgam.world;

import xyz.sakubami.firstgam.chunks.ChunkManager;
import xyz.sakubami.firstgam.chunks.WorldGenerator;
import xyz.sakubami.firstgam.entities.livingentity.Player;

import java.util.List;

public class WorldManager {
    private static WorldManager instance;
    public static void init() { instance = new WorldManager(); }
    public static WorldManager get() { return instance; }

    private World currentWorld;
    private List<World> savedWorlds;

    public WorldManager() {

    }

    public void loadWorld() {
        this.currentWorld = new World("BITCH");
        currentWorld.init();
        currentWorld.connectPlayer(new Player("SAKUBAI"));
    }

    public void createNewWorld() {
        this.currentWorld = new World("BITCH");
    }

    public World getCurrentWorld() {
        return this.currentWorld;
    }
}
