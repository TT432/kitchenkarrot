package io.github.tt432.kitchenkarrot.event.listener;

import com.mojang.math.Vector3f;
import io.github.tt432.kitchenkarrot.item.ShakerItem;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber(Dist.CLIENT)
public class RenderHand {
    static int max = 32;
    static int current;

    static int getCurrent() {
        return current;
    }

    static int getMax() {
        return max;
    }

    @SubscribeEvent
    public static void onEvent(TickEvent.ClientTickEvent e) {
        if (current++ == max) {
            current = 0;
        }
    }

    @SubscribeEvent
    public static void onEvent(RenderHandEvent event) {
        var hand = event.getHand();

        if (hand != InteractionHand.MAIN_HAND) {
            return;
        }

        var stack = event.getItemStack();

        if (stack.getItem() instanceof ShakerItem && stack.getTag() != null && stack.getTag().getBoolean("sharking")) {
            var ps = event.getPoseStack();
            var partialTicks = event.getPartialTicks();

            int max = getMax();
            int current = getCurrent();

            float progress = max - current - partialTicks + 1.0F;

            float f2 = Mth.cos(progress / 4.0F * (float)Math.PI) * 0.1F;
            ps.translate(0.0D, f2, 0.0D);

            float f3 = 1.0F - (float)Math.pow(.5, 27.0D);
            ps.translate(f3 * 0.6F, f3 * -0.5F, f3 * 0.0F);
            ps.mulPose(Vector3f.YP.rotationDegrees(f3 * 90.0F));
            ps.mulPose(Vector3f.XP.rotationDegrees(f3 * 10.0F));
            ps.mulPose(Vector3f.ZP.rotationDegrees(f3 * 30.0F));
        }
    }
}
