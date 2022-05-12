package io.github.tt432.kitchenkarrot.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author DustW
 **/
public class ModItemTags {
    public static final TagKey<Item> CONTAINER_ITEM =
            ItemTags.create(new ResourceLocation("kitchenkarrot:container_item"));
}
