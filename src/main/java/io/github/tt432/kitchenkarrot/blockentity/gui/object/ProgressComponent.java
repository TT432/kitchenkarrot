package io.github.tt432.kitchenkarrot.blockentity.gui.object;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;

/**
 * @author DustW
 **/
public class ProgressComponent {
    private final ResourceLocation texture;
    private final int width;
    private final int height;
    private final int texX;
    private final int texY;
    AbstractContainerScreen<?> screen;
    boolean vertical;

    public ProgressComponent(AbstractContainerScreen<?> screen, ResourceLocation texture,
                             int texX, int texY, int width, int height, boolean vertical) {
        this.texture = texture;
        this.texX = texX;
        this.texY = texY;
        this.width = width;
        this.height = height;
        this.screen = screen;
        this.vertical = vertical;
    }

    public void render(PoseStack poseStack, int max, int current, int x, int y) {
        if (current <= 0) {
            return;
        }

        RenderSystem.setShaderTexture(0, texture);
        var p = current * 1. / max;

        if (vertical) {
            var trueHeight = (int) (height * p);
            var trueY = y + height - trueHeight;
            var trueTexY = texY + height - trueHeight;
            screen.blit(poseStack, x, trueY, texX, trueTexY, width, trueHeight);
        }
        else {
            var trueWidth = width - (int) (this.width * p);
            screen.blit(poseStack, x, y, texX, texY, trueWidth, this.height);
        }
    }
}
