package io.github.tt432.kitchenkarrot.blockentity;

import io.github.tt432.kitchenkarrot.blockentity.sync.SyncData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public abstract class KKBlockEntity extends BlockEntity implements MenuProvider {
    List<SyncData<?>> syncDataList = new ArrayList<>();

    public KKBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
        syncDataInit(syncDataList);
    }

    /** 如果你有一些需要自动同步的内容，请放到这里面 */
    protected void syncDataInit(List<SyncData<?>> list) {

    }

    public void tick() {
        sync(level);
    }

    private static final String SYNC_KEY = "sync";

    protected boolean isSyncTag(CompoundTag tag) {
        return tag.contains(SYNC_KEY);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (isSyncTag(pTag)) {
            syncDataList.forEach(data -> data.load(pTag));
        }
        else {
            syncDataList.forEach(data -> {
                if (data.needSave) {
                    data.load(pTag);
                }
            });
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        syncDataList.forEach(data -> {
            if (data.needSave) {
                data.save(pTag, true);
            }
        });
    }

    boolean forceOnce;

    public void forceOnce() {
        forceOnce = true;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag result = new CompoundTag();
        result.putBoolean(SYNC_KEY, true);
        syncDataList.forEach(data -> data.save(result, forceOnce));

        if (forceOnce) {
            forceOnce = false;
        }

        return result;
    }

    public abstract List<ItemStack> drops();

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
