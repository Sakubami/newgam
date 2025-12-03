package xyz.sakubami.firstgam.entities.livingentity;

import xyz.sakubami.firstgam.textures.entities.EntityType;

public class Player extends LivingEntity{
    private final String displayName;

    public Player(String displayName) {
        super(EntityType.PLAYER, "PLAYER");
        this.displayName = displayName;
    }

    public String getName() { return this.displayName; }
}
