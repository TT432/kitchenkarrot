package io.github.tt432.kitchenkarrot.gui;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.gui.base.KKGui;
import io.github.tt432.kitchenkarrot.gui.widget.ProgressWidget;
import io.github.tt432.kitchenkarrot.menu.BrewingBarrelMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

/**
 * @author DustW
 **/
public class BrewingBarrelGui extends KKGui<BrewingBarrelMenu> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(Kitchenkarrot.MOD_ID, "textures/gui/brewing_barrel.png");

    public BrewingBarrelGui(BrewingBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();

        this.menu.blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(handler -> {
            if (handler instanceof IFluidTank tank) {
                addRenderableWidget(new ProgressWidget(this, TEXTURE, leftPos + 22, topPos + 21,
                        176, 44, 8, 33, true,
                        () -> new TextComponent(tank.getFluidAmount() + " / " + tank.getCapacity()),
                        true, tank::getCapacity, tank::getFluidAmount));
            }
        });
    }
}
