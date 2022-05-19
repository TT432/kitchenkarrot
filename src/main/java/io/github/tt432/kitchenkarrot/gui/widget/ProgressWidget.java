package io.github.tt432.kitchenkarrot.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class ProgressWidget extends AbstractWidget {
    private final ResourceLocation texture;
    AbstractContainerScreen<?> screen;
    int texX;
    int texY;
    boolean vertical;
    Supplier<Integer> maxGetter;
    Supplier<Integer> currentGetter;
    boolean needTooltip;
    Supplier<Component> messageSupplier;

    public ProgressWidget(AbstractContainerScreen<?> screen,
                          ResourceLocation texture,
                          int x, int y,
                          int texX, int texY,
                          int width, int height,
                          boolean vertical,
                          Supplier<Component> message, boolean needTooltip,
                          Supplier<Integer> maxGetter, Supplier<Integer> currentGetter) {
        super(x, y, width, height, message.get());
        this.texture = texture;
        this.screen = screen;
        this.vertical = vertical;
        this.maxGetter = maxGetter;
        this.currentGetter = currentGetter;
        this.texX = texX;
        this.texY = texY;
        this.needTooltip = needTooltip;
        this.messageSupplier = message;
    }

    public ProgressWidget(AbstractContainerScreen<?> screen,
                               ResourceLocation texture,
                               int x, int y,
                               int texX, int texY,
                               int width, int height,
                               boolean vertical,
                               Component message, boolean needTooltip,
                               Supplier<Integer> maxGetter, Supplier<Integer> currentGetter) {
        this(screen, texture, x, y, texX, texY, width, height, vertical,
                () -> message, needTooltip, maxGetter, currentGetter);
    }

    public ProgressWidget(AbstractContainerScreen<?> screen,
                          ResourceLocation texture,
                          int x, int y,
                          int texX, int texY,
                          int width, int height,
                          boolean vertical,
                          Supplier<Integer> maxGetter, Supplier<Integer> currentGetter) {
        this(screen, texture, x, y, texX, texY, width, height, vertical,
                new TextComponent(""), false, maxGetter, currentGetter);
    }

    @Override
    public void render(PoseStack poseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (!visible) {
            return;
        }

        this.isHovered = pMouseX >= this.x && pMouseY >= this.y && pMouseX < this.x + this.width && pMouseY < this.y + this.height;

        var current = currentGetter.get();
        var max = maxGetter.get();

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

        if (isHovered && needTooltip) {
            screen.renderTooltip(poseStack, getMessage(), pMouseX, pMouseY);
        }
    }

    @Override
    public Component getMessage() {
        return messageSupplier.get();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return false;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {
        if (needTooltip) {
            narrationElementOutput.add(NarratedElementType.HINT, getMessage());
        }
    }
}
