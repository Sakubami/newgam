package xyz.sakubami.firstgam.objects.container.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.objects.container.ItemHolder;
import xyz.sakubami.firstgam.saving.SerializedObject;
import xyz.sakubami.firstgam.textures.objects.ObjectTexture;
import xyz.sakubami.firstgam.user_interfaces.InterfaceT;

public abstract class InterfaceHolder extends ItemHolder {
    private final InterfaceT interfaceT;

    public InterfaceHolder(ObjectTexture texture, String id, InterfaceT interfaceT) {
        super(texture, id);
        this.interfaceT = interfaceT;
    }

    protected void openInterfaceMethod() {
        System.out.println("I OPENED THE INTERFACE");
    }

    @Override
    public SerializedObject toData() {
        SerializedObject data = super.toData();
        data.interfaceT = interfaceT;
        return data;
    }
}
