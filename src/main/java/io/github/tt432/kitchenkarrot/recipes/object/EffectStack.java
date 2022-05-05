package io.github.tt432.kitchenkarrot.recipes.object;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author DustW
 **/
public class EffectStack {
    /**
     * "id":"BUFF效果名字","lvl":"一个int数值，代表BUFF等级","duration":"一个int数值，代表BUFF持续时间"
     */
    @Expose
    public String id;
    @Expose
    public int lvl;
    @Expose
    public int duration;

    private MobEffectInstance cache;

    public MobEffectInstance get() {
        if (cache == null) {
            var effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(id));

            if (effect != null) {
                cache = new MobEffectInstance(effect, duration, lvl - 1);
            }
        }

        return cache;
    }
}
