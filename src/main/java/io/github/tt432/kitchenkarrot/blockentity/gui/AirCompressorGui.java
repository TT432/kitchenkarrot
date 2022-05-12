package io.github.tt432.kitchenkarrot.blockentity.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.gui.object.ProgressSlot;
import io.github.tt432.kitchenkarrot.blockentity.menu.AirCompressorMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class AirCompressorGui extends AbstractContainerScreen<AirCompressorMenu> {

    private final ResourceLocation GUI =
            new ResourceLocation(Kitchenkarrot.MOD_ID, "textures/gui/air_compressor.png");

    private final ProgressSlot progressSlot;
    private final ProgressSlot burnSlot;
    AirCompressorMenu container;
    int relX;
    int relY;

    public AirCompressorGui(AirCompressorMenu container, Inventory inv, Component name) {
        super(container, inv, name);
        this.container = container;
        progressSlot = new ProgressSlot(this, GUI,
                176, 14, 24, 17, false);
        burnSlot = new ProgressSlot(this, GUI,
                176, 0, 14, 14, true);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
        renderSlots(matrixStack);
    }

    protected void renderSlots(PoseStack ps) {
        var be = container.blockEntity;

        progressSlot.render(ps, be.getMaxProgress(), be.getProgress(), relX + 79, relY + 34);
        burnSlot.render(ps, be.getMaxBurnTime(), be.getBurnTime(), relX + 93, relY + 60);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        super.renderLabels(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        relX = (this.width - this.imageWidth) / 2;
        relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
