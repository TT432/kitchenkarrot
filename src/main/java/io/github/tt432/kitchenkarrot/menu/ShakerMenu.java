package io.github.tt432.kitchenkarrot.menu;

import io.github.tt432.kitchenkarrot.item.ShakerItem;
import io.github.tt432.kitchenkarrot.menu.base.KKMenu;
import io.github.tt432.kitchenkarrot.menu.reg.ModMenuTypes;
import io.github.tt432.kitchenkarrot.recipes.register.RecipeManager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class ShakerMenu extends KKMenu {
    ItemStack itemStack;

    public ShakerMenu(int pContainerId, Inventory inventory) {
        super(ModMenuTypes.SHAKER.get(), pContainerId, inventory);

        itemStack = inventory.getSelected();
        addSlots();
        finishRecipe();
    }

    private void finishRecipe() {
        if (ShakerItem.getFinish(itemStack)) {
            itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                var list = getInputs(handler);

                RecipeManager.getCocktailRecipes(inventory.player.level).stream()
                    .filter(r -> r.matches(list)).findFirst().ifPresent(cocktailRecipe -> {
                        var result = handler.insertItem(11, cocktailRecipe.getResultItem(), false);

                        if (!result.isEmpty()) {
                            inventory.player.drop(result, true);
                        }

                        for (int i = 0; i < 5; i++) {
                            handler.extractItem(i, 1, false);
                        }
                    });
            });

            ShakerItem.setFinish(itemStack, false);
        }
    }

    List<ItemStack> getInputs(IItemHandler handler) {
        var list = new ArrayList<ItemStack>();

        for (int i = 0; i < 5; i++) {
            list.add(handler.getStackInSlot(i));
        }

        return list;
    }

    @Override
    protected Slot addSlot(IItemHandler handler, int index, int x, int y) {
        return addSlot(new SlotItemHandler(handler, index, x, y) {
            @Override
            public void setChanged() {
                itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    var list = getInputs(handler);

                    var recipe = RecipeManager.getCocktailRecipes(inventory.player.level)
                            .stream().filter(r -> r.matches(list)).findFirst();
                    if (recipe.isPresent()) {
                        ShakerItem.setRecipeTime(itemStack, recipe.get());
                    }
                    else {
                        ShakerItem.setRecipeTime(itemStack, null);
                    }
                });
            }
        });
    }

    void addSlots() {
        itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(h, 0, 110 + 1, 25 + 1);
            addSlot(h, 1, 128 + 1, 25 + 1);
            addSlot(h, 2, 146 + 1, 25 + 1);
            addSlot(h, 3, 118 + 1, 43 + 1);
            addSlot(h, 4, 136 + 1, 43 + 1);

            addSlot(h, 5, 12 + 1, 13 + 1);
            addSlot(h, 6, 30 + 1, 13 + 1);
            addSlot(h, 7, 12 + 1, 31 + 1);
            addSlot(h, 8, 30 + 1, 31 + 1);

            addSlot(h, 9, 12 + 1, 49 + 1);
            addSlot(h, 10, 30 + 1, 49 + 1);

            addResultSlot(h, 11, 71 + 1, 33 + 1);
        });
    }
}
