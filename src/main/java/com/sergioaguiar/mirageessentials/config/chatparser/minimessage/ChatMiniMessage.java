package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.util.HashMap;

import com.sergioaguiar.mirageessentials.util.ModLogger;

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
    protected static final String TOML_GENDERS_SECTION_STRING = "Genders";

    // Colors Template Names
    protected static final String DIVISION_COLOR_TEMPLATE_STRING = "DivisionColor";
    protected static final String DIVISION_COLOR_TEMPLATE_TAG_STRING = "divisioncolor";
    protected static final String LABEL_COLOR_TEMPLATE_STRING = "LabelColor";
    protected static final String LABEL_COLOR_TEMPLATE_TAG_STRING = "labelcolor";
    protected static final String SHINY_COLOR_TEMPLATE_STRING = "ShinyColor";
    protected static final String SHINY_COLOR_TEMPLATE_TAG_STRING = "shinycolor";
    protected static final String ALPHA_COLOR_TEMPLATE_STRING = "AlphaColor";
    protected static final String ALPHA_COLOR_TEMPLATE_TAG_STRING = "alphacolor";
    protected static final String HA_COLOR_TEMPLATE_STRING = "HaColor";
    protected static final String HA_COLOR_TEMPLATE_TAG_STRING = "hacolor";
    protected static final String NATURE_STAT_UP_COLOR_TEMPLATE_STRING = "NatureStatUpColor";
    protected static final String NATURE_STAT_UP_COLOR_TEMPLATE_TAG_STRING = "naturestatupcolor";
    protected static final String NATURE_STAT_DOWN_COLOR_TEMPLATE_STRING = "NatureStatDownColor";
    protected static final String NATURE_STAT_DOWN_COLOR_TEMPLATE_TAG_STRING = "naturestatdowncolor";
    protected static final String TRUE_COLOR_TEMPLATE_STRING = "TrueColor";
    protected static final String TRUE_COLOR_TEMPLATE_TAG_STRING = "truecolor";
    protected static final String FALSE_COLOR_TEMPLATE_STRING = "FalseColor";
    protected static final String FALSE_COLOR_TEMPLATE_TAG_STRING = "falsecolor";
    protected static final String BURN_COLOR_TEMPLATE_STRING = "BurnColor";
    protected static final String BURN_COLOR_TEMPLATE_TAG_STRING = "burncolor";
    protected static final String SLEEP_COLOR_TEMPLATE_STRING = "SleepColor";
    protected static final String SLEEP_COLOR_TEMPLATE_TAG_STRING = "sleepcolor";
    protected static final String PARALYSIS_COLOR_TEMPLATE_STRING = "ParalysisColor";
    protected static final String PARALYSIS_COLOR_TEMPLATE_TAG_STRING = "paralysiscolor";
    protected static final String POISON_COLOR_TEMPLATE_STRING = "PoisonColor";
    protected static final String POISON_COLOR_TEMPLATE_TAG_STRING = "poisoncolor";
    protected static final String FREEZE_COLOR_TEMPLATE_STRING = "FreezeColor";
    protected static final String FREEZE_COLOR_TEMPLATE_TAG_STRING = "freezecolor";
    protected static final String FAINT_COLOR_TEMPLATE_STRING = "FaintColor";
    protected static final String FAINT_COLOR_TEMPLATE_TAG_STRING = "faintcolor";
    protected static final String COMMAND_PREFIX_GRADIENT_LEFT_COLOR_TEMPLATE_STRING = "CommandPrefixGradientLeftColor";
    protected static final String COMMAND_PREFIX_GRADIENT_LEFT_COLOR_TEMPLATE_TAG_STRING = "commandprefixgradientleftcolor";
    protected static final String COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_TEMPLATE_STRING = "CommandPrefixGradientRightColor";
    protected static final String COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_TEMPLATE_TAG_STRING = "commandprefixgradientrightcolor";
    protected static final String MALE_COLOR_TEMPLATE_STRING = "MaleColor";
    protected static final String MALE_COLOR_TEMPLATE_TAG_STRING = "malecolor";
    protected static final String FEMALE_COLOR_TEMPLATE_STRING = "FemaleColor";
    protected static final String FEMALE_COLOR_TEMPLATE_TAG_STRING = "femalecolor";
    protected static final String GENDERLESS_COLOR_TEMPLATE_STRING = "GenderlessColor";
    protected static final String GENDERLESS_COLOR_TEMPLATE_TAG_STRING = "genderlesscolor";

    // PokeInfo Template Names
    public static final String NAME_TEMPLATE_STRING = "Name";
    public static final String NAME_TEMPLATE_TAG_STRING = "name";
    public static final String TITLE_TEMPLATE_STRING = "Title";
    public static final String TITLE_TEMPLATE_TAG_STRING = "title";
    public static final String GENDER_TEMPLATE_STRING = "Gender";
    public static final String GENDER_TEMPLATE_TAG_STRING = "gender";
    public static final String CAUGHT_BALL_TEMPLATE_STRING = "CaughtBall";
    public static final String CAUGHT_BALL_TEMPLATE_TAG_STRING = "caughtball";
    public static final String CURRENT_HEALTH_TEMPLATE_STRING = "CurrentHealth";
    public static final String CURRENT_HEALTH_TEMPLATE_TAG_STRING = "currenthealth";
    public static final String MAX_HEALTH_TEMPLATE_STRING = "MaxHealth";
    public static final String MAX_HEALTH_TEMPLATE_TAG_STRING = "maxhealth";
    public static final String STATUS_TEMPLATE_STRING = "Status";
    public static final String STATUS_TEMPLATE_TAG_STRING = "status";
    public static final String CUSTOM_STATUS_TEMPLATE_STRING = "CustomStatus";
    public static final String CUSTOM_STATUS_TEMPLATE_TAG_STRING = "customstatus";
    public static final String CUSTOM_CONDITION_TEMPLATE_STRING = "CustomCondition";
    public static final String CUSTOM_CONDITION_TEMPLATE_TAG_STRING = "customcondition";
    public static final String TYPE1_TEMPLATE_STRING = "Type1";
    public static final String TYPE1_TEMPLATE_TAG_STRING = "type1";
    public static final String TYPE1_COLOR_TEMPLATE_STRING = "Type1Color";
    public static final String TYPE1_COLOR_TEMPLATE_TAG_STRING = "type1color";
    public static final String TYPE2_TEMPLATE_STRING = "Type2";
    public static final String TYPE2_TEMPLATE_TAG_STRING = "type2";
    public static final String TYPE2_COLOR_TEMPLATE_STRING = "Type2Color";
    public static final String TYPE2_COLOR_TEMPLATE_TAG_STRING = "type2color";
    public static final String TERA_TYPE_TEMPLATE_STRING = "TeraType";
    public static final String TERA_TYPE_TEMPLATE_TAG_STRING = "teratype";
    public static final String TERA_TYPE_COLOR_TEMPLATE_STRING = "TeraTypeColor";
    public static final String TERA_TYPE_COLOR_TEMPLATE_TAG_STRING = "teratypecolor";
    public static final String CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING = "CustomTypesMonotype";
    public static final String CUSTOM_TYPES_MONOTYPE_TEMPLATE_TAG_STRING = "customtypesmonotype";
    public static final String CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING = "CustomTypesDuotype";
    public static final String CUSTOM_TYPES_DUOTYPE_TEMPLATE_TAG_STRING = "customtypesduotype";
    public static final String CUSTOM_TYPES_TEMPLATE_STRING = "CustomTypes";
    public static final String CUSTOM_TYPES_TEMPLATE_TAG_STRING = "customtypes";
    public static final String CUSTOM_SHININESS_TEMPLATE_STRING = "CustomShininess";
    public static final String CUSTOM_SHININESS_TEMPLATE_TAG_STRING = "customshininess";
    public static final String CUSTOM_ALPHANESS_TEMPLATE_STRING = "CustomAlphaness";
    public static final String CUSTOM_ALPHANESS_TEMPLATE_TAG_STRING = "customalphaness";
    public static final String FORM_TEMPLATE_STRING = "Form";
    public static final String FORM_TEMPLATE_TAG_STRING = "form";
    public static final String FORMS_EXPANDED_TEMPLATE_STRING = "FormsExpanded";
    public static final String FORMS_EXPANDED_TEMPLATE_TAG_STRING = "formsexpanded";
    public static final String FORMS_FULLY_EXPANDED_TEMPLATE_STRING = "FormsFullyExpanded";
    public static final String FORMS_FULLY_EXPANDED_TEMPLATE_TAG_STRING = "formsfullyexpanded";
    public static final String CUSTOM_FORMS_TEMPLATE_STRING = "CustomForms";
    public static final String CUSTOM_FORMS_TEMPLATE_TAG_STRING = "customforms";
    public static final String CUSTOM_SPECIES_TEMPLATE_STRING = "CustomSpecies";
    public static final String CUSTOM_SPECIES_TEMPLATE_TAG_STRING = "customspecies";
    public static final String HELD_ITEM_TEMPLATE_STRING = "HeldItem";
    public static final String HELD_ITEM_TEMPLATE_TAG_STRING = "helditem";
    public static final String CUSTOM_HELD_ITEM_TEMPLATE_STRING = "CustomHeldItem";
    public static final String CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING = "customhelditem";
    public static final String HELD_ITEM_CUSTOM_NAME_TEMPLATE_STRING = "HeldItemCustomName";
    public static final String HELD_ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING = "helditemcustomname";
    public static final String COSMETIC_ITEM_TEMPLATE_STRING = "CosmeticItem";
    public static final String COSMETIC_ITEM_TEMPLATE_TAG_STRING = "cosmeticitem";
    public static final String COSMETIC_ITEM_CUSTOM_NAME_TEMPLATE_STRING = "CosmeticItemCustomName";
    public static final String COSMETIC_ITEM_CUSTOM_NAME_TEMPLATE_TAG_STRING = "cosmeticitemcustomname";
    public static final String CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING = "CustomCosmeticItem";
    public static final String CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING = "customcosmeticitem";
    public static final String ABILITY_TEMPLATE_STRING = "Ability";
    public static final String ABILITY_TEMPLATE_TAG_STRING = "ability";
    public static final String CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING = "CustomHiddenAbility";
    public static final String CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING = "customhiddenability";
    public static final String NATURE_REAL_TEMPLATE_STRING = "NatureReal";
    public static final String NATURE_REAL_TEMPLATE_TAG_STRING = "naturereal";
    public static final String NATURE_REAL_STAT_UP_TEMPLATE_STRING = "NatureRealStatUp";
    public static final String NATURE_REAL_STAT_UP_TEMPLATE_TAG_STRING = "naturerealstatup";
    public static final String NATURE_REAL_STAT_DOWN_TEMPLATE_STRING = "NatureRealStatDown";
    public static final String NATURE_REAL_STAT_DOWN_TEMPLATE_TAG_STRING = "naturerealstatdown";
    public static final String NATURE_EFFECTIVE_TEMPLATE_STRING = "NatureEffective";
    public static final String NATURE_EFFECTIVE_TEMPLATE_TAG_STRING = "natureeffective";
    public static final String NATURE_EFFECTIVE_STAT_UP_TEMPLATE_STRING = "NatureEffectiveStatUp";
    public static final String NATURE_EFFECTIVE_STAT_UP_TEMPLATE_TAG_STRING = "natureeffectivestatup";
    public static final String NATURE_EFFECTIVE_STAT_DOWN_TEMPLATE_STRING = "NatureEffectiveStatDown";
    public static final String NATURE_EFFECTIVE_STAT_DOWN_TEMPLATE_TAG_STRING = "natureeffectivestatdown";
    public static final String CUSTOM_NATURE_REAL_STATS_TEMPLATE_STRING = "CustomNatureRealStats";
    public static final String CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING = "customnaturerealstats";
    public static final String CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_STRING = "CustomNatureEffectiveStats";
    public static final String CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING = "customnatureeffectivestats";
    public static final String CUSTOM_MINTNESS_TEMPLATE_STRING = "CustomMintness";
    public static final String CUSTOM_MINTNESS_TEMPLATE_TAG_STRING = "custommintness";
    public static final String LEVEL_TEMPLATE_STRING = "Level";
    public static final String LEVEL_TEMPLATE_TAG_STRING = "level";
    public static final String CURRENT_EXPERIENCE_TEMPLATE_STRING = "CurrentExperience";
    public static final String CURRENT_EXPERIENCE_TEMPLATE_TAG_STRING = "currentexperience";
    public static final String REQUIRED_EXPERIENCE_TEMPLATE_STRING = "RequiredExperience";
    public static final String REQUIRED_EXPERIENCE_TEMPLATE_TAG_STRING = "requiredexperience";
    public static final String REMAINING_EXPERIENCE_TEMPLATE_STRING = "RemainingExperience";
    public static final String REMAINING_EXPERIENCE_TEMPLATE_TAG_STRING = "remainingexperience";
    public static final String CUSTOM_EXPERIENCE_TEMPLATE_STRING = "CustomExperience";
    public static final String CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING = "customexperience";
    public static final String FRIENDSHIP_TEMPLATE_STRING = "Friendship";
    public static final String FRIENDSHIP_TEMPLATE_TAG_STRING = "friendship";
    public static final String MOVE1_TEMPLATE_STRING = "Move1";
    public static final String MOVE1_TEMPLATE_TAG_STRING = "move1";
    public static final String MOVE1_COLOR_TEMPLATE_STRING = "Move1Color";
    public static final String MOVE1_COLOR_TEMPLATE_TAG_STRING = "move1color";
    public static final String MOVE1_USED_PP_TEMPLATE_STRING = "Move1UsedPP";
    public static final String MOVE1_USED_PP_TEMPLATE_TAG_STRING = "move1usedpp";
    public static final String MOVE1_REMAINING_PP_TEMPLATE_STRING = "Move1RemainingPP";
    public static final String MOVE1_REMAINING_PP_TEMPLATE_TAG_STRING = "move1remainingpp";
    public static final String MOVE1_TOTAL_PP_TEMPLATE_STRING = "Move1TotalPP";
    public static final String MOVE1_TOTAL_PP_TEMPLATE_TAG_STRING = "move1totalpp";
    public static final String MOVE1_CUSTOM_PP_TEMPLATE_STRING = "Move1CustomPP";
    public static final String MOVE1_CUSTOM_PP_TEMPLATE_TAG_STRING = "move1custompp";
    public static final String MOVE2_TEMPLATE_STRING = "Move2";
    public static final String MOVE2_TEMPLATE_TAG_STRING = "move2";
    public static final String MOVE2_COLOR_TEMPLATE_STRING = "Move2Color";
    public static final String MOVE2_COLOR_TEMPLATE_TAG_STRING = "move2color";
    public static final String MOVE2_USED_PP_TEMPLATE_STRING = "Move2UsedPP";
    public static final String MOVE2_USED_PP_TEMPLATE_TAG_STRING = "move2usedpp";
    public static final String MOVE2_REMAINING_PP_TEMPLATE_STRING = "Move2RemainingPP";
    public static final String MOVE2_REMAINING_PP_TEMPLATE_TAG_STRING = "move2remainingpp";
    public static final String MOVE2_TOTAL_PP_TEMPLATE_STRING = "Move2TotalPP";
    public static final String MOVE2_TOTAL_PP_TEMPLATE_TAG_STRING = "move2totalpp";
    public static final String MOVE2_CUSTOM_PP_TEMPLATE_STRING = "Move2CustomPP";
    public static final String MOVE2_CUSTOM_PP_TEMPLATE_TAG_STRING = "move2custompp";
    public static final String MOVE3_TEMPLATE_STRING = "Move3";
    public static final String MOVE3_TEMPLATE_TAG_STRING = "move3";
    public static final String MOVE3_COLOR_TEMPLATE_STRING = "Move3Color";
    public static final String MOVE3_COLOR_TEMPLATE_TAG_STRING = "move3color";
    public static final String MOVE3_USED_PP_TEMPLATE_STRING = "Move3UsedPP";
    public static final String MOVE3_USED_PP_TEMPLATE_TAG_STRING = "move3usedpp";
    public static final String MOVE3_REMAINING_PP_TEMPLATE_STRING = "Move3RemainingPP";
    public static final String MOVE3_REMAINING_PP_TEMPLATE_TAG_STRING = "move3remainingpp";
    public static final String MOVE3_TOTAL_PP_TEMPLATE_STRING = "Move3TotalPP";
    public static final String MOVE3_TOTAL_PP_TEMPLATE_TAG_STRING = "move3totalpp";
    public static final String MOVE3_CUSTOM_PP_TEMPLATE_STRING = "Move3CustomPP";
    public static final String MOVE3_CUSTOM_PP_TEMPLATE_TAG_STRING = "move3custompp";
    public static final String MOVE4_TEMPLATE_STRING = "Move4";
    public static final String MOVE4_TEMPLATE_TAG_STRING = "move4";
    public static final String MOVE4_COLOR_TEMPLATE_STRING = "Move4Color";
    public static final String MOVE4_COLOR_TEMPLATE_TAG_STRING = "move4color";
    public static final String MOVE4_USED_PP_TEMPLATE_STRING = "Move4UsedPP";
    public static final String MOVE4_USED_PP_TEMPLATE_TAG_STRING = "move4usedpp";
    public static final String MOVE4_REMAINING_PP_TEMPLATE_STRING = "Move4RemainingPP";
    public static final String MOVE4_REMAINING_PP_TEMPLATE_TAG_STRING = "move4remainingpp";
    public static final String MOVE4_TOTAL_PP_TEMPLATE_STRING = "Move4TotalPP";
    public static final String MOVE4_TOTAL_PP_TEMPLATE_TAG_STRING = "move4totalpp";
    public static final String MOVE4_CUSTOM_PP_TEMPLATE_STRING = "Move4CustomPP";
    public static final String MOVE4_CUSTOM_PP_TEMPLATE_TAG_STRING = "move4custompp";
    public static final String MOVE_TEMPLATE_STRING = "Move";
    public static final String MOVE_TEMPLATE_TAG_STRING = "move";
    public static final String MOVE_COLOR_TEMPLATE_STRING = "MoveColor";
    public static final String MOVE_COLOR_TEMPLATE_TAG_STRING = "movecolor";
    public static final String MOVE_USED_PP_TEMPLATE_STRING = "MoveUsedPP";
    public static final String MOVE_USED_PP_TEMPLATE_TAG_STRING = "moveusedpp";
    public static final String MOVE_REMAINING_PP_TEMPLATE_STRING = "MoveRemainingPP";
    public static final String MOVE_REMAINING_PP_TEMPLATE_TAG_STRING = "moveremainingpp";
    public static final String MOVE_TOTAL_PP_TEMPLATE_STRING = "MoveTotalPP";
    public static final String MOVE_TOTAL_PP_TEMPLATE_TAG_STRING = "movetotalpp";
    public static final String MOVE_CUSTOM_PP_TEMPLATE_STRING = "MoveCustomPP";
    public static final String MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING = "movecustompp";
    public static final String CUSTOM_MOVES_TEMPLATE_STRING = "CustomMoves";
    public static final String CUSTOM_MOVES_TEMPLATE_TAG_STRING = "custommoves";
    public static final String REAL_IV_TOTAL_TEMPLATE_STRING = "RealIVTotal";
    public static final String REAL_IV_TOTAL_TEMPLATE_TAG_STRING = "realivtotal";
    public static final String EFFECTIVE_IV_TOTAL_TEMPLATE_STRING = "EffectiveIVTotal";
    public static final String EFFECTIVE_IV_TOTAL_TEMPLATE_TAG_STRING = "effectiveivtotal";
    public static final String REAL_IV_PERCENTAGE_TEMPLATE_STRING = "RealIVPercentage";
    public static final String REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING = "realivpercentage";
    public static final String EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING = "EffectiveIVPercentage";
    public static final String EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING = "effectiveivpercentage";
    public static final String CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING = "CustomRealIVPercentage";
    public static final String CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING = "customrealivpercentage";
    public static final String CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING = "CustomEffectiveIVPercentage";
    public static final String CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING = "customeffectiveivpercentage";
    public static final String CUSTOM_GENERAL_IVS_TEMPLATE_STRING = "CustomGeneralIVs";
    public static final String CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING = "customgeneralivs";
    public static final String REAL_HP_IVS_TEMPLATE_STRING = "RealHpIVs";
    public static final String REAL_HP_IVS_TEMPLATE_TAG_STRING = "realhpivs";
    public static final String EFFECTIVE_HP_IVS_TEMPLATE_STRING = "EffectiveHpIVs";
    public static final String EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING = "effectivehpivs";
    public static final String CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING = "CustomEffectiveHpIVs";
    public static final String CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING = "customeffectivehpivs";
    public static final String HP_COLOR_TEMPLATE_STRING = "HpColor";
    public static final String HP_COLOR_TEMPLATE_TAG_STRING = "hpcolor";
    public static final String CUSTOM_HP_IVS_TEMPLATE_STRING = "CustomHpIVs";
    public static final String CUSTOM_HP_IVS_TEMPLATE_TAG_STRING = "customhpivs";
    public static final String REAL_ATK_IVS_TEMPLATE_STRING = "RealAtkIVs";
    public static final String REAL_ATK_IVS_TEMPLATE_TAG_STRING = "realatkivs";
    public static final String EFFECTIVE_ATK_IVS_TEMPLATE_STRING = "EffectiveAtkIVs";
    public static final String EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING = "effectiveatkivs";
    public static final String CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING = "CustomEffectiveAtkIVs";
    public static final String CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING = "customeffectiveatkivs";
    public static final String ATK_COLOR_TEMPLATE_STRING = "AtkColor";
    public static final String ATK_COLOR_TEMPLATE_TAG_STRING = "atkcolor";
    public static final String CUSTOM_ATK_IVS_TEMPLATE_STRING = "CustomAtkIVs";
    public static final String CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING = "customatkivs";
    public static final String REAL_DEF_IVS_TEMPLATE_STRING = "RealDefIVs";
    public static final String REAL_DEF_IVS_TEMPLATE_TAG_STRING = "realdefivs";
    public static final String EFFECTIVE_DEF_IVS_TEMPLATE_STRING = "EffectiveDefIVs";
    public static final String EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING = "effectivedefivs";
    public static final String CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING = "CustomEffectiveDefIVs";
    public static final String CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING = "customeffectivedefivs";
    public static final String DEF_COLOR_TEMPLATE_STRING = "DefColor";
    public static final String DEF_COLOR_TEMPLATE_TAG_STRING = "defcolor";
    public static final String CUSTOM_DEF_IVS_TEMPLATE_STRING = "CustomdefIVs";
    public static final String CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING = "customdefivs";
    public static final String REAL_SPA_IVS_TEMPLATE_STRING = "RealSpaIVs";
    public static final String REAL_SPA_IVS_TEMPLATE_TAG_STRING = "realspaivs";
    public static final String EFFECTIVE_SPA_IVS_TEMPLATE_STRING = "EffectiveSpaIVs";
    public static final String EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING = "effectivespaivs";
    public static final String CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING = "CustomEffectiveSpaIVs";
    public static final String CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING = "customeffectivespaivs";
    public static final String SPA_COLOR_TEMPLATE_STRING = "SpaColor";
    public static final String SPA_COLOR_TEMPLATE_TAG_STRING = "spacolor";
    public static final String CUSTOM_SPA_IVS_TEMPLATE_STRING = "CustomspaIVs";
    public static final String CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING = "customspaivs";
    public static final String REAL_SPD_IVS_TEMPLATE_STRING = "RealSpdIVs";
    public static final String REAL_SPD_IVS_TEMPLATE_TAG_STRING = "realspdivs";
    public static final String EFFECTIVE_SPD_IVS_TEMPLATE_STRING = "EffectiveSpdIVs";
    public static final String EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING = "effectivespdivs";
    public static final String CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING = "CustomEffectiveSpdIVs";
    public static final String CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING = "customeffectivespdivs";
    public static final String SPD_COLOR_TEMPLATE_STRING = "SpdColor";
    public static final String SPD_COLOR_TEMPLATE_TAG_STRING = "spdcolor";
    public static final String CUSTOM_SPD_IVS_TEMPLATE_STRING = "CustomspdIVs";
    public static final String CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING = "customspdivs";
    public static final String REAL_SPE_IVS_TEMPLATE_STRING = "RealSpeIVs";
    public static final String REAL_SPE_IVS_TEMPLATE_TAG_STRING = "realspeivs";
    public static final String EFFECTIVE_SPE_IVS_TEMPLATE_STRING = "EffectiveSpeIVs";
    public static final String EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING = "effectivespeivs";
    public static final String CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING = "CustomEffectiveSpeIVs";
    public static final String CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING = "customeffectivespeivs";
    public static final String SPE_COLOR_TEMPLATE_STRING = "SpeColor";
    public static final String SPE_COLOR_TEMPLATE_TAG_STRING = "specolor";
    public static final String CUSTOM_SPE_IVS_TEMPLATE_STRING = "CustomspeIVs";
    public static final String CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING = "customspeivs";
    public static final String EV_TOTAL_TEMPLATE_STRING = "EVTotal";
    public static final String EV_TOTAL_TEMPLATE_TAG_STRING = "evtotal";
    public static final String EV_PERCENTAGE_TEMPLATE_STRING = "EVPercentage";
    public static final String EV_PERCENTAGE_TEMPLATE_TAG_STRING = "evpercentage";
    public static final String CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING = "CustomEVPercentage";
    public static final String CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING = "customevpercentage";
    public static final String CUSTOM_GENERAL_EVS_TEMPLATE_STRING = "CustomGeneralEVs";
    public static final String CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING = "customgeneralevs";
    public static final String HP_EV_TEMPLATE_STRING = "HpEVs";
    public static final String HP_EV_TEMPLATE_TAG_STRING = "hpevs";
    public static final String CUSTOM_HP_EVS_TEMPLATE_STRING = "CustomHpEVs";
    public static final String CUSTOM_HP_EVS_TEMPLATE_TAG_STRING = "customhpevs";
    public static final String ATK_EV_TEMPLATE_STRING = "AtkEVs";
    public static final String ATK_EV_TEMPLATE_TAG_STRING = "atkevs";
    public static final String CUSTOM_ATK_EVS_TEMPLATE_STRING = "CustomAtkEVs";
    public static final String CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING = "customatkevs";
    public static final String DEF_EV_TEMPLATE_STRING = "DefEVs";
    public static final String DEF_EV_TEMPLATE_TAG_STRING = "defevs";
    public static final String CUSTOM_DEF_EVS_TEMPLATE_STRING = "CustomDefEVs";
    public static final String CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING = "customdefevs";
    public static final String SPA_EV_TEMPLATE_STRING = "SpaEVs";
    public static final String SPA_EV_TEMPLATE_TAG_STRING = "spaevs";
    public static final String CUSTOM_SPA_EVS_TEMPLATE_STRING = "CustomSpaEVs";
    public static final String CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING = "customspaevs";
    public static final String SPD_EV_TEMPLATE_STRING = "SpdEVs";
    public static final String SPD_EV_TEMPLATE_TAG_STRING = "spdevs";
    public static final String CUSTOM_SPD_EVS_TEMPLATE_STRING = "CustomSpdEVs";
    public static final String CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING = "customspdevs";
    public static final String SPE_EV_TEMPLATE_STRING = "SpeEVs";
    public static final String SPE_EV_TEMPLATE_TAG_STRING = "speevs";
    public static final String CUSTOM_SPE_EVS_TEMPLATE_STRING = "CustomSpeEVs";
    public static final String CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING = "customspeevs";
    public static final String SIZE_TEMPLATE_STRING = "Size";
    public static final String SIZE_TEMPLATE_TAG_STRING = "size";
    public static final String SCALE_MODIFIER_TEMPLATE_STRING = "ScaleModifier";
    public static final String SCALE_MODIFIER_TEMPLATE_TAG_STRING = "scalemodifier";
    public static final String SCALE_MODIFIER_100_TEMPLATE_STRING = "ScaleModifier100";
    public static final String SCALE_MODIFIER_100_TEMPLATE_TAG_STRING = "scalemodifier100";
    public static final String EGG_GROUPS_TEMPLATE_STRING = "EggGroups";
    public static final String EGG_GROUPS_TEMPLATE_TAG_STRING = "egggroups";
    public static final String NEUTERED_TEMPLATE_STRING = "Neutered";
    public static final String NEUTERED_TEMPLATE_TAG_STRING = "neutered";
    public static final String CUSTOM_NEUTERED_TEMPLATE_STRING = "CustomNeutered";
    public static final String CUSTOM_NEUTERED_TEMPLATE_TAG_STRING = "customneutered";
    public static final String ORIGINAL_TRAINER_NAME_TEMPLATE_STRING = "OriginalTrainerName";
    public static final String ORIGINAL_TRAINER_NAME_TEMPLATE_TAG_STRING = "originaltrainername";

    protected static final String POKEMON_INFO_TEMPLATE_STRING = "PokemonInfo";
    protected static final String POKEMON_INFO_TEMPLATE_TAG_STRING = "pokemoninfo";
    protected static final String CUSTOM_FORM_TEMPLATE_STRING = "CustomForm";
    protected static final String CUSTOM_FORM_TEMPLATE_TAG_STRING = "customform";
    protected static final String CUSTOM_MOVE_TEMPLATE_STRING = "CustomMove";
    protected static final String CUSTOM_MOVE_TEMPLATE_TAG_STRING = "custommove";

    protected static final String BURN_TEMPLATE_STRING = "Burn";
    protected static final String BURN_TEMPLATE_TAG_STRING = "burn";
    protected static final String SLEEP_TEMPLATE_STRING = "Sleep";
    protected static final String SLEEP_TEMPLATE_TAG_STRING = "sleep";
    protected static final String PARALYSIS_TEMPLATE_STRING = "Paralysis";
    protected static final String PARALYSIS_TEMPLATE_TAG_STRING = "paralysis";
    protected static final String POISON_TEMPLATE_STRING = "Poison";
    protected static final String POISON_TEMPLATE_TAG_STRING = "poison";
    protected static final String FREEZE_TEMPLATE_STRING = "Freeze";
    protected static final String FREEZE_TEMPLATE_TAG_STRING = "freeze";
    protected static final String FAINT_TEMPLATE_STRING = "Faint";
    protected static final String FAINT_TEMPLATE_TAG_STRING = "faint";

    protected static final String AMORPHOUS_TEMPLATE_STRING = "Amorphous";
    protected static final String AMORPHOUS_TEMPLATE_TAG_STRING = "amorphous";
    protected static final String BUG_TEMPLATE_STRING = "Bug";
    protected static final String BUG_TEMPLATE_TAG_STRING = "bug";
    protected static final String DITTO_TEMPLATE_STRING = "Ditto";
    protected static final String DITTO_TEMPLATE_TAG_STRING = "ditto";
    protected static final String DRAGON_TEMPLATE_STRING = "Dragon";
    protected static final String DRAGON_TEMPLATE_TAG_STRING = "dragon";
    protected static final String FAIRY_TEMPLATE_STRING = "Fairy";
    protected static final String FAIRY_TEMPLATE_TAG_STRING = "fairy";
    protected static final String FIELD_TEMPLATE_STRING = "Field";
    protected static final String FIELD_TEMPLATE_TAG_STRING = "field";
    protected static final String FLYING_TEMPLATE_STRING = "Flying";
    protected static final String FLYING_TEMPLATE_TAG_STRING = "flying";
    protected static final String GRASS_TEMPLATE_STRING = "Grass";
    protected static final String GRASS_TEMPLATE_TAG_STRING = "grass";
    protected static final String HUMAN_LIKE_TEMPLATE_STRING = "Human-Like";
    protected static final String HUMAN_LIKE_TEMPLATE_TAG_STRING = "human-like";
    protected static final String MINERAL_TEMPLATE_STRING = "Mineral";
    protected static final String MINERAL_TEMPLATE_TAG_STRING = "mineral";
    protected static final String MONSTER_TEMPLATE_STRING = "Monster";
    protected static final String MONSTER_TEMPLATE_TAG_STRING = "monster";
    protected static final String UNDISCOVERED_TEMPLATE_STRING = "Undiscovered";
    protected static final String UNDISCOVERED_TEMPLATE_TAG_STRING = "undiscovered";
    protected static final String WATER1_TEMPLATE_STRING = "Water1";
    protected static final String WATER1_TEMPLATE_TAG_STRING = "water1";
    protected static final String WATER2_TEMPLATE_STRING = "Water2";
    protected static final String WATER2_TEMPLATE_TAG_STRING = "water2";
    protected static final String WATER3_TEMPLATE_STRING = "Water3";
    protected static final String WATER3_TEMPLATE_TAG_STRING = "water3";

    protected static final String GENDER_MALE_TEMPLATE_STRING = "Male";
    protected static final String GENDER_MALE_TEMPLATE_TAG_STRING = "male";
    protected static final String GENDER_FEMALE_TEMPLATE_STRING = "Female";
    protected static final String GENDER_FEMALE_TEMPLATE_TAG_STRING = "female";
    protected static final String GENDER_GENDERLESS_TEMPLATE_STRING = "Genderless";
    protected static final String GENDER_GENDERLESS_TEMPLATE_TAG_STRING = "genderless";

    protected static final String TRUE_TEMPLATE_STRING = "True";
    protected static final String TRUE_TEMPLATE_TAG_STRING = "true";
    protected static final String FALSE_TEMPLATE_STRING = "False";
    protected static final String FALSE_TEMPLATE_TAG_STRING = "false";
    
    // Color Defaults
    protected static final String DEFAULT_DIVISION_COLOR_STRING = "#646464";
    protected static final String DEFAULT_LABEL_COLOR_STRING = "#10F2F2";
    protected static final String DEFAULT_SHINY_COLOR_STRING = "#e7e436";
    protected static final String DEFAULT_ALPHA_COLOR_STRING = "#d14040";
    protected static final String DEFAULT_HA_COLOR_STRING = "#31d6e2";
    protected static final String DEFAULT_NATURE_STAT_UP_COLOR_STRING = "#60d651";
    protected static final String DEFAULT_NATURE_STAT_DOWN_COLOR_STRING = "#d14040";
    protected static final String DEFAULT_TRUE_COLOR_STRING = "#40d440";
    protected static final String DEFAULT_FALSE_COLOR_STRING = "#d12828";
    protected static final String DEFAULT_BURN_COLOR_STRING = "#ff3333";
    protected static final String DEFAULT_SLEEP_COLOR_STRING = "#66ffff";
    protected static final String DEFAULT_PARALYSIS_COLOR_STRING = "#ffff00";
    protected static final String DEFAULT_POISON_COLOR_STRING = "#990099";
    protected static final String DEFAULT_FREEZE_COLOR_STRING = "#66ddff";
    protected static final String DEFAULT_FAINT_COLOR_STRING = "#808080";
    protected static final String DEFAULT_COMMAND_PREFIX_GRADIENT_LEFT_COLOR_STRING = "#1ce9e0";
    protected static final String DEFAULT_COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_STRING = "#fa70f6";
    protected static final String DEFAULT_MALE_COLOR_STRING = "#0984f7";
    protected static final String DEFAULT_FEMALE_COLOR_STRING = "#e16ef0";
    protected static final String DEFAULT_GENDERLESS_COLOR_STRING = "#bdbdbd";
    protected static final String DEFAULT_HP_COLOR_STRING = "#4f9c45";
    protected static final String DEFAULT_ATK_COLOR_STRING = "#b33f3f";
    protected static final String DEFAULT_DEF_COLOR_STRING = "#d1842c";
    protected static final String DEFAULT_SPA_COLOR_STRING = "#d140ca";
    protected static final String DEFAULT_SPD_COLOR_STRING = "#ddda36";
    protected static final String DEFAULT_SPE_COLOR_STRING = "#3bd8dd";

    // PokeInfo Defaults
    protected static final String DEFAULT_POKEMON_INFO =
        """
        <gradient:<type1color>:<type2color>><name>, <title></gradient>
        <customtypes> <color:<divisioncolor>>│</Color> <gender> <color:<divisioncolor>>│</color> <caughtball>
        <color:<divisioncolor>>────────────────────────────
        <color:<labelcolor>>Condition:</color> <customcondition>
        <color:<labelcolor>>Species:</color> <customspecies>
        <color:<labelcolor>>Held Item:</color> <white><helditem></white> <customcosmeticitem>
        <color:<labelcolor>>Ability:</color> <white><ability></white> <customhiddenability>
        <color:<labelcolor>>Nature</color><custommintness><color:<labelcolor>>:</color> <white><natureeffective></white> <customnatureeffectivestats>
        <color:<labelcolor>>Level:</color> <white><level></white> <customexperience>
        <color:<labelcolor>>Friendship:</color> <white><friendship></white>
        <dark_gray>────────────────────────────
        <custommoves>
        <dark_gray>────────────────────────────
        <color:<labelcolor>>IVs:</color> <customgeneralivs>
            <customhpivs> <customatkivs> <customdefivs>
            <customspaivs> <customspdivs> <customspeivs>
        <dark_gray>────────────────────────────
        <color:<labelcolor>>EVs:</color> <customgeneralevs>
            <customhpev> <customatkev> <customdefev>
            <customspaev> <customspdev> <customspeev>
        <dark_gray>────────────────────────────
        <color:<labelcolor>>Size:</color> <white><size></white> <color:<labelcolor>>(</color><white><scalemodifier100>% scale</white><color:<labelcolor>>)</color>
        <color:<labelcolor>>Egg Groups:</color> <white><egggroups></white>
        <customneutered>
        <color:<labelcolor>>OT:</color> <white><originaltrainername></white>
        """;

    protected static final String DEFAULT_CUSTOM_STATUS =
        """
        <color:<labelcolor>>(</color><status><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_CONDITION =
        """
        <green><currenthealth></green><color:<labelcolor>>/</color><green><maxhealth></green> <customstatus>
        """;

    protected static final String DEFAULT_CUSTOM_TYPES_MONOTYPE =
        """
        <color:<type1color>><type1></color> <color:<labelcolor>>(</color><color:<teratypecolor>><teratype></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_TYPES_DUOTYPE =
        """
        <color:<type1color>><type1></color><color:<labelcolor>>/</color><color:<type2color>><type2></color> <color:<labelcolor>>(</color><color:<teratypecolor>><teratype></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SHININESS =
        """
        <color:<shinycolor>> ★ </color>
        """;

    protected static final String DEFAULT_CUSTOM_ALPHANESS =
        """
        <color:<alphacolor>> α </color>
        """;

    protected static final String DEFAULT_CUSTOM_FORM =
        """
        <color:<labelcolor>>(</color><white><form></white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPECIES =
        """
        <customshininess><customalphaness> <white><species></white> <customforms>
        """;

    protected static final String DEFAULT_CUSTOM_HELD_ITEM =
        """
        <color:<labelcolor>>(</color><white><helditem></white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_COSMETIC_ITEM =
        """
        <color:<labelcolor>>(</color><white><cosmeticitem></white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_HIDDEN_ABILITY =
        """
        <color:<labelcolor>>(</color><color:<hacolor>>HA</color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_NATURE_REAL_STATS =
        """
        <color:<labelcolor>>(</color><color:<naturestatupcolor>>↑<naturerealstatup></color><color:<labelcolor>>/</color><color:<naturestatdowncolor>>↓<naturerealstatdown></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_NATURE_EFFECTIVE_STATS =
        """
        <color:<labelcolor>>(</color><color:<naturestatupcolor>>↑<natureeffectivestatup></color><color:<labelcolor>>/</color><color:<naturestatdowncolor>>↓<natureeffectivestatdown></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_MINTNESS =
        """
        <color:<labelcolor>> (Minted)</color>
        """;

    protected static final String DEFAULT_CUSTOM_EXPERIENCE =
        """
        <color:<labelcolor>>(</color><white><currentexperience></white><color:<labelcolor>>/</color><white><requiredexperience></white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_MOVE_CUSTOM_PP =
        """
        <color:<labelcolor>>(</color><white>><moveremainingpp></white><color:<labelcolor>>/</color><white><movetotalpp></white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_MOVE =
        """
        <color:<labelcolor>> ▶ </color><color:<movecolor>><move></color> <movecustompp>
        """;

    protected static final String DEFAULT_CUSTOM_REAL_IV_PERCENTAGE =
        """
        <color:<labelcolor>>(</color><white><realivpercentage>%</white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_IV_PERCENTAGE =
        """
        <color:<labelcolor>>(</color><white><effectiveivpercentage>%</white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_GENERAL_IVS =
        """
        <white><effectiveivtotal></white><color:<labelcolor>>/</color><white>186</white> <customeffectiveivpercentage>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_HP_IVS =
        """
        <color:<labelcolor>>(</color><color:<hpcolor>><effectivehpivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_HP_IVS =
        """
        <color:<hpcolor>>Hp <realhpivs></color> <customeffectivehpivs>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_ATK_IVS =
        """
        <color:<labelcolor>>(</color><color:<atkcolor>><effectiveatkivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_ATK_IVS =
        """
        <color:<atkcolor>>Atk <realatkivs></color> <customeffectiveatkivs>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_DEF_IVS =
        """
        <color:<labelcolor>>(</color><color:<defcolor>><effectivedefivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_DEF_IVS =
        """
        <color:<defcolor>>Def <realdefivs></color> <customeffectivedefivs>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPA_IVS =
        """
        <color:<labelcolor>>(</color><color:<spacolor>><effectivespaivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPA_IVS =
        """
        <color:<spacolor>>Spa <realspaivs></color> <customeffectivespaivs>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPD_IVS =
        """
        <color:<labelcolor>>(</color><color:<spdcolor>><effectivespdivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPD_IVS =
        """
        <color:<spdcolor>>Spd <realspdivs></color> <customeffectivespdivs>
        """;

    protected static final String DEFAULT_CUSTOM_EFFECTIVE_SPE_IVS =
        """
        <color:<labelcolor>>(</color><color:<specolor>><effectivespeivs></color><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_SPE_IVS =
        """
        <color:<specolor>>Spe <realspeivs></color> <customeffectivespeivs>
        """;

    protected static final String DEFAULT_CUSTOM_EV_PERCENTAGE =
        """
        <color:<labelcolor>>(</color><white><evpercentage>%</white><color:<labelcolor>>)</color>
        """;

    protected static final String DEFAULT_CUSTOM_GENERAL_EVS =
        """
        <white><evtotal></white><color:<labelcolor>>/</color><white>510</white> <customevpercentage>
        """;

    protected static final String DEFAULT_CUSTOM_HP_EVS =
        """
        <color:<hpcolor>>Hp <hpevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_ATK_EVS =
        """
        <color:<atkcolor>>Atk <atkevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_DEF_EVS =
        """
        <color:<defcolor>>Def <defevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_SPA_EVS =
        """
        <color:<spacolor>>Spa <spaevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_SPD_EVS =
        """
        <color:<spdcolor>>Spd <spdevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_SPE_EVS =
        """
        <color:<specolor>>Spe <speevs></color>
        """;

    protected static final String DEFAULT_CUSTOM_NEUTERED =
        """
        <color:<labelcolor>>Neutered:</color> <neutered>
        """;

    protected static final String DEFAULT_BURN = "<color:<burncolor>>[BRN]</color>";
    protected static final String DEFAULT_SLEEP = "<color:<sleepcolor>>[SLP]</color>";
    protected static final String DEFAULT_PARALYSIS = "<color:<paralysiscolor>>[PAR]</color>";
    protected static final String DEFAULT_POISON = "<color:<poisoncolor>>[PSN]</color>";
    protected static final String DEFAULT_FREEZE = "<color:<freezecolor>>[FRZ]</color>";
    protected static final String DEFAULT_FAINT = "<color:<faintcolor>>[FNT]</color>";

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

    protected static final String DEFAULT_MALE_GENDER = "<color:<malecolor>>♂</color>";
    protected static final String DEFAULT_FEMALE_GENDER = "<color:<femalecolor>>♀</color>";
    protected static final String DEFAULT_GENDERLESS_GENDER = "<color:<genderlesscolor>>⚲</color>";

    protected static final String DEFAULT_TRUE = "<color:<truecolor>>Yes</color>";
    protected static final String DEFAULT_FALSE = "<color:<falsecolor>>No</color>";

    // State Data
    private static HashMap<String, String> CUSTOM_COLORS;
    private static HashMap<String, String> STAT_COLORS;
    private static HashMap<String, String> POKE_INFO_TEMPLATES;
    private static HashMap<String, String> STATUS_TEMPLATES;
    private static HashMap<String, String> EGG_GROUP_TEMPLATES;
    private static HashMap<String, String> GENDER_TEMPLATES;
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
            GENDER_TEMPLATES = new HashMap<>();
            BOOLEAN_TEMPLATES = new HashMap<>();

            CUSTOM_COLORS.put(DIVISION_COLOR_TEMPLATE_TAG_STRING, DEFAULT_DIVISION_COLOR_STRING);
            CUSTOM_COLORS.put(LABEL_COLOR_TEMPLATE_TAG_STRING, DEFAULT_LABEL_COLOR_STRING);
            CUSTOM_COLORS.put(SHINY_COLOR_TEMPLATE_TAG_STRING, DEFAULT_SHINY_COLOR_STRING);
            CUSTOM_COLORS.put(ALPHA_COLOR_TEMPLATE_TAG_STRING, DEFAULT_ALPHA_COLOR_STRING);
            CUSTOM_COLORS.put(HA_COLOR_TEMPLATE_TAG_STRING, DEFAULT_HA_COLOR_STRING);
            CUSTOM_COLORS.put(NATURE_STAT_UP_COLOR_TEMPLATE_TAG_STRING, DEFAULT_NATURE_STAT_UP_COLOR_STRING);
            CUSTOM_COLORS.put(NATURE_STAT_DOWN_COLOR_TEMPLATE_TAG_STRING, DEFAULT_NATURE_STAT_DOWN_COLOR_STRING);
            CUSTOM_COLORS.put(TRUE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_TRUE_COLOR_STRING);
            CUSTOM_COLORS.put(FALSE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_FALSE_COLOR_STRING);
            CUSTOM_COLORS.put(BURN_COLOR_TEMPLATE_TAG_STRING, DEFAULT_BURN_COLOR_STRING);
            CUSTOM_COLORS.put(SLEEP_COLOR_TEMPLATE_TAG_STRING, DEFAULT_SLEEP_COLOR_STRING);
            CUSTOM_COLORS.put(PARALYSIS_COLOR_TEMPLATE_TAG_STRING, DEFAULT_PARALYSIS_COLOR_STRING);
            CUSTOM_COLORS.put(POISON_COLOR_TEMPLATE_TAG_STRING, DEFAULT_POISON_COLOR_STRING);
            CUSTOM_COLORS.put(FREEZE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_FREEZE_COLOR_STRING);
            CUSTOM_COLORS.put(FAINT_COLOR_TEMPLATE_TAG_STRING, DEFAULT_FAINT_COLOR_STRING);
            CUSTOM_COLORS.put(COMMAND_PREFIX_GRADIENT_LEFT_COLOR_TEMPLATE_TAG_STRING, DEFAULT_COMMAND_PREFIX_GRADIENT_LEFT_COLOR_STRING);
            CUSTOM_COLORS.put(COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_TEMPLATE_TAG_STRING, DEFAULT_COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_STRING);
            CUSTOM_COLORS.put(MALE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_MALE_COLOR_STRING);
            CUSTOM_COLORS.put(FEMALE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_FEMALE_COLOR_STRING);
            CUSTOM_COLORS.put(GENDERLESS_COLOR_TEMPLATE_TAG_STRING, DEFAULT_GENDERLESS_COLOR_STRING);

            STAT_COLORS.put(HP_COLOR_TEMPLATE_TAG_STRING, DEFAULT_HP_COLOR_STRING);
            STAT_COLORS.put(ATK_COLOR_TEMPLATE_TAG_STRING, DEFAULT_ATK_COLOR_STRING);
            STAT_COLORS.put(DEF_COLOR_TEMPLATE_TAG_STRING, DEFAULT_DEF_COLOR_STRING);
            STAT_COLORS.put(SPA_COLOR_TEMPLATE_TAG_STRING, DEFAULT_SPA_COLOR_STRING);
            STAT_COLORS.put(SPD_COLOR_TEMPLATE_TAG_STRING, DEFAULT_SPD_COLOR_STRING);
            STAT_COLORS.put(SPE_COLOR_TEMPLATE_TAG_STRING, DEFAULT_SPE_COLOR_STRING);

            POKE_INFO_TEMPLATES.put(POKEMON_INFO_TEMPLATE_TAG_STRING, DEFAULT_POKEMON_INFO);
            POKE_INFO_TEMPLATES.put(CUSTOM_STATUS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_STATUS);
            POKE_INFO_TEMPLATES.put(CUSTOM_CONDITION_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_CONDITION);
            POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_MONOTYPE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_TYPES_MONOTYPE);
            POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_DUOTYPE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_TYPES_DUOTYPE);
            POKE_INFO_TEMPLATES.put(CUSTOM_SHININESS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SHININESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ALPHANESS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_ALPHANESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_FORM_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_FORM);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPECIES_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPECIES);
            POKE_INFO_TEMPLATES.put(CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_HELD_ITEM);
            POKE_INFO_TEMPLATES.put(CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_COSMETIC_ITEM);
            POKE_INFO_TEMPLATES.put(CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_HIDDEN_ABILITY);
            POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_NATURE_REAL_STATS);
            POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_NATURE_EFFECTIVE_STATS);
            POKE_INFO_TEMPLATES.put(CUSTOM_MINTNESS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_MINTNESS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EXPERIENCE);
            POKE_INFO_TEMPLATES.put(MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING, DEFAULT_MOVE_CUSTOM_PP);
            POKE_INFO_TEMPLATES.put(CUSTOM_MOVE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_MOVE);
            POKE_INFO_TEMPLATES.put(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_REAL_IV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_IV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_GENERAL_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_HP_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_HP_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_HP_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_ATK_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_ATK_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_DEF_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_DEF_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPA_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPA_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPD_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPD_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EFFECTIVE_SPE_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPE_IVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_EV_PERCENTAGE);
            POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_GENERAL_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_HP_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_HP_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_ATK_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_DEF_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPA_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPD_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_SPE_EVS);
            POKE_INFO_TEMPLATES.put(CUSTOM_NEUTERED_TEMPLATE_TAG_STRING, DEFAULT_CUSTOM_NEUTERED);

            STATUS_TEMPLATES.put(BURN_TEMPLATE_TAG_STRING, DEFAULT_BURN);
            STATUS_TEMPLATES.put(SLEEP_TEMPLATE_TAG_STRING, DEFAULT_SLEEP);
            STATUS_TEMPLATES.put(PARALYSIS_TEMPLATE_TAG_STRING, DEFAULT_PARALYSIS);
            STATUS_TEMPLATES.put(POISON_TEMPLATE_TAG_STRING, DEFAULT_POISON);
            STATUS_TEMPLATES.put(FAINT_TEMPLATE_TAG_STRING, DEFAULT_FAINT);

            EGG_GROUP_TEMPLATES.put(AMORPHOUS_TEMPLATE_TAG_STRING, DEFAULT_AMORPHOUS);
            EGG_GROUP_TEMPLATES.put(BUG_TEMPLATE_TAG_STRING, DEFAULT_BUG);
            EGG_GROUP_TEMPLATES.put(DITTO_TEMPLATE_TAG_STRING, DEFAULT_DITTO);
            EGG_GROUP_TEMPLATES.put(DRAGON_TEMPLATE_TAG_STRING, DEFAULT_DRAGON);
            EGG_GROUP_TEMPLATES.put(FAIRY_TEMPLATE_TAG_STRING, DEFAULT_FAIRY);
            EGG_GROUP_TEMPLATES.put(FIELD_TEMPLATE_TAG_STRING, DEFAULT_FIELD);
            EGG_GROUP_TEMPLATES.put(FLYING_TEMPLATE_TAG_STRING, DEFAULT_FLYING);
            EGG_GROUP_TEMPLATES.put(GRASS_TEMPLATE_TAG_STRING, DEFAULT_GRASS);
            EGG_GROUP_TEMPLATES.put(HUMAN_LIKE_TEMPLATE_TAG_STRING, DEFAULT_HUMAN_LIKE);
            EGG_GROUP_TEMPLATES.put(MINERAL_TEMPLATE_TAG_STRING, DEFAULT_MINERAL);
            EGG_GROUP_TEMPLATES.put(MONSTER_TEMPLATE_TAG_STRING, DEFAULT_MONSTER);
            EGG_GROUP_TEMPLATES.put(UNDISCOVERED_TEMPLATE_TAG_STRING, DEFAULT_UNDISCOVERED);
            EGG_GROUP_TEMPLATES.put(WATER1_TEMPLATE_TAG_STRING, DEFAULT_WATER1);
            EGG_GROUP_TEMPLATES.put(WATER2_TEMPLATE_TAG_STRING, DEFAULT_WATER2);
            EGG_GROUP_TEMPLATES.put(WATER3_TEMPLATE_TAG_STRING, DEFAULT_WATER3);

            GENDER_TEMPLATES.put(GENDER_MALE_TEMPLATE_TAG_STRING, DEFAULT_MALE_GENDER);
            GENDER_TEMPLATES.put(GENDER_FEMALE_TEMPLATE_TAG_STRING, DEFAULT_FEMALE_GENDER);
            GENDER_TEMPLATES.put(GENDER_GENDERLESS_TEMPLATE_TAG_STRING, DEFAULT_GENDERLESS_GENDER);

            BOOLEAN_TEMPLATES.put(TRUE_TEMPLATE_TAG_STRING, DEFAULT_TRUE);
            BOOLEAN_TEMPLATES.put(FALSE_TEMPLATE_TAG_STRING, DEFAULT_FALSE);
        }
        catch (IllegalStateException e) 
        {
            ModLogger.error("Failed to load default chat MiniMessage colors: %s".formatted(e.getMessage()), e);
        }
    }

    public static String getCustomColor(String colorName) { return CUSTOM_COLORS.get(colorName); }

    public static HashMap<String, String> getCustomColors() { return CUSTOM_COLORS; }

    protected static void setCustomColor(String colorName, String color)
    { 
        CUSTOM_COLORS.put(colorName, color);
    }

    // Colors Setters and Getters
    public static String getHpColor() { return STAT_COLORS.get(HP_COLOR_TEMPLATE_TAG_STRING); }
    public static String getAtkColor() { return STAT_COLORS.get(ATK_COLOR_TEMPLATE_TAG_STRING); }
    public static String getDefColor() { return STAT_COLORS.get(DEF_COLOR_TEMPLATE_TAG_STRING); }
    public static String getSpaColor() { return STAT_COLORS.get(SPA_COLOR_TEMPLATE_TAG_STRING); }
    public static String getSpdColor() { return STAT_COLORS.get(SPD_COLOR_TEMPLATE_TAG_STRING); }
    public static String getSpeColor() { return STAT_COLORS.get(SPE_COLOR_TEMPLATE_TAG_STRING); }

    protected static void setHpColor(String color) { STAT_COLORS.put(HP_COLOR_TEMPLATE_TAG_STRING, color); }
    protected static void setAtkColor(String color) { STAT_COLORS.put(ATK_COLOR_TEMPLATE_TAG_STRING, color); }
    protected static void setDefColor(String color) { STAT_COLORS.put(DEF_COLOR_TEMPLATE_TAG_STRING, color); }
    protected static void setSpaColor(String color) { STAT_COLORS.put(SPA_COLOR_TEMPLATE_TAG_STRING, color); }
    protected static void setSpdColor(String color) { STAT_COLORS.put(SPD_COLOR_TEMPLATE_TAG_STRING, color); }
    protected static void setSpeColor(String color) { STAT_COLORS.put(SPE_COLOR_TEMPLATE_TAG_STRING, color); }

    // PokeInfo Setters and Getters
    public static String getPokemonInfoTemplate() { return POKE_INFO_TEMPLATES.get(POKEMON_INFO_TEMPLATE_TAG_STRING); }
    public static String getCustomStatusTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_STATUS_TEMPLATE_TAG_STRING); }
    public static String getCustomConditionTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_CONDITION_TEMPLATE_TAG_STRING); }
    public static String getCustomTypesMonotypeTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_TYPES_MONOTYPE_TEMPLATE_TAG_STRING); }
    public static String getCustomTypesDuotypeTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_TYPES_DUOTYPE_TEMPLATE_TAG_STRING); }
    public static String getCustomShininessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SHININESS_TEMPLATE_TAG_STRING); }
    public static String getCustomAlphanessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ALPHANESS_TEMPLATE_TAG_STRING); }
    public static String getCustomFormTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_FORM_TEMPLATE_TAG_STRING); }
    public static String getCustomSpeciesTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPECIES_TEMPLATE_TAG_STRING); }
    public static String getCustomHeldItemTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING); }
    public static String getCustomCosmeticItemTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING); }
    public static String getCustomHiddenAbilityTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING); }
    public static String getCustomNatureRealStatsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING); }
    public static String getCustomNatureEffectiveStatsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING); }
    public static String getCustomMintnessTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_MINTNESS_TEMPLATE_TAG_STRING); }
    public static String getCustomExperienceTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING); }
    public static String getMoveCustomPPTemplate() { return POKE_INFO_TEMPLATES.get(MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING); }
    public static String getCustomMoveTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_MOVE_TEMPLATE_TAG_STRING); }
    public static String getCustomRealIVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveIVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING); }
    public static String getCustomGeneralIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveHpIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomHpIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HP_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveAtkIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomAtkIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveDefIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomDefIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveSpaIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpaIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveSpdIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpdIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEffectiveSpeIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpeIVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING); }
    public static String getCustomEVPercentageTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING); }
    public static String getCustomGeneralEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomHpEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_HP_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomAtkEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomDefEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpaEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpdEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomSpeEVsTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING); }
    public static String getCustomNeuteredTemplate() { return POKE_INFO_TEMPLATES.get(CUSTOM_NEUTERED_TEMPLATE_TAG_STRING); }

    protected static void setPokemonInfoTemplate(String template) { POKE_INFO_TEMPLATES.put(POKEMON_INFO_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomStatusTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_STATUS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomConditionTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_CONDITION_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomTypesMonotypeTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_MONOTYPE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomTypesDuotypeTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_TYPES_DUOTYPE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomShininessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SHININESS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomAlphanessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ALPHANESS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomFormTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_FORM_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpeciesTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPECIES_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomHeldItemTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HELD_ITEM_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomCosmeticItemTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_COSMETIC_ITEM_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomHiddenAbilityTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HIDDEN_ABILITY_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomNatureRealStatsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_REAL_STATS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomNatureCustomStatsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomMintnessTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_MINTNESS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomExperienceTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EXPERIENCE_TEMPLATE_TAG_STRING, template); }
    protected static void setMoveCustomPPTemplate(String template) { POKE_INFO_TEMPLATES.put(MOVE_CUSTOM_PP_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomMoveTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_MOVE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomRealIVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveIVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomGeneralIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveHpIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomHpIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HP_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveAtkIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomAtkIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ATK_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveDefIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomDefIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_DEF_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveSpaIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpaIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPA_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveSpdIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpdIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPD_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEffectiveSpeIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpeIVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPE_IVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomEVPercentageTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_EV_PERCENTAGE_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomGeneralEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_GENERAL_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomHpEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_HP_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomAtkEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_ATK_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomDefEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_DEF_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpaEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPA_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpdEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPD_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomSpeEVsTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_SPE_EVS_TEMPLATE_TAG_STRING, template); }
    protected static void setCustomNeuteredTemplate(String template) { POKE_INFO_TEMPLATES.put(CUSTOM_NEUTERED_TEMPLATE_TAG_STRING, template); }

    public static String getBurnTemplate() { return STATUS_TEMPLATES.get(BURN_TEMPLATE_TAG_STRING); }
    public static String getSleepTemplate() { return STATUS_TEMPLATES.get(SLEEP_TEMPLATE_TAG_STRING); }
    public static String getParalysisTemplate() { return STATUS_TEMPLATES.get(PARALYSIS_TEMPLATE_TAG_STRING); }
    public static String getPoisonTemplate() { return STATUS_TEMPLATES.get(POISON_TEMPLATE_TAG_STRING); }
    public static String getFreezeTemplate() { return STATUS_TEMPLATES.get(FREEZE_TEMPLATE_TAG_STRING); }
    public static String getFaintTemplate() { return STATUS_TEMPLATES.get(FAINT_TEMPLATE_TAG_STRING); }

    protected static void setBurnTemplate(String template) { STATUS_TEMPLATES.put(BURN_TEMPLATE_TAG_STRING, template); }
    protected static void setSleepTemplate(String template) { STATUS_TEMPLATES.put(SLEEP_TEMPLATE_TAG_STRING, template); }
    protected static void setParalysisTemplate(String template) { STATUS_TEMPLATES.put(PARALYSIS_TEMPLATE_TAG_STRING, template); }
    protected static void setPoisonTemplate(String template) { STATUS_TEMPLATES.put(POISON_TEMPLATE_TAG_STRING, template); }
    protected static void setFreezeTemplate(String template) { STATUS_TEMPLATES.put(FREEZE_TEMPLATE_TAG_STRING, template); }
    protected static void setFaintTemplate(String template) { STATUS_TEMPLATES.put(FAINT_TEMPLATE_TAG_STRING, template); }

    public static String getAmorphousTemplate() { return EGG_GROUP_TEMPLATES.get(AMORPHOUS_TEMPLATE_TAG_STRING); }
    public static String getBugTemplate() { return EGG_GROUP_TEMPLATES.get(BUG_TEMPLATE_TAG_STRING); }
    public static String getDittoTemplate() { return EGG_GROUP_TEMPLATES.get(DITTO_TEMPLATE_TAG_STRING); }
    public static String getDragonTemplate() { return EGG_GROUP_TEMPLATES.get(DRAGON_TEMPLATE_TAG_STRING); }
    public static String getFairyTemplate() { return EGG_GROUP_TEMPLATES.get(FAIRY_TEMPLATE_TAG_STRING); }
    public static String getFieldTemplate() { return EGG_GROUP_TEMPLATES.get(FIELD_TEMPLATE_TAG_STRING); }
    public static String getFlyingTemplate() { return EGG_GROUP_TEMPLATES.get(FLYING_TEMPLATE_TAG_STRING); }
    public static String getGrassTemplate() { return EGG_GROUP_TEMPLATES.get(GRASS_TEMPLATE_TAG_STRING); }
    public static String getHumanLikeTemplate() { return EGG_GROUP_TEMPLATES.get(HUMAN_LIKE_TEMPLATE_TAG_STRING); }
    public static String getMineralTemplate() { return EGG_GROUP_TEMPLATES.get(MINERAL_TEMPLATE_TAG_STRING); }
    public static String getMonsterTemplate() { return EGG_GROUP_TEMPLATES.get(MONSTER_TEMPLATE_TAG_STRING); }
    public static String getUndiscoveredTemplate() { return EGG_GROUP_TEMPLATES.get(UNDISCOVERED_TEMPLATE_TAG_STRING); }
    public static String getWater1Template() { return EGG_GROUP_TEMPLATES.get(WATER1_TEMPLATE_TAG_STRING); }
    public static String getWater2Template() { return EGG_GROUP_TEMPLATES.get(WATER2_TEMPLATE_TAG_STRING); }
    public static String getWater3Template() { return EGG_GROUP_TEMPLATES.get(WATER3_TEMPLATE_TAG_STRING); }

    protected static void setAmorphousTemplate(String template) { EGG_GROUP_TEMPLATES.put(AMORPHOUS_TEMPLATE_TAG_STRING, template); }
    protected static void setBugTemplate(String template) { EGG_GROUP_TEMPLATES.put(BUG_TEMPLATE_TAG_STRING, template); }
    protected static void setDittoTemplate(String template) { EGG_GROUP_TEMPLATES.put(DITTO_TEMPLATE_TAG_STRING, template); }
    protected static void setDragonTemplate(String template) { EGG_GROUP_TEMPLATES.put(DRAGON_TEMPLATE_TAG_STRING, template); }
    protected static void setFairyTemplate(String template) { EGG_GROUP_TEMPLATES.put(FAIRY_TEMPLATE_TAG_STRING, template); }
    protected static void setFieldTemplate(String template) { EGG_GROUP_TEMPLATES.put(FIELD_TEMPLATE_TAG_STRING, template); }
    protected static void setFlyingTemplate(String template) { EGG_GROUP_TEMPLATES.put(FLYING_TEMPLATE_TAG_STRING, template); }
    protected static void setGrassTemplate(String template) { EGG_GROUP_TEMPLATES.put(GRASS_TEMPLATE_TAG_STRING, template); }
    protected static void setHumanLikeTemplate(String template) { EGG_GROUP_TEMPLATES.put(HUMAN_LIKE_TEMPLATE_TAG_STRING, template); }
    protected static void setMineralTemplate(String template) { EGG_GROUP_TEMPLATES.put(MINERAL_TEMPLATE_TAG_STRING, template); }
    protected static void setMonsterTemplate(String template) { EGG_GROUP_TEMPLATES.put(MONSTER_TEMPLATE_TAG_STRING, template); }
    protected static void setUndiscoveredTemplate(String template) { EGG_GROUP_TEMPLATES.put(UNDISCOVERED_TEMPLATE_TAG_STRING, template); }
    protected static void setWater1Template(String template) { EGG_GROUP_TEMPLATES.put(WATER1_TEMPLATE_TAG_STRING, template); }
    protected static void setWater2Template(String template) { EGG_GROUP_TEMPLATES.put(WATER2_TEMPLATE_TAG_STRING, template); }
    protected static void setWater3Template(String template) { EGG_GROUP_TEMPLATES.put(WATER3_TEMPLATE_TAG_STRING, template); }

    public static String getTrueTemplate() { return BOOLEAN_TEMPLATES.get(TRUE_TEMPLATE_TAG_STRING); }
    public static String getFalseTemplate() { return BOOLEAN_TEMPLATES.get(FALSE_TEMPLATE_TAG_STRING); }

    protected static void setTrueTemplate(String template) { BOOLEAN_TEMPLATES.put(TRUE_TEMPLATE_TAG_STRING, template); }
    protected static void setFalseTemplate(String template) { BOOLEAN_TEMPLATES.put(FALSE_TEMPLATE_TAG_STRING, template); }

    protected static void setMaleGenderTemplate(String template) { GENDER_TEMPLATES.put(GENDER_MALE_TEMPLATE_TAG_STRING, template); }
    protected static void setFemaleGenderTemplate(String template) { GENDER_TEMPLATES.put(GENDER_FEMALE_TEMPLATE_TAG_STRING, template); }
    protected static void setGenderlessGenderTemplate(String template) { GENDER_TEMPLATES.put(GENDER_GENDERLESS_TEMPLATE_TAG_STRING, template); }

    public static String getMaleGenderTemplate() { return GENDER_TEMPLATES.get(GENDER_MALE_TEMPLATE_TAG_STRING); }
    public static String getFemaleGenderTemplate() { return GENDER_TEMPLATES.get(GENDER_FEMALE_TEMPLATE_TAG_STRING); }
    public static String getGenderlessGenderTemplate() { return GENDER_TEMPLATES.get(GENDER_GENDERLESS_TEMPLATE_TAG_STRING); }
}