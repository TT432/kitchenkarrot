package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeManager;
import io.github.tt432.kitchenkarrot.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class AirCompressorBlockEntity extends KKBlockEntity {
    /** 原料 / 容器输入 */
    ItemStackHandler input1 = new KKItemStackHandler(this, 5) {
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            if (!isItemValid(slot, stack)) {return;}
            super.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot != 4 || stack.is(ModItemTags.CONTAINER_ITEM);
        }
    };
    /** 燃料输入 */
    ItemStackHandler input2 = new KKItemStackHandler(this, 1) {
        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            if (!isItemValid(slot, stack)) {return;}
            super.setStackInSlot(slot, stack);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return ForgeHooks.getBurnTime(stack, null) != 0;
        }
    };
    /** 成品输出 */
    ItemStackHandler output = new KKItemStackHandler(this, 1);

    public AirCompressorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.AIR_COMPRESSOR.get(), pWorldPosition, pBlockState);
    }

    int progress;
    int maxBurnTime;
    int burnTime;
    AirCompressorRecipe recipe;
    ResourceLocation recipeId;

    public AirCompressorRecipe getRecipe() {
        return recipe == null && recipeId != null ?
                recipe = (AirCompressorRecipe) level.getRecipeManager().byKey(recipeId).get() : recipe;
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide) {
            return;
        }

        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i < input1.getSlots(); i++) {
            items.add(input1.getStackInSlot(i));
        }

        if (progress == 0) {
            // 尚未开始
            if (!recipeValid(items)) {
                if (burnTime > 0 || !input2.getStackInSlot(0).isEmpty()) {
                    var recipeList = RecipeManager.getAirCompressorRecipe(level)
                            .stream().filter(r -> r.matches(items)).toArray();

                    for (Object obj : recipeList) {
                        var r = (AirCompressorRecipe) obj;

                        if (output.insertItem(0, r.getResultItem(), true).isEmpty()) {
                            setRecipe(r);
                            progress = recipe.getCraftingTime();

                            if (burnTime == 0) {
                                addFuel();
                            }

                            setChanged();

                            return;
                        }
                    }
                }
            }
            else {
                // 结束
                for (ItemStack matchItem : getRecipe().getMatchItems(items)) {
                    matchItem.shrink(1);
                }

                if (getRecipe().getContainer() != null) {
                    input1.extractItem(4, 1, false);
                }

                output.insertItem(0, recipe.getResultItem(), false);

                stop();
            }
        }
        else if (burnTime != 0 && recipeValid(items)) {
            // 还在工作
            progress = Math.max(progress - 1, 0);
        }
        else {
            // 中止
            stop();
        }

        if ((burnTime = Math.max(burnTime - 1, 0)) == 0 && recipeValid(items)) {
            // 燃料耗尽
            addFuel();
        }
    }

    protected boolean recipeValid(List<ItemStack> items) {
        return getRecipe() != null && getRecipe().matches(items);
    }

    protected void setRecipe(AirCompressorRecipe recipe) {
        this.recipe = recipe;
        this.recipeId = recipe.getId();
    }

    protected void addFuel() {
        burnTime = ForgeHooks.getBurnTime(input2.getStackInSlot(0), null);
        input2.extractItem(0, 1, false);
        maxBurnTime = burnTime;

        setChanged();
    }

    protected void stop() {
        recipe = null;
        recipeId = null;
        progress = 0;

        setChanged();
    }

    @Override
    public List<ItemStack> drops() {
        return dropAll(input1, input2, output);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return side == null ? LazyOptional.empty() : CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                .orEmpty(cap, LazyOptional.of(() -> switch (side) {
                    case DOWN -> output;
                    case UP -> input1;
                    case NORTH, SOUTH, WEST, EAST -> input2;
                }));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AirCompressorMenu(pContainerId, pInventory, this);
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMaxProgress() {
        return getRecipe() == null ? 0 : recipe.getCraftingTime();
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    public ItemStackHandler getInput1() {
        return input1;
    }

    public ItemStackHandler getInput2() {
        return input2;
    }

    public ItemStackHandler getOutput() {
        return output;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        var input1 = getInput1().serializeNBT();
        var input2 = getInput2().serializeNBT();
        var output = getOutput().serializeNBT();

        pTag.put("input1", input1);
        pTag.put("input2", input2);
        pTag.put("output", output);

        pTag.putInt("progress", progress);
        pTag.putInt("burnTime", burnTime);
        pTag.putInt("maxBurn", maxBurnTime);

        if (recipeId != null) {
            pTag.putString("recipe", recipeId.toString());
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        if (!pTag.contains("sync")) {
            input1.deserializeNBT(pTag.getCompound("input1"));
            input2.deserializeNBT(pTag.getCompound("input2"));
            output.deserializeNBT(pTag.getCompound("output"));

            progress = pTag.getInt("progress");
            burnTime = pTag.getInt("burnTime");
        }

        if (pTag.contains("recipe")) {
            recipeId = new ResourceLocation(pTag.getString("recipe"));
        }

        maxBurnTime = pTag.getInt("maxBurn");
    }

    @Override
    public CompoundTag getUpdateTag() {
        var result = new CompoundTag();

        if (recipeId != null) {
            result.putString("recipe", recipeId.toString());
        }

        result.putInt("maxBurn", maxBurnTime);
        result.putBoolean("sync", true);

        return result;
    }
}
