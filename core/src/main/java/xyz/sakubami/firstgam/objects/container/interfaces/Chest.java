package xyz.sakubami.firstgam.objects.container.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Chest extends InterfaceHolder {
    public Chest(float width, float height, TextureRegion texture) {
        super(width, height, texture);
    }

    @Override
    public void getItems() {
        System.out.println("GOT ITEMS");
    }

    @Override
    public void openInterface() {
        System.out.println("OPENED INTERFACE");
    }
}
