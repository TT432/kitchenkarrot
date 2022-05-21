package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.function.Predicate;

/**
 * @author DustW
 **/
public class FluidTankSyncData extends SyncData<FluidTank> {
    public FluidTankSyncData(String name, int capacity, Predicate<FluidStack> validator, boolean needSave) {
        super(name, new FluidTank(capacity, validator) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
            }
        }, needSave);
    }

    public FluidTankSyncData(String name, int capacity, boolean needSave) {
        this(name, capacity, (f) -> true, needSave);
    }

    @Override
    public void set(FluidTank value) {
    }

    @Override
    protected CompoundTag toTag() {
        return get().writeToNBT(new CompoundTag());
    }

    @Override
    protected FluidTank fromTag(CompoundTag tag) {
        return get().readFromNBT(tag);
    }
}
