package xyz.sakubami.firstgam.saving;

import xyz.sakubami.firstgam.textures.objects.ObjectTexture;
import xyz.sakubami.firstgam.user_interfaces.InterfaceT;

import java.util.Map;

public class SerializedObject {
    public int x;
    public int y;
    public ObjectTexture texture;
    public String id;

    public Map<Integer, SerializedItemStack> items;
    public InterfaceT interfaceT;

    public SerializedObject() {}
}
