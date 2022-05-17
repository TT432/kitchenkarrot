package io.github.tt432.kitchenkarrot.blockentity.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.gui.object.KKGui;
import io.github.tt432.kitchenkarrot.blockentity.gui.object.ProgressComponent;
import io.github.tt432.kitchenkarrot.blockentity.menu.AirCompressorMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class AirCompressorGui extends KKGui<AirCompressorMenu> {

    private static final ResourceLocation GUI =
            new ResourceLocation(Kitchenkarrot.MOD_ID, "textures/gui/air_compressor.png");

    private final ProgressComponent progressComponent;
    private final ProgressComponent burnComponent;
    AirCompressorBlockEntity be;

    public AirCompressorGui(AirCompressorMenu container, Inventory inv, Component name) {
        super(container, inv, name, GUI);
        be = container.blockEntity;
        progressComponent = new ProgressComponent(this, GUI,
                176, 14, 24, 17, false);
        burnComponent = new ProgressComponent(this, GUI,
                176, 0, 14, 14, true);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderComponent(matrixStack);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderComponent(PoseStack ps) {
        progressComponent.render(ps, be.getMaxProgress(), be.getProgress(), relX + 79, relY + 34);
        burnComponent.render(ps, be.getMaxBurnTime(), be.getBurnTime(), relX + 93, relY + 60);
    }
}
