package com.sergioaguiar.mirageessentials.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cobblemon.mod.common.api.abilities.PotentialAbility;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.abilities.HiddenAbilityType;

import net.minecraft.text.MutableText;

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
        return pokemon.getStatus().getStatus().getShowdownName();
    }

    public static List<ElementalType> getPokemonTypes(Pokemon pokemon)
    {
        return StreamSupport
            .stream(pokemon.getTypes().spliterator(), false)
            .collect(Collectors.toUnmodifiableList());
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
}
