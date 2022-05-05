package io.github.tt432.kitchenkarrot.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ticket.ITicketGetter;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author DustW
 **/
public class EatFastItem extends Item implements RemainItemItem {
    int tick;
    ItemStack remainItem = ItemStack.EMPTY;

    public EatFastItem(Properties properties, int tick) {
        super(properties);
        this.tick = tick;
    }

    public EatFastItem(Properties p_42979_, int tick, ItemStack remainItem) {
        this(p_42979_, tick);
        this.remainItem = remainItem;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        itemStack = livingEntity.eat(level, itemStack);

        if (itemStack.isEmpty()) {
            return getRemainItem();
        }

        if (livingEntity instanceof Player player) {
            player.getInventory().add(getRemainItem());
        }

        return itemStack;
    }

    @Override
    public int getUseDuration(ItemStack p_43001_) {
        return tick;
    }

    @Override
    public ItemStack getRemainItem() {
        return remainItem;
    }
}
