package xyz.sakubami.firstgam.objects;

import xyz.sakubami.firstgam.objects.container.interfaces.Chest;
import xyz.sakubami.firstgam.objects.normal.Tree;
import xyz.sakubami.firstgam.textures.objects.ObjectType;

public class ObjectRegistry {
    static {
        GameObject.registerType(ObjectType.CHEST, Chest::new);
        GameObject.registerType(ObjectType.TREE, Tree::new);
    }

    public static void init() {}
}
