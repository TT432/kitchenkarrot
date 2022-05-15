package io.github.tt432.kitchenkarrot.blockentity.sync;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author DustW
 **/
public class FluidSyncData extends SyncData<FluidStack> {
    protected FluidSyncData(String name, FluidStack defaultValue) {
        super(name, defaultValue);
    }

    @Override
    protected CompoundTag toTag() {
        var result = new CompoundTag();
        result.putString("name", get().getFluid().toString());
        result.putInt("amount", get().getAmount());
        result.put("tag", get().getTag());
        return result;
    }

    @Override
    protected FluidStack fromTag(CompoundTag tag) {
        var name = tag.getString("name");
        var amount = tag.getInt("amount");
        var tag2 = tag.getCompound("tag");
        return new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(name)), amount, tag2);
    }
}
