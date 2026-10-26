package com.sergioaguiar.mirageessentials.parser;

import java.util.ArrayList;
import java.util.List;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.pc.PCBox;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import com.sergioaguiar.mirageessentials.config.chatparser.colors.ChatColors;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings;
import com.sergioaguiar.mirageessentials.util.MinecraftUtils;
import com.sergioaguiar.mirageessentials.util.MiniMessageUtils;
import com.sergioaguiar.mirageessentials.util.NeoDaycareUtils;
import com.sergioaguiar.mirageessentials.util.TextUtils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlaceholderResolver
{
    public static Text resolveText(ServerPlayerEntity player, String input) 
    {
        try 
        {
            String lower = input.toLowerCase().replaceAll("\\s+", "");
            if (lower.startsWith("party:") || lower.startsWith("poke:")) 
            {
                String[] parts = lower.split(":");
                int slot = Integer.parseInt(parts[1]);
                boolean isClosedSheet = parts.length > 2 && parts[2].equals("closed");

                return getPartyPokemon(player, slot, isClosedSheet);
            }
            else if (lower.startsWith("pc:")) 
            {
                String[] parts = lower.split(":");
                int box = Integer.parseInt(parts[1]);
                int slot = Integer.parseInt(parts[2]);
                boolean isClosedSheet = parts.length > 3 && parts[3].equals("closed");

                return getPCPokemonName(player, box, slot, isClosedSheet);
            }
            else if (lower.startsWith("item"))
            {
                if (lower.startsWith("item:"))
                {
                    String[] parts = lower.split(":");
                    int index = Integer.parseInt(parts[1]);

                    return getHotbarItem(player, index);
                }

                return getMainHandItem(player);
            }
            else if (lower.startsWith("help"))
            {
                if (lower.startsWith("help:"))
                {
                    String[] parts = lower.split(":");
                    String helpType = parts[1];

                    if (helpType.equals("shout") || helpType.equals("shouts") || helpType.equals("shouting"))
                    {
                        return getHelpShoutText();
                    }
                }

                return getHelpText();
            }
        } 
        catch (Exception e) {}

        return null;
    }

    public static Text getPartyPokemon(ServerPlayerEntity player, int slot, boolean isClosedSheet)
    {
        if (slot < 1 || slot > 6) return TextUtils.errorPlaceholder("Invalid Slot");

        PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(player);
        Pokemon pokemon = party.get(slot - 1);

        if (pokemon == null) return TextUtils.errorPlaceholder("Empty Slot");

        return buildPokemonText(pokemon, isClosedSheet);
    }

    public static ArrayList<Text> getAllPartyPokemon(ServerPlayerEntity player, boolean isClosedSheet)
    {
        PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(player);
        ArrayList<Text> pokemonInfos = new ArrayList<>();

        for (int i = 0; i < party.size(); i++)
        {
            Pokemon pokemon = party.get(i);
            if (pokemon == null) continue;

            pokemonInfos.add(buildPokemonText(pokemon, isClosedSheet));
        }
        return pokemonInfos;
    }

    public static Text getPCPokemonName(ServerPlayerEntity player, int box, int slot, boolean isClosedSheet)
    {
        PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(player);

        if (box < 1 || box > pc.getBoxes().size()) return TextUtils.errorPlaceholder("Invalid Box");
        if (slot < 1 || slot > 30) return TextUtils.errorPlaceholder("Invalid Slot");

        PCBox boxStorage = pc.getBoxes().get(box - 1);
        Pokemon pokemon = boxStorage.get(slot - 1);

        if (pokemon == null) return TextUtils.errorPlaceholder("Empty Slot");
        
        return buildPokemonText(pokemon, isClosedSheet);
    }

    public static Text buildPokemonText(Pokemon pokemon, boolean isClosedSheet)
    {
        Component tooltip = NeoDaycareUtils.isEgg(pokemon)
            ? MiniMessageUtils.getPokeInfoComponent(pokemon, false, isClosedSheet) // TODO: EDIT TO EGG ONE LATER
            : MiniMessageUtils.getPokeInfoComponent(pokemon, false, isClosedSheet);

        return MiniMessageUtils.renderPokeHoverable(pokemon, isClosedSheet, HoverEvent.showText(tooltip));
    }

    public static Text getMainHandItem(ServerPlayerEntity player)
    {
        if (player == null)
        {
            return TextUtils.errorPlaceholder("Invalid Player");
        }

        ItemStack stack = player.getMainHandStack();

        if (stack == null || stack.isEmpty())
        {
            return TextUtils.errorPlaceholder("Empty Slot");
        }

        return MinecraftUtils.getItemHoverableText(stack);
    }

    public static Text getHelpText()
    {
        return TextUtils.getHelpText();
    }

    public static Text getHelpShoutText()
    {
        return TextUtils.getHelpShoutText();
    }

    public static Text getHotbarItem(ServerPlayerEntity player, int index)
    {
        if (player == null)
        {
            return TextUtils.errorPlaceholder("Invalid Player");
        }

        if (index < 1 || index > 9)
        {
            return TextUtils.errorPlaceholder("Invalid Slot");
        }

        ItemStack stack = player.getInventory().getStack(index - 1);

        if (stack == null || stack.isEmpty())
        {
            return TextUtils.errorPlaceholder("Empty Slot");
        }

        return MinecraftUtils.getItemHoverableText(stack);
    }

    public static Text buildEggTooltip(Pokemon pokemon)
    {
        Species species = pokemon.getSpecies();
        String nickname = (pokemon.getNickname() == null || pokemon.getNickname().getLiteralString() == null) 
            ? species.getName() : pokemon.getNickname().getLiteralString();

        TextUtils.CustomTextBuilder tooltipBuilder = new TextUtils.CustomTextBuilder();
        boolean first = true;

        if (ChatSettings.shouldShowEggNickname())
        {
            if (first) first = false;
            else tooltipBuilder.append(Text.literal("\n"));

            tooltipBuilder.append
            (
                nickname,
                ChatColors.getTooltipEggNicknameColor()
            );
        }

        if (ChatSettings.shouldshowEggHatchStepProgress())
        {
            if (first) first = false;
            else tooltipBuilder.append(Text.literal("\n"));

            tooltipBuilder.append(getStepsText(pokemon));
        }

        if (ChatSettings.shouldShowEggStageMessage())
        {
            if (first) first = false;
            else tooltipBuilder.append(Text.literal("\n"));

            tooltipBuilder.append(Text.literal("\n"));
            tooltipBuilder.append(getHatchStageText(pokemon));
        }

        return tooltipBuilder.getText();
    }

    public static List<Text> getEggTooltipTextList(Pokemon pokemon)
    {
        List<Text> tooltipText = new ArrayList<>();

        if (ChatSettings.shouldshowEggHatchStepProgress())
        {
            tooltipText.add(getStepsText(pokemon));
        }

        if (ChatSettings.shouldShowEggStageMessage())
        {
            List<Text> eggStageLines = TextUtils.coloredEggStageLineList(pokemon);

            if (tooltipText.isEmpty())
            {
                eggStageLines.remove(0);
            }
            
            tooltipText.addAll(eggStageLines);
        }

        return tooltipText;
    }

    private static Text getStepsText(Pokemon pokemon)
    {
        return TextUtils.coloredStepsLine(pokemon);
    }

    private static Text getHatchStageText(Pokemon pokemon)
    {
        return TextUtils.coloredEggStageLine(pokemon);
    }
}
