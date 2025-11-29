package xyz.sakubami.firstgam.objects.container.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.items.ItemStack;
import xyz.sakubami.firstgam.saving.SerializedObject;
import xyz.sakubami.firstgam.textures.TextureManager;
import xyz.sakubami.firstgam.textures.objects.ObjectTexture;
import xyz.sakubami.firstgam.user_interfaces.InterfaceT;

import java.util.HashMap;

public class Chest extends InterfaceHolder {
    public Chest() {
        super(ObjectTexture.CHEST, "CHEST", InterfaceT.INVENTORY);
    }

    public void openInterface() {
        super.openInterfaceMethod();
    }
}
