package xyz.sakubami.firstgam.objects.container;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.objects.GameObject;

public abstract class ItemHolder extends GameObject {

    public ItemHolder(float width, float height, TextureRegion texture) {
        super(width, height, texture);
    }

    public void getItems() {} //TODO replace with list of items
}
