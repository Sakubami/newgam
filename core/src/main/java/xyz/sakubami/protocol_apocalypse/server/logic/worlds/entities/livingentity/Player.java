package xyz.sakubami.protocol_apocalypse.server.logic.worlds.entities.livingentity;

import xyz.sakubami.protocol_apocalypse.shared.types.EntityType;

public class Player extends LivingEntity{
    public Player() {
        super(EntityType.PLAYER, "PLAYER");
    }
}
