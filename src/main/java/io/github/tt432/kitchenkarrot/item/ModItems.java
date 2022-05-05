package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Kitchenkarrot.MOD_ID);

    public static final RegistryObject<Item> ICE_CUBES = simple("ice_cubes");
    public static final RegistryObject<Item> CARROT_SPICES = simple("carrot_spices");
    public static final RegistryObject<Item> ROCK_SALT = simple("rock_salt");
    public static final RegistryObject<Item> SEA_SALT = simple("sea_salt");
    public static final RegistryObject<Item> FINE_SALT = simple("fine_salt");
    public static final RegistryObject<Item> SUNFLOWER_OIL = simple("sunflower_oil");
    public static final RegistryObject<Item> ACORN_OIL = simple("acorn_oil");
    public static final RegistryObject<Item> CHORUS_OIL = simple("chorus_oil");
    public static final RegistryObject<Item> EMPTY_CAN = simple("empty_can");
    public static final RegistryObject<Item> RAW_SWEET_LOAF = simple("raw_sweet_loaf");
    public static final RegistryObject<Item> SWEET_LOAF = simple("sweet_loaf");

    public static final RegistryObject<Item> ACORN = food("acorn", 2, 0.4F);
    public static final RegistryObject<Item> BIRCH_SAP = food("birch_sap", 3, 2);
    public static final RegistryObject<Item> GEM_CARROT = food("gem_carrot", 6, 8);
    public static final RegistryObject<Item> BAMBOO_POTATO = food("bamboo_potato", 8, 8);
    public static final RegistryObject<Item> SWEET_LOAF_SLICE = food("sweet_loaf_slice", 7, 9.6F);
    public static final RegistryObject<Item> PICKLED_SEA_PICKLES = food("pickled_sea_pickles", 3, 2);
    public static final RegistryObject<Item> BIRCH_SAP_CHOCOLATE_BAR = food("birch_sap_chocolate_bar", 5, 9.2F);
    public static final RegistryObject<Item> CHOCOLATE_CROISSANT = food("chocolate_croissant", 7, 8);
    public static final RegistryObject<Item> BEETROOT_CREPE = food("beetroot_crepe", 10, 9.6F);
    public static final RegistryObject<Item> CHINESE_CREPE = food("chinese_crepe", 10, 9.6F);
    public static final RegistryObject<Item> CROQUE_MADAME = food("croque_madame", 8, 10);
    public static final RegistryObject<Item> SIRLOIN_STEAK = food("sirloin_steak", 12, 14.4F);
    public static final RegistryObject<Item> BEEF_GRAINS = food("beef_grains", 3, 4);
    public static final RegistryObject<Item> SASHIMI = food("sashimi", 4, 2.4F);
    public static final RegistryObject<Item> CHORUS_MOUSSE = food("chorus_mousse", 12, 14.4F);
    public static final RegistryObject<Item> SMALL_CHORUS_MOUSSE = food("small_chorus_mousse", 4, 4.8F);
    public static final RegistryObject<Item> FEAST_PIZZA_SLICE = food("feast_pizza_slice", 10, 12.8F);
    public static final RegistryObject<Item> SHINY_PIZZA_SLICE = food("shiny_pizza_slice", 7, 24);
    public static final RegistryObject<Item> FRIES = food("fries", 7, 10.8F);
    public static final RegistryObject<Item> DRUMSTICK = food("drumstick", 9, 12.8F);
    public static final RegistryObject<Item> FRIED_CHICKEN_COMBO = food("fried_chicken_combo", 15, 30);
    public static final RegistryObject<Item> POPACORN = food("popacorn", 2, 3.2F);
    public static final RegistryObject<Item> ACORN_WINE = food("acorn_wine", 2, 5.2F);
    public static final RegistryObject<Item> RAW_VEGGIE_BEEF = food("raw_veggie_beef", 3, 1.8F);
    public static final RegistryObject<Item> COOKED_VEGGIE_BEEF = food("cooked_veggie_beef", 8, 12.8F);
    public static final RegistryObject<Item> RAW_VEGGIE_PORK = food("raw_veggie_pork", 3, 0.6F);
    public static final RegistryObject<Item> COOKED_VEGGIE_PORK = food("cooked_veggie_pork", 8, 12.8F);
    public static final RegistryObject<Item> RAW_VEGGIE_RABBIT = food("raw_veggie_rabbit", 2, 1.2F);
    public static final RegistryObject<Item> COOKED_VEGGIE_RABBIT = food("cooked_veggie_rabbit", 6, 7.2F);

    public static final RegistryObject<Item> FRIED_PUMPKIN_CAKE = fastFood("fried_pumpkin_cake", 4, 3.2F, 24);
    public static final RegistryObject<Item> SEED_PIE = fastFood("seed_pie", 4, 2.4F, 24);
    public static final RegistryObject<Item> KELP_WITH_SUNFLOWER_SEED = fastFood("kelp_with_sunflower_seed", 2, 1, 12);
    public static final RegistryObject<Item> RICE_CAKE = fastFood("rice_cake", 4, 4, 18);
    public static final RegistryObject<Item> CUPCAKE = fastFood("cupcake", 2, 3.2F, 24);
    public static final RegistryObject<Item> LEAFY_FRESH_CHOCOLATE = fastFood("leafy_fresh_chocolate", 2, 4.8F, 18);
    public static final RegistryObject<Item> CANNED_BEEF_POTATO = fastFood("canned_beef_potato", 8, 12.8F, 1);
    public static final RegistryObject<Item> CANNED_PORK_BEETROOT = fastFood("canned_pork_beetroot", 8, 12.8F, 1);
    public static final RegistryObject<Item> CANNED_RABBIT_PUMPKIN = fastFood("canned_rabbit_pumpkin", 8, 12.8F, 1);
    public static final RegistryObject<Item> CANNED_CANDIED_APPLE = fastFood("canned_candied_apple", 6, 14.4F, 1);

    public static final RegistryObject<Item> GRILLED_FISH_AND_CACTUS = ITEMS.register("grilled_fish_and_cactus",
            () -> new InstantHealthFood(defaultProperties()
                    .food(new FoodProperties.Builder().nutrition(6).saturationMod(7.2F).build())));

    public static final RegistryObject<Item> PILLAGER_PIE =
            foodEffect("pillager_pie", 8, 4.8F,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.BAD_OMEN, 600), 1));

    public static final RegistryObject<Item> MONSTER_LASAGNA =
            foodEffect("monster_lasagna", 13, 3.2F,
                        new EffectEntry(() -> new MobEffectInstance(MobEffects.HUNGER, 30), 1));

    public static final RegistryObject<Item> SMALL_MONSTER_LASAGNA =
            foodEffect("small_monster_lasagna", 5, 1.6F,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.HUNGER, 10), 1));

    public static final RegistryObject<Item> DUNGEON_PIZZA_SLICE =
            foodEffect("dungeon_pizza_slice", 10, 6F,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.POISON, 10), .2F));

    public static final RegistryObject<Item> ICED_MELON_LAGER =
            foodEffect("iced_melon_lager", 3, 2.4F,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 2), 1));

    public static final RegistryObject<Item> GLOW_BERRY_LAGER =
            foodEffect("glow_berry_lager", 2, 2.4F,
                        new EffectEntry(() -> new MobEffectInstance(MobEffects.GLOWING, 300, 2), 1));

    public static final RegistryObject<Item> RUM =
            foodEffect("rum", 0, 0F,
                        new EffectEntry(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 300), 1));

    public static final RegistryObject<Item> VODKA =
            foodEffect("vodka", 0, 0F,
                        new EffectEntry(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300), 1));

    public static final RegistryObject<Item> MEAD = ITEMS.register("mead",
            () -> new ClearEffectItem(defaultProperties().food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> FISHERMEN_DELIGHT =
            foodEffectRemain("fishermens_delight", 10, 10.4F, Items.BOWL,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.LUCK, 180), 1));

    public static final RegistryObject<Item> SWEET_BERRY_MILK =
            foodEffectRemain("sweet_berry_milk", 4, 4.8F, Items.GLASS_BOTTLE,
                    new EffectEntry(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 60), 1));

    public static final RegistryObject<Item> CREAM_OF_MUSHROOM_SOUP =
            foodEffectRemain("cream_of_mushroom_soup", 8, 9.2F, Items.BOWL);


    public static final RegistryObject<Item> COCKTAIL = ITEMS.register("cocktail",
            () -> new CocktailItem(defaultProperties().food(
                    new FoodProperties.Builder().alwaysEat().build()
            )));

    public static final RegistryObject<Item> SHAKER = cocktail("shaker");
    public static final RegistryObject<Item> RUM_BASE = cocktail("rum_base");
    public static final RegistryObject<Item> VODKA_BASE = cocktail("vodka_base");
    public static final RegistryObject<Item> ACORN_WINE_BASE = cocktail("acorn_wine_base");
    public static final RegistryObject<Item> MEAD_BASE = cocktail("mead_base");



    private static RegistryObject<Item> simple(String name) {
        return ITEMS.register(name, () -> new Item(defaultProperties()));
    }

    private static RegistryObject<Item> cocktail(String name) {
        return ITEMS.register(name, () -> new Item(defaultProperties().tab(Kitchenkarrot.COCKTAIL_TAB)));
    }

    private static RegistryObject<Item> food(String name, int nutrition, float saturation) {
        return ITEMS.register(name, () -> new Item(defaultProperties()
                .food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build())));
    }

    private static RegistryObject<Item> foodEffect(String name, int nutrition, float saturation, EffectEntry... list) {
        return ITEMS.register(name, () -> new Item(defaultProperties()
                .food(addEffects(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation), list))));
    }

    private static RegistryObject<Item> foodEffectRemain(String name, int nutrition, float saturation, Item remain, EffectEntry... list) {
        return ITEMS.register(name, () -> new EatFastItem(
                defaultProperties()
                        .food(addEffects(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod(saturation), list)),
                32,
                new ItemStack(remain)));
    }

    private static RegistryObject<Item> fastFood(String name, int nutrition, float saturation, int tick) {
        return ITEMS.register(name, () -> new EatFastItem(defaultProperties()
                .food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()), tick));
    }

    private static Item.Properties defaultProperties() {
        return new Item.Properties().tab(Kitchenkarrot.MAIN_TAB);
    }

    private static FoodProperties addEffects(FoodProperties.Builder builder, EffectEntry... list) {
        for (EffectEntry effectEntry : list) {
            builder.effect(effectEntry.effect, effectEntry.probability);
        }

        return builder.build();
    }

    private record EffectEntry(Supplier<MobEffectInstance> effect, float probability) { }
}
