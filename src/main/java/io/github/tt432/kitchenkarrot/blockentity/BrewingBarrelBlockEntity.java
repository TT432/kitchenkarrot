package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import io.github.tt432.kitchenkarrot.blockentity.sync.FluidSyncData;
import io.github.tt432.kitchenkarrot.blockentity.sync.SyncData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author DustW
 **/
public class BrewingBarrelBlockEntity extends KKBlockEntity {
    FluidTank input1 = new FluidTank(4000, (f) -> f.getFluid() == Fluids.WATER) {
        @Override
        protected void onContentsChanged() {
            BrewingBarrelBlockEntity.this.fluid.set(fluid);
        }
    };
    KKItemStackHandler input2 = new KKItemStackHandler(this, 6);
    KKItemStackHandler result = new KKItemStackHandler(this, 1);
    private final FluidSyncData fluid =
            new FluidSyncData("fluid", FluidStack.EMPTY, true);

    public BrewingBarrelBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BREWING_BARREL.get(), pWorldPosition, pBlockState);
    }

    @Override
    protected void syncDataInit(List<SyncData<?>> list) {
        list.add(fluid);
    }

    @Override
    public List<ItemStack> drops() {
        return dropAll(input2, result);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new BrewingBarrelMenu(pContainerId, pInventory, this);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> input1));
    }
}
