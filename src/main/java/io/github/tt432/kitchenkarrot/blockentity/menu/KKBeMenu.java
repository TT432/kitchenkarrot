package io.github.tt432.kitchenkarrot.blockentity.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * @author DustW
 **/
public class KKBeMenu<T extends BlockEntity> extends KKMenu {
    public final T blockEntity;

    public KKBeMenu(MenuType<?> type, int windowId, Inventory inv, T blockEntity) {
        super(type, windowId, inv);
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return inventory.stillValid(pPlayer);
    }
}
