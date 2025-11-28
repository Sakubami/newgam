package xyz.sakubami.firstgam.saving;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.objects.GameObject;

import java.util.Map;

public class SerializedObject {
    public int x;
    public int y;
    public float width;
    public float height;
    public TextureRegion texture;
    public String type;

    public Map<Integer, SerializedItemStack> inventory;

    public SerializedObject() {}

    public SerializedObject(int x, int y, float width, float height, TextureRegion texture, String type) {

    }
}
