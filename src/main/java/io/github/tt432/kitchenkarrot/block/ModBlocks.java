package io.github.tt432.kitchenkarrot.block;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.BrewingBarrelBlockEntity;
import io.github.tt432.kitchenkarrot.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
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

    public static final RegistryObject<Block> BREWING_BARREL = BLOCKS.register("brewing_barrel",
            () -> new KKEntityBlock<BrewingBarrelBlockEntity>(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2.0f, 2.0f)
                    .noOcclusion()) {

                @Override
                public BlockEntityType<BrewingBarrelBlockEntity> getBlockEntity() {
                    return ModBlockEntities.BREWING_BARREL.get();
                }
            });

    public static final RegistryObject<Block> ROCK_SALT = salt("rock_salt");
    public static final RegistryObject<Block> SEA_SALT = salt("sea_salt");
    public static final RegistryObject<Block> FINE_SALT = salt("fine_salt");

    public static final RegistryObject<Block> SUNFLOWER_OIL = oil("sunflower_oil");
    public static final RegistryObject<Block> ACORN_OIL = oil("acorn_oil");
    public static final RegistryObject<Block> CHORUS_OIL = oil("chorus_oil");

    private static RegistryObject<Block> oil(String name) {
        return BLOCKS.register(name,
                () -> new Block(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()
                        .strength(2.0f, 2.0f)) {
                    @Override
                    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                        return OIL;
                    }
                });
    }

    private static RegistryObject<Block> salt(String name) {
        return BLOCKS.register(name,
                () -> new Block(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()
                        .strength(2.0f, 2.0f)) {
                    @Override
                    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                        return SALT;
                    }
                });
    }

    protected static final VoxelShape OIL = Shapes.or(
            Block.box(
                    5, 0, 5,
                    11, 5, 11),
            Block.box(
                    6, 5, 6,
                    10, 6, 10)
    );

    protected static final VoxelShape SALT = Shapes.or(
            Block.box(
                    6, 0, 6,
                    10, 7, 10),
            Block.box(
                    7, 7, 7,
                    9, 9, 9)
    );
}
