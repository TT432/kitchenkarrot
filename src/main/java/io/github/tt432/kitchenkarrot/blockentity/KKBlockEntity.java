package io.github.tt432.kitchenkarrot.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public abstract class KKBlockEntity extends BlockEntity implements MenuProvider {
    public KKBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    public boolean needSync;

    public void tick() {
        if (needSync) {
            sync(level);
            needSync = false;
        }
    }

    @Override
    public void setChanged() {
        needSync = true;

        super.setChanged();
    }

    public abstract List<ItemStack> drops();

    protected List<ItemStack> dropAll(IItemHandler... itemHandlers) {
        List<ItemStack> result = new ArrayList<>();

        for (IItemHandler itemHandler : itemHandlers) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                result.add(itemHandler.getStackInSlot(i));
            }
        }

        return result;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent(defaultName());
    }

    String name;

    protected String defaultName() {
        return name == null ? name = "container." + getType().getRegistryName().toString().replace("/", ".") : name;
    }

    public void sync(Level level) {
        if (!level.isClientSide) {
            ClientboundBlockEntityDataPacket p = ClientboundBlockEntityDataPacket.create(this);
            ((ServerLevel)this.level).getChunkSource().chunkMap.getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(k -> k.connection.send(p));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, KKBlockEntity e) {
        e.tick();
    }
}
