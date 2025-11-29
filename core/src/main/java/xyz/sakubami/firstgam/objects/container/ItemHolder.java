package xyz.sakubami.firstgam.objects.container;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.items.ItemStack;
import xyz.sakubami.firstgam.objects.GameObject;
import xyz.sakubami.firstgam.saving.SerializedItemStack;
import xyz.sakubami.firstgam.saving.SerializedObject;
import xyz.sakubami.firstgam.textures.objects.ObjectTexture;

import java.util.HashMap;

public abstract class ItemHolder extends GameObject {
    private final HashMap<Integer, ItemStack> items;

    public ItemHolder(ObjectTexture texture, String id) {
        super(texture, id);
        this.items = new HashMap<>();
    }

    public HashMap<Integer, ItemStack> getItems() { return items; }

    @Override
    public SerializedObject toData() {
        SerializedObject data = super.toData();
        HashMap<Integer, SerializedItemStack> serializedItems = new HashMap<>();
        for (Integer key : this.items.keySet()) {
            serializedItems.put(key, this.items.get(key).toData());
        }
        data.items = serializedItems;
        return data;
    }

    @Override
    public void fromData(SerializedObject data) {
        this.items.clear();

        for(Integer key : data.items.keySet()) {
            SerializedItemStack itemData = data.items.get(key);
            ItemStack stack = new ItemStack(itemData.texture, itemData.id);
            stack.fromData(data.items.get(key));
            this.items.put(key, stack);
        }
    }
}
