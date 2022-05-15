package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

/**
 * @author DustW
 **/
public class ItemSyncData extends SyncData<ItemStack> {
    protected ItemSyncData(String name, ItemStack defaultValue) {
        super(name, defaultValue);
    }

    @Override
    protected CompoundTag toTag() {
        return get().serializeNBT();
    }

    @Override
    protected ItemStack fromTag(CompoundTag tag) {
        return ItemStack.of(tag);
    }
}
