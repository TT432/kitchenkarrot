package io.github.tt432.kitchenkarrot.block;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author DustW
 **/
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Kitchenkarrot.MOD_ID);

    public static final RegistryObject<Block> AIR_COMPRESSOR = BLOCKS.register("air_compressor",
            () -> new KKEntityBlock<AirCompressorBlockEntity>(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2.0f, 2.0f)
                    .noOcclusion()) {
                @Override
                public BlockEntityType<AirCompressorBlockEntity> getBlockEntity() {
                    return ModBlockEntities.AIR_COMPRESSOR.get();
                }
            });
}
