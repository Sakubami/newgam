package xyz.sakubami.firstgam.entities;

import xyz.sakubami.firstgam.entities.livingentity.Player;
import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.objects.container.interfaces.Chest;
import xyz.sakubami.firstgam.objects.normal.Tree;
import xyz.sakubami.firstgam.textures.entities.EntityType;
import xyz.sakubami.firstgam.textures.objects.ObjectType;

public class EntityRegistry {
    static {
        Entity.registerType(EntityType.PLAYER, Player::new);
    }

    public static void init() {}
}
