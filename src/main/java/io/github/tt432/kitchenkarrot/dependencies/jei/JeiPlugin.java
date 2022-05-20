package io.github.tt432.kitchenkarrot.dependencies.jei;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.dependencies.jei.category.CocktailRecipeCategory;
import io.github.tt432.kitchenkarrot.gui.AirCompressorGui;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;
import io.github.tt432.kitchenkarrot.dependencies.jei.category.AirCompressorRecipeCategory;
import io.github.tt432.kitchenkarrot.item.ModItems;
import io.github.tt432.kitchenkarrot.menu.ShakerMenu;
import io.github.tt432.kitchenkarrot.recipes.recipe.AirCompressorRecipe;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

/**
 * @author DustW
 **/
@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    public static final RecipeType<AirCompressorRecipe> AIR_COMPRESSOR =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "air_compressor"),
                    AirCompressorRecipe.class);

    public static final RecipeType<CocktailRecipe> COCKTAIL =
            new RecipeType<>(new ResourceLocation(Kitchenkarrot.MOD_ID, "cocktail"),
                    CocktailRecipe.class);

    protected <C extends Container, T extends Recipe<C>> List<T> getRecipe(net.minecraft.world.item.crafting.RecipeType<T> recipeType) {
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(recipeType);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new AirCompressorRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new CocktailRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(AIR_COMPRESSOR, getRecipe(RecipeTypes.AIR_COMPRESSOR.get()));
        registration.addRecipes(COCKTAIL, getRecipe(RecipeTypes.COCKTAIL.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.AIR_COMPRESSOR.get()), AIR_COMPRESSOR);
        registration.addRecipeCatalyst(new ItemStack(ModItems.SHAKER.get()), COCKTAIL);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AirCompressorGui.class, 79, 34, 24, 17, AIR_COMPRESSOR);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AirCompressorMenu.class, AIR_COMPRESSOR,
                36, 5, 0, 36);
        registration.addRecipeTransferHandler(ShakerMenu.class, COCKTAIL,
                36, 5, 0, 36);
    }

    public static final ResourceLocation UID = new ResourceLocation(Kitchenkarrot.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }
}
