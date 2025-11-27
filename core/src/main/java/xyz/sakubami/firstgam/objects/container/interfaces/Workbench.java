package xyz.sakubami.firstgam.objects.container.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Workbench extends InterfaceHolder{
    public Workbench(float width, float height, TextureRegion texture) {
        super(width, height, texture);
    }

    @Override
    public void openInterface() {
        System.out.println("test");
    }
}
