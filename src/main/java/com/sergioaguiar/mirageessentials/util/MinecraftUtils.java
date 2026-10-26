package com.sergioaguiar.mirageessentials.util;

import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;

import net.kyori.adventure.text.event.HoverEvent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MinecraftUtils
{
    public static Text getItemNBTName(ItemStack item)
    {
        return item.get(DataComponentTypes.ITEM_NAME);
    }

    public static Text getItemAnvilName(ItemStack item)
    {
        return item.get(DataComponentTypes.CUSTOM_NAME);
    }

    public static Text getItemBaseNameText(ItemStack item)
    {
        return item.getItem().getName();
    }

    public static String getItemBaseNameString(ItemStack item)
    {
        return item.getItem().getName().getString();
    }

    public static String getItemRealNameString(ItemStack item)
    {
        Text anvilName = MinecraftUtils.getItemAnvilName(item);
        Text nbtName = MinecraftUtils.getItemNBTName(item);

        return anvilName != null && !anvilName.getString().isEmpty()
            ? anvilName.getString()
            : nbtName != null && !nbtName.getString().isEmpty()
                ? nbtName.getString()
                : MinecraftUtils.getItemBaseNameString(item);
    }

    public static ItemStack clearItemAnvilName(ItemStack item)
    {
        ItemStack newItem = item.copy();
        item.remove(DataComponentTypes.CUSTOM_NAME);

        return newItem;
    }

    public static boolean isNBTEditedItem(ItemStack item)
    {
        Text itemName = getItemNBTName(item);

        return itemName != null && !itemName.getString().isEmpty();
    }

    public static String getItemColor(ItemStack item)
    {
        if (item == null || item.isEmpty()) return ChatMiniMessage.getItemColor();

        return isNBTEditedItem(item)
            ? String.format("#%06x", item.getItemBarColor())
            : ChatMiniMessage.getItemColor();
    }

    public static String getItemBracketColor(ItemStack item)
    {
        if (item == null || item.isEmpty()) return ChatMiniMessage.getItemBracketColor();

        return isNBTEditedItem(item)
            ? String.format("#%06x", item.getItemBarColor())
            : ChatMiniMessage.getItemBracketColor();
    }

    public static Text getItemHoverableText(ItemStack item)
    {
        return MiniMessageUtils.renderItemHoverable(item, HoverEvent.showItem(MiniMessageUtils.toShowItem(item)));
    }
}
