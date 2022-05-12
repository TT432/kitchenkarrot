package io.github.tt432.kitchenkarrot.blockentity.menu;

import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @author DustW
 **/
public class AirCompressorMenu extends KKMenu {
    public AirCompressorBlockEntity blockEntity;

    public AirCompressorMenu(int pContainerId, Inventory inventory, AirCompressorBlockEntity blockEntity) {
        super(ModMenuTypes.AIR_COMPRESSOR.get(), pContainerId, inventory);
        this.blockEntity = blockEntity;
        addProgressSlot();
        addItemSlots();
    }

    public AirCompressorMenu(int windowId, Inventory inv, FriendlyByteBuf data) {
        this(windowId, inv, (AirCompressorBlockEntity) inv.player.getLevel().getBlockEntity(data.readBlockPos()));
    }

    void addProgressSlot() {
        addDataSlot(() -> blockEntity::getProgress, () -> blockEntity::setProgress);
        addDataSlot(() -> blockEntity::getBurnTime, () -> blockEntity::setBurnTime);
    }

    void addItemSlots() {
        var input1 = blockEntity.getInput1();
        addSlot(input1, 0, 32 + 1, 25 + 1);
        addSlot(input1, 1, 50 + 1, 25 + 1);
        addSlot(input1, 2, 32 + 1, 43 + 1);
        addSlot(input1, 3, 50 + 1, 43 + 1);
        addSlot(input1, 4, 81 + 1, 14 + 1);
        var input2 = blockEntity.getInput2();
        addSlot(input2, 0, 74 + 1, 58 + 1);
        var output = blockEntity.getOutput();
        addResultSlot(output, 0, 120 + 1, 34 + 1);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var slot = slots.get(index);
        var slotItem = slot.getItem();
        var playerSlotSize = 36;

        if (index < playerSlotSize) {
            for (int i = slots.size() - 1; i >= playerSlotSize; i--) {
                var temp = slots.get(i);

                if (temp.safeInsert(slotItem).isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }

            return ItemStack.EMPTY;
        }
        else {
            for (int i = 0; i < playerSlotSize; i++) {
                var temp = slots.get(i);

                if (temp.safeInsert(slotItem).isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return inventory.stillValid(pPlayer);
    }
}
