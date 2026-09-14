package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.util.HashMap;

import com.mojang.serialization.DataResult;
import com.sergioaguiar.mirageessentials.util.ModLogger;

import net.minecraft.text.TextColor;

public class ChatMiniMessage
{
    // Colors Sections
    protected static final String TOML_CUSTOM_COLOR_SECTION_STRING = "Colors";
    protected static final String TOML_STAT_COLOR_SECTION_STRING = "StatColors";

    // PokeInfo Sections
    protected static final String TOML_POKE_INFO_SECTION_STRING = "PokeInfo";
    protected static final String TOML_STATUSES_SECTION_STRING = "Statuses";
    protected static final String TOML_EGG_GROUPS_SECTION_STRING = "EggGroups";
    protected static final String TOML_BOOLEANS_SECTION_STRING = "Booleans";

    // Colors Template Names
    protected static final String HP_COLOR_TEMPLATE_STRING = "HpColor";
    protected static final String ATK_COLOR_TEMPLATE_STRING = "AtkColor";
    protected static final String DEF_COLOR_TEMPLATE_STRING = "DefColor";
    protected static final String SPA_COLOR_TEMPLATE_STRING = "SpaColor";
    protected static final String SPD_COLOR_TEMPLATE_STRING = "SpdColor";
    protected static final String SPE_COLOR_TEMPLATE_STRING = "SpeColor";

    // PokeInfo Template Names
    protected static final String POKEMON_INFO_TEMPLATE_STRING = "PokemonInfo";
    protected static final String CUSTOM_STATUS_TEMPLATE_STRING = "CustomStatus";
    protected static final String CUSTOM_CONDITION_TEMPLATE_STRING = "CustomCondition";
    protected static final String CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING = "CustomTypesMonotype";
    protected static final String CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING = "CustomTypesDuotype";
    protected static final String CUSTOM_SHININESS_TEMPLATE_STRING = "CustomShininess";
    protected static final String CUSTOM_ALPHANESS_TEMPLATE_STRING = "CustomAlphaness";
    protected static final String CUSTOM_FORM_TEMPLATE_STRING = "CustomForm";
    protected static final String CUSTOM_SPECIES_TEMPLATE_STRING = "CustomSpecies";
    protected static final String CUSTOM_HELD_ITEM_TEMPLATE_STRING = "CustomHeldItem";
    protected static final String CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING = "CustomCosmeticItem";
    protected static final String CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING = "CustomHiddenAbility";
    protected static final String CUSTOM_NATURE_STATS_TEMPLATE_STRING = "CustomNatureStats";
    protected static final String CUSTOM_MINTNESS_TEMPLATE_STRING = "CustomMintness";
    protected static final String CUSTOM_EXPERIENCE_TEMPLATE_STRING = "CustomExperience";
    protected static final String MOVE_CUSTOM_PP_TEMPLATE_STRING = "MoveCustomPP";
    protected static final String CUSTOM_MOVE_TEMPLATE_STRING = "CustomMove";
    protected static final String CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING = "CustomRealIVPercentage";
    protected static final String CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING = "CustomEffectiveIVPercentage";
    protected static final String CUSTOM_GENERAL_IVS_TEMPLATE_STRING = "CustomGeneralIVs";
    protected static final String CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING = "CustomEffectiveHpIVs";
    protected static final String CUSTOM_HP_IVS_TEMPLATE_STRING = "CustomHpIVs";
    protected static final String CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING = "CustomEffectiveAtkIVs";
    protected static final String CUSTOM_ATK_IVS_TEMPLATE_STRING = "CustomAtkIVs";
    protected static final String CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING = "CustomEffectivedefIVs";
    protected static final String CUSTOM_DEF_IVS_TEMPLATE_STRING = "CustomdefIVs";
    protected static final String CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING = "CustomEffectivespaIVs";
    protected static final String CUSTOM_SPA_IVS_TEMPLATE_STRING = "CustomspaIVs";
    protected static final String CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING = "CustomEffectivespdIVs";
    protected static final String CUSTOM_SPD_IVS_TEMPLATE_STRING = "CustomspdIVs";
    protected static final String CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING = "CustomEffectivespeIVs";
    protected static final String CUSTOM_SPE_IVS_TEMPLATE_STRING = "CustomspeIVs";
    protected static final String CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING = "CustomEVPercentage";
    protected static final String CUSTOM_GENERAL_EVS_TEMPLATE_STRING = "CustomGeneralEVs";
    protected static final String CUSTOM_HP_EVS_TEMPLATE_STRING = "CustomHpEVs";
    protected static final String CUSTOM_ATK_EVS_TEMPLATE_STRING = "CustomAtkEVs";
    protected static final String CUSTOM_DEF_EVS_TEMPLATE_STRING = "CustomDefEVs";
    protected static final String CUSTOM_SPA_EVS_TEMPLATE_STRING = "CustomSpaEVs";
    protected static final String CUSTOM_SPD_EVS_TEMPLATE_STRING = "CustomSpdEVs";
    protected static final String CUSTOM_SPE_EVS_TEMPLATE_STRING = "CustomSpeEVs";
    protected static final String CUSTOM_NEUTERED_TEMPLATE_STRING = "CustomNeutered";

    protected static final String BURN_TEMPLATE_STRING = "Burn";
    protected static final String SLEEP_TEMPLATE_STRING = "Sleep";
    protected static final String PARALYSIS_TEMPLATE_STRING = "Paralysis";
    protected static final String POISON_TEMPLATE_STRING = "Poison";
    protected static final String FAINT_TEMPLATE_STRING = "Faint";

    protected static final String AMORPHOUS_TEMPLATE_STRING = "Amorphous";
    protected static final String BUG_TEMPLATE_STRING = "Bug";
    protected static final String DITTO_TEMPLATE_STRING = "Ditto";
    protected static final String DRAGON_TEMPLATE_STRING = "Dragon";
    protected static final String FAIRY_TEMPLATE_STRING = "Fairy";
    protected static final String FIELD_TEMPLATE_STRING = "Field";
    protected static final String FLYING_TEMPLATE_STRING = "Flying";
    protected static final String GRASS_TEMPLATE_STRING = "Grass";
    protected static final String HUMAN_LIKE_TEMPLATE_STRING = "Human-Like";
    protected static final String MINERAL_TEMPLATE_STRING = "Mineral";
    protected static final String MONSTER_TEMPLATE_STRING = "Monster";
    protected static final String UNDISCOVERED_TEMPLATE_STRING = "Undiscovered";
    protected static final String WATER1_TEMPLATE_STRING = "Water1";
    protected static final String WATER2_TEMPLATE_STRING = "Water2";
    protected static final String WATER3_TEMPLATE_STRING = "Water3";

    protected static final String TRUE_TEMPLATE_STRING = "True";
    protected static final String FALSE_TEMPLATE_STRING = "False";

    // Color Defaults
    protected static final String DEFAULT_HP_COLOR_STRING = "#4f9c45";
    protected static final String DEFAULT_ATK_COLOR_STRING = "#b33f3f";
    protected static final String DEFAULT_DEF_COLOR_STRING = "#d1842c";
    protected static final String DEFAULT_SPA_COLOR_STRING = "#d140ca";
    protected static final String DEFAULT_SPD_COLOR_STRING = "#ddda36";
    protected static final String DEFAULT_SPE_COLOR_STRING = "#3bd8dd";

    private static final DataResult<TextColor> DEFAULT_HP_COLOR = TextColor.parse(DEFAULT_HP_COLOR_STRING);
    private static final DataResult<TextColor> DEFAULT_ATK_COLOR = TextColor.parse(DEFAULT_ATK_COLOR_STRING);
    private static final DataResult<TextColor> DEFAULT_DEF_COLOR = TextColor.parse(DEFAULT_DEF_COLOR_STRING);
    private static final DataResult<TextColor> DEFAULT_SPA_COLOR = TextColor.parse(DEFAULT_SPA_COLOR_STRING);
    private static final DataResult<TextColor> DEFAULT_SPD_COLOR = TextColor.parse(DEFAULT_SPD_COLOR_STRING);
    private static final DataResult<TextColor> DEFAULT_SPE_COLOR = TextColor.parse(DEFAULT_SPE_COLOR_STRING);

    // PokeInfo Defaults
    protected static final String DEFAULT_POKEMON_INFO =
        """
        <gradient:{Type1Color}:{Type2Color}>{Name}, {Title}</gradient>
        {CustomTypes} <color:{DivisionColor}>│</Color> {Gender} <color:{DivisionColor}>│</color> {CaughtBall}
        <color:{DivisionColor}>────────────────────────────
        <color:{LabelColor}>Condition:</color> {CustomCondition}
        <color:{LabelColor}>Species:</color> {CustomSpecies}
        <color:{LabelColor}>Held Item:</color> <white>{HeldItem}</white> {CustomCosmeticItem}
        <color:{LabelColor}>Ability:</color> <white>{Ability}</white> {CustomHiddenAbility}
        <color:{LabelColor}>Nature</color>{CustomMintness}<color:{LabelColor}>:</color> <white>{Nature}</white> {CustomNatureStats}
        <color:{LabelColor}>Level:</color> <white>{Level}</white> {CustomExperience}
        <color:{LabelColor}>Friendship:</color> <white>{Friendship}</white>
        <dark_gray>────────────────────────────
        {CustomMoves}
        <dark_gray>────────────────────────────
        <color:{LabelColor}>IVs:</color> {CustomGeneralIVs}
            {CustomHpIVs} {CustomAtkIVs} {CustomDefIVs}
            {CustomSpaIVs} {CustomSpdIVs} {CustomSpeIVs}
        <dark_gray>────────────────────────────
        <color:{LabelColor}>EVs:</color> {CustomGeneralEVs}
            {CustomHpEV} {CustomAtkEV} {CustomDefEV}
            {CustomSpaEV} {CustomSpdEV} {CustomSpeEV}
        <dark_gray>────────────────────────────
        <color:{LabelColor}>Size:</color> <white>{Size}</white> <color:{LabelColor}>(</color><white>{ScaleModifier100}%% scale</white><color:{LabelColor}>)</color>
        <color:{LabelColor}>Egg Groups:</color> <white>{EggGroups}</white>
        {CustomNeutered}
        <color:{LabelColor}>OT:</color> <white>{OriginalTrainerName}</white>
        """;

    protected static final String DEFAULT_CUSTOM_STATUS =
        """
        <color:{LabelColor}>(</color>{Status}<color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_CONDITION =
        """
        <green>{CurrentHealth}</green><color:{LabelColor}>/</color><green>{MaxHealth}</green> {CustomStatus}
        """;

    protected static final String DEFAULT_CUSTOM_TYPES_MONOTYPE =
        """
        <color:{Type1Color}>{Type1}</color> <color:{TeraTypeColor}>{TeraType}</color>
        """;

    protected static final String DEFAULT_CUSTOM_TYPES_DUOTYPE =
        """
        <color:{Type1Color}>{Type1}</color><color:{LabelColor}>/</color><color:{Type2Color}>{Type2}</color> <color:{TeraTypeColor}>{TeraType}</color>
        """;

    protected static final String DEFAULT_CUSTOM_SHININESS =
        """
        <color:{ShinyColor}> ★ </color>
        """;

    protected static final String DEFAULT_CUSTOM_ALPHANESS =
        """
        <color:{AlphaColor}> α </color>
        """;

    protected static final String DEFAULT_CUSTOM_FORM =
        """
        <color:{LabelColor}>(</color><white>{Form}</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPECIES =
        """
        {CustomShininess}{CustomAlphaness} <white>{Species}</white> {CustomForms}
        """;

    protected static final String DEFAULT_CUSTOM_HELD_ITEM =
        """
        <color:{LabelColor}>(</color><white>{HeldItem}</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_COSMETIC_ITEM =
        """
        <color:{LabelColor}>(</color><white>{CosmeticItem}</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_HIDDEN_ABILITY =
        """
        <color:{LabelColor}>(</color><color:{HaColor}>HA</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_NATURE_STATS =
        """
        <color:{LabelColor}>(</color><color:{NatureStatUpColor}>↑{NatureStatUp}</color><color:{LabelColor}>/</color><color:{NatureStatDownColor}>↓{NatureStatDown}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_MINTNESS =
        """
        <color:{LabelColor}> (Minted)</color>
        """;

    protected static final String DEFAULT_CUSTOM_EXPERIENCE =
        """
        <color:{LabelColor}>(</color><white>{CurrentExperience}</white><color:{LabelColor}>/</color><white>{RequiredExperience}</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_MOVE_CUSTOM_PP =
        """
        <color:{LabelColor}>(</color><color:{MoveRemainingPPColor}>{MoveRemainingPP}</color><color:{LabelColor}>/</color><color:{MoveRemainingPPColor}>{MoveTotalPP}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_MOVE =
        """
        <color:{LabelColor}> ▶ </color><color:{MoveColor}>{Move}</color> {MoveCustomPP}
        """;

    protected static final String DEFAULT_CUSTOM_REAL_IV_PERCENTAGE =
        """
        <color:{LabelColor}>(</color><white>{RealIVPercentage}%%</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_IV_PERCENTAGE =
        """
        <color:{LabelColor}>(</color><white>{EffectiveIVPercentage}%%</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_GENERAL_IVS =
        """
        <white>{EffectiveIVTotal}</white><color:{LabelColor}>/</color><white>186</white> {CustomEffectiveIVPercentage}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_HP_IVS =
        """
        <color:{LabelColor}>(</color><color:{HpColor}>{EffectiveHpIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_HP_IVS =
        """
        <color:{HpColor}>Hp {RealHpIVs}</color> {CustomEffectiveHpIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_ATK_IVS =
        """
        <color:{LabelColor}>(</color><color:{AtkColor}>{EffectiveAtkIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_ATK_IVS =
        """
        <color:{AtkColor}>Atk {RealAtkIVs}</color> {CustomEffectiveAtkIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_DEF_IVS =
        """
        <color:{LabelColor}>(</color><color:{DefColor}>{EffectiveDefIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_DEF_IVS =
        """
        <color:{DefColor}>Def {RealDefIVs}</color> {CustomEffectiveDefIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPA_IVS =
        """
        <color:{LabelColor}>(</color><color:{SpaColor}>{EffectiveSpaIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPA_IVS =
        """
        <color:{SpaColor}>Spa {RealSpaIVs}</color> {CustomEffectiveSpaIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPD_IVS =
        """
        <color:{LabelColor}>(</color><color:{SpdColor}>{EffectiveSpdIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPD_IVS =
        """
        <color:{SpdColor}>Spd {RealSpdIVs}</color> {CustomEffectiveSpdIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPE_IVS =
        """
        <color:{LabelColor}>(</color><color:{SpeColor}>{EffectiveSpeIVs}</color><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPE_IVS =
        """
        <color:{SpeColor}>Spe {RealSpeIVs}</color> {CustomEffectiveSpeIVs}
        """;

    protected static final String DEFAULT_CUSTOM_EV_PERCENTAGE =
        """
        <color:{LabelColor}>(</color><white>{EVPercentage}%%</white><color:{LabelColor}>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_GENERAL_EVS =
        """
        <white>{EVTotal}</white><color:{LabelColor}>/</color><white>510</white> {CustomEVPercentage}
        """;

    protected static final String DEFAULT_CUSTOM_HP_EVS =
        """
        <color:{HpColor}>Hp {HpEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_ATK_EVS =
        """
        <color:{AtkColor}>Atk {AtkEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_DEF_EVS =
        """
        <color:{DefColor}>Def {DefEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPA_EVS =
        """
        <color:{SpaColor}>Spa {SpaEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPD_EVS =
        """
        <color:{SpdColor}>Spd {SpdEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPE_EVS =
        """
        <color:{SpeColor}>Spe {SpeEVs}</color>
        """;

    protected static final String DEFAULT_CUSTOM_NEUTERED =
        """
        <color:{LabelColor}>Neutered:</color> {Neutered}
        """;

    protected static final String DEFAULT_BURN = "<color:{BurnColor}>[BRN]</color>";
    protected static final String DEFAULT_SLEEP = "<color:{SleepColor}>[SLP]</color>";
    protected static final String DEFAULT_PARALYSIS = "<color:{ParalysisColor}>[PAR]</color>";
    protected static final String DEFAULT_POISON = "<color:{PoisonColor}>[PSN]</color>";
    protected static final String DEFAULT_FAINT = "<color:{FaintColor}>[FNT]</color>";

    protected static final String DEFAULT_AMORPHOUS = "<white>Amorphous</white>";
    protected static final String DEFAULT_BUG = "<white>Bug</white>";
    protected static final String DEFAULT_DITTO = "<white>Ditto</white>";
    protected static final String DEFAULT_DRAGON = "<white>Dragon</white>";
    protected static final String DEFAULT_FAIRY = "<white>Fairy</white>";
    protected static final String DEFAULT_FIELD = "<white>Field</white>";
    protected static final String DEFAULT_FLYING = "<white>Flying</white>";
    protected static final String DEFAULT_GRASS = "<white>Grass</white>";
    protected static final String DEFAULT_HUMAN_LIKE = "<white>Human-Like</white>";
    protected static final String DEFAULT_MINERAL = "<white>Mineral</white>";
    protected static final String DEFAULT_MONSTER = "<white>Monster</white>";
    protected static final String DEFAULT_UNDISCOVERED = "<white>Undiscovered</white>";
    protected static final String DEFAULT_WATER1 = "<white>Water1</white>";
    protected static final String DEFAULT_WATER2 = "<white>Water2</white>";
    protected static final String DEFAULT_WATER3 = "<white>Water3</white>";

    protected static final String DEFAULT_TRUE = "<color:{TrueColor}>Yes</color>";
    protected static final String DEFAULT_FALSE = "<color:{FalseColor}>No</color>";

    // State Data
    private static HashMap<String, TextColor> CUSTOM_COLORS;
    private static HashMap<String, TextColor> STAT_COLORS;
    private static HashMap<String, String> POKE_INFO_TEMPLATES;
    private static HashMap<String, String> STATUS_TEMPLATES;
    private static HashMap<String, String> EGG_GROUP_TEMPLATES;
    private static HashMap<String, String> BOOLEAN_TEMPLATES;

    public static void setDefaults()
    {
        try 
        {
            CUSTOM_COLORS = new HashMap<>();
            STAT_COLORS = new HashMap<>();
            POKE_INFO_TEMPLATES = new HashMap<>();
            STATUS_TEMPLATES = new HashMap<>();
            EGG_GROUP_TEMPLATES = new HashMap<>();
            BOOLEAN_TEMPLATES = new HashMap<>();

            STAT_COLORS.put(HP_COLOR_TEMPLATE_STRING, DEFAULT_HP_COLOR.getOrThrow());
            STAT_COLORS.put(ATK_COLOR_TEMPLATE_STRING, DEFAULT_ATK_COLOR.getOrThrow());
            STAT_COLORS.put(DEF_COLOR_TEMPLATE_STRING, DEFAULT_DEF_COLOR.getOrThrow());
            STAT_COLORS.put(SPA_COLOR_TEMPLATE_STRING, DEFAULT_SPA_COLOR.getOrThrow());
            STAT_COLORS.put(SPD_COLOR_TEMPLATE_STRING, DEFAULT_SPD_COLOR.getOrThrow());
            STAT_COLORS.put(SPE_COLOR_TEMPLATE_STRING, DEFAULT_SPE_COLOR.getOrThrow());

            POKE_INFO_TEMPLATES.put(POKEMON_INFO_TEMPLATE_STRING, DEFAULT_POKEMON_INFO);
            POKE_INFO_TEMPLATES.put(CUSTOM_STATUS_TEMPLATE_STRING, DEFAULT_CUSTOM_STATUS);
            POKE_INFO_TEMPLATES.put(CUSTOM_CONDITION_TEMPLATE_STRING, DEFAULT_CUSTOM_CONDITION);
            POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING, DEFAULT_CUSTOM_TYPES_MONOTYPE);
            POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING, DEFAULT_CUSTOM_TYPES_DUOTYPE);
            POKE_INFO_TEMPLATES.put(CUSTOM_SHININESS_TEMPLATE_STRING, DEFAULT_CUSTOM_SHININESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ALPHANESS_TEMPLATE_STRING, DEFAULT_CUSTOM_ALPHANESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_FORM_TEMPLATE_STRING, DEFAULT_CUSTOM_FORM);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPECIES_TEMPLATE_STRING, DEFAULT_CUSTOM_SPECIES);
            POKE_INFO_TEMPLATES.put(CUSTOM_HELD_ITEM_TEMPLATE_STRING, DEFAULT_CUSTOM_HELD_ITEM);
            POKE_INFO_TEMPLATES.put(CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING, DEFAULT_CUSTOM_COSMETIC_ITEM);
            POKE_INFO_TEMPLATES.put(CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING, DEFAULT_CUSTOM_HIDDEN_ABILITY);
            POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_STATS_TEMPLATE_STRING, DEFAULT_CUSTOM_NATURE_STATS);
            POKE_INFO_TEMPLATES.put(CUSTOM_MINTNESS_TEMPLATE_STRING, DEFAULT_CUSTOM_MINTNESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EXPERIENCE_TEMPLATE_STRING, DEFAULT_CUSTOM_EXPERIENCE);
            POKE_INFO_TEMPLATES.put(MOVE_CUSTOM_PP_TEMPLATE_STRING, DEFAULT_MOVE_CUSTOM_PP);
            POKE_INFO_TEMPLATES.put(CUSTOM_MOVE_TEMPLATE_STRING, DEFAULT_CUSTOM_MOVE);
            POKE_INFO_TEMPLATES.put(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING, DEFAULT_CUSTOM_REAL_IV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_IV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_GENERAL_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_HP_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_HP_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_HP_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_ATK_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ATK_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_ATK_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_DEF_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_DEF_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_DEF_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPA_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPA_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPA_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPD_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPD_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPD_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPE_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPE_IVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPE_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING, DEFAULT_CUSTOM_EV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_GENERAL_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_HP_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_HP_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ATK_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_ATK_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_DEF_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_DEF_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPA_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPA_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPD_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPD_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPE_EVS_TEMPLATE_STRING, DEFAULT_CUSTOM_SPE_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_NEUTERED_TEMPLATE_STRING, DEFAULT_CUSTOM_NEUTERED);

            STATUS_TEMPLATES.put(BURN_TEMPLATE_STRING, DEFAULT_BURN);
            STATUS_TEMPLATES.put(SLEEP_TEMPLATE_STRING, DEFAULT_SLEEP);
            STATUS_TEMPLATES.put(PARALYSIS_TEMPLATE_STRING, DEFAULT_PARALYSIS);
            STATUS_TEMPLATES.put(POISON_TEMPLATE_STRING, DEFAULT_POISON);
            STATUS_TEMPLATES.put(FAINT_TEMPLATE_STRING, DEFAULT_FAINT);

            EGG_GROUP_TEMPLATES.put(AMORPHOUS_TEMPLATE_STRING, DEFAULT_AMORPHOUS);
            EGG_GROUP_TEMPLATES.put(BUG_TEMPLATE_STRING, DEFAULT_BUG);
            EGG_GROUP_TEMPLATES.put(DITTO_TEMPLATE_STRING, DEFAULT_DITTO);
            EGG_GROUP_TEMPLATES.put(DRAGON_TEMPLATE_STRING, DEFAULT_DRAGON);
            EGG_GROUP_TEMPLATES.put(FAIRY_TEMPLATE_STRING, DEFAULT_FAIRY);
            EGG_GROUP_TEMPLATES.put(FIELD_TEMPLATE_STRING, DEFAULT_FIELD);
            EGG_GROUP_TEMPLATES.put(FLYING_TEMPLATE_STRING, DEFAULT_FLYING);
            EGG_GROUP_TEMPLATES.put(GRASS_TEMPLATE_STRING, DEFAULT_GRASS);
            EGG_GROUP_TEMPLATES.put(HUMAN_LIKE_TEMPLATE_STRING, DEFAULT_HUMAN_LIKE);
            EGG_GROUP_TEMPLATES.put(MINERAL_TEMPLATE_STRING, DEFAULT_MINERAL);
            EGG_GROUP_TEMPLATES.put(MONSTER_TEMPLATE_STRING, DEFAULT_MONSTER);
            EGG_GROUP_TEMPLATES.put(UNDISCOVERED_TEMPLATE_STRING, DEFAULT_UNDISCOVERED);
            EGG_GROUP_TEMPLATES.put(WATER1_TEMPLATE_STRING, DEFAULT_WATER1);
            EGG_GROUP_TEMPLATES.put(WATER2_TEMPLATE_STRING, DEFAULT_WATER2);
            EGG_GROUP_TEMPLATES.put(WATER3_TEMPLATE_STRING, DEFAULT_WATER3);

            BOOLEAN_TEMPLATES.put(TRUE_TEMPLATE_STRING, DEFAULT_TRUE);
            BOOLEAN_TEMPLATES.put(FALSE_TEMPLATE_STRING, DEFAULT_FALSE);
        }
        catch (IllegalStateException e) 
        {
            ModLogger.error("Failed to load default chat MiniMessage colors: %s".formatted(e.getMessage()), e);
        }
    }

    public static void clearCustomColors()
    {
        CUSTOM_COLORS.clear();
    }

    public static TextColor getCustomColor(String colorName) { return CUSTOM_COLORS.get(colorName); }

    protected static void setCustomColor(String colorName, String color)
    { 
        setCustomColor(colorName, TextColor.parse(color));
    }

    protected static void setCustomColor(String colorName, DataResult<TextColor> color)
    {
        setCustomColor(colorName, color.getOrThrow());
    }

    protected static void setCustomColor(String colorName, TextColor color)
    {
        CUSTOM_COLORS.put(colorName, color);
    }

    // Colors Setters and Getters
    public static TextColor getHpColor() { return STAT_COLORS.get(HP_COLOR_TEMPLATE_STRING); }
    public static TextColor getAtkColor() { return STAT_COLORS.get(ATK_COLOR_TEMPLATE_STRING); }
    public static TextColor getDefColor() { return STAT_COLORS.get(DEF_COLOR_TEMPLATE_STRING); }
    public static TextColor getSpaColor() { return STAT_COLORS.get(SPA_COLOR_TEMPLATE_STRING); }
    public static TextColor getSpdColor() { return STAT_COLORS.get(SPD_COLOR_TEMPLATE_STRING); }
    public static TextColor getSpeColor() { return STAT_COLORS.get(SPE_COLOR_TEMPLATE_STRING); }

    protected static void setHpColor(TextColor color) { STAT_COLORS.put(HP_COLOR_TEMPLATE_STRING, color); }
    protected static void setAtkColor(TextColor color) { STAT_COLORS.put(ATK_COLOR_TEMPLATE_STRING, color); }
    protected static void setDefColor(TextColor color) { STAT_COLORS.put(DEF_COLOR_TEMPLATE_STRING, color); }
    protected static void setSpaColor(TextColor color) { STAT_COLORS.put(SPA_COLOR_TEMPLATE_STRING, color); }
    protected static void setSpdColor(TextColor color) { STAT_COLORS.put(SPD_COLOR_TEMPLATE_STRING, color); }
    protected static void setSpeColor(TextColor color) { STAT_COLORS.put(SPE_COLOR_TEMPLATE_STRING, color); }

    // PokeInfo Setters and Getters
    public static String getPokemonInfoTemplate() { return POKE_INFO_TEMPLATES.get(POKEMON_INFO_TEMPLATE_STRING); }
    public static String getCustomStatusTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_STATUS_TEMPLATE_STRING); }
    public static String getCustomConditionTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_CONDITION_TEMPLATE_STRING); }
    public static String getCustomTypesMonotypeTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING); }
    public static String getCustomTypesDuotypeTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING); }
    public static String getCustomShininessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SHININESS_TEMPLATE_STRING); }
    public static String getCustomAlphanessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ALPHANESS_TEMPLATE_STRING); }
    public static String getCustomFormTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_FORM_TEMPLATE_STRING); }
    public static String getCustomSpeciesTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPECIES_TEMPLATE_STRING); }
    public static String getCustomHeldItemTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HELD_ITEM_TEMPLATE_STRING); }
    public static String getCustomCosmeticItemTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING); }
    public static String getCustomHiddenAbilityTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING); }
    public static String getCustomNatureStatsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_NATURE_STATS_TEMPLATE_STRING); }
    public static String getCustomMintnessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_MINTNESS_TEMPLATE_STRING); }
    public static String getCustomExperienceTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EXPERIENCE_TEMPLATE_STRING); }
    public static String getMoveCustomPPTemplate() { return POKE_INFO_TEMPLATES.get(MOVE_CUSTOM_PP_TEMPLATE_STRING); }
    public static String getCustomMoveTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_MOVE_TEMPLATE_STRING); }
    public static String getCustomRealIVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING); }
    public static String getCustomEffectiveIVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING); }
    public static String getCustomGeneralIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_GENERAL_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveHpIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING); }
    public static String getCustomHpIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HP_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveAtkIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING); }
    public static String getCustomAtkIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ATK_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveDefIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING); }
    public static String getCustomDefIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_DEF_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveSpaIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING); }
    public static String getCustomSpaIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPA_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveSpdIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING); }
    public static String getCustomSpdIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPD_IVS_TEMPLATE_STRING); }
    public static String getCustomEffectiveSpeIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING); }
    public static String getCustomSpeIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPE_IVS_TEMPLATE_STRING); }
    public static String getCustomEVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING); }
    public static String getCustomGeneralEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_GENERAL_EVS_TEMPLATE_STRING); }
    public static String getCustomHpEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HP_EVS_TEMPLATE_STRING); }
    public static String getCustomAtkEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ATK_EVS_TEMPLATE_STRING); }
    public static String getCustomDefEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_DEF_EVS_TEMPLATE_STRING); }
    public static String getCustomSpaEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPA_EVS_TEMPLATE_STRING); }
    public static String getCustomSpdEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPD_EVS_TEMPLATE_STRING); }
    public static String getCustomSpeEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPE_EVS_TEMPLATE_STRING); }
    public static String getCustomNeuteredTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_NEUTERED_TEMPLATE_STRING); }

    protected static void setPokemonInfoTemplate(String template) { POKE_INFO_TEMPLATES.put(POKEMON_INFO_TEMPLATE_STRING, template); }
    protected static void setCustomStatusTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_STATUS_TEMPLATE_STRING, template); }
    protected static void setCustomConditionTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_CONDITION_TEMPLATE_STRING, template); }
    protected static void setCustomTypesMonotypeTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING, template); }
    protected static void setCustomTypesDuotypeTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING, template); }
    protected static void setCustomShininessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SHININESS_TEMPLATE_STRING, template); }
    protected static void setCustomAlphanessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ALPHANESS_TEMPLATE_STRING, template); }
    protected static void setCustomFormTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_FORM_TEMPLATE_STRING, template); }
    protected static void setCustomSpeciesTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPECIES_TEMPLATE_STRING, template); }
    protected static void setCustomHeldItemTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HELD_ITEM_TEMPLATE_STRING, template); }
    protected static void setCustomCosmeticItemTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING, template); }
    protected static void setCustomHiddenAbilityTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING, template); }
    protected static void setCustomNatureStatsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_STATS_TEMPLATE_STRING, template); }
    protected static void setCustomMintnessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_MINTNESS_TEMPLATE_STRING, template); }
    protected static void setCustomExperienceTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EXPERIENCE_TEMPLATE_STRING, template); }
    protected static void setMoveCustomPPTemplate(String template) { POKE_INFO_TEMPLATES.put(MOVE_CUSTOM_PP_TEMPLATE_STRING, template); }
    protected static void setCustomMoveTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_MOVE_TEMPLATE_STRING, template); }
    protected static void setCustomRealIVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveIVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING, template); }
    protected static void setCustomGeneralIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveHpIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomHpIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HP_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveAtkIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomAtkIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ATK_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveDefIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomDefIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_DEF_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveSpaIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpaIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPA_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveSpdIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpdIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPD_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEffectiveSpeIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpeIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPE_IVS_TEMPLATE_STRING, template); }
    protected static void setCustomEVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING, template); }
    protected static void setCustomGeneralEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomHpEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HP_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomAtkEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ATK_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomDefEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_DEF_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpaEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPA_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpdEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPD_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomSpeEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPE_EVS_TEMPLATE_STRING, template); }
    protected static void setCustomNeuteredTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_NEUTERED_TEMPLATE_STRING, template); }

    public static String getBurnTemplate() { return STATUS_TEMPLATES.get(BURN_TEMPLATE_STRING); }
    public static String getSleepTemplate() { return STATUS_TEMPLATES.get(SLEEP_TEMPLATE_STRING); }
    public static String getParalysisTemplate() { return STATUS_TEMPLATES.get(PARALYSIS_TEMPLATE_STRING); }
    public static String getPoisonTemplate() { return STATUS_TEMPLATES.get(POISON_TEMPLATE_STRING); }
    public static String getFaintTemplate() { return STATUS_TEMPLATES.get(FAINT_TEMPLATE_STRING); }

    protected static void setBurnTemplate(String template) { STATUS_TEMPLATES.put(BURN_TEMPLATE_STRING, template); }
    protected static void setSleepTemplate(String template) { STATUS_TEMPLATES.put(SLEEP_TEMPLATE_STRING, template); }
    protected static void setParalysisTemplate(String template) { STATUS_TEMPLATES.put(PARALYSIS_TEMPLATE_STRING, template); }
    protected static void setPoisonTemplate(String template) { STATUS_TEMPLATES.put(POISON_TEMPLATE_STRING, template); }
    protected static void setFaintTemplate(String template) { STATUS_TEMPLATES.put(FAINT_TEMPLATE_STRING, template); }

    public static String getAmorphousTemplate() { return EGG_GROUP_TEMPLATES.get(AMORPHOUS_TEMPLATE_STRING); }
    public static String getBugTemplate() { return EGG_GROUP_TEMPLATES.get(BUG_TEMPLATE_STRING); }
    public static String getDittoTemplate() { return EGG_GROUP_TEMPLATES.get(DITTO_TEMPLATE_STRING); }
    public static String getDragonTemplate() { return EGG_GROUP_TEMPLATES.get(DRAGON_TEMPLATE_STRING); }
    public static String getFairyTemplate() { return EGG_GROUP_TEMPLATES.get(FAIRY_TEMPLATE_STRING); }
    public static String getFieldTemplate() { return EGG_GROUP_TEMPLATES.get(FIELD_TEMPLATE_STRING); }
    public static String getFlyingTemplate() { return EGG_GROUP_TEMPLATES.get(FLYING_TEMPLATE_STRING); }
    public static String getGrassTemplate() { return EGG_GROUP_TEMPLATES.get(GRASS_TEMPLATE_STRING); }
    public static String getHumanLikeTemplate() { return EGG_GROUP_TEMPLATES.get(HUMAN_LIKE_TEMPLATE_STRING); }
    public static String getMineralTemplate() { return EGG_GROUP_TEMPLATES.get(MINERAL_TEMPLATE_STRING); }
    public static String getMonsterTemplate() { return EGG_GROUP_TEMPLATES.get(MONSTER_TEMPLATE_STRING); }
    public static String getUndiscoveredTemplate() { return EGG_GROUP_TEMPLATES.get(UNDISCOVERED_TEMPLATE_STRING); }
    public static String getWater1Template() { return EGG_GROUP_TEMPLATES.get(WATER1_TEMPLATE_STRING); }
    public static String getWater2Template() { return EGG_GROUP_TEMPLATES.get(WATER2_TEMPLATE_STRING); }
    public static String getWater3Template() { return EGG_GROUP_TEMPLATES.get(WATER3_TEMPLATE_STRING); }

    protected static void setAmorphousTemplate(String template) { EGG_GROUP_TEMPLATES.put(AMORPHOUS_TEMPLATE_STRING, template); }
    protected static void setBugTemplate(String template) { EGG_GROUP_TEMPLATES.put(BUG_TEMPLATE_STRING, template); }
    protected static void setDittoTemplate(String template) { EGG_GROUP_TEMPLATES.put(DITTO_TEMPLATE_STRING, template); }
    protected static void setDragonTemplate(String template) { EGG_GROUP_TEMPLATES.put(DRAGON_TEMPLATE_STRING, template); }
    protected static void setFairyTemplate(String template) { EGG_GROUP_TEMPLATES.put(FAIRY_TEMPLATE_STRING, template); }
    protected static void setFieldTemplate(String template) { EGG_GROUP_TEMPLATES.put(FIELD_TEMPLATE_STRING, template); }
    protected static void setFlyingTemplate(String template) { EGG_GROUP_TEMPLATES.put(FLYING_TEMPLATE_STRING, template); }
    protected static void setGrassTemplate(String template) { EGG_GROUP_TEMPLATES.put(GRASS_TEMPLATE_STRING, template); }
    protected static void setHumanLikeTemplate(String template) { EGG_GROUP_TEMPLATES.put(HUMAN_LIKE_TEMPLATE_STRING, template); }
    protected static void setMineralTemplate(String template) { EGG_GROUP_TEMPLATES.put(MINERAL_TEMPLATE_STRING, template); }
    protected static void setMonsterTemplate(String template) { EGG_GROUP_TEMPLATES.put(MONSTER_TEMPLATE_STRING, template); }
    protected static void setUndiscoveredTemplate(String template) { EGG_GROUP_TEMPLATES.put(UNDISCOVERED_TEMPLATE_STRING, template); }
    protected static void setWater1Template(String template) { EGG_GROUP_TEMPLATES.put(WATER1_TEMPLATE_STRING, template); }
    protected static void setWater2Template(String template) { EGG_GROUP_TEMPLATES.put(WATER2_TEMPLATE_STRING, template); }
    protected static void setWater3Template(String template) { EGG_GROUP_TEMPLATES.put(WATER3_TEMPLATE_STRING, template); }

    public static String getTrueTemplate() { return BOOLEAN_TEMPLATES.get(TRUE_TEMPLATE_STRING); }
    public static String getFalseTemplate() { return BOOLEAN_TEMPLATES.get(FALSE_TEMPLATE_STRING); }

    protected static void setTrueTemplate(String template) { BOOLEAN_TEMPLATES.put(TRUE_TEMPLATE_STRING, template); }
    protected static void setFalseTemplate(String template) { BOOLEAN_TEMPLATES.put(FALSE_TEMPLATE_STRING, template); }
}