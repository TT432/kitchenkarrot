package io.github.tt432.kitchenkarrot;

import io.github.tt432.kitchenkarrot.block.ModBlocks;
import io.github.tt432.kitchenkarrot.blockentity.ModBlockEntities;
import io.github.tt432.kitchenkarrot.menu.ModMenuTypes;
import io.github.tt432.kitchenkarrot.item.ModItems;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author DustW
 */
@Mod(Kitchenkarrot.MOD_ID)
public class Kitchenkarrot {
    public static final String MOD_ID = "kitchenkarrot";

    public static final CreativeModeTab MAIN_TAB = new CreativeModeTab(MOD_ID + ".main") {
        @Override
        public ItemStack makeIcon() {
            return ItemStack.EMPTY;
        }
    };

    public static final CreativeModeTab COCKTAIL_TAB = new CreativeModeTab(MOD_ID + ".cocktail") {
        @Override
        public ItemStack makeIcon() {
            return ItemStack.EMPTY;
        }
    };

    public Kitchenkarrot() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModMenuTypes.MENUS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        RecipeManager.register(bus);
    }
}
