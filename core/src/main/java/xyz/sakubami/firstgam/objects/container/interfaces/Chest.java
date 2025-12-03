package xyz.sakubami.firstgam.objects.container.interfaces;

import xyz.sakubami.firstgam.textures.objects.ObjectType;
import xyz.sakubami.firstgam.user_interfaces.InterfaceT;

public class Chest extends InterfaceHolder {
    public Chest() {
        super(ObjectType.CHEST, "CHEST", InterfaceT.INVENTORY);
    }

    public void openInterface() {
        super.openInterfaceMethod();
    }
}
