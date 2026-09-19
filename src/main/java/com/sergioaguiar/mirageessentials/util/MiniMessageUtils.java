package com.sergioaguiar.mirageessentials.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.status.Statuses;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.pokemon.Gender;
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
        ElementalType elementalType = CobblemonUtils.getElementalTypeFromShowdownId(type.getShowdownId());

        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE1_COLOR_TEMPLATE_STRING,
            String.format("#%06x", elementalType.getPrimaryColor())
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
        ElementalType elementalType = CobblemonUtils.getElementalTypeFromShowdownId(type.getShowdownId());

        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE2_COLOR_TEMPLATE_STRING,
            String.format("#%06x", elementalType.getPrimaryColor())
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
        return Placeholder.parsed
        (
            ChatMiniMessage.FORM_TEMPLATE_STRING,
            renderCustomForm(form)
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
        String template = ChatMiniMessage.getCustomFormTemplate();

        return MiniMessage.miniMessage().serialize
        (
            MiniMessage.miniMessage().deserialize
            (
                template,
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
            ChatMiniMessage.getCustomHeldItemTemplate(),
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
}
