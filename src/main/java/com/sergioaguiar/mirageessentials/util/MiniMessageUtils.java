package com.sergioaguiar.mirageessentials.util;

import java.util.List;

import com.cobblemon.mod.common.api.pokemon.status.Statuses;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

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

        return TagResolver.builder()
                .resolver(getNameResolver(CobblemonUtils.getPokemonName(pokemon.getNickname(), species)))
                .resolver(getTitleResolver(CobblemonUtils.getPokemonTitle(pokemon)))
                .resolver(getGenderResolver(pokemon.getGender()))
                .resolver(getCaughtBallResolver(CobblemonUtils.getPokemonCaughtBall(pokemon)))
                .resolver(getCustomConditionResolver(pokemon))
                .resolvers(getCustomConditionResolvers(pokemon))
                .resolver(getCustomTypesResolver(types, teraType, isMonotype))
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype))
                .resolver(getCustomShininessResolver(pokemon))
                .resolver(getCustomAlphanessResolver(pokemon))
                .build();
    }

    public static TagResolver getCustomStatusTagResolver(Pokemon pokemon)
    {
        return TagResolver.builder()
                .resolvers(getCustomStatusResolver(pokemon))
                .build();
    }

    public static TagResolver getCustomConditionTagResolver(Pokemon pokemon)
    {
        return TagResolver.builder()
                .resolvers(getCustomConditionResolver(pokemon))
                .build();
    }

    public static TagResolver getCustomTypesTagResolver(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return TagResolver.builder()
                .resolvers(getCustomTypesResolver(types, teraType, isMonotype))
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
            ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_STRING,
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
}
