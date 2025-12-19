package xyz.sakubami.protocol_apocalypse.server.logic.worlds;

import xyz.sakubami.protocol_apocalypse.server.saving.Saviour;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedEntity;
import xyz.sakubami.protocol_apocalypse.server.saving.data.WorldData;
import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity.Player;
import xyz.sakubami.protocol_apocalypse.shared.Configuration;
import xyz.sakubami.protocol_apocalypse.shared.utils.DirectoryHelper;

import java.util.List;

public class WorldManager {
    private static WorldManager instance;
    public static void init() { instance = new WorldManager(); }
    public static WorldManager get() { return instance; }

    private final List<String> savedWorlds;

    public WorldManager() {
        this.savedWorlds = DirectoryHelper.listFolders("worlds");
        for (String string : savedWorlds) {
            System.out.println(string);
        }
    }

    public World selectWorld(String name) {
        if (savedWorlds.contains(name))
            return loadWorld(name);
        else
            return createNewWorld(name);
    }

    public World loadWorld(String name) {
        Player player = (Player) Saviour.get().loadObject("worlds/" + name + "/players/" + Configuration.getClientPlayerUUID().toString(), SerializedEntity.class);
        return Saviour.get().loadObject("worlds/" + name, WorldData.class);
    }

    public World createNewWorld(String name) {
        DirectoryHelper.createDirectory("worlds/" + name + "/batches");
        return new World(name);
    }
}
