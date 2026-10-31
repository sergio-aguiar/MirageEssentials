package com.sergioaguiar.mirageessentials.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.pokemon.egg.EggGroup;
import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.api.pokemon.status.Statuses;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.IVs;
import com.cobblemon.mod.common.pokemon.Nature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.gson.JsonPrimitive;
import com.sergioaguiar.mirageessentials.config.chatparser.aspects.ChatAspects;
import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;
import com.sergioaguiar.mirageessentials.config.chatparser.strings.ChatStrings;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.fabric.FabricAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEvent.ShowItem;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonDataComponentValue;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

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

    public static Text render(String template, TagResolver placeholders)
    {
        if (template == null || template.isBlank()) return Text.empty();

        Component component = MINIMESSAGE_INSTANCE.deserialize
        (
            template,
            placeholders
        );

        return toMinecraftText(removeEmptyLines(component));
    }

    public static Text render(String template, TagResolver placeholders, HoverEvent<?> hoverEvent)
    {
        if (template == null || template.isBlank()) return Text.empty();

        Component component = MINIMESSAGE_INSTANCE.deserialize
        (
            template,
            placeholders
        );

        return toMinecraftText(removeEmptyLines(component), hoverEvent);
    }

    private static Component removeEmptyLines(Component component)
    {
        if (component == null) return Component.empty();

        String serialized = MINIMESSAGE_INSTANCE.serialize(component);
        StringBuilder sanitized = new StringBuilder();

        for (String line : serialized.split("<br>|<newline>|\\R", -1))
        {
            if (line == null || line.isBlank()) continue;
            if (sanitized.length() > 0) sanitized.append('\n');
            sanitized.append(line);
        }

        return sanitized.length() == 0
            ? Component.empty()
            : MINIMESSAGE_INSTANCE.deserialize(sanitized.toString());
    }

    public static List<Text> renderLines(String template, TagResolver placeholders)
    {
        List<Text> lines = new ArrayList<>();

        if (template == null || template.isEmpty()) return lines;

        String[] split = template.split("\\R", -1);

        for (String line : split)
        {
            if (line == null || line.isBlank()) continue;

            Component component = MINIMESSAGE_INSTANCE.deserialize(line, placeholders);

            if (!Component.IS_NOT_EMPTY.test(component)) continue;

            lines.addAll(splitComponentLines(component));
        }

        return lines;
    }

    private static List<Text> splitComponentLines(Component component)
    {
        List<Text> lines = new ArrayList<>();

        String serialized = MINIMESSAGE_INSTANCE.serialize(component);

        for (String line : serialized.split("<br>|<newline>|\\R", -1))
        {
            if (line == null || line.isBlank()) continue;
            lines.add(toMinecraftText(MINIMESSAGE_INSTANCE.deserialize(line)));
        }

        return lines;
    }

    public static Text toMinecraftText(Component component)
    {
        return FabricAudiences.nonWrappingSerializer().serialize
        (
            component.style(component.style().decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE))
        );
    }

    public static Text toMinecraftText(Component component, HoverEvent<?> hoverEvent)
    {
        return FabricAudiences.nonWrappingSerializer().serialize
        (
            component.style(component.style()
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
                .hoverEvent(hoverEvent)
            )
        );
    }

    public static Text renderPokeInfo(Pokemon pokemon, boolean isPartyCheck, boolean isClosedSheet)
    {
        String newTemplate = parsePokeInfoColors
        (
            ChatMiniMessage.getPokemonInfoTemplate(),
            pokemon,
            CobblemonUtils.getPokemonMoves(pokemon),
            CobblemonUtils.getPokemonTypes(pokemon)
        );

        return render(newTemplate, getPokeInfoTagResolver(pokemon, isPartyCheck, isClosedSheet));
    }

    public static Component getPokeInfoComponent(Pokemon pokemon, boolean isPartyCheck, boolean isClosedSheet)
    {
        String newTemplate = parsePokeInfoColors
        (
            ChatMiniMessage.getPokemonInfoTemplate(),
            pokemon,
            CobblemonUtils.getPokemonMoves(pokemon),
            CobblemonUtils.getPokemonTypes(pokemon)
        );

        if (newTemplate == null || newTemplate.isBlank()) return Component.empty();

        return removeEmptyLines
        (
            MINIMESSAGE_INSTANCE.deserialize
            (
                newTemplate,
                getPokeInfoTagResolver(pokemon, isPartyCheck, isClosedSheet)
            )
        );
    }

    public static List<Text> renderPokeInfoLines(Pokemon pokemon, boolean isPartyCheck, boolean isClosedSheet)
    {
        String template = ChatMiniMessage.getPokemonInfoTemplate();
        List<Text> lines = new ArrayList<>();

        if (template == null || template.isEmpty()) return lines;

        String[] split = template.split("\\R", -1);

        for (String line : split)
        {
            Component component = MINIMESSAGE_INSTANCE.deserialize
            (
                parsePokeInfoColors
                (
                    line,
                    pokemon,
                    CobblemonUtils.getPokemonMoves(pokemon),
                    CobblemonUtils.getPokemonTypes(pokemon)
                ),
                getPokeInfoTagResolver(pokemon, isPartyCheck, isClosedSheet)
            );

            lines.addAll(splitComponentLines(component));
        }

        return lines;
    }

    public static Text renderCustomName(Pokemon pokemon)
    {
        String newTemplate = parseCustomNameColors
        (
            ChatMiniMessage.getCustomNameTemplate(),
            pokemon,
            CobblemonUtils.getPokemonTypes(pokemon)
        );

        return render(newTemplate, getCustomNameTagResolver(pokemon));
    }

    public static Text renderPokeHoverable(Pokemon pokemon, boolean isClosedSheet, HoverEvent<?> hoverEvent)
    {
        String newTemplate = parsePokeHoverableColors
        (
            ChatMiniMessage.getPokeHoverableTemplate(),
            pokemon,
            CobblemonUtils.getPokemonTypes(pokemon),
            pokemon.getTeraType()
        );

        return render(newTemplate, getPokeHoverableTagResolver(pokemon, isClosedSheet), hoverEvent);
    }

    public static Text renderItemHoverable(ItemStack item, HoverEvent<?> hoverEvent)
    {
        String newTemplate = parseItemHoverableColors
        (
            ChatMiniMessage.getItemHoverableTemplate(),
            item
        );

        return render(newTemplate, getItemHoverableTagResolver(item), hoverEvent);
    }

    private static String parsePokeInfoColors(String template, Pokemon pokemon, List<Move> moves, List<ElementalType> types)
    {
        return resolveCustomColors
        (
            resolveHpColor
            (
                resolveAtkColor
                (
                    resolveDefColor
                    (
                        resolveSpaColor
                        (
                            resolveSpdColor
                            (
                                resolveSpeColor
                                (
                                    resolveType1Color
                                    (
                                        resolveType2Color
                                        (
                                            resolveTeraTypeColor
                                            (
                                                resolveMove1Color
                                                (
                                                    resolveMove2Color
                                                    (
                                                        resolveMove3Color
                                                        (
                                                            resolveMove4Color
                                                            (
                                                                resolveTitleColor
                                                                (
                                                                    resolveShinyColor(template),
                                                                    pokemon
                                                                ),
                                                                moves
                                                            ),
                                                            moves
                                                        ),
                                                        moves
                                                    ),
                                                    moves
                                                ), 
                                                pokemon.getTeraType()
                                            ),
                                            types.size() < 2 ? types.get(0) : types.get(1)
                                        ),
                                        types.get(0))
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    private static String parseCustomNameColors(String template, Pokemon pokemon, List<ElementalType> types)
    {
        return resolveCustomColors
        (
            resolveTitleColor
            (
                resolveType1Color
                (
                    resolveType2Color
                    (
                        template,
                        types.size() < 2 ? types.get(0) : types.get(1)
                    ),
                    types.get(0)
                ),
                pokemon
            )
        );
    }

    private static String parsePokeHoverableColors(String template, Pokemon pokemon, List<ElementalType> types, TeraType teraType)
    {
        return resolveCustomColors
        (
            resolveTitleColor
            (
                resolveType1Color
                (
                    resolveType2Color
                    (
                        resolveTeraTypeColor
                        (
                            resolveShinyColor
                            (
                                resolveCustomCategoryColor
                                (
                                    resolveCustomShinyColor1
                                    (
                                        resolveCustomShinyColor2
                                        (
                                            resolveCustomShinyColorCategory
                                            (
                                                resolveCustomCategoryBracketColor
                                                (
                                                    resolveCustomShinyBracketColor1
                                                    (
                                                        resolveCustomShinyBracketColor2
                                                        (
                                                            resolveCustomShinyBracketColorCategory
                                                            (
                                                                resolveShinyBracketColor(template),
                                                                pokemon
                                                            ),
                                                            pokemon,
                                                            types
                                                        ),
                                                        pokemon,
                                                        types
                                                    ),
                                                    pokemon
                                                ),
                                                pokemon
                                            ),
                                            pokemon,
                                            types
                                        ),
                                        pokemon,
                                        types
                                    ),
                                    pokemon
                                )
                            ),
                            teraType
                        ),
                        types.size() < 2 ? types.get(0) : types.get(1)
                    ),
                    types.get(0)
                ),
                pokemon
            )
        );
    }

    private static String parseItemHoverableColors(String template, ItemStack item)
    {
        return resolveItemColor
        (
            resolveItemBracketColor
            (
                template,
                item
            ),
            item
        );
    }

    public static TagResolver getPokeInfoTagResolver(Pokemon pokemon, boolean isPartyCheck, boolean isClosedSheet)
    {
        List<ElementalType> types = CobblemonUtils.getPokemonTypes(pokemon);
        boolean isMonotype = types.size() < 2;
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
        Set<Stats> hyperTrainedStats = CobblemonUtils.getHyperTrainedStats(ivs);
        EVs evs = pokemon.getEvs();
        int totalEvs = CobblemonUtils.getEVTotal(evs);
        float scale = pokemon.getScaleModifier();
        boolean isNeutered = NeoDaycareUtils.isNeutered(pokemon);

        return TagResolver.builder()
                .resolver(getCustomNameResolver(pokemon, isPartyCheck))
                .resolvers(getCustomNameResolvers(pokemon))
                .resolver(getGenderResolver(pokemon.getGender(), isClosedSheet))
                .resolver(getCaughtBallResolver(CobblemonUtils.getPokemonCaughtBall(pokemon)))
                .resolver(getCustomConditionResolver(pokemon, isClosedSheet))
                .resolvers(getCustomConditionResolvers(pokemon, isClosedSheet))
                .resolver(getCustomSpeciesResolver(pokemon))
                .resolvers(getCustomSpeciesResolvers(pokemon))
                .resolver(getCustomTypesResolver(types, teraType, isMonotype, isClosedSheet))
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype, isClosedSheet))
                .resolver(getCustomHeldItemResolver(heldItem == null || heldItem.isEmpty(), heldItemName, heldItemCustomName, isClosedSheet))
                .resolvers(getCustomHeldItemResolvers(heldItemName, heldItemCustomName, isClosedSheet))
                .resolver(getCustomCosmeticItemResolver(cosmeticItem == null || cosmeticItem.isEmpty(), cosmeticItemName, cosmeticItemCustomName))
                .resolvers(getCustomCosmeticItemResolvers(cosmeticItemName, cosmeticItemCustomName))
                .resolver(getAbilityResolver(CobblemonUtils.getPokemonAbility(pokemon), isClosedSheet))
                .resolver(getCustomHiddenAbilityResolver(CobblemonUtils.hasHiddenAbility(pokemon), isClosedSheet))
                .resolver(getNatureRealResolver(CobblemonUtils.getPokemonNatureName(realNature), isClosedSheet))
                .resolver(getNatureEffectiveResolver(CobblemonUtils.getPokemonNatureName(effectiveNature), isClosedSheet))
                .resolver(getCustomNatureRealStatsResolver(realNature, isClosedSheet))
                .resolvers(getCustomNatureRealStatsResolvers(realNature, isClosedSheet))
                .resolver(getCustomNatureEffectiveStatsResolver(effectiveNature, isClosedSheet))
                .resolvers(getCustomNatureEffectiveStatsResolvers(effectiveNature, isClosedSheet))
                .resolver(getCustomMintnessResolver(CobblemonUtils.isMinted(realNature, effectiveNature), isClosedSheet))
                .resolver(getLevelResolver(String.valueOf(level)))
                .resolver(getCustomExperienceResolver(level, currentExperience, RemainingExperience))
                .resolvers(getCustomExperienceResolvers(currentExperience, RemainingExperience))
                .resolver(getFriendshipResolver(CobblemonUtils.getPokemonFriendship(pokemon), isClosedSheet))
                .resolver(getMove1Resolver(moves, isClosedSheet))
                .resolver(getMove1CustomPPResolver(moves, isClosedSheet))
                .resolvers(getMove1CustomPPResolvers(moves, isClosedSheet))
                .resolver(getMove2Resolver(moves, isClosedSheet))
                .resolver(getMove2CustomPPResolver(moves, isClosedSheet))
                .resolvers(getMove2CustomPPResolvers(moves, isClosedSheet))
                .resolver(getMove3Resolver(moves, isClosedSheet))
                .resolver(getMove3CustomPPResolver(moves, isClosedSheet))
                .resolvers(getMove3CustomPPResolvers(moves, isClosedSheet))
                .resolver(getMove4Resolver(moves, isClosedSheet))
                .resolver(getMove4CustomPPResolver(moves, isClosedSheet))
                .resolvers(getMove4CustomPPResolvers(moves, isClosedSheet))
                .resolver(getCustomMovesResolver(moves, isClosedSheet))
                .resolver(getCustomGeneralIVsResolver(totalRealIVs, totalEffectiveIVs, isClosedSheet))
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs, isClosedSheet))
                .resolver(getCustomHpIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomHpIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomAtkIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomAtkIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomDefIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomDefIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomSpaIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomSpaIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomSpdIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomSpdIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomSpeIVsResolver(ivs, hyperTrainedStats, isClosedSheet))
                .resolvers(getCustomSpeIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .resolver(getCustomGeneralEVsResolver(totalEvs, isClosedSheet))
                .resolvers(getCustomGeneralEVsResolvers(totalEvs, isClosedSheet))
                .resolver(getCustomHpEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomHpEVsResolvers(evs, isClosedSheet))
                .resolver(getCustomAtkEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomAtkEVsResolvers(evs, isClosedSheet))
                .resolver(getCustomDefEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomDefEVsResolvers(evs, isClosedSheet))
                .resolver(getCustomSpaEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomSpaEVsResolvers(evs, isClosedSheet))
                .resolver(getCustomSpdEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomSpdEVsResolvers(evs, isClosedSheet))
                .resolver(getCustomSpeEVsResolver(evs, isClosedSheet))
                .resolvers(getCustomSpeEVsResolvers(evs, isClosedSheet))
                .resolver(getSizeResolver(CobblemonUtils.getPokemonSizeName(pokemon)))
                .resolver(getScaleModifierResolver(scale))
                .resolver(getScaleModifier100Resolver(scale))
                .resolver(getEggGroupsResolver(pokemon.getSpecies().getEggGroups()))
                .resolver(getCustomNeuteredResolver(isNeutered))
                .resolvers(getCustomNeuteredResolvers(isNeutered))
                .resolver(getOriginalTrainerNameResolver(CobblemonUtils.getPokemonOTDisplayName(pokemon)))
                .resolver(getHiddenResolver(isClosedSheet))
                .build();
    }

    public static TagResolver getCustomNameTagResolver(Pokemon pokemon)
    {
        return TagResolver.builder()
            .resolvers(getCustomNameResolvers(pokemon))
            .build();
    }

    public static TagResolver getPokeHoverableTagResolver(Pokemon pokemon, boolean isClosedSheet)
    {
        return TagResolver.builder()
            .resolver(getCustomNameResolver(pokemon, isClosedSheet))
            .resolvers(getCustomNameResolvers(pokemon))
            .resolver(getCustomSpeciesResolver(pokemon))
            .resolvers(getCustomSpeciesResolvers(pokemon))
            .build();
    }

    public static TagResolver getItemHoverableTagResolver(ItemStack item)
    {
        return TagResolver.builder()
            .resolver(getItemBaseNameResolver(item))
            .resolver(getItemAnvilNameResolver(item))
            .resolver(getItemCustomNameResolver(item))
            .resolver(getItemRealNameResolver(item))
            .build();
    }

    private static TagResolver.Single getNameResolver(String name)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NAME_TEMPLATE_TAG_STRING,
            name
        );
    }

    private static TagResolver.Single getTitleResolver(String title)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TITLE_TEMPLATE_TAG_STRING,
            title
        );
    }

    public static TagResolver.Single getCustomNameResolver(Pokemon pokemon, boolean isPartyCheck)
    {
        if (isPartyCheck)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_NAME_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        List<ElementalType> types = CobblemonUtils.getPokemonTypes(pokemon);

        String newTemplate = parseCustomNameColors(ChatMiniMessage.getCustomNameTemplate(), pokemon, types);

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomNameResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NAME_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNameResolvers(Pokemon pokemon)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNameResolver(CobblemonUtils.getPokemonName(pokemon.getNickname(), CobblemonUtils.getPokemonSpecies(pokemon))));
        resolvers.add(getTitleResolver(CobblemonUtils.getPokemonTitle(pokemon)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getGenderResolver(Gender gender, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.GENDER_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        String template = switch (gender)
        {
            case MALE -> ChatMiniMessage.getMaleGenderTemplate();
            case FEMALE -> ChatMiniMessage.getFemaleGenderTemplate();
            default -> ChatMiniMessage.getGenderlessGenderTemplate();
        };

        String newTemplate = resolveCustomColors(template);

        return Placeholder.parsed
        (
            ChatMiniMessage.GENDER_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getCaughtBallResolver(String caughtBall)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CAUGHT_BALL_TEMPLATE_TAG_STRING,
            caughtBall
        );
    }

    private static TagResolver.Single getCurrentHealthResolver(String currentHealth, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CURRENT_HEALTH_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : currentHealth
        );
    }

    private static TagResolver.Single getMaxHealthResolver(String maxHealth, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MAX_HEALTH_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : maxHealth
        );
    }

    private static TagResolver.Single getStatusResolver(String statusShowdownName, boolean isFainted, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.STATUS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

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

        String newTemplate = resolveCustomColors(template);

        return Placeholder.parsed
        (
            ChatMiniMessage.STATUS_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    public static TagResolver.Single getCustomStatusResolver(Pokemon pokemon, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (pokemon.getStatus() == null)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomStatusTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomStatusResolvers(pokemon, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomStatusResolvers(Pokemon pokemon, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getStatusResolver(CobblemonUtils.getPokemonStatus(pokemon), pokemon.isFainted(), isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomConditionResolver(Pokemon pokemon, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.CUSTOM_CONDITION_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomConditionTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomConditionResolvers(pokemon, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_CONDITION_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomConditionResolvers(Pokemon pokemon, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCurrentHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon), isClosedSheet));
        resolvers.add(getMaxHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon), isClosedSheet));
        resolvers.add(getCustomStatusResolver(pokemon, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomStatusResolvers(pokemon, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getType1Resolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE1_TEMPLATE_TAG_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    private static TagResolver.Single getType2Resolver(ElementalType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TYPE2_TEMPLATE_TAG_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    private static TagResolver.Single getTeraTypeResolver(TeraType type, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.TERA_TYPE_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.TERA_TYPE_TEMPLATE_TAG_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    public static TagResolver.Single getCustomTypesResolver(List<ElementalType> types, TeraType teraType, boolean isMonotype, boolean isClosedSheet)
    {
        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_TYPES_TEMPLATE_TAG_STRING,
            isMonotype 
                ? getCustomTypesMonotypeComponent(types, teraType, isMonotype, isClosedSheet)
                : getCustomTypesDuotypeComponent(types, teraType, isMonotype, isClosedSheet)
        );
    }

    public static Component getCustomTypesMonotypeComponent(List<ElementalType> types, TeraType teraType, boolean isMonotype, boolean isClosedSheet)
    {
        String newTemplate = resolveCustomColors
        (
            resolveType1Color
            (
                resolveTeraTypeColor
                (
                    ChatMiniMessage.getCustomTypesMonotypeTemplate(),
                    teraType
                ),
                types.get(0)
            )
        );

        return MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomTypesMonotypeResolvers(types, teraType, isMonotype, isClosedSheet))
                .build()
        );
    }

    public static Component getCustomTypesDuotypeComponent(List<ElementalType> types, TeraType teraType , boolean isMonotype, boolean isClosedSheet)
    {
        String newTemplate = resolveCustomColors
        (
            resolveType1Color
            (
                resolveType2Color
                (
                    resolveTeraTypeColor
                    (
                        ChatMiniMessage.getCustomTypesDuotypeTemplate(),
                        teraType
                    ),
                    types.get(1)
                ),
                types.get(0)
            )
        );

        return MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype, isClosedSheet))
                .build()
        );
    }

    public static TagResolver.Single[] getCustomTypesMonotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getType1Resolver(types.get(0)));
        resolvers.add(getTeraTypeResolver(teraType, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single[] getCustomTypesDuotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getType2Resolver(isMonotype ? types.get(0) : types.get(1)));
        resolvers.addAll(Arrays.asList(getCustomTypesMonotypeResolvers(types, teraType, isMonotype, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getCustomShininessResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(resolveShinyColor(ChatMiniMessage.getCustomShininessTemplate()));

        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_SHININESS_TEMPLATE_TAG_STRING,
            pokemon.getShiny()
                ? newTemplate
                : ""
        );
    }

    private static TagResolver.Single getCustomAlphanessResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomAlphanessTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_ALPHANESS_TEMPLATE_TAG_STRING,
            pokemon.isAlpha()
                ? newTemplate
                : ""
        );
    }

    private static TagResolver.Single getSpeciesResolver(String species)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPECIES_TEMPLATE_TAG_STRING,
            species
        );
    }

    private static TagResolver.Single getFormResolver(String form)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.FORM_TEMPLATE_TAG_STRING,
            form
        );
    }

    private static TagResolver.Single getFormsExpandedResolver(String form, List<SpeciesFeature> features)
    {
        StringBuilder result = getExpandedFormStringBuilder(form, features);

        String newTemplate = resolveCustomColors(result.toString());

        return Placeholder.parsed
        (
            ChatMiniMessage.FORMS_EXPANDED_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getFormsFullyExpandedResolver(String form, List<SpeciesFeature> features, Set<String> aspects)
    {
        StringBuilder result = getExpandedFormStringBuilder(form, features);
        result = getFullyExpandedFormStringBuilder(result, aspects);

        String newTemplate = resolveCustomColors(result.toString());

        return Placeholder.parsed
        (
            ChatMiniMessage.FORMS_FULLY_EXPANDED_TEMPLATE_TAG_STRING,
            newTemplate
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

        if (!isFormNormal)
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
                .forEach(x -> customAspects.add(ChatAspects.getAspectFriendlyName(x)));
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
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomFormTemplate());

        return MiniMessage.miniMessage().serialize
        (
            MiniMessage.miniMessage().deserialize
            (
                newTemplate,
                TagResolver
                    .builder()
                    .resolver(getFormResolver(form))
                    .build()
            )
        );
    }

    public static TagResolver.Single getCustomSpeciesResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(resolveShinyColor(ChatMiniMessage.getCustomSpeciesTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpeciesResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPECIES_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeciesResolvers(Pokemon pokemon)
    {
        String form = CobblemonUtils.getPokemonBaseForm(pokemon);
        Set<String> aspects = pokemon.getAspects();
        List<SpeciesFeature> features = pokemon.getFeatures();

        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpeciesResolver(CobblemonUtils.getPokemonSpecies(pokemon)));
        resolvers.add(getCustomShininessResolver(pokemon));
        resolvers.add(getCustomAlphanessResolver(pokemon));
        resolvers.add(getFormResolver(form));
        resolvers.add(getFormsExpandedResolver(form, features));
        resolvers.add(getFormsFullyExpandedResolver(form, features, aspects));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getHeldItemResolver(String heldItem, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.HELD_ITEM_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_TEMPLATE_TAG_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getHeldItemCustomNameResolver(String heldItem, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.HELD_ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getCustomHeldItemResolver(boolean hasHeldItem, String heldItemName, String heldItemCustomName, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        if (hasHeldItem)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomHeldItemTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHeldItemResolvers(heldItemName, heldItemCustomName, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHeldItemResolvers(String heldItemName, String heldItemCustomName, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getHeldItemResolver(heldItemName, isClosedSheet));
        resolvers.add(getHeldItemCustomNameResolver(heldItemCustomName, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getCosmeticItemResolver(String cosmeticItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.COSMETIC_ITEM_TEMPLATE_TAG_STRING,
            cosmeticItem
        );
    }

    private static TagResolver.Single getCosmeticItemCustomNameResolver(String cosmeticItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.COSMETIC_ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING,
            cosmeticItem
        );
    }

    private static TagResolver.Single getCustomCosmeticItemResolver(boolean hasCosmeticItem, String cosmeticItemName, String cosmeticItemCustomName)
    {
        if (hasCosmeticItem)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomCosmeticItemTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomCosmeticItemResolvers(cosmeticItemName, cosmeticItemCustomName))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomCosmeticItemResolvers(String cosmeticItemName, String cosmeticItemCustomName)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCosmeticItemResolver(cosmeticItemName.equals(ChatStrings.getEmptyHeldItemString()) ? "" : cosmeticItemName));
        resolvers.add(getCosmeticItemCustomNameResolver(cosmeticItemCustomName.equals(ChatStrings.getEmptyHeldItemString()) ? "" : cosmeticItemCustomName));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getAbilityResolver(String ability, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.ABILITY_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.ABILITY_TEMPLATE_TAG_STRING,
            ability
        );
    }

    private static TagResolver.Single getCustomHiddenAbilityResolver(boolean isHA, boolean isClosedSheet)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomHiddenAbilityTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING,
            isClosedSheet
                ? "" 
                : isHA 
                    ? newTemplate
                    : ""
        );
    }

    private static TagResolver.Single getNatureRealResolver(String nature, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.NATURE_REAL_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_TEMPLATE_TAG_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureEffectiveResolver(String nature, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.NATURE_EFFECTIVE_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_TEMPLATE_TAG_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureRealStatUpResolver(String stat, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_UP_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : stat
        );
    }

    private static TagResolver.Single getNatureRealStatDownResolver(String stat, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_DOWN_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatUpResolver(String stat, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_UP_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatDownResolver(String stat, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_DOWN_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : stat
        );
    }

    public static TagResolver.Single getCustomNatureRealStatsResolver(Nature realNature, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        if (CobblemonUtils.isNeutralNature(realNature))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomNatureRealStatsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomNatureRealStatsResolvers(realNature, isClosedSheet))
                .resolver(getHiddenResolver(isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureRealStatsResolvers(Nature realNature, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNatureRealStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(realNature), isClosedSheet));
        resolvers.add(getNatureRealStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(realNature), isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomNatureEffectiveStatsResolver(Nature effectiveNature, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (CobblemonUtils.isNeutralNature(effectiveNature))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomNatureEffectiveStatsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomNatureEffectiveStatsResolvers(effectiveNature, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureEffectiveStatsResolvers(Nature effectiveNature, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNatureEffectiveStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(effectiveNature), isClosedSheet));
        resolvers.add(getNatureEffectiveStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(effectiveNature), isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getCustomMintnessResolver(boolean isMinted, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_MINTNESS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomMintnessTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_MINTNESS_TEMPLATE_TAG_STRING,
            isMinted ? newTemplate : ""
        );
    }

    private static TagResolver.Single getLevelResolver(String level)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.LEVEL_TEMPLATE_TAG_STRING,
            level
        );
    }

    private static TagResolver.Single getCurrentExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CURRENT_EXPERIENCE_TEMPLATE_TAG_STRING,
            exp
        );
    }

    private static TagResolver.Single getRequiredExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REQUIRED_EXPERIENCE_TEMPLATE_TAG_STRING,
            exp
        );
    }

    private static TagResolver.Single getRemainingExperienceResolver(String exp)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REMAINING_EXPERIENCE_TEMPLATE_TAG_STRING,
            exp
        );
    }

    public static TagResolver.Single getCustomExperienceResolver(int level, int currentExp, int remainingExp)
    {
        if (level == 100)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomExperienceTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomExperienceResolvers(currentExp, remainingExp))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomExperienceResolvers(int currentExp, int remainingExp)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCurrentExperienceResolver(String.valueOf(currentExp)));
        resolvers.add(getRequiredExperienceResolver(String.valueOf(currentExp + remainingExp)));
        resolvers.add(getRemainingExperienceResolver(String.valueOf(remainingExp)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getFriendshipResolver(String friendship, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.FRIENDSHIP_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.FRIENDSHIP_TEMPLATE_TAG_STRING,
            friendship
        );
    }

    private static TagResolver.Single getMove1Resolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.MOVE1_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TEMPLATE_TAG_STRING,
            moves.size() > 0 ? moves.get(0).getDisplayName().getString() : ""
        );
    }

    private static TagResolver.Single getMove1UsedPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE1_USED_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() - moves.get(0).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove1RemainingPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE1_REMAINING_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove1TotalPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE1_TOTAL_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove1CustomPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (moves.size() < 1)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors
        (
            ChatMiniMessage.getMoveCustomPPTemplate().replace
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                ChatMiniMessage.MOVE1_TEMPLATE_TAG_STRING
            )
        );

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove1CustomPPResolvers(moves, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove1CustomPPResolvers(List<Move> moves, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove1UsedPPResolver(moves, isClosedSheet));
        resolvers.add(getMove1RemainingPPResolver(moves, isClosedSheet));
        resolvers.add(getMove1TotalPPResolver(moves, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove2Resolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.MOVE2_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TEMPLATE_TAG_STRING,
            moves.size() > 1 ? moves.get(1).getDisplayName().getString() : ""
        );
    }

    private static TagResolver.Single getMove2UsedPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_USED_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() - moves.get(1).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove2RemainingPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_REMAINING_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove2TotalPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_TOTAL_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove2CustomPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (moves.size() < 2)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors
        (
            ChatMiniMessage.getMoveCustomPPTemplate().replace
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                ChatMiniMessage.MOVE2_TEMPLATE_TAG_STRING
            )
        );

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove2CustomPPResolvers(moves, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove2CustomPPResolvers(List<Move> moves, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove2UsedPPResolver(moves, isClosedSheet));
        resolvers.add(getMove2RemainingPPResolver(moves, isClosedSheet));
        resolvers.add(getMove2TotalPPResolver(moves, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove3Resolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.MOVE3_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TEMPLATE_TAG_STRING,
            moves.size() > 2 ? moves.get(2).getDisplayName().getString() : ""
        );
    }

    private static TagResolver.Single getMove3UsedPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE3_USED_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() - moves.get(2).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove3RemainingPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE3_REMAINING_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove3TotalPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE3_TOTAL_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove3CustomPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (moves.size() < 3)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors
        (
            ChatMiniMessage.getMoveCustomPPTemplate().replace
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                ChatMiniMessage.MOVE3_TEMPLATE_TAG_STRING
            )
        );

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove3CustomPPResolvers(moves, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove3CustomPPResolvers(List<Move> moves, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove3UsedPPResolver(moves, isClosedSheet));
        resolvers.add(getMove3RemainingPPResolver(moves, isClosedSheet));
        resolvers.add(getMove3TotalPPResolver(moves, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove4Resolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.MOVE4_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TEMPLATE_TAG_STRING,
            moves.size() > 3 ? moves.get(3).getDisplayName().getString() : ""
        );
    }

    private static TagResolver.Single getMove4UsedPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE4_USED_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() - moves.get(3).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove4RemainingPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE4_REMAINING_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove4TotalPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE4_TOTAL_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove4CustomPPResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (moves.size() < 4)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors
        (
            ChatMiniMessage.getMoveCustomPPTemplate().replace
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                ChatMiniMessage.MOVE4_TEMPLATE_TAG_STRING
            )
        );

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove4CustomPPResolvers(moves, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove4CustomPPResolvers(List<Move> moves, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove4UsedPPResolver(moves, isClosedSheet));
        resolvers.add(getMove4RemainingPPResolver(moves, isClosedSheet));
        resolvers.add(getMove4TotalPPResolver(moves, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomMovesResolver(List<Move> moves, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_MOVES_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        Component component = Component.empty();

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomMoveTemplate());

        for (int i = 0; i < moves.size(); i++)
        {
            String moveTemplate = (resolveCustomMoveColor(newTemplate, moves.get(i)) + (i < moves.size() - 1 ? "<newline>" : "")).replace
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                "%s%s".formatted(ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING, String.valueOf(i + 1))
            );

            Component moveComponent = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
            (
                i == 0 ? resolveMove1Color(moveTemplate, moves)
                    : i == 1
                    ? resolveMove2Color(moveTemplate, moves)
                        : i == 2
                        ? resolveMove3Color(moveTemplate, moves)
                            : resolveMove4Color(moveTemplate, moves),
                TagResolver.builder()
                    .resolver(getMoveResolver(moves.get(i), isClosedSheet))
                    .resolver(getMoveCustomPPResolver(moves.get(i), isClosedSheet))
                    .resolvers(getMove1Resolver(moves, isClosedSheet))
                    .resolvers(getMove1CustomPPResolver(moves, isClosedSheet))
                    .resolvers(getMove1CustomPPResolvers(moves, isClosedSheet))
                    .resolvers(getMove2Resolver(moves, isClosedSheet))
                    .resolvers(getMove2CustomPPResolver(moves, isClosedSheet))
                    .resolvers(getMove2CustomPPResolvers(moves, isClosedSheet))
                    .resolvers(getMove3Resolver(moves, isClosedSheet))
                    .resolvers(getMove3CustomPPResolver(moves, isClosedSheet))
                    .resolvers(getMove3CustomPPResolvers(moves, isClosedSheet))
                    .resolvers(getMove4Resolver(moves, isClosedSheet))
                    .resolvers(getMove4CustomPPResolver(moves, isClosedSheet))
                    .resolvers(getMove4CustomPPResolvers(moves, isClosedSheet))
                    .build()
            );

            component = component.append(moveComponent);
        }

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_MOVES_TEMPLATE_TAG_STRING,
            component
        );
    }

    private static TagResolver.Single getMoveResolver(Move move, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
            move.getDisplayName().getString()
        );
    }

    private static TagResolver.Single getMoveCustomPPResolver(Move move, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(
            newTemplate,
            TagResolver.builder()
                .resolver(getMoveUsedPPResolver(move, isClosedSheet))
                .resolver(getMoveRemainingPPResolver(move, isClosedSheet))
                .resolver(getMoveTotalPPResolver(move, isClosedSheet))
                .build()
        );

        return Placeholder.component(
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    private static TagResolver.Single getMoveUsedPPResolver(Move move, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE_USED_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getMaxPp() - move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveRemainingPPResolver(Move move, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE_REMAINING_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveTotalPPResolver(Move move, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE_TOTAL_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getMaxPp())
        );
    }

    private static TagResolver.Single getRealIVTotalResolver(int ivTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.REAL_IV_TOTAL_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getEffectiveIVTotalResolver(int ivTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.EFFECTIVE_IV_TOTAL_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getRealIVPercentageResolver(int ivTotal, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : "%.2f".formatted((ivTotal / 186.0) * 100.0)
        );
    }

    private static TagResolver.Single getEffectiveIVPercentageResolver(int ivTotal, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : "%.2f".formatted((ivTotal / 186.0) * 100.0)
        );
    }

    public static TagResolver.Single getCustomRealIVPercentageResolver(int totalIvs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomRealIVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomRealIVPercentageResolvers(totalIvs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomRealIVPercentageResolvers(int totalIvs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealIVTotalResolver(totalIvs, isClosedSheet));
        resolvers.add(getRealIVPercentageResolver(totalIvs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomEffectiveIVPercentageResolver(int totalIvs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomEffectiveIVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveIVPercentageResolvers(totalIvs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveIVPercentageResolvers(int totalIvs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveIVTotalResolver(totalIvs, isClosedSheet));
        resolvers.add(getEffectiveIVPercentageResolver(totalIvs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomGeneralIVsResolver(int totalRealIVs, int totalEffectiveIVs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomGeneralIVsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomGeneralIVsResolvers(int totalRealIVs, int totalEffectiveIVs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomRealIVPercentageResolver(totalRealIVs, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomRealIVPercentageResolvers(totalRealIVs, isClosedSheet)));
        resolvers.add(getCustomEffectiveIVPercentageResolver(totalEffectiveIVs, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveIVPercentageResolvers(totalEffectiveIVs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealHpIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_HP_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.HP))
        );
    }

    private static TagResolver.Single getEffectiveHpIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.HP))
        );
    }

    public static TagResolver.Single getCustomEffectiveHpIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.HP))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveHpColor(ChatMiniMessage.getCustomEffectiveHpIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveHpIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveHpIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveHpIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomHpIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_HP_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveHpColor(ChatMiniMessage.getCustomHpIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHpIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HP_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHpIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealHpIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveHpIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveHpIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealAtkIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_ATK_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.ATTACK))
        );
    }

    private static TagResolver.Single getEffectiveAtkIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.ATTACK))
        );
    }

    public static TagResolver.Single getCustomEffectiveAtkIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.ATTACK))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveAtkColor(ChatMiniMessage.getCustomEffectiveAtkIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveAtkIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveAtkIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveAtkIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomAtkIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveAtkColor(ChatMiniMessage.getCustomAtkIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomAtkIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomAtkIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealAtkIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveAtkIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveAtkIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealDefIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_DEF_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.DEFENCE))
        );
    }

    private static TagResolver.Single getEffectiveDefIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.DEFENCE))
        );
    }

    public static TagResolver.Single getCustomEffectiveDefIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.DEFENCE))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveDefColor(ChatMiniMessage.getCustomEffectiveDefIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveDefIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveDefIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveDefIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomDefIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveDefColor(ChatMiniMessage.getCustomDefIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomDefIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomDefIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealDefIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveDefIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveDefIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpaIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPA_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.SPECIAL_ATTACK))
        );
    }

    private static TagResolver.Single getEffectiveSpaIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.SPECIAL_ATTACK))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpaIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.SPECIAL_ATTACK))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpaColor(ChatMiniMessage.getCustomEffectiveSpaIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveSpaIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpaIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpaIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpaIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpaColor(ChatMiniMessage.getCustomSpaIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpaIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpaIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpaIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveSpaIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpaIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpdIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPD_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.SPECIAL_DEFENCE))
        );
    }

    private static TagResolver.Single getEffectiveSpdIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.SPECIAL_DEFENCE))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpdIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.SPECIAL_DEFENCE))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpdColor(ChatMiniMessage.getCustomEffectiveSpdIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveSpdIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpdIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpdIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpdIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpdColor(ChatMiniMessage.getCustomSpdIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpdIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpdIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpdIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveSpdIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpdIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpeIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPE_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.get(Stats.SPEED))
        );
    }

    private static TagResolver.Single getEffectiveSpeIVsResolver(IVs ivs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(ivs.getEffectiveBattleIV(Stats.SPEED))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpeIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        if (!hyperTrainedStats.contains(Stats.SPEED))
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpeColor(ChatMiniMessage.getCustomEffectiveSpeIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveSpeIVsResolvers(ivs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpeIVsResolvers(IVs ivs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpeIVsResolver(ivs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpeIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpeColor(ChatMiniMessage.getCustomSpeIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpeIVsResolvers(ivs, hyperTrainedStats, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpeIVsResolver(ivs, isClosedSheet));
        resolvers.add(getCustomEffectiveSpeIVsResolver(ivs, hyperTrainedStats, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpeIVsResolvers(ivs, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getEVTotalResolver(int evTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.EV_TOTAL_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.EV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(evTotal)
        );
    }

    private static TagResolver.Single getEVPercentageResolver(int evTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.EV_PERCENTAGE_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        return Placeholder.unparsed
        (
            ChatMiniMessage.EV_PERCENTAGE_TEMPLATE_TAG_STRING,
            "%.2f".formatted((evTotal / 510.0) * 100.0)
        );
    }

    public static TagResolver.Single getCustomEVPercentageResolver(int evTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomEVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEVPercentageResolvers(evTotal, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEVPercentageResolvers(int evTotal, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEVTotalResolver(evTotal, isClosedSheet));
        resolvers.add(getEVPercentageResolver(evTotal, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomGeneralEVsResolver(int evTotal, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.parsed
            (
                ChatMiniMessage.CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING,
                resolveCustomColors(ChatMiniMessage.getHiddenTemplate())
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomGeneralEVsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomGeneralEVsResolvers(evTotal, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomGeneralEVsResolvers(int evTotal, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomEVPercentageResolver(evTotal, isClosedSheet));
        resolvers.addAll(Arrays.asList(getCustomEVPercentageResolvers(evTotal, isClosedSheet)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getHpEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HP_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.HP))
        );
    }

    public static TagResolver.Single getCustomHpEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_HP_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveHpColor(ChatMiniMessage.getCustomHpEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHpEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HP_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHpEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getHpEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getAtkEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ATK_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.ATTACK))
        );
    }

    public static TagResolver.Single getCustomAtkEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveAtkColor(ChatMiniMessage.getCustomAtkEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomAtkEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomAtkEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getAtkEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getDefEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.DEF_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.DEFENCE))
        );
    }

    public static TagResolver.Single getCustomDefEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveDefColor(ChatMiniMessage.getCustomDefEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomDefEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomDefEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getDefEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpaEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPA_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.SPECIAL_ATTACK))
        );
    }

    public static TagResolver.Single getCustomSpaEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpaColor(ChatMiniMessage.getCustomSpaEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpaEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpaEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpaEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpdEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPD_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.SPECIAL_DEFENCE))
        );
    }

    public static TagResolver.Single getCustomSpdEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpdColor(ChatMiniMessage.getCustomSpdEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpdEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpdEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpdEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpeEVResolver(EVs evs, boolean isClosedSheet)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPE_EV_TEMPLATE_TAG_STRING,
            isClosedSheet ? "" : String.valueOf(evs.get(Stats.SPEED))
        );
    }

    public static TagResolver.Single getCustomSpeEVsResolver(EVs evs, boolean isClosedSheet)
    {
        if (isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(resolveSpeColor(ChatMiniMessage.getCustomSpeEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpeEVsResolvers(evs, isClosedSheet))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeEVsResolvers(EVs evs, boolean isClosedSheet)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpeEVResolver(evs, isClosedSheet));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSizeResolver(String size)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SIZE_TEMPLATE_TAG_STRING,
            size
        );
    }

    private static TagResolver.Single getScaleModifierResolver(float scale)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SCALE_MODIFIER_TEMPLATE_TAG_STRING,
            String.format("%.2f", scale)
        );
    }

    private static TagResolver.Single getScaleModifier100Resolver(float scale)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SCALE_MODIFIER_100_TEMPLATE_TAG_STRING,
            String.format("%d", (int) (scale * 100))
        );
    }

    private static TagResolver.Single getEggGroupsResolver(HashSet<EggGroup> eggGroups)
    {
        StringBuilder template = new StringBuilder();

        for (EggGroup eggGroup : eggGroups)
        {
            if (!template.isEmpty()) template.append(", ");
            template.append("<%s>".formatted(CobblemonUtils.getEggGroupTemplateName(eggGroup).toLowerCase()));
        }

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            template.toString(),
            TagResolver.builder()
                .resolvers(getEggGroupsResolvers())
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.EGG_GROUPS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getEggGroupsResolvers()
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getAmorphousResolver());
        resolvers.add(getBugResolver());
        resolvers.add(getDittoResolver());
        resolvers.add(getDragonResolver());
        resolvers.add(getFairyResolver());
        resolvers.add(getFieldResolver());
        resolvers.add(getFlyingResolver());
        resolvers.add(getGrassResolver());
        resolvers.add(getHumanLikeResolver());
        resolvers.add(getMineralResolver());
        resolvers.add(getMonsterResolver());
        resolvers.add(getUndiscoveredResolver());
        resolvers.add(getWater1Resolver());
        resolvers.add(getWater2Resolver());
        resolvers.add(getWater3Resolver());

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getAmorphousResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getAmorphousTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.AMORPHOUS_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getBugResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getBugTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.BUG_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getDittoResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getDittoTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.DITTO_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getDragonResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getDragonTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.DRAGON_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getFairyResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getFairyTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.FAIRY_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getFieldResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getFieldTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.FIELD_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getFlyingResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getFlyingTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.FLYING_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getGrassResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getGrassTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.GRASS_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getHumanLikeResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getHumanLikeTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.HUMAN_LIKE_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getMineralResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getMineralTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.MINERAL_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getMonsterResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getMonsterTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.MONSTER_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getUndiscoveredResolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getUndiscoveredTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.UNDISCOVERED_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getWater1Resolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getWater1Template());

        return Placeholder.parsed
        (
            ChatMiniMessage.WATER1_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getWater2Resolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getWater2Template());

        return Placeholder.parsed
        (
            ChatMiniMessage.WATER2_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getWater3Resolver()
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getWater3Template());

        return Placeholder.parsed
        (
            ChatMiniMessage.WATER3_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    private static TagResolver.Single getNeuteredResolver(boolean neutered)
    {
        String newTemplate = resolveCustomColors(neutered ? ChatMiniMessage.getTrueTemplate() : ChatMiniMessage.getFalseTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.NEUTERED_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    public static TagResolver.Single getCustomNeuteredResolver(boolean neutered)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomNeuteredTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomNeuteredResolvers(neutered))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NEUTERED_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNeuteredResolvers(boolean neutered)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNeuteredResolver(neutered));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getOriginalTrainerNameResolver(String ot)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ORIGINAL_TRAINER_NAME_TEMPLATE_TAG_STRING,
            ot
        );
    }

    private static TagResolver.Single getItemBaseNameResolver(ItemStack item)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ITEM_BASE_NAME_TEMPLATE_TAG_STRING,
            MinecraftUtils.getItemBaseNameString(item)
        );
    }

    private static TagResolver.Single getItemAnvilNameResolver(ItemStack item)
    {
        Text anvilName = MinecraftUtils.getItemAnvilName(item);

        return Placeholder.unparsed
        (
            ChatMiniMessage.ITEM_ANVIL_NAME_TEMPLATE_TAG_STRING,
            anvilName != null && !anvilName.getString().isEmpty()
                ? anvilName.getString()
                : MinecraftUtils.getItemBaseNameString(item)
        );
    }

    private static TagResolver.Single getItemCustomNameResolver(ItemStack item)
    {
        Text nbtName = MinecraftUtils.getItemNBTName(item);

        return Placeholder.unparsed
        (
            ChatMiniMessage.ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING,
            nbtName != null && !nbtName.getString().isEmpty()
                ? nbtName.getString()
                : MinecraftUtils.getItemBaseNameString(item)
        );
    }

    private static TagResolver.Single getItemRealNameResolver(ItemStack item)
    {
        Text anvilName = MinecraftUtils.getItemAnvilName(item);
        Text nbtName = MinecraftUtils.getItemNBTName(item);

        return Placeholder.unparsed
        (
            ChatMiniMessage.ITEM_REAL_NAME_TEMPLATE_TAG_STRING,
            anvilName != null && !anvilName.getString().isEmpty()
                ? anvilName.getString()
                : nbtName != null && !nbtName.getString().isEmpty()
                    ? nbtName.getString()
                    : MinecraftUtils.getItemBaseNameString(item)
        );
    }

    private static String resolveCustomColors(String template)
    {
        for (Entry<String, String> customColor : ChatMiniMessage.getCustomColors().entrySet())
        {
            template = template.replace
            (
                "<%s>".formatted(customColor.getKey()),
                customColor.getValue()
            );
        }

        return template;
    }

    private static String resolveTitleColor(String template, Pokemon pokemon)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.TITLE_COLOR_TEMPLATE_TAG_STRING),
            CobblemonUtils.getPokemonTitleColor(pokemon)
        );
    }

    private static String resolveHpColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.HP_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getHpColor()
        );
    }

    private static String resolveAtkColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.ATK_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getAtkColor()
        );
    }

    private static String resolveDefColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.DEF_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getDefColor()
        );
    }

    private static String resolveSpaColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.SPA_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getSpaColor()
        );
    }

    private static String resolveSpdColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.SPD_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getSpdColor()
        );
    }

    private static String resolveSpeColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.SPE_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getSpeColor()
        );
    }

    private static String resolveCustomCategoryColor(String template, Pokemon pokemon)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_CATEGORY_COLOR_TEMPLATE_TAG_STRING),
            getCategoryColor(pokemon, false)
        );
    }

    private static String resolveCustomCategoryBracketColor(String template, Pokemon pokemon)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_CATEGORY_BRACKET_COLOR_TEMPLATE_TAG_STRING),
            getCategoryBracketColor(pokemon, false)
        );
    }

    private static String resolveCustomShinyColor1(String template, Pokemon pokemon, List<ElementalType> types)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_COLOR_1_TEMPLATE_TAG_STRING),
            pokemon.getShiny() ? ChatMiniMessage.getShinyColor() : String.format("#%06x", types.get(0).getPrimaryColor())
        );
    }

    private static String resolveCustomShinyBracketColor1(String template, Pokemon pokemon, List<ElementalType> types)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_BRACKET_COLOR_1_TEMPLATE_TAG_STRING),
            pokemon.getShiny() ? ChatMiniMessage.getShinyBracketColor() : String.format("#%06x", types.get(0).getPrimaryColor())
        );
    }

    private static String resolveCustomShinyColor2(String template, Pokemon pokemon, List<ElementalType> types)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_COLOR_2_TEMPLATE_TAG_STRING),
            pokemon.getShiny() ? ChatMiniMessage.getShinyColor() : String.format("#%06x", types.get(types.size() > 1 ? 1 : 0).getPrimaryColor())
        );
    }

    private static String resolveCustomShinyBracketColor2(String template, Pokemon pokemon, List<ElementalType> types)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_BRACKET_COLOR_2_TEMPLATE_TAG_STRING),
            pokemon.getShiny() ? ChatMiniMessage.getShinyBracketColor() : String.format("#%06x", types.get(types.size() > 1 ? 1 : 0).getPrimaryColor())
        );
    }

    private static String resolveCustomShinyColorCategory(String template, Pokemon pokemon)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_COLOR_CATEGORY_TEMPLATE_TAG_STRING),
            getCategoryColor(pokemon, true)
        );
    }

    private static String resolveCustomShinyBracketColorCategory(String template, Pokemon pokemon)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.CUSTOM_SHINY_BRACKET_COLOR_CATEGORY_TEMPLATE_TAG_STRING),
            getCategoryBracketColor(pokemon, true)
        );
    }

    private static String resolveShinyColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.SHINY_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getShinyColor()
        );
    }

    private static String resolveShinyBracketColor(String template)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.SHINY_BRACKET_COLOR_TEMPLATE_TAG_STRING),
            ChatMiniMessage.getShinyBracketColor()
        );
    }

    private static String resolveType1Color(String template, ElementalType type)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.TYPE1_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", type.getPrimaryColor())
        );
    }

    private static String resolveType2Color(String template, ElementalType type)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.TYPE2_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", type.getPrimaryColor())
        );
    }

    private static String resolveTeraTypeColor(String template, TeraType type)
    {
        ElementalType elementalType = CobblemonUtils.getElementalTypeFromShowdownId(type.showdownId());

        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.TERA_TYPE_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", elementalType.getPrimaryColor())
        );
    }

    private static String resolveMove1Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE1_COLOR_TEMPLATE_TAG_STRING),
            moves.size() > 0 ? String.format("#%06x",  moves.get(0).getType().getPrimaryColor()) : "#ffffff"
        );
    }

    private static String resolveMove2Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE2_COLOR_TEMPLATE_TAG_STRING),
            moves.size() > 1 ? String.format("#%06x",  moves.get(1).getType().getPrimaryColor()) : "#ffffff"
        );
    }

    private static String resolveMove3Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE3_COLOR_TEMPLATE_TAG_STRING),
            moves.size() > 2 ? String.format("#%06x",  moves.get(2).getType().getPrimaryColor()) : "#ffffff"
        );
    }

    private static String resolveMove4Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE4_COLOR_TEMPLATE_TAG_STRING),
            moves.size() > 3 ? String.format("#%06x",  moves.get(3).getType().getPrimaryColor()) : "#ffffff"
        );
    }

    private static String resolveCustomMoveColor(String template, Move move)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", move.getType().getPrimaryColor())
        );
    }

    private static String resolveItemColor(String template, ItemStack item)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.ITEM_COLOR_TEMPLATE_TAG_STRING),
            MinecraftUtils.getItemColor(item)
        );
    }

    private static String resolveItemBracketColor(String template, ItemStack item)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.ITEM_BRACKET_COLOR_TEMPLATE_TAG_STRING),
            MinecraftUtils.getItemBracketColor(item)
        );
    }

    private static TagResolver.Single getHiddenResolver(boolean isClosedSheet)
    {
        if (!isClosedSheet)
        {
            return Placeholder.component
            (
                ChatMiniMessage.HIDDEN_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getHiddenTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.HIDDEN_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    public static String getCategoryColor(Pokemon pokemon, boolean returnShiny)
    {
        if (returnShiny && pokemon.getShiny()) return ChatMiniMessage.getShinyColor();
        if (ChatAspects.isCustomPokemon(pokemon.getAspects())) return ChatMiniMessage.getCustomAspectCategoryColor();
        if (pokemon.isLegendary()) return ChatMiniMessage.getLegendaryCategoryColor();
        if (pokemon.isMythical()) return ChatMiniMessage.getMythicalCategoryColor();
        if (pokemon.isUltraBeast()) return ChatMiniMessage.getUltraBeastCategoryColor();
        return ChatMiniMessage.getRegularCategoryColor();
    }

    public static String getCategoryBracketColor(Pokemon pokemon, boolean returnShiny)
    {
        if (returnShiny && pokemon.getShiny()) return ChatMiniMessage.getShinyBracketColor();
        if (ChatAspects.isCustomPokemon(pokemon.getAspects())) return ChatMiniMessage.getCustomAspectCategoryBracketColor();
        if (pokemon.isLegendary()) return ChatMiniMessage.getLegendaryCategoryBracketColor();
        if (pokemon.isMythical()) return ChatMiniMessage.getMythicalCategoryBracketColor();
        if (pokemon.isUltraBeast()) return ChatMiniMessage.getUltraBeastCategoryBracketColor();
        return ChatMiniMessage.getRegularCategoryBracketColor();
    }

    public static ShowItem toShowItem(ItemStack stack)
    {
        ShowItem showItem = ShowItem.showItem
        (
            Key.key(Registries.ITEM.getId(stack.getItem()).toString()),
            stack.getCount()
        );

        // This project stores its NBT-edited item name in ITEM_NAME.
        Text itemName = stack.get(DataComponentTypes.ITEM_NAME);

        if (itemName != null)
        {
            var adventureName = FabricAudiences.nonWrappingSerializer().deserialize(itemName);

            showItem = showItem.dataComponents
            (
                Map.of
                (
                    Key.key("minecraft:item_name"),
                    GsonDataComponentValue.gsonDataComponentValue
                    (
                        new JsonPrimitive(GsonComponentSerializer.gson().serialize(adventureName))
                    )
                )
            );
        }

        return showItem;
    }
}
