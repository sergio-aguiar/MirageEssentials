package com.sergioaguiar.mirageessentials.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import com.sergioaguiar.mirageessentials.config.chatparser.aspects.ChatAspects;
import com.sergioaguiar.mirageessentials.config.chatparser.minimessage.ChatMiniMessage;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings;
import com.sergioaguiar.mirageessentials.config.chatparser.strings.ChatStrings;

import net.kyori.adventure.platform.fabric.FabricAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minecraft.item.ItemStack;
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
        Component component = MINIMESSAGE_INSTANCE.deserialize
        (
            template,
            placeholders
        );

        return toMinecraftText(component);
    }

    public static List<Text> renderLines(String template, TagResolver placeholders)
    {
        List<Text> lines = new ArrayList<>();

        if (template == null || template.isEmpty()) return lines;

        String[] split = template.split("\\R", -1);
        for (String line : split) lines.add(render(line,placeholders));

        return lines;
    }

    public static Text toMinecraftText(Component component)
    {
        return FabricAudiences.nonWrappingSerializer().serialize(component);
    }

    public static Text renderPokeInfo(String template, Pokemon pokemon)
    {
        List<ElementalType> types = CobblemonUtils.getPokemonTypes(pokemon);
        List<Move> moves = CobblemonUtils.getPokemonMoves(pokemon);

        String newTemplate = resolveCustomColors
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
                                                                template,
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

        return render(newTemplate, getPokeInfoTagResolver(pokemon));
    }

    public static TagResolver getPokeInfoTagResolver(Pokemon pokemon)
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
                .resolver(getNameResolver(CobblemonUtils.getPokemonName(pokemon.getNickname(), CobblemonUtils.getPokemonSpecies(pokemon))))
                .resolver(getTitleResolver(CobblemonUtils.getPokemonTitle(pokemon)))
                .resolver(getGenderResolver(pokemon.getGender()))
                .resolver(getCaughtBallResolver(CobblemonUtils.getPokemonCaughtBall(pokemon)))
                .resolver(getCustomConditionResolver(pokemon))
                .resolvers(getCustomConditionResolvers(pokemon))
                .resolver(getCustomSpeciesResolver(pokemon))
                .resolvers(getCustomSpeciesResolvers(pokemon))
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
                .resolver(getMove1CustomPPResolver(moves))
                .resolvers(getMove1CustomPPResolvers(moves))
                .resolver(getMove2Resolver(moves))
                .resolver(getMove2CustomPPResolver(moves))
                .resolvers(getMove2CustomPPResolvers(moves))
                .resolver(getMove3Resolver(moves))
                .resolver(getMove3CustomPPResolver(moves))
                .resolvers(getMove3CustomPPResolvers(moves))
                .resolver(getMove4Resolver(moves))
                .resolver(getMove4CustomPPResolver(moves))
                .resolvers(getMove4CustomPPResolvers(moves))
                .resolver(getCustomMovesResolver(moves))
                .resolver(getCustomGeneralIVsResolver(totalRealIVs, totalEffectiveIVs))
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs))
                .resolver(getCustomHpIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomHpIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomAtkIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomAtkIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomDefIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomDefIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomSpaIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomSpaIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomSpdIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomSpdIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomSpeIVsResolver(ivs, hyperTrainedStats))
                .resolvers(getCustomSpeIVsResolvers(ivs, hyperTrainedStats))
                .resolver(getCustomGeneralEVsResolver(totalEvs))
                .resolvers(getCustomGeneralEVsResolvers(totalEvs))
                .resolver(getCustomHpEVsResolver(evs))
                .resolvers(getCustomHpEVsResolvers(evs))
                .resolver(getCustomAtkEVsResolver(evs))
                .resolvers(getCustomAtkEVsResolvers(evs))
                .resolver(getCustomDefEVsResolver(evs))
                .resolvers(getCustomDefEVsResolvers(evs))
                .resolver(getCustomSpaEVsResolver(evs))
                .resolvers(getCustomSpaEVsResolvers(evs))
                .resolver(getCustomSpdEVsResolver(evs))
                .resolvers(getCustomSpdEVsResolvers(evs))
                .resolver(getCustomSpeEVsResolver(evs))
                .resolvers(getCustomSpeEVsResolvers(evs))
                .resolver(getSizeResolver(CobblemonUtils.getPokemonSizeName(pokemon)))
                .resolver(getScaleModifierResolver(scale))
                .resolver(getScaleModifier100Resolver(scale))
                .resolver(getEggGroupsResolver(pokemon.getSpecies().getEggGroups()))
                .resolver(getCustomNeuteredResolver(isNeutered))
                .resolvers(getCustomNeuteredResolvers(isNeutered))
                .resolver(getOriginalTrainerNameResolver(cosmeticItemCustomName))
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

    private static TagResolver.Single getGenderResolver(Gender gender)
    {
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

    private static TagResolver.Single getCurrentHealthResolver(String currentHealth)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.CURRENT_HEALTH_TEMPLATE_TAG_STRING,
            currentHealth
        );
    }

    private static TagResolver.Single getMaxHealthResolver(String maxHealth)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MAX_HEALTH_TEMPLATE_TAG_STRING,
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

        String newTemplate = resolveCustomColors(template);

        return Placeholder.parsed
        (
            ChatMiniMessage.STATUS_TEMPLATE_TAG_STRING,
            newTemplate
        );
    }

    public static TagResolver.Single getCustomStatusResolver(Pokemon pokemon)
    {
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
                .resolvers(getCustomStatusResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomStatusResolvers(Pokemon pokemon)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getStatusResolver(CobblemonUtils.getPokemonStatus(pokemon), pokemon.isFainted()));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomConditionResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomConditionTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomConditionResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_CONDITION_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomConditionResolvers(Pokemon pokemon)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCurrentHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon)));
        resolvers.add(getMaxHealthResolver(CobblemonUtils.getPokemonCurrentHealth(pokemon)));
        resolvers.add(getCustomStatusResolver(pokemon));
        resolvers.addAll(Arrays.asList(getCustomStatusResolvers(pokemon)));

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

    private static TagResolver.Single getTeraTypeResolver(TeraType type)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.TERA_TYPE_TEMPLATE_TAG_STRING,
            TextUtils.toTitleCase(type.getName())
        );
    }

    public static TagResolver.Single getCustomTypesResolver(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_TYPES_TEMPLATE_TAG_STRING,
            isMonotype 
                ? getCustomTypesMonotypeComponent(types, teraType, isMonotype)
                : getCustomTypesDuotypeComponent(types, teraType, isMonotype)
        );
    }

    public static Component getCustomTypesMonotypeComponent(List<ElementalType> types, TeraType teraType , boolean isMonotype)
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
                .resolvers(getCustomTypesMonotypeResolvers(types, teraType, isMonotype))
                .build()
        );
    }

    public static Component getCustomTypesDuotypeComponent(List<ElementalType> types, TeraType teraType , boolean isMonotype)
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
                .resolvers(getCustomTypesDuotypeResolvers(types, teraType, isMonotype))
                .build()
        );
    }

    public static TagResolver.Single[] getCustomTypesMonotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getType1Resolver(types.get(0)));
        resolvers.add(getTeraTypeResolver(teraType));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single[] getCustomTypesDuotypeResolvers(List<ElementalType> types, TeraType teraType , boolean isMonotype)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getType2Resolver(isMonotype ? types.get(0) : types.get(1)));
        resolvers.addAll(Arrays.asList(getCustomTypesMonotypeResolvers(types, teraType, isMonotype)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getCustomShininessResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomShininessTemplate());

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

        return Placeholder.component(
                ChatMiniMessage.FORMS_EXPANDED_TEMPLATE_TAG_STRING,
                MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(newTemplate)
        );
    }

    private static TagResolver.Single getFormsFullyExpandedResolver(String form, List<SpeciesFeature> features, Set<String> aspects)
    {
        StringBuilder result = getExpandedFormStringBuilder(form, features);
        result = getFullyExpandedFormStringBuilder(result, aspects);

        String newTemplate = resolveCustomColors(result.toString());

        return Placeholder.component(
                ChatMiniMessage.FORMS_FULLY_EXPANDED_TEMPLATE_TAG_STRING,
                MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(newTemplate)
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
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomFormTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomFormsResolvers(pokemon))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_FORMS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomFormsResolvers(Pokemon pokemon)
    {
        String form = CobblemonUtils.getPokemonBaseForm(pokemon);
        Set<String> aspects = pokemon.getAspects();
        List<SpeciesFeature> features = pokemon.getFeatures();

        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomShininessResolver(pokemon));
        resolvers.add(getCustomAlphanessResolver(pokemon));
        resolvers.add(getFormResolver(form));
        resolvers.add(getFormsExpandedResolver(form, features));
        resolvers.add(getFormsFullyExpandedResolver(form, features, aspects));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpeciesResolver(Pokemon pokemon)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomSpeciesTemplate());

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
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomFormsResolver(pokemon));
        resolvers.addAll(Arrays.asList(getCustomFormsResolvers(pokemon)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getHeldItemResolver(String heldItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_TEMPLATE_TAG_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getHeldItemCustomNameResolver(String heldItem)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HELD_ITEM_TEMPLATE_TAG_STRING,
            heldItem
        );
    }

    private static TagResolver.Single getCustomHeldItemResolver(String heldItemName, String heldItemCustomName)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomHeldItemTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHeldItemResolvers(heldItemName, heldItemCustomName))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHeldItemResolvers(String heldItemName, String heldItemCustomName)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getHeldItemResolver(heldItemName));
        resolvers.add(getHeldItemCustomNameResolver(heldItemCustomName));

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

    private static TagResolver.Single getCustomCosmeticItemResolver(String cosmeticItemName, String cosmeticItemCustomName)
    {
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

    private static TagResolver.Single getAbilityResolver(String ability)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ABILITY_TEMPLATE_TAG_STRING,
            ability
        );
    }

    private static TagResolver.Single getCustomHiddenAbilityResolver(boolean isHA)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomHiddenAbilityTemplate());

        return Placeholder.parsed
        (
            ChatMiniMessage.CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING,
            isHA ? newTemplate : ""
        );
    }

    private static TagResolver.Single getNatureRealResolver(String nature)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_TEMPLATE_TAG_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureEffectiveResolver(String nature)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_TEMPLATE_TAG_STRING,
            nature
        );
    }

    private static TagResolver.Single getNatureRealStatUpResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_UP_TEMPLATE_TAG_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureRealStatDownResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_REAL_STAT_DOWN_TEMPLATE_TAG_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatUpResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_UP_TEMPLATE_TAG_STRING,
            stat
        );
    }

    private static TagResolver.Single getNatureEffectiveStatDownResolver(String stat)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.NATURE_EFFECTIVE_STAT_DOWN_TEMPLATE_TAG_STRING,
            stat
        );
    }

    public static TagResolver.Single getCustomNatureRealStatsResolver(Nature realNature)
    {
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
                .resolvers(getCustomNatureRealStatsResolvers(realNature))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureRealStatsResolvers(Nature realNature)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNatureRealStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(realNature)));
        resolvers.add(getNatureRealStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(realNature)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomNatureEffectiveStatsResolver(Nature effectiveNature)
    {
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
                .resolvers(getCustomNatureEffectiveStatsResolvers(effectiveNature))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomNatureEffectiveStatsResolvers(Nature effectiveNature)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getNatureEffectiveStatUpResolver(CobblemonUtils.getPokemonNatureIncreasedStat(effectiveNature)));
        resolvers.add(getNatureEffectiveStatDownResolver(CobblemonUtils.getPokemonNatureDecreasedStat(effectiveNature)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getCustomMintnessResolver(boolean isMinted)
    {
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

    private static TagResolver.Single getFriendshipResolver(String friendship)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.FRIENDSHIP_TEMPLATE_TAG_STRING,
            friendship
        );
    }

    private static TagResolver.Single getMove1Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TEMPLATE_TAG_STRING,
            moves.size() > 0 ? moves.get(0).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove1UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() - moves.get(0).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove1RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove1TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE1_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 0 ? moves.get(0).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove1CustomPPResolver(List<Move> moves)
    {
        if (moves.size() < 1)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove1CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove1CustomPPResolvers(List<Move> moves)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove1UsedPPResolver(moves));
        resolvers.add(getMove1RemainingPPResolver(moves));
        resolvers.add(getMove1TotalPPResolver(moves));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove2Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TEMPLATE_TAG_STRING,
            moves.size() > 1 ? moves.get(1).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove2UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() - moves.get(1).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove2RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove2TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE2_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 1 ? moves.get(1).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove2CustomPPResolver(List<Move> moves)
    {
        if (moves.size() < 2)
        {
            return Placeholder.component
            (
                ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING,
                Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove2CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove2CustomPPResolvers(List<Move> moves)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove2UsedPPResolver(moves));
        resolvers.add(getMove2RemainingPPResolver(moves));
        resolvers.add(getMove2TotalPPResolver(moves));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove3Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TEMPLATE_TAG_STRING,
            moves.size() > 2 ? moves.get(2).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove3UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() - moves.get(2).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove3RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove3TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE3_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 2 ? moves.get(2).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove3CustomPPResolver(List<Move> moves)
    {
        if (moves.size() < 3)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove3CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove3CustomPPResolvers(List<Move> moves)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove3UsedPPResolver(moves));
        resolvers.add(getMove3RemainingPPResolver(moves));
        resolvers.add(getMove3TotalPPResolver(moves));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getMove4Resolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TEMPLATE_TAG_STRING,
            moves.size() > 3 ? moves.get(3).getDisplayName().toString() : ""
        );
    }

    private static TagResolver.Single getMove4UsedPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() - moves.get(3).getCurrentPp() : 0) 
        );
    }

    private static TagResolver.Single getMove4RemainingPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getCurrentPp() : 0)
        );
    }

    private static TagResolver.Single getMove4TotalPPResolver(List<Move> moves)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE4_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(moves.size() > 3 ? moves.get(3).getMaxPp() : 0) 
        );
    }

    public static TagResolver.Single getMove4CustomPPResolver(List<Move> moves)
    {
        if (moves.size() < 4)
        {
            return Placeholder.component
            (
                    ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING,
                    Component.empty()
            );
        }

        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getMove4CustomPPResolvers(moves))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getMove4CustomPPResolvers(List<Move> moves)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getMove4UsedPPResolver(moves));
        resolvers.add(getMove4RemainingPPResolver(moves));
        resolvers.add(getMove4TotalPPResolver(moves));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomMovesResolver(List<Move> moves)
    {
        Component component = Component.empty();

        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomMoveTemplate());

        for (int i = 0; i < moves.size(); i++)
        {
            String moveTemplate = resolveCustomMoveColor(newTemplate, moves.get(i));

            Component moveComponent = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
            (
                moveTemplate + (i < moves.size() - 1 ? "<newline>" : ""),
                TagResolver.builder()
                    .resolver(getMoveResolver(moves.get(i)))
                    .resolver(getMoveCustomPPResolver(moves.get(i)))
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

    private static TagResolver.Single getMoveResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_TEMPLATE_TAG_STRING,
            move.getDisplayName().getString()
        );
    }

    private static TagResolver.Single getMoveCustomPPResolver(Move move)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getMoveCustomPPTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize(
            newTemplate,
            TagResolver.builder()
                .resolver(getMoveUsedPPResolver(move))
                .resolver(getMoveRemainingPPResolver(move))
                .resolver(getMoveTotalPPResolver(move))
                .build()
        );

        return Placeholder.component(
            ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING,
            component
        );
    }

    private static TagResolver.Single getMoveUsedPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_USED_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getMaxPp() - move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveRemainingPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_REMAINING_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getCurrentPp())
        );
    }

    private static TagResolver.Single getMoveTotalPPResolver(Move move)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.MOVE_TOTAL_PP_TEMPLATE_TAG_STRING,
            String.valueOf(move.getMaxPp())
        );
    }

    private static TagResolver.Single getRealIVTotalResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getEffectiveIVTotalResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(ivTotal)
        );
    }

    private static TagResolver.Single getRealIVPercentageResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            "%.2f".formatted((ivTotal / 186.0) * 100.0)
        );
    }

    private static TagResolver.Single getEffectiveIVPercentageResolver(int ivTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            "%.2f".formatted((ivTotal / 186.0) * 100.0)
        );
    }

    public static TagResolver.Single getCustomRealIVPercentageResolver(int totalIvs)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomRealIVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomRealIVPercentageResolvers(totalIvs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomRealIVPercentageResolvers(int totalIvs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealIVTotalResolver(totalIvs));
        resolvers.add(getRealIVPercentageResolver(totalIvs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomEffectiveIVPercentageResolver(int totalIvs)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomEffectiveIVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEffectiveIVPercentageResolvers(totalIvs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveIVPercentageResolvers(int totalIvs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveIVTotalResolver(totalIvs));
        resolvers.add(getEffectiveIVPercentageResolver(totalIvs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomGeneralIVsResolver(int totalRealIVs, int totalEffectiveIVs)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomGeneralIVsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomGeneralIVsResolvers(totalRealIVs, totalEffectiveIVs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING,
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

    private static TagResolver.Single getRealHpIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_HP_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.HP))
        );
    }

    private static TagResolver.Single getEffectiveHpIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.HP))
        );
    }

    public static TagResolver.Single getCustomEffectiveHpIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.HP))
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
                .resolvers(getCustomEffectiveHpIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveHpIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveHpIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomHpIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveHpColor(ChatMiniMessage.getCustomHpIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHpIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HP_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHpIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealHpIVsResolver(ivs));
        resolvers.add(getCustomEffectiveHpIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveHpIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealAtkIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_ATK_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.ATTACK))
        );
    }

    private static TagResolver.Single getEffectiveAtkIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.ATTACK))
        );
    }

    public static TagResolver.Single getCustomEffectiveAtkIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.ATTACK))
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
                .resolvers(getCustomEffectiveAtkIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveAtkIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveAtkIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomAtkIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveAtkColor(ChatMiniMessage.getCustomAtkIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomAtkIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomAtkIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealAtkIVsResolver(ivs));
        resolvers.add(getCustomEffectiveAtkIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveAtkIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealDefIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_DEF_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.DEFENCE))
        );
    }

    private static TagResolver.Single getEffectiveDefIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.DEFENCE))
        );
    }

    public static TagResolver.Single getCustomEffectiveDefIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.DEFENCE))
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
                .resolvers(getCustomEffectiveDefIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveDefIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveDefIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomDefIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveDefColor(ChatMiniMessage.getCustomDefIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomDefIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomDefIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealDefIVsResolver(ivs));
        resolvers.add(getCustomEffectiveDefIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveDefIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpaIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPA_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.SPECIAL_ATTACK))
        );
    }

    private static TagResolver.Single getEffectiveSpaIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.SPECIAL_ATTACK))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpaIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.SPECIAL_ATTACK))
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
                .resolvers(getCustomEffectiveSpaIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpaIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpaIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpaIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveSpaColor(ChatMiniMessage.getCustomSpaIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpaIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpaIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpaIVsResolver(ivs));
        resolvers.add(getCustomEffectiveSpaIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpaIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpdIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPD_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.SPECIAL_DEFENCE))
        );
    }

    private static TagResolver.Single getEffectiveSpdIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.SPECIAL_DEFENCE))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpdIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.SPECIAL_DEFENCE))
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
                .resolvers(getCustomEffectiveSpdIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpdIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpdIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpdIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveSpdColor(ChatMiniMessage.getCustomSpdIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpdIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpdIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpdIVsResolver(ivs));
        resolvers.add(getCustomEffectiveSpdIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpdIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getRealSpeIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.REAL_SPE_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.get(Stats.SPEED))
        );
    }

    private static TagResolver.Single getEffectiveSpeIVsResolver(IVs ivs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
            String.valueOf(ivs.getEffectiveBattleIV(Stats.SPEED))
        );
    }

    public static TagResolver.Single getCustomEffectiveSpeIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        if (hyperTrainedStats.contains(Stats.SPEED))
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
                .resolvers(getCustomEffectiveSpeIVsResolvers(ivs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEffectiveSpeIVsResolvers(IVs ivs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEffectiveSpeIVsResolver(ivs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomSpeIVsResolver(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        String newTemplate = resolveCustomColors(resolveSpeColor(ChatMiniMessage.getCustomSpeIVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpeIVsResolvers(ivs, hyperTrainedStats))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeIVsResolvers(IVs ivs, Set<Stats> hyperTrainedStats)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getRealSpeIVsResolver(ivs));
        resolvers.add(getCustomEffectiveSpeIVsResolver(ivs, hyperTrainedStats));
        resolvers.addAll(Arrays.asList(getCustomEffectiveSpeIVsResolvers(ivs)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getEVTotalResolver(int evTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EV_TOTAL_TEMPLATE_TAG_STRING,
            String.valueOf(evTotal)
        );
    }

    private static TagResolver.Single getEVPercentageResolver(int evTotal)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.EV_PERCENTAGE_TEMPLATE_TAG_STRING,
            "%.2f".formatted((evTotal / 510.0) * 100.0)
        );
    }

    public static TagResolver.Single getCustomEVPercentageResolver(int evTotal)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomEVPercentageTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomEVPercentageResolvers(evTotal))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomEVPercentageResolvers(int evTotal)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getEVTotalResolver(evTotal));
        resolvers.add(getEVPercentageResolver(evTotal));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    public static TagResolver.Single getCustomGeneralEVsResolver(int evTotal)
    {
        String newTemplate = resolveCustomColors(ChatMiniMessage.getCustomGeneralEVsTemplate());

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomGeneralEVsResolvers(evTotal))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomGeneralEVsResolvers(int evTotal)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getCustomEVPercentageResolver(evTotal));
        resolvers.addAll(Arrays.asList(getCustomEVPercentageResolvers(evTotal)));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getHpEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.HP_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.HP))
        );
    }

    public static TagResolver.Single getCustomHpEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveHpColor(ChatMiniMessage.getCustomHpEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomHpEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_HP_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomHpEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getHpEVResolver(evs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getAtkEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.ATK_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.ATTACK))
        );
    }

    public static TagResolver.Single getCustomAtkEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveAtkColor(ChatMiniMessage.getCustomAtkEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomAtkEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomAtkEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getAtkEVResolver(evs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getDefEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.DEF_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.DEFENCE))
        );
    }

    public static TagResolver.Single getCustomDefEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveDefColor(ChatMiniMessage.getCustomDefEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomDefEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomDefEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getDefEVResolver(evs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpaEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPA_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.SPECIAL_ATTACK))
        );
    }

    public static TagResolver.Single getCustomSpaEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveSpaColor(ChatMiniMessage.getCustomSpaEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpaEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpaEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpaEVResolver(evs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpdEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPD_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.SPECIAL_DEFENCE))
        );
    }

    public static TagResolver.Single getCustomSpdEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveSpdColor(ChatMiniMessage.getCustomSpdEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpdEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpdEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpdEVResolver(evs));

        return resolvers.toArray(new TagResolver.Single[0]);
    }

    private static TagResolver.Single getSpeEVResolver(EVs evs)
    {
        return Placeholder.unparsed
        (
            ChatMiniMessage.SPE_EV_TEMPLATE_TAG_STRING,
            String.valueOf(evs.get(Stats.SPEED))
        );
    }

    public static TagResolver.Single getCustomSpeEVsResolver(EVs evs)
    {
        String newTemplate = resolveCustomColors(resolveSpeColor(ChatMiniMessage.getCustomSpeEVsTemplate()));

        Component component = MiniMessageUtils.MINIMESSAGE_INSTANCE.deserialize
        (
            newTemplate,
            TagResolver.builder()
                .resolvers(getCustomSpeEVsResolvers(evs))
                .build()
        );

        return Placeholder.component
        (
            ChatMiniMessage.CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING,
            component
        );
    }

    public static TagResolver.Single[] getCustomSpeEVsResolvers(EVs evs)
    {
        List<TagResolver.Single> resolvers = new ArrayList<>();

        resolvers.add(getSpeEVResolver(evs));

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
            String.format("#%06x", moves.size() > 0 ? moves.get(0).getType().getPrimaryColor() : "#ffffff")
        );
    }

    private static String resolveMove2Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE2_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", moves.size() > 1 ? moves.get(1).getType().getPrimaryColor() : "#ffffff")
        );
    }

    private static String resolveMove3Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE3_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", moves.size() > 2 ? moves.get(2).getType().getPrimaryColor() : "#ffffff")
        );
    }

    private static String resolveMove4Color(String template, List<Move> moves)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE4_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", moves.size() > 3 ? moves.get(3).getType().getPrimaryColor() : "#ffffff")
        );
    }

    private static String resolveCustomMoveColor(String template, Move move)
    {
        return template.replace
        (
            "<%s>".formatted(ChatMiniMessage.MOVE4_COLOR_TEMPLATE_TAG_STRING),
            String.format("#%06x", move.getType().getPrimaryColor())
        );
    }
}
