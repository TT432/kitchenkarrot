package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.nbt.CompoundTag;

/**
 * @author DustW
 **/
public abstract class BeSyncData<V> {
    private V value;
    private boolean changed;
    private final String name;

    protected BeSyncData(V defaultValue, String name) {
        this.value = defaultValue;
        this.name = name;
    }

    protected abstract CompoundTag toTag();
    protected abstract V fromTag(CompoundTag tag);

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.changed = true;
        this.value = value;
    }

    public void save(CompoundTag tag) {
        if (changed) {
            tag.put(name, toTag());
            changed = false;
        }
    }

    public void load(CompoundTag tag) {
        if (tag.contains(name)) {
            value = fromTag(tag.getCompound(name));
        }
    }
}
