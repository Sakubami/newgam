package xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities;

import xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity.Player;
import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;

public class EntityRegistry {
    static {
        Entity.registerType(EntityType.PLAYER, Player::new);
    }

    public static void init() {}
}
