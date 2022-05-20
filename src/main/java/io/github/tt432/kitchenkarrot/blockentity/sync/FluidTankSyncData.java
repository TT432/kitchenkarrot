package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fluids.capability.templates.FluidTank;

/**
 * @author DustW
 **/
public class FluidTankSyncData extends SyncData<FluidTank> {
    public FluidTankSyncData(String name, FluidTank defaultValue, boolean needSave) {
        super(name, defaultValue, needSave);
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
