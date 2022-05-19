package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * @author DustW
 **/
public class ShakerItem extends Item {
    public ShakerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (pCategory == Kitchenkarrot.COCKTAIL_TAB) {
            pItems.add(new ItemStack(this));
            var alwaysSharking = new ItemStack(this);
            alwaysSharking.getOrCreateTag().putBoolean("sharking", true);
            pItems.add(alwaysSharking);
        }
    }
}
