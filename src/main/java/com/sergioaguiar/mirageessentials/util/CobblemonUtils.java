package com.sergioaguiar.mirageessentials.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cobblemon.mod.common.api.abilities.PotentialAbility;
import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.pokemon.egg.EggGroup;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Nature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.abilities.HiddenAbilityType;
import com.cobblemon.mod.common.pokemon.status.PersistentStatusContainer;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings;
import com.sergioaguiar.mirageessentials.config.chatparser.strings.ChatStrings;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class CobblemonUtils 
{
    public static String getPokemonName(MutableText nickname, String species)
    {
        return (nickname == null || nickname.getLiteralString() == null) 
            ? species
            : nickname.getLiteralString();
    }

    public static String getPokemonSpecies(Pokemon pokemon)
    {
        return pokemon.getSpecies().getName();
    }

    public static String getPokemonTitle(Pokemon pokemon)
    {
        return pokemon.getActiveMark() == null
            ? "" 
            : pokemon.getActiveMark().getTitle();
    }

    public static String getPokemonCaughtBall(Pokemon pokemon)
    {
        return pokemon.getCaughtBall().item.getName().getString();
    }

    public static String getPokemonCurrentHealth(Pokemon pokemon)
    {
        return String.valueOf(pokemon.getCurrentHealth());
    }

    public static String getPokemonMaxHealth(Pokemon pokemon)
    {
        return String.valueOf(pokemon.getMaxHealth());
    }

    public static String getPokemonStatus(Pokemon pokemon)
    {
        PersistentStatusContainer status = pokemon.getStatus();

        return status == null ? "" : status.getStatus().getShowdownName();
    }

    public static List<ElementalType> getPokemonTypes(Pokemon pokemon)
    {
        return StreamSupport
            .stream(pokemon.getTypes().spliterator(), false)
            .collect(Collectors.toUnmodifiableList());
    }

    public static String getPokemonBaseForm(Pokemon pokemon)
    {
        return pokemon.getForm().getName();
    }

    public static ItemStack getPokemonHeldItem(Pokemon pokemon)
    {
        return pokemon.getHeldItem$common();
    }

    public static ItemStack getPokemonCosmeticItem(Pokemon pokemon)
    {
        return pokemon.getCosmeticItem();
    }

    public static String getPokemonItemName(ItemStack item)
    {
        if (item == null || item.isEmpty()) return ChatStrings.getEmptyHeldItemString();

        Text itemName = item.get(DataComponentTypes.ITEM_NAME);

        return itemName != null && itemName.getString().length() > 0
            ? itemName.getString()
            : item.getName().getString();
    }

    public static String getPokemonItemCustomName(ItemStack item)
    {
        if (item == null || item.isEmpty()) return ChatStrings.getEmptyHeldItemString();

        Text itemName = item.get(DataComponentTypes.ITEM_NAME);

        return itemName != null && itemName.getString().length() > 0
            ? itemName.getString()
            : ChatSettings.shouldShowOriginalItemNames()
                ? item.getItem().getName().getString()
                : item.getName().getString();
    }

    public static String getPokemonAbility(Pokemon pokemon)
    {
        return Text.translatable(pokemon.getAbility().getDisplayName()).getString();
    }

    public static boolean hasHiddenAbility(Pokemon pokemon)
    {
        if (pokemon == null) return false;

        HashSet<String> uniqueAbilities = new HashSet<>();
        boolean isHidden = false;

        for (final PotentialAbility ability : pokemon.getForm().getAbilities())
        {
            uniqueAbilities.add(ability.getTemplate().getDisplayName());
            if (ability.getType() == HiddenAbilityType.INSTANCE && ability.getTemplate() == pokemon.getAbility().getTemplate()) isHidden = true;
        }

        return isHidden && uniqueAbilities.size() > 1 ? true : false;
    }

    public static boolean hasForcedAbility(Pokemon pokemon)
    {
        if (pokemon == null) return false;
        if (pokemon.getAbility().getForced()) return true;
        return false;
    }

    public static String getPokemonNatureName(Nature nature)
    {
        return nature.getDisplayName().toString();
    }

    public static String getPokemonNatureIncreasedStat(Nature nature)
    {
        return nature.getIncreasedStat().toString();
    }

    public static String getPokemonNatureDecreasedStat(Nature nature)
    {
        return nature.getIncreasedStat().toString();
    }

    public static boolean isNeutralNature(Nature nature)
    {
        return nature.getIncreasedStat() != null && nature.getDecreasedStat() != null;
    }

    public static boolean isMinted(Nature realNature, Nature effectiveNature)
    {
        return !realNature.getDisplayName().toString().equals(effectiveNature.getDisplayName().toString());
    }

    public static String getPokemonFriendship(Pokemon pokemon)
    {
        return Integer.toString(pokemon.getFriendship());
    }

    public static List<Move> getPokemonMoves(Pokemon pokemon)
    {
        return pokemon.getMoveSet().getMoves();
    }

    public static int getRealIVTotal(IVs ivs)
    {
        int hp = ivs.get(Stats.HP);
        int atk = ivs.get(Stats.ATTACK);
        int def = ivs.get(Stats.DEFENCE);
        int spa = ivs.get(Stats.SPECIAL_ATTACK);
        int spd = ivs.get(Stats.SPECIAL_DEFENCE);
        int spe = ivs.get(Stats.SPEED);

        return hp + atk + def + spa + spd + spe;
    }

    public static int getEffectiveIVTotal(IVs ivs)
    {
        int hp = ivs.getEffectiveBattleIV(Stats.HP);
        int atk = ivs.getEffectiveBattleIV(Stats.ATTACK);
        int def = ivs.getEffectiveBattleIV(Stats.DEFENCE);
        int spa = ivs.getEffectiveBattleIV(Stats.SPECIAL_ATTACK);
        int spd = ivs.getEffectiveBattleIV(Stats.SPECIAL_DEFENCE);
        int spe = ivs.getEffectiveBattleIV(Stats.SPEED);

        return hp + atk + def + spa + spd + spe;
    }

    public static boolean isHyperTrained(IVs ivs)
    {
        if (ivs.get(Stats.HP) != ivs.getEffectiveBattleIV(Stats.HP)) return true;
        if (ivs.get(Stats.ATTACK) != ivs.getEffectiveBattleIV(Stats.ATTACK)) return true;
        if (ivs.get(Stats.DEFENCE) != ivs.getEffectiveBattleIV(Stats.DEFENCE)) return true;
        if (ivs.get(Stats.SPECIAL_ATTACK) != ivs.getEffectiveBattleIV(Stats.SPECIAL_ATTACK)) return true;
        if (ivs.get(Stats.SPECIAL_DEFENCE) != ivs.getEffectiveBattleIV(Stats.SPECIAL_DEFENCE)) return true;
        if (ivs.get(Stats.SPEED) != ivs.getEffectiveBattleIV(Stats.SPEED)) return true;
        return false;
    }

    public static Set<Stats> getHyperTrainedStats(IVs ivs)
    {
        Set<Stats> hyperTrainedStats = new HashSet<>();

        if (ivs.get(Stats.HP) != ivs.getEffectiveBattleIV(Stats.HP)) hyperTrainedStats.add(Stats.HP);
        if (ivs.get(Stats.ATTACK) != ivs.getEffectiveBattleIV(Stats.ATTACK)) hyperTrainedStats.add(Stats.ATTACK);
        if (ivs.get(Stats.DEFENCE) != ivs.getEffectiveBattleIV(Stats.DEFENCE)) hyperTrainedStats.add(Stats.DEFENCE);
        if (ivs.get(Stats.SPECIAL_ATTACK) != ivs.getEffectiveBattleIV(Stats.SPECIAL_ATTACK)) hyperTrainedStats.add(Stats.SPECIAL_ATTACK);
        if (ivs.get(Stats.SPECIAL_DEFENCE) != ivs.getEffectiveBattleIV(Stats.SPECIAL_DEFENCE)) hyperTrainedStats.add(Stats.SPECIAL_DEFENCE);
        if (ivs.get(Stats.SPEED) != ivs.getEffectiveBattleIV(Stats.SPEED)) hyperTrainedStats.add(Stats.SPEED);

        return hyperTrainedStats;
    }

    public static int getEVTotal(EVs evs)
    {
        int hp = evs.get(Stats.HP);
        int atk = evs.get(Stats.ATTACK);
        int def = evs.get(Stats.DEFENCE);
        int spa = evs.get(Stats.SPECIAL_ATTACK);
        int spd = evs.get(Stats.SPECIAL_DEFENCE);
        int spe = evs.get(Stats.SPEED);

        return hp + atk + def + spa + spd + spe;
    }

    public static String getPokemonSizeName(Pokemon pokemon)
    {
        return pokemon.getSizeCategory().name();
    }
    
    public static ElementalType getElementalTypeFromShowdownId(String showdownId)
    {
        if (showdownId.equals(ElementalTypes.BUG.getShowdownId())) return ElementalTypes.BUG;
        if (showdownId.equals(ElementalTypes.DARK.getShowdownId())) return ElementalTypes.DARK;
        if (showdownId.equals(ElementalTypes.DRAGON.getShowdownId())) return ElementalTypes.DRAGON;
        if (showdownId.equals(ElementalTypes.ELECTRIC.getShowdownId())) return ElementalTypes.ELECTRIC;
        if (showdownId.equals(ElementalTypes.FAIRY.getShowdownId())) return ElementalTypes.FAIRY;
        if (showdownId.equals(ElementalTypes.FIGHTING.getShowdownId())) return ElementalTypes.FIGHTING;
        if (showdownId.equals(ElementalTypes.FIRE.getShowdownId())) return ElementalTypes.FIRE;
        if (showdownId.equals(ElementalTypes.FLYING.getShowdownId())) return ElementalTypes.FLYING;
        if (showdownId.equals(ElementalTypes.GHOST.getShowdownId())) return ElementalTypes.GHOST;
        if (showdownId.equals(ElementalTypes.GRASS.getShowdownId())) return ElementalTypes.GRASS;
        if (showdownId.equals(ElementalTypes.GROUND.getShowdownId())) return ElementalTypes.GROUND;
        if (showdownId.equals(ElementalTypes.ICE.getShowdownId())) return ElementalTypes.ICE;
        if (showdownId.equals(ElementalTypes.NORMAL.getShowdownId())) return ElementalTypes.NORMAL;
        if (showdownId.equals(ElementalTypes.POISON.getShowdownId())) return ElementalTypes.POISON;
        if (showdownId.equals(ElementalTypes.PSYCHIC.getShowdownId())) return ElementalTypes.PSYCHIC;
        if (showdownId.equals(ElementalTypes.ROCK.getShowdownId())) return ElementalTypes.ROCK;
        if (showdownId.equals(ElementalTypes.STEEL.getShowdownId())) return ElementalTypes.STEEL;
        if (showdownId.equals(ElementalTypes.WATER.getShowdownId())) return ElementalTypes.WATER;
        return ElementalTypes.BUG;
    }

    public static String getEggGroupTemplateName(EggGroup eggGroup)
    {
        switch (eggGroup) {
            case AMORPHOUS:
                return "Amorphous";
            case BUG:
                return "Bug";
            case DITTO:
                return "Ditto";
            case DRAGON:
                return "Dragon";
            case FAIRY:
                return "Fairy";
            case FIELD:
                return "Field";
            case FLYING:
                return "Flying";
            case GRASS:
                return "Grass";
            case HUMAN_LIKE:
                return "Human-Like";
            case MINERAL:
                return "Mineral";
            case MONSTER:
                return "Monster";
            case UNDISCOVERED:
                return "Undiscovered";
            case WATER_1:
                return "Water1";
            case WATER_2:
                return "Water2";
            case WATER_3:
                return "Water3";
            default:
                return "Undiscovered";
        }
    }

    public static String getPokemonOTName(Pokemon pokemon)
    {
        return pokemon.getOriginalTrainer();
    }

    public static String getPokemonOTDisplayName(Pokemon pokemon)
    {
        return pokemon.getOriginalTrainerName();
    }
}
