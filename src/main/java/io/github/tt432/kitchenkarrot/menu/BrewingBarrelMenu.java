package io.github.tt432.kitchenkarrot.menu;

import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import io.github.tt432.kitchenkarrot.menu.base.KKBeMenu;
import io.github.tt432.kitchenkarrot.menu.reg.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class BrewingBarrelMenu extends KKBeMenu<BrewingBarrelBlockEntity> {
    public BrewingBarrelMenu(int windowId, Inventory inv, BrewingBarrelBlockEntity blockEntity) {
        super(ModMenuTypes.BREWING_BARREL.get(), windowId, inv, blockEntity);
    }
}
