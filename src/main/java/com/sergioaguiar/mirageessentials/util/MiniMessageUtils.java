package com.sergioaguiar.mirageessentials.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.status.Statuses;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Nature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.config.chatparser.aspects.ChatAspects;
import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings;
import com.sergioaguiar.mirageessentials.config.chatparser.strings.ChatStrings;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minecraft.item.ItemStack;

public class MiniMessageUtils
{
    private static final MiniMessage MINIMESSAGE_INSTANCE = MiniMessage.miniMessage();

    // Cobblemon Status Showdown Names (cached for performance)
    private static final String BURN_SHOWDOWN_NAME = Statuses.BURN.getShowdownName();
    private static final String SLEEP_SHOWDOWN_NAME = Statuses.SLEEP.getShowdownName();
    private static final String PARALYSIS_SHOWDOWN_NAME = Statuses.PARALYSIS.getShowdownName();
    private static final String POISON_SHOWDOWN_NAME = Statuses.POISON.getShowdownName();
    private static final String POISON_BADLY_SHOWDOWN_NAME = Statuses.POISON_BADLY.getShowdownName();
    private static final String FROZEN_SHOWDOWN_NAME = Statuses.FROZEN.getShowdownName();

    private MiniMessageUtils() {}

    public static MiniMessage get()
    {
        return MINIMESSAGE_INSTANCE;
    }

    public static TagResolver getPokeInfoTagResolver(Pokemon pokemon)
    {
        String species = CobblemonUtils.getPokemonSpecies(pokemon);
        List<ElementalType> types = CobblemonUtils.getPokemonTypes(pokemon);
        boolean isMonotype = types.size() == 1;
        TeraType teraType = pokemon.getTeraType();
        ItemStack heldItem = CobblemonUtils.getPokemonHeldItem(pokemon);
        String heldItemName = CobblemonUtils.getPokemonItemName(heldItem);
        String heldItemCustomName = CobblemonUtils.getPokemonItemCustomName(heldItem);
        ItemStack cosmeticItem = CobblemonUtils.getPokemonCosmeticItem(pokemon);
        String cosmeticItemName = CobblemonUtils.getPokemonItemName(cosmeticItem);
        String cosmeticItemCustomName = CobblemonUtils.getPokemonItemCustomName(cosmeticItem);
        Nature realNature = pokemon.getNature();
        Nature effectiveNature = pokemon.getEffectiveNature();
        int level = pokemon.getLevel();
        int currentExperience = pokemon.getExperience();
        int RemainingExperience = pokemon.getExperienceToNextLevel();
        List<Move> moves = CobblemonUtils.getPokemonMoves(pokemon);
        IVs ivs = pokemon.getIvs();
        int totalRealIVs = CobblemonUtils.getRealIVTotal(ivs);
        int totalEffectiveIVs = CobblemonUtils.getEffectiveIVTotal(ivs);

        return TagResolver.builder()
                .resolver(getNameResolver(CobblemonUtils.getPokemonName(pokemon.getNickname(), species)))
                .resolver(getTitleResolver(CobblemonUtils.getPokemonTitle(pokemon)))
                .resolver(getGenderResolver(pokemon.getGender()))
                .resolver(getCaughtBallResolver(CobblemonUtils.getPokemonCaughtBall(pokemon)))
                .resolver(getCustomConditionResolver(pokemon))
                .resolvers(getCustomConditionResolvers(pokemon))
                .resolver(getCustomTypesResolver(types, teraType, isMonotype))
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype))
                .resolver(getCustomFormsResolver(pokemon))
                .resolvers(getCustomFormsResolvers(pokemon))
                .resolver(getCustomHeldItemResolver(heldItemName, heldItemCustomName))
                .resolvers(getCustomHeldItemResolvers(heldItemName, heldItemCustomName))
                .resolver(getCustomCosmeticItemResolver(cosmeticItemName, cosmeticItemCustomName))
                .resolvers(getCustomCosmeticItemResolvers(cosmeticItemName, cosmeticItemCustomName))
                .resolver(getAbilityResolver(CobblemonUtils.getPokemonAbility(pokemon)))
                .resolver(getCustomHiddenAbilityResolver(CobblemonUtils.hasHiddenAbility(pokemon)))
                .resolver(getNatureRealResolver(CobblemonUtils.getPokemonNatureName(realNature)))
                .resolver(getNatureEffectiveResolver(CobblemonUtils.getPokemonNatureName(effectiveNature)))
                .resolver(getCustomNatureRealStatsResolver(realNature))
                .resolvers(getCustomNatureRealStatsResolver(realNature))
                .resolver(getCustomNatureEffectiveStatsResolver(effectiveNature))
                .resolvers(getCustomNatureEffectiveStatsResolver(effectiveNature))
                .resolver(getCustomMintnessResolver(CobblemonUtils.isMinted(realNature, effectiveNature)))
                .resolver(getLevelResolver(String.valueOf(level)))
                .resolver(getCustomExperienceResolver(level, currentExperience, RemainingExperience))
                .resolvers(getCustomExperienceResolvers(currentExperience, RemainingExperience))
                .resolver(getFriendshipResolver(CobblemonUtils.getPokemonFriendship(pokemon)))
                .resolver(getMove1Resolver(moves))
                .resolver(getMove1ColorResolver(moves))
                .resolver(getMove1CustomPPResolver(moves))
                .resolvers(getMove1CustomPPResolvers(moves))
                .resolver(getMove2Resolver(moves))
                .resolver(getMove2ColorResolver(moves))
                .resolver(getMove2CustomPPResolver(moves))
                .resolvers(getMove2CustomPPResolvers(moves))
                .resolver(getMove3Resolver(moves))
                .resolver(getMove3ColorResolver(moves))
                .resolver(getMove3CustomPPResolver(moves))
                .resolvers(getMove3CustomPPResolvers(moves))
                .resolver(getMove4Resolver(moves))
                .resolver(getMove4ColorResolver(moves))
                .resolver(getMove4CustomPPResolver(moves))
                .resolvers(getMove4CustomPPResolvers(moves))
                .resolver(getCustomMovesResolver(moves))
                .resolver(getCustomGeneralIVsResolver(totalRealIVs, totalEffectiveIVs))
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs))
                .build();
    }

    private static TagResolver.Single getNameResolver(String name)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NAME_TEMPLATE_STRING,
            name
        );
    }

    private static TagResolver.Single getTitleResolver(String title)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TITLE_TEMPLATE_STRING,
            title
        );
    }

    private static TagResolver.Single getGenderResolver(Gender gender)
    {
        String template = switch (gender)
        {
            case MALE -> ChatMiniMessage.getMaleGenderTemplate();
            case FEMALE -> ChatMiniMessage.getFemaleGenderTemplate();
            default -> ChatMiniMessage.getGenderlessGenderTemplate();
        };

        return Placeholder.parsed
        (
            ChatMiniMessage.GENDER_TEMPLATE_STRING,
            template
        );
    }

    private static TagResolver.Single getCaughtBallResolver(String caughtBall)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CAUGHT_BALL_TEMPLATE_STRING,
            caughtBall
        );
    }

    private static TagResolver.Single getCurrentHealthResolver(String currentHealth)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CURRENT_HEALTH_TEMPLATE_STRING,
            currentHealth
        );
    }

    private static TagResolver.Single getMaxHealthResolver(String maxHealth)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MAX_HEALTH_TEMPLATE_STRING,
            maxHealth
        );
    }

    private static TagResolver.Single getStatusResolver(String statusShowdownName, boolean isFainted)
    {
        String template = isFainted
            ? ChatMiniMessage.getFaintTemplate() 
            : statusShowdownName.equals(BURN_SHOWDOWN_NAME)
                ? ChatMiniMessage.getBurnTemplate()
                : statusShowdownName.equals(SLEEP_SHOWDOWN_NAME)
                    ? ChatMiniMessage.getSleepTemplate()
                    : statusShowdownName.equals(PARALYSIS_SHOWDOWN_NAME)
                        ? ChatMiniMessage.getParalysisTemplate()
                        : statusShowdownName.equals(POISON_SHOWDOWN_NAME) || statusShowdownName.equals(POISON_BADLY_SHOWDOWN_NAME)
                            ? ChatMiniMessage.getPoisonTemplate()
                            : statusShowdownName.equals(FROZEN_SHOWDOWN_NAME)
                                ? ChatMiniMessage.getFreezeTemplate()
                                : "";

        return Placeholder.parsed
        (
            ChatMiniMessage.STATUS_TEMPLATE_STRING,
            template
        );
    }

    public static TagResolver.Single getCustomStatusResolver(Pokemon pokemon)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomStatusTemplate(),
            TagResolver.builder()
                .resolvers(getCustomStatusResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomStatusResolvers(Pokemon pokemon)
    {
        return new TagResolver.Single[]
        {
            getStatusResolver(CobblemonUtils.getPokemonStatus(pokemon), pokemon.isFainted())
        };
    }

    public static TagResolver.Single getCustomConditionResolver(Pokemon pokemon)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomConditionTemplate(),
            TagResolver.builder()
                .resolvers(getCustomConditionResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_CONDITION_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomConditionResolvers(Pokemon pokemon)
    {
        return new TagResolver.Single[]
        {
            getCurrentHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon)),
            getMaxHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon)),
            getStatusResolver(CobblemonUtils.getPokemonStatus(pokemon), pokemon.isFainted()),
            getCustomStatusResolver(pokemon)
        };
    }

    private static TagResolver.Single getType1Resolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE1_TEMPLATE_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    private static TagResolver.Single getType1ColorResolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE1_COLOR_TEMPLATE_STRING,
            String.format("#%06x", type.getPrimaryColor())
        );
    }

    private static TagResolver.Single getType2Resolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE2_TEMPLATE_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    private static TagResolver.Single getType2ColorResolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE2_COLOR_TEMPLATE_STRING,
            String.format("#%06x", type.getPrimaryColor())
        );
    }

    private static TagResolver.Single getTeraTypeResolver(TeraType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TERA_TYPE_TEMPLATE_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    private static TagResolver.Single getTeraTypeColorResolver(TeraType type)
    {
        ElementalType elementalType = CobblemonUtils.getElementalTypeFromShowdownId(type.showdownId());

        return Placeholder.unparsed
        (
            ChatMiniMessage.TERA_TYPE_COLOR_TEMPLATE_STRING,
            String.format("#%06x", elementalType.getPrimaryColor())
        );
    }

    public static TagResolver.Single getCustomTypesResolver(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_TYPES_TEMPLATE_STRING,
            isMonotype 
                ? getCustomTypesMonotypeComponent(types, teraType, isMonotype)
                : getCustomTypesDuotypeComponent(types, teraType, isMonotype)
        );
    }

    public static Component getCustomTypesMonotypeComponent(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomTypesMonotypeTemplate(),
            TagResolver.builder()
                .resolvers(getCustomTypesMonotypeResolvers(types, teraType, isMonotype))
                .build()
        );
    }

    public static Component getCustomTypesDuotypeComponent(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomTypesDuotypeTemplate(),
            TagResolver.builder()
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype))
                .build()
        );
    }

    public static TagResolver.Single[] getCustomTypesMonotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return new TagResolver.Single[]
        {
            getType1Resolver(types.get(0)),
            getType1ColorResolver(types.get(0)),
            getTeraTypeResolver(teraType),
            getTeraTypeColorResolver(teraType)
        };
    }

    public static TagResolver.Single[] getCustomTypesDuotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return new TagResolver.Single[]
        {
            getType1Resolver(types.get(0)),
            getType1ColorResolver(types.get(0)),
            getType2Resolver(isMonotype ? types.get(0) : types.get(1)),
            getType2ColorResolver(isMonotype ? types.get(0) : types.get(1)),
            getTeraTypeResolver(teraType),
            getTeraTypeColorResolver(teraType)
        };
    }

    private static TagResolver.Single getCustomShininessResolver(Pokemon pokemon)
    {
        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_SHININESS_TEMPLATE_STRING,
            pokemon.getShiny()
                ? ChatMiniMessage.getCustomShininessTemplate()
                : ""
        );
    }

    private static TagResolver.Single getCustomAlphanessResolver(Pokemon pokemon)
    {
        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_ALPHANESS_TEMPLATE_STRING,
            pokemon.getShiny()
                ? ChatMiniMessage.getCustomAlphanessTemplate()
                : ""
        );
    }

    private static TagResolver.Single getFormResolver(String form)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.FORM_TEMPLATE_STRING,
            form
        );
    }

    private static TagResolver.Single getFormsExpandedResolver(String form, List<SpeciesFeature> features)
    {
        StringBuilder result = getExpandedFormStringBuilder(form, features);

        return Placeholder.component(
                ChatMiniMessage.FORMS_EXPANDED_TEMPLATE_STRING,
                MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(result.toString())
        );
    }

    private static TagResolver.Single getFormsFullyExpandedResolver(String form, List<SpeciesFeature> features, Set<String> aspects)
    {
        StringBuilder result = getExpandedFormStringBuilder(form, features);
        result = getFullyExpandedFormStringBuilder(result, aspects);

        return Placeholder.component(
                ChatMiniMessage.FORMS_FULLY_EXPANDED_TEMPLATE_STRING,
                MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(result.toString())
        );
    }

    private static StringBuilder getExpandedFormStringBuilder(String form, List<SpeciesFeature> features)
    {
        StringBuilder result = new StringBuilder();
        List<StringSpeciesFeature> allowedSpeciesFeatures = new LinkedList<>();
        boolean isFormNormal = form.equals(TextUtils.NORMAL_FORM_STRING);

        features
            .stream()
            .filter(x -> x instanceof StringSpeciesFeature)
            .filter(x -> !ChatAspects.isSpeciesFeatureIgnored(x.getName()))
            .forEach(x -> allowedSpeciesFeatures.add((StringSpeciesFeature) x));

        if (!isFormNormal || ChatSettings.shouldShowFormIfNormal())
        {
            result.append(renderCustomForm(form));
        }

        if (isFormNormal && !allowedSpeciesFeatures.isEmpty())
        {
            for (StringSpeciesFeature stringFeature : allowedSpeciesFeatures)
            {
                if (stringFeature == null) continue;

                String featureKey = stringFeature.getName();
                String featureValue = stringFeature.getValue();

                if (featureKey.equals(ChatAspects.SPECIES_FEATURE_MOOSHTANK_STRING) 
                        && featureValue.equals(ChatAspects.SPECIES_FEATURE_MOOSHTANK_FALSE_STRING))
                {
                    continue;
                }
                else if (featureKey.equals(ChatAspects.SPECIES_FEATURE_NETHERITE_COATING_STRING))
                {
                    if (featureValue.equals(ChatAspects.SPECIES_FEATURE_NETHERITE_COATING_NONE_STRING)) continue;
                    else featureValue += ChatAspects.SPECIES_FEATURE_NETHERITE_COATING_APPEND_STRING;
                }
                else if (featureKey.equals(ChatAspects.SPECIES_FEATURE_REGION_BIAS_STRING))
                {
                    featureValue += ChatAspects.SPECIES_FEATURE_REGION_BIAS_APPEND_STRING;
                }
                else if (featureKey.equals(ChatAspects.SPECIES_FEATURE_TREE_STRING)
                        && featureValue.equals(ChatAspects.SPECIES_FEATURE_TREE_NONE_STRING))
                {
                    continue;
                }

                result.append(renderCustomForm(TextUtils.toTitleCaseWithDelimiters(featureValue)));
            }
        }

        return result;
    }

    private static StringBuilder getFullyExpandedFormStringBuilder(StringBuilder result, Set<String> aspects)
    {
        Set<String> customAspects = new HashSet<>();

        if (ChatAspects.getDisplayedAspectsCount() > 0)
        {
            aspects
                .stream()
                .filter(x -> ChatAspects.shouldDisplayAspect(x))
                .forEach(x -> customAspects.add(x));
        }

        for (String aspect : customAspects)
        {
            result.append(" ");
            result.append(renderCustomForm(aspect));
        }

        return result;
    }

    private static String renderCustomForm(String form)
    {
        return MiniMessage.miniMessage().serialize
        (
            MiniMessage.miniMessage().deserialize
            (
                ChatMiniMessage.getCustomFormTemplate(),
                TagResolver
                    .builder()
                    .resolver(getFormResolver(form))
                    .build()
            )
        );
    }

    public static TagResolver.Single getCustomFormsResolver(Pokemon pokemon)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomStatusTemplate(),
            TagResolver.builder()
                .resolvers(getCustomFormsResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_FORMS_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomFormsResolvers(Pokemon pokemon)
    {
        String form = CobblemonUtils.getPokemonBaseForm(pokemon);
        Set<String> aspects = pokemon.getAspects();
        List<SpeciesFeature> features = pokemon.getFeatures();

        return new TagResolver.Single[]
        {
            getCustomShininessResolver(pokemon),
            getCustomAlphanessResolver(pokemon),
            getFormResolver(form),
            getFormsExpandedResolver(form, features),
            getFormsFullyExpandedResolver(form, features, aspects)
        };
    }

    public static TagResolver.Single getCustomSpeciesResolver(Pokemon pokemon)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomStatusTemplate(),
            TagResolver.builder()
                .resolvers(getCustomSpeciesResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPECIES_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeciesResolvers(Pokemon pokemon)
    {
        String form = CobblemonUtils.getPokemonBaseForm(pokemon);
        Set<String> aspects = pokemon.getAspects();
        List<SpeciesFeature> features = pokemon.getFeatures();

        return new TagResolver.Single[]
        {
            getCustomShininessResolver(pokemon),
            getCustomAlphanessResolver(pokemon),
            getFormResolver(form),
            getFormsExpandedResolver(form, features),
            getFormsFullyExpandedResolver(form, features, aspects),
            getCustomFormsResolver(pokemon)
        };
    }

    private static TagResolver.Single getHeldItemResolver(String heldItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_TEMPLATE_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getHeldItemCustomNameResolver(String heldItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_TEMPLATE_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getCustomHeldItemResolver(String heldItemName, String heldItemCustomName)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomHeldItemTemplate(),
            TagResolver.builder()
                .resolvers(getCustomHeldItemResolvers(heldItemName, heldItemCustomName))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHeldItemResolvers(String heldItemName, String heldItemCustomName)
    {
        return new TagResolver.Single[]
        {
            getHeldItemResolver(heldItemName),
            getHeldItemCustomNameResolver(heldItemCustomName)
        };
    }

    private static TagResolver.Single getCosmeticItemResolver(String cosmeticItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.COSMETIC_ITEM_TEMPLATE_STRING,
            cosmeticItem
        );
    }

    private static TagResolver.Single getCosmeticItemCustomNameResolver(String cosmeticItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.COSMETIC_ITEM_CUSTOM_NAME_TEMPLATE_STRING,
            cosmeticItem
        );
    }

    private static TagResolver.Single getCustomCosmeticItemResolver(String cosmeticItemName, String cosmeticItemCustomName)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomCosmeticItemTemplate(),
            TagResolver.builder()
                .resolvers(getCustomHeldItemResolvers(cosmeticItemName, cosmeticItemCustomName))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomCosmeticItemResolvers(String cosmeticItemName, String cosmeticItemCustomName)
    {
        return new TagResolver.Single[]
        {
            getCosmeticItemResolver(cosmeticItemName.equals(ChatStrings.getEmptyHeldItemString()) ? "" : cosmeticItemName),
            getCosmeticItemCustomNameResolver(cosmeticItemCustomName.equals(ChatStrings.getEmptyHeldItemString()) ? "" : cosmeticItemCustomName)
        };
    }

    private static TagResolver.Single getAbilityResolver(String ability)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ABILITY_TEMPLATE_STRING,
            ability
        );
    }

    private static TagResolver.Single getCustomHiddenAbilityResolver(boolean isHA)
    {
        return Placeholder.parsed
        (
            ChatMiniMessage.ABILITY_TEMPLATE_STRING,
            isHA ? ChatMiniMessage.getCustomHiddenAbilityTemplate() : ""
        );
    }

    private static TagResolver.Single getNatureRealResolver(String nature)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_TEMPLATE_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureEffectiveResolver(String nature)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_TEMPLATE_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureRealStatUpResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_UP_TEMPLATE_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureRealStatDownResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_DOWN_TEMPLATE_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatUpResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_UP_TEMPLATE_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatDownResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_DOWN_TEMPLATE_STRING,
            stat
        );
    }

    public static TagResolver.Single getCustomNatureRealStatsResolver(Nature realNature)
    {
        if (CobblemonUtils.isNeutralNature(realNature))
        {
            return Placeholder.component
            (
                    ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_STRING,
                    Component.empty()
            );
        }

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomNatureRealStatsTemplate(),
            TagResolver.builder()
                .resolvers(getCustomNatureRealStatsResolvers(realNature))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureRealStatsResolvers(Nature realNature)
    {
        return new TagResolver.Single[]
        {
            getNatureRealStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(realNature)),
            getNatureRealStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(realNature))
        };
    }

    public static TagResolver.Single getCustomNatureEffectiveStatsResolver(Nature effectiveNature)
    {
        if (CobblemonUtils.isNeutralNature(effectiveNature))
        {
            return Placeholder.component
            (
                    ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_STRING,
                    Component.empty()
            );
        }

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomNatureEffectiveStatsTemplate(),
            TagResolver.builder()
                .resolvers(getCustomNatureEffectiveStatsResolvers(effectiveNature))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureEffectiveStatsResolvers(Nature effectiveNature)
    {
        return new TagResolver.Single[]
        {
            getNatureEffectiveStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(effectiveNature)),
            getNatureEffectiveStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(effectiveNature))
        };
    }

    private static TagResolver.Single getCustomMintnessResolver(boolean isMinted)
    {
        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_MINTNESS_TEMPLATE_STRING,
            isMinted ? ChatMiniMessage.getCustomMintnessTemplate() : ""
        );
    }

    private static TagResolver.Single getLevelResolver(String level)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.LEVEL_TEMPLATE_STRING,
            level
        );
    }

    private static TagResolver.Single getCurrentExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CURRENT_EXPERIENCE_TEMPLATE_STRING,
            exp
        );
    }

    private static TagResolver.Single getRequiredExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REQUIRED_EXPERIENCE_TEMPLATE_STRING,
            exp
        );
    }

    private static TagResolver.Single getRemainingExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REMAINING_EXPERIENCE_TEMPLATE_STRING,
            exp
        );
    }

    public static TagResolver.Single getCustomExperienceResolver(int level, int currentExp, int remainingExp)
    {
        if (level == 100)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.CUSTOM_EXPERIENCE_TEMPLATE_STRING,
                    Component.empty()
            );
        }

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomExperienceTemplate(),
            TagResolver.builder()
                .resolvers(getCustomExperienceResolvers(currentExp, remainingExp))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EXPERIENCE_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomExperienceResolvers(int currentExp, int remainingExp)
    {
        return new TagResolver.Single[]
        {
            getCurrentExperienceResolver(String.valueOf(currentExp)),
            getRequiredExperienceResolver(String.valueOf(currentExp + remainingExp)),
            getRemainingExperienceResolver(String.valueOf(remainingExp))
        };
    }

    private static TagResolver.Single getFriendshipResolver(String friendship)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.FRIENDSHIP_TEMPLATE_STRING,
            friendship
        );
    }

    private static TagResolver.Single getMove1Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TEMPLATE_STRING,
            moves.size() > 0 ? moves.get(0).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove1ColorResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_COLOR_TEMPLATE_STRING,
            moves.size() > 0 ? String.format("#%06x", moves.get(0).getType().getPrimaryColor()) : ""
        );
    }

    private static TagResolver.Single getMove1UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_USED_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() - moves.get(0).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove1RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_REMAINING_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove1TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TOTAL_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove1CustomPPResolver(List<Move> moves)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getMoveCustomPPTemplate(),
            TagResolver.builder()
                .resolvers(getMove1CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove1CustomPPResolvers(List<Move> moves)
    {
        return new TagResolver.Single[]
        {
            getMove1UsedPPResolver(moves),
            getMove1RemainingPPResolver(moves),
            getMove1TotalPPResolver(moves)
        };
    }

    private static TagResolver.Single getMove2Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TEMPLATE_STRING,
            moves.size() > 1 ? moves.get(1).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove2ColorResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_COLOR_TEMPLATE_STRING,
            moves.size() > 1 ? String.format("#%06x", moves.get(1).getType().getPrimaryColor()) : ""
        );
    }

    private static TagResolver.Single getMove2UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_USED_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() - moves.get(1).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove2RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_REMAINING_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove2TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TOTAL_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove2CustomPPResolver(List<Move> moves)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getMoveCustomPPTemplate(),
            TagResolver.builder()
                .resolvers(getMove2CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove2CustomPPResolvers(List<Move> moves)
    {
        return new TagResolver.Single[]
        {
            getMove2UsedPPResolver(moves),
            getMove2RemainingPPResolver(moves),
            getMove2TotalPPResolver(moves)
        };
    }

    private static TagResolver.Single getMove3Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TEMPLATE_STRING,
            moves.size() > 2 ? moves.get(2).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove3ColorResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_COLOR_TEMPLATE_STRING,
            moves.size() > 2 ? String.format("#%06x", moves.get(2).getType().getPrimaryColor()) : ""
        );
    }

    private static TagResolver.Single getMove3UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_USED_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() - moves.get(2).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove3RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_REMAINING_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove3TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TOTAL_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove3CustomPPResolver(List<Move> moves)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getMoveCustomPPTemplate(),
            TagResolver.builder()
                .resolvers(getMove3CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove3CustomPPResolvers(List<Move> moves)
    {
        return new TagResolver.Single[]
        {
            getMove3UsedPPResolver(moves),
            getMove3RemainingPPResolver(moves),
            getMove3TotalPPResolver(moves)
        };
    }

    private static TagResolver.Single getMove4Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TEMPLATE_STRING,
            moves.size() > 3 ? moves.get(3).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove4ColorResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_COLOR_TEMPLATE_STRING,
            moves.size() > 3 ? String.format("#%06x", moves.get(3).getType().getPrimaryColor()) : ""
        );
    }

    private static TagResolver.Single getMove4UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_USED_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() - moves.get(3).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove4RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_REMAINING_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove4TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TOTAL_PP_TEMPLATE_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove4CustomPPResolver(List<Move> moves)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getMoveCustomPPTemplate(),
            TagResolver.builder()
                .resolvers(getMove4CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove4CustomPPResolvers(List<Move> moves)
    {
        return new TagResolver.Single[]
        {
            getMove4UsedPPResolver(moves),
            getMove4RemainingPPResolver(moves),
            getMove4TotalPPResolver(moves)
        };
    }

    public static TagResolver.Single getCustomMovesResolver(List<Move> moves)
    {
        Component component = Component.empty();

        for (Move move : moves)
        {
            Component moveComponent = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
            (
                ChatMiniMessage.getCustomMoveTemplate(),
                TagResolver.builder()
                    .resolver(getMoveResolver(move))
                    .resolver(getMoveColorResolver(move))
                    .resolver(getCustomMovePPResolver(move))
                    .build()
            );

            component = component.append(moveComponent);
        }

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_MOVES_TEMPLATE_STRING,
            component
        );
    }

    private static TagResolver.Single getMoveResolver(Move move)
    {
        return Placeholder.unparsed(
            ChatMiniMessage.MOVE_TEMPLATE_STRING,
            move.getDisplayName().toString()
        );
    }

    private static TagResolver.Single getMoveColorResolver(Move move)
    {
        return Placeholder.unparsed(
            ChatMiniMessage.MOVE_COLOR_TEMPLATE_STRING,
            String.format("#%06x", move.getType().getPrimaryColor())
        );
    }

    private static TagResolver.Single getCustomMovePPResolver(Move move)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(
            ChatMiniMessage.getMoveCustomPPTemplate(),
            TagResolver.builder()
                .resolver(getMoveUsedPPResolver(move))
                .resolver(getMoveRemainingPPResolver(move))
                .resolver(getMoveTotalPPResolver(move))
                .build()
        );

        return Placeholder.component(
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_STRING,
            component
        );
    }

    private static TagResolver.Single getMoveUsedPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_STRING,
            String.valueOf(move.getMaxPp() - move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveRemainingPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_STRING,
            String.valueOf(move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveTotalPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_STRING,
            String.valueOf(move.getMaxPp())
        );
    }

    private static TagResolver.Single getRealIVTotalResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_TOTAL_TEMPLATE_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getEffectiveIVTotalResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_TOTAL_TEMPLATE_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getRealIVPercentageResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_PERCENTAGE_TEMPLATE_STRING,
            String.valueOf((ivTotal / 186.0) * 100.0)
        );
    }

    private static TagResolver.Single getEffectiveIVPercentageResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING,
            String.valueOf((ivTotal / 186.0) * 100.0)
        );
    }

    public static TagResolver.Single getCustomRealIVPercentageResolver(int totalIvs)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomRealIVPercentageTemplate(),
            TagResolver.builder()
                .resolvers(getCustomRealIVPercentageResolvers(totalIvs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomRealIVPercentageResolvers(int totalIvs)
    {
        return new TagResolver.Single[]
        {
            getRealIVTotalResolver(totalIvs),
            getRealIVPercentageResolver(totalIvs)
        };
    }

    public static TagResolver.Single getCustomEffectiveIVPercentageResolver(int totalIvs)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomEffectiveIVPercentageTemplate(),
            TagResolver.builder()
                .resolvers(getCustomEffectiveIVPercentageResolvers(totalIvs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveIVPercentageResolvers(int totalIvs)
    {
        return new TagResolver.Single[]
        {
            getEffectiveIVTotalResolver(totalIvs),
            getEffectiveIVPercentageResolver(totalIvs)
        };
    }

    public static TagResolver.Single getCustomGeneralIVsResolver(int totalRealIVs, int totalEffectiveIVs)
    {
        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            ChatMiniMessage.getCustomGeneralIVsTemplate(),
            TagResolver.builder()
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_GENERAL_IVS_TEMPLATE_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomGeneralIVsResolvers(int totalRealIVs, int totalEffectiveIVs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomRealIVPercentageResolver(totalRealIVs));
        resolvers.addAll(Arrays.asList(getCustomRealIVPercentageResolvers(totalRealIVs)));
        resolvers.add(getCustomEffectiveIVPercentageResolver(totalEffectiveIVs));
        resolvers.addAll(Arrays.asList(getCustomEffectiveIVPercentageResolvers(totalEffectiveIVs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }
}
