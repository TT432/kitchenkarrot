package io.github.tt432.kitchenkarrot.item;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.client.CocktailList;
import io.github.tt432.kitchenkarrot.recipes.object.EffectStack;
import io.github.tt432.kitchenkarrot.recipes.recipe.CocktailRecipe;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author DustW
 **/
public class CocktailItem extends Item {
    public CocktailItem(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (p_41391_ == Kitchenkarrot.COCKTAIL_TAB) {
            for (String cocktail : CocktailList.INSTANCE.cocktails) {
                var stack = new ItemStack(this);
                setCocktail(stack, new ResourceLocation(cocktail));
                p_41392_.add(stack);
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        var recipe = get(pLevel, getCocktail(pStack));

        if (recipe != null && pLivingEntity instanceof Player player) {
            player.getFoodData().eat(recipe.content.hunger, recipe.content.saturation);

            if (!player.getAbilities().instabuild) {
                pStack.shrink(1);
            }

            for (EffectStack effectStack : recipe.content.effect) {
                player.addEffect(effectStack.get());
            }
        }

        return pStack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public String getDescriptionId(ItemStack p_41455_) {
        var name = getCocktail(p_41455_);

        if (name != null) {
            return name.toString().replace(":", ".");
        }

        return super.getDescriptionId(p_41455_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @org.jetbrains.annotations.Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        ResourceLocation name = getCocktail(p_41421_);

        if (name != null) {
            p_41423_.add(new TranslatableComponent(name.toString().replace(":", ".") + ".tooltip"));

            var recipe = get(p_41422_, getCocktail(p_41421_));

            if (recipe != null) {
                p_41423_.add(new TranslatableComponent("item.cocktail.author", recipe.author));
            }
        }
    }

    @Nullable
    public static ResourceLocation getCocktail(ItemStack itemStack) {
        if (itemStack.getTag() != null && itemStack.getTag().contains("cocktail")) {
            return new ResourceLocation(itemStack.getTag().getString("cocktail"));
        }

        return null;
    }

    public static void setCocktail(ItemStack itemStack, ResourceLocation name) {
        itemStack.getOrCreateTag().putString("cocktail", name.toString());
    }

    @Nullable
    public static CocktailRecipe get(Level level, ResourceLocation resourceLocation) {
        var a = level.getRecipeManager().byKey(resourceLocation);

        if (a.isPresent() && a.get().getType() == RecipeTypes.COCKTAIL.get()) {
            return (CocktailRecipe) a.get();
        }

        return null;
    }
}
