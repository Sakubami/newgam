package xyz.sakubami.protocol_apocalypse.server.logic.items;

import xyz.sakubami.protocol_apocalypse.server.saving.data.Serializable;
import xyz.sakubami.protocol_apocalypse.server.saving.data.SerializedItemStack;
import xyz.sakubami.protocol_apocalypse.shared.types.ItemType;

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
    public void readData(SerializedItemStack data) {
    }
}
