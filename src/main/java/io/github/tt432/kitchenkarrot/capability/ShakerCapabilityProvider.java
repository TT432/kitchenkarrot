package io.github.tt432.kitchenkarrot.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author DustW
 **/
public class ShakerCapabilityProvider extends CapabilityProvider<ShakerCapabilityProvider> implements INBTSerializable<CompoundTag> {
    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> new ItemStackHandler(12));

    public ShakerCapabilityProvider() {
        super(ShakerCapabilityProvider.class);
    }

    @Override
    public CompoundTag serializeNBT() {
        return handler.resolve().get().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        handler.ifPresent(h -> h.deserializeNBT(nbt));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, handler.cast());
    }
}
