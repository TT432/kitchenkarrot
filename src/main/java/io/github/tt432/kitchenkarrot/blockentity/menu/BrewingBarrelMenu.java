package io.github.tt432.kitchenkarrot.blockentity.menu;

import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class BrewingBarrelMenu extends KKBeMenu<BrewingBarrelBlockEntity> {
    public BrewingBarrelMenu(int windowId, Inventory inv, BrewingBarrelBlockEntity blockEntity) {
        super(ModMenuTypes.BREWING_BARREL.get(), windowId, inv, blockEntity);
    }
}
