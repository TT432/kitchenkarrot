package io.github.tt432.kitchenkarrot.blockentity.menu;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Kitchenkarrot.MOD_ID);

    public static final RegistryObject<MenuType<AirCompressorMenu>> AIR_COMPRESSOR =
            MENUS.register("air_compressor", () -> IForgeMenuType.create(AirCompressorMenu::new));
}
