package io.github.tt432.kitchenkarrot.recipes.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.tt432.kitchenkarrot.recipes.base.BaseRecipe;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeSerializers;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author DustW
 **/
public class AirCompressorRecipe extends BaseRecipe<AirCompressorRecipe> {
    @Expose
    NonNullList<Ingredient> ingredient = NonNullList.withSize(4, Ingredient.EMPTY);
    @Expose
    @SerializedName("craftingtime")
    int craftingTime;
    @Expose
    Ingredient container;
    @Expose
    ItemStack result;

    @Override
    public boolean matches(List<ItemStack> inputs) {
        var temp = new LinkedHashSet<>(inputs);
        var ref = new Object() {
            ItemStack tempStack = ItemStack.EMPTY;
        };
        return inputs.size() > 4 && testContainer(inputs.get(4)) &&
                ingredient.stream().allMatch(i -> i == Ingredient.EMPTY ||
                        (i.test(ref.tempStack = temp.stream().filter(i).findFirst().orElse(ItemStack.EMPTY)) &&
                                !ref.tempStack.isEmpty() && temp.remove(ref.tempStack)));
    }

    public boolean testContainer(ItemStack stack) {
        return container == null || container.test(stack);
    }

    public Ingredient getContainer() {
        return container;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    public List<ItemStack> getMatchItems(List<ItemStack> inputs) {
        List<ItemStack> result = new ArrayList<>();
        var copy = new ArrayList<>(inputs);
        copy.remove(copy.size() - 1);
        ingredient.forEach(i -> {
            if (i != Ingredient.EMPTY) {
                ItemStack tempStack = copy.stream().filter(i).findFirst().orElse(ItemStack.EMPTY);
                if (!tempStack.isEmpty()) {
                    copy.remove(tempStack);
                    result.add(tempStack);
                }
            }
        });
        return result;
    }

    public int getCraftingTime() {
        return craftingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializers.AIR_COMPRESSOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypes.AIR_COMPRESSOR.get();
    }
}
