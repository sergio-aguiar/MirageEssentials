package com.sergioaguiar.mirageessentials.util;

import java.util.ArrayList;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.parser.PlaceholderResolver;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ShoutUtils
{
    public static void doDebugShout(ServerPlayerEntity player, Pokemon pokemon)
    {
        if (pokemon == null)
        {
            player.sendMessage(MiniMessageUtils.renderDebugShoutEmptySlotText(), false);
            return;
        }

        player.sendMessage(MiniMessageUtils.renderDebugShoutSuccessText(pokemon));
    }

    public static void doPCShout(ServerPlayerEntity player, Pokemon pokemon, int box, int slot, boolean closed, boolean self)
    {
        if (pokemon == null)
        {
            player.sendMessage(MiniMessageUtils.renderPcShoutEmptySlotText(), false);
            return;
        }

        Text shoutText = MiniMessageUtils.renderPcShoutSuccessText(player, pokemon, closed);

        if (self) player.sendMessage(shoutText, false);
        else player.getServer().getPlayerManager().broadcast(shoutText, false);
    }

    public static void doPartyShout(ServerPlayerEntity player, Pokemon pokemon, boolean closed, boolean self)
    {
        if (pokemon == null)
        {
            player.sendMessage(MiniMessageUtils.renderPartyShoutEmptySlotText(), false);
            return;
        }

        Text shoutText = MiniMessageUtils.renderPcShoutSuccessText(player, pokemon, closed);

        if (self) player.sendMessage(shoutText, false);
        else player.getServer().getPlayerManager().broadcast(shoutText, false);
    }

    public static void doPartyShoutAll(ServerPlayerEntity player, boolean closed, boolean self)
    {
        ArrayList<Text> pokemonInfos = PlaceholderResolver.getAllPartyPokemon(player, closed);

        if (pokemonInfos.isEmpty())
        {
            player.sendMessage(MiniMessageUtils.renderPartyShoutAllEmptyPartyText(), false);
            return;
        }
        
        Text shoutText = MiniMessageUtils.renderPartyShoutAllSuccessText(player, Cobblemon.INSTANCE.getStorage().getParty(player), closed);

        if (self) player.sendMessage(shoutText, false);
        else player.getServer().getPlayerManager().broadcast(shoutText, false);
    }

    public static void doItemShout(ServerPlayerEntity player, int itemIndex)
    {
        ItemStack stack = player.getInventory().getStack(itemIndex - 1);
        doItemShout(player, stack);
    }

    public static void doItemShout(ServerPlayerEntity player, ItemStack stack)
    {
        if (stack == null || stack.isEmpty())
        {
            player.sendMessage(MiniMessageUtils.renderItemShoutEmptySlotText(), false);
            return;
        }
        
        player.getServer().getPlayerManager().broadcast(MiniMessageUtils.renderItemShoutSuccessText(player, stack), false);
    }
}
