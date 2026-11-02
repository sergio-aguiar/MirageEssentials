package com.sergioaguiar.mirageessentials.util;

import java.util.ArrayList;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.config.chatparser.colors.ChatColors;
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
            TextUtils.CustomTextBuilder messageBuilder = new TextUtils.CustomTextBuilder();

            messageBuilder.append
            (
                "PartyShout » ",
                ChatColors.getCommandPrefixColor()
            );

            messageBuilder.append
            (
                "You can not shout an empty slot!",
                ChatColors.getCommandValueColor()
            );

            player.sendMessage(messageBuilder.getText(), false);

            return;
        }

        TextUtils.CustomTextBuilder shoutBuilder = new TextUtils.CustomTextBuilder();

        shoutBuilder.append
        (
            "PartyShout » ",
            ChatColors.getCommandPrefixColor()
        );

        shoutBuilder.append
        (
            "Player ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append
        (
            player.getDisplayName().getString(),
            ChatColors.getCommandPlayerColor()
        );

        shoutBuilder.append
        (
            " shouted: ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append(PlaceholderResolver.buildPokemonText(pokemon, closed));

        if (self) player.sendMessage(shoutBuilder.getText(), false);
        else player.getServer().getPlayerManager().broadcast(shoutBuilder.getText(), false);
    }

    public static void doPartyShoutAll(ServerPlayerEntity player, boolean closed, boolean self)
    {
        ArrayList<Text> pokemonInfos = PlaceholderResolver.getAllPartyPokemon(player, closed);

        if (pokemonInfos.isEmpty())
        {
            TextUtils.CustomTextBuilder messageBuilder = new TextUtils.CustomTextBuilder();

            messageBuilder.append
            (
                "PartyShoutAll » ",
                ChatColors.getCommandPrefixColor()
            );

            messageBuilder.append
            (
                "You can not shout an empty party!",
                ChatColors.getCommandValueColor()
            );

            player.sendMessage(messageBuilder.getText(), false);
            return;
        }
        
        TextUtils.CustomTextBuilder shoutBuilder = new TextUtils.CustomTextBuilder();

        shoutBuilder.append
        (
            "PartyShoutAll » ",
            ChatColors.getCommandPrefixColor()
        );

        shoutBuilder.append
        (
            "Player ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append
        (
            player.getDisplayName().getString(),
            ChatColors.getCommandPlayerColor()
        );

        shoutBuilder.append
        (
            " shouted: ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append
        (
            "\n",
            ChatColors.getCommandValueColor()
        );

        int infoAmount = pokemonInfos.size();
        for (int i = 0; i < infoAmount; i++)
        {
            shoutBuilder.append(pokemonInfos.get(i));
            if (i == 2)
            {
                shoutBuilder.append
                (
                    "\n",
                    ChatColors.getCommandValueColor()
                );
            }
            else if (i != infoAmount - 1)
            {
                shoutBuilder.append
                (
                    " ",
                    ChatColors.getCommandValueColor()
                );
            }
        }

        if (self) player.sendMessage(shoutBuilder.getText(), false);
        else player.getServer().getPlayerManager().broadcast(shoutBuilder.getText(), false);
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
            TextUtils.CustomTextBuilder messageBuilder = new TextUtils.CustomTextBuilder();

            messageBuilder.append
            (
                "ItemShout » ",
                ChatColors.getCommandPrefixColor()
            );

            messageBuilder.append
            (
                "You can not shout an empty item slot!",
                ChatColors.getCommandValueColor()
            );

            player.sendMessage(messageBuilder.getText(), false);
            return;
        }

        TextUtils.CustomTextBuilder shoutBuilder = new TextUtils.CustomTextBuilder();

        shoutBuilder.append
        (
            "ItemShout » ",
            ChatColors.getCommandPrefixColor()
        );

        shoutBuilder.append
        (
            "Player ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append
        (
            player.getDisplayName().getString(),
            ChatColors.getCommandPlayerColor()
        );

        shoutBuilder.append
        (
            " shouted: ",
            ChatColors.getCommandValueColor()
        );

        shoutBuilder.append(MinecraftUtils.getItemHoverableText(stack));
        
        player.getServer().getPlayerManager().broadcast(shoutBuilder.getText(), false);
    }
}
