package io.github.tt432.kitchenkarrot.menu;

import io.github.tt432.kitchenkarrot.menu.base.KKMenu;
import io.github.tt432.kitchenkarrot.menu.reg.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * @author DustW
 **/
public class ShakerMenu extends KKMenu {
    ItemStack itemStack;

    public ShakerMenu(int pContainerId, Inventory inventory) {
        super(ModMenuTypes.SHAKER.get(), pContainerId, inventory);

        itemStack = inventory.getSelected();
        addSlots();
    }

    void addSlots() {
        itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(h, 0, 110 + 1, 25 + 1);
            addSlot(h, 1, 128 + 1, 25 + 1);
            addSlot(h, 2, 146 + 1, 25 + 1);
            addSlot(h, 3, 118 + 1, 43 + 1);
            addSlot(h, 4, 136 + 1, 43 + 1);

            addSlot(h, 5, 12 + 1, 13 + 1);
            addSlot(h, 6, 30 + 1, 13 + 1);
            addSlot(h, 7, 12 + 1, 31 + 1);
            addSlot(h, 8, 30 + 1, 31 + 1);

            addSlot(h, 9, 12 + 1, 49 + 1);
            addSlot(h, 10, 30 + 1, 49 + 1);

            addResultSlot(h, 11, 71 + 1, 33 + 1);
        });
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);

        itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            if (!pPlayer.level.isClientSide) {
                for (int i = 0; i < handler.getSlots(); i++) {
                    pPlayer.getInventory().placeItemBackInInventory(handler.extractItem(i, 64, false));
                }
            }
        });
    }
}
