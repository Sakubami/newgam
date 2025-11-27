package xyz.sakubami.firstgam.objects.container.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.objects.container.ItemHolder;

public abstract class InterfaceHolder extends ItemHolder {
    public InterfaceHolder(float width, float height, TextureRegion texture) {
        super(width, height, texture);
    }

    public void openInterface() {}
}
