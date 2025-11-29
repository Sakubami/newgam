package xyz.sakubami.firstgam.items;

import xyz.sakubami.firstgam.saving.Serializable;
import xyz.sakubami.firstgam.saving.SerializedItemStack;
import xyz.sakubami.firstgam.textures.items.ItemType;

public class ItemStack implements Serializable<SerializedItemStack> {
    private final ItemType texture;
    private final String id;

    public ItemStack(ItemType texture, String id) {
        this.texture = texture;
        this.id = id;
    }

    @Override
    public SerializedItemStack toData() {
        SerializedItemStack data = new SerializedItemStack();
        data.texture = texture;
        data.id = id;
        return data;
    }

    @Override
    public void fromData(SerializedItemStack data) {
    }
}
