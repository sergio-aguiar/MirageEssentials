package com.sergioaguiar.mirageessentials.config.chatparser.colors;

import com.mojang.serialization.DataResult;
import com.sergioaguiar.mirageessentials.util.ModLogger;

import net.minecraft.text.TextColor;

public class ChatColors 
{
    private static final DataResult<TextColor> DEFAULT_COMMAND_PREFIX_COLOR = TextColor.parse("#2facdd");
    private static final DataResult<TextColor> DEFAULT_COMMAND_VALUE_COLOR = TextColor.parse("#d1d8eb");
    private static final DataResult<TextColor> DEFAULT_COMMAND_PLAYER_COLOR = TextColor.parse("#cfe95e");

    private static final DataResult<TextColor> DEFAULT_HOVERABLE_ERROR_BRACKET_COLOR = TextColor.parse("#c00303");

    private static final DataResult<TextColor> DEFAULT_TOOLTIP_LABEL_COLOR = TextColor.parse("#3463e6");
    private static final DataResult<TextColor> DEFAULT_TOOLTIP_VALUE_COLOR = TextColor.parse("#d1d8eb");

    private static final DataResult<TextColor> DEFAULT_TOOLTIP_EGG_NICKNAME_COLOR = TextColor.parse("#0a9120");
    private static final DataResult<TextColor> DEFAULT_TOOLTIP_EGG_HATCH_STAGE_MESSAGE_COLOR = TextColor.parse("#14dd14");

    private static final DataResult<TextColor> DEFAULT_PARTYCHECK_BUTTON_TITLE_COLOR = TextColor.parse("#1f22b8");
    private static final DataResult<TextColor> DEFAULT_PARTYCHECK_OPTION_NAME_COLOR = TextColor.parse("#3463e6");
    private static final DataResult<TextColor> DEFAULT_PARTYCHECK_OPTION_SPLITTER_COLOR = TextColor.parse("#3463e6");
    private static final DataResult<TextColor> DEFAULT_PARTYCHECK_OPTION_DESCRIPTION_COLOR = TextColor.parse("#d1d8eb");
    private static final DataResult<TextColor> DEFAULT_PARTYCHECK_FOOTER_COLOR = TextColor.parse("#d1d8eb");

    private static TextColor commandPrefixColor;
    private static TextColor commandValueColor;
    private static TextColor commandPlayerColor;

    private static TextColor hoverableBracketErrorColor;
    private static TextColor hoverableTextErrorColor;

    private static TextColor tooltipLabelColor;
    private static TextColor tooltipValueColor;

    private static TextColor tooltipEggNicknameColor;
    private static TextColor tooltipEggHatchStageMessageColor;

    private static TextColor partyCheckButtonTitleColor;
    private static TextColor partyCheckOptionNameColor;
    private static TextColor partyCheckOptionSplitterColor;
    private static TextColor partyCheckOptionDescriptionColor;
    private static TextColor partyCheckFooterColor;

    public static void setDefaults()
    {
        try 
        {
            commandPrefixColor = DEFAULT_COMMAND_PREFIX_COLOR.getOrThrow();
            commandValueColor = DEFAULT_COMMAND_VALUE_COLOR.getOrThrow();
            commandPlayerColor = DEFAULT_COMMAND_PLAYER_COLOR.getOrThrow();

            hoverableBracketErrorColor = DEFAULT_HOVERABLE_ERROR_BRACKET_COLOR.getOrThrow();

            tooltipLabelColor = DEFAULT_TOOLTIP_LABEL_COLOR.getOrThrow();
            tooltipValueColor = DEFAULT_TOOLTIP_VALUE_COLOR.getOrThrow();

            tooltipEggNicknameColor = DEFAULT_TOOLTIP_EGG_NICKNAME_COLOR.getOrThrow();
            tooltipEggHatchStageMessageColor = DEFAULT_TOOLTIP_EGG_HATCH_STAGE_MESSAGE_COLOR.getOrThrow();

            partyCheckButtonTitleColor = DEFAULT_PARTYCHECK_BUTTON_TITLE_COLOR.getOrThrow();
            partyCheckOptionNameColor = DEFAULT_PARTYCHECK_OPTION_NAME_COLOR.getOrThrow();
            partyCheckOptionSplitterColor = DEFAULT_PARTYCHECK_OPTION_SPLITTER_COLOR.getOrThrow();
            partyCheckOptionDescriptionColor = DEFAULT_PARTYCHECK_OPTION_DESCRIPTION_COLOR.getOrThrow();
            partyCheckFooterColor = DEFAULT_PARTYCHECK_FOOTER_COLOR.getOrThrow();
        }
        catch (IllegalStateException e) 
        {
            ModLogger.error("Failed to load default chat colors: %s".formatted(e.getMessage()), e);
        }
    }

    public static TextColor getCommandPrefixColor() { return commandPrefixColor; }
    public static TextColor getCommandValueColor() { return commandValueColor; }
    public static TextColor getCommandPlayerColor() { return commandPlayerColor; }
    public static TextColor getHoverableBracketErrorColor() { return hoverableBracketErrorColor; }
    public static TextColor getHoverableTextErrorColor() { return hoverableTextErrorColor; }
    public static TextColor getTooltipLabelColor() { return tooltipLabelColor; }
    public static TextColor getTooltipValueColor() { return tooltipValueColor; }
    public static TextColor getTooltipEggNicknameColor() { return tooltipEggNicknameColor; }
    public static TextColor getTooltipEggHatchStageMessageColor() { return tooltipEggHatchStageMessageColor; }
    public static TextColor getPartyCheckButtonTitleColor() { return partyCheckButtonTitleColor; }
    public static TextColor getPartyCheckOptionNameColor() { return partyCheckOptionNameColor; }
    public static TextColor getPartyCheckOptionSplitterColor() { return partyCheckOptionSplitterColor; }
    public static TextColor getPartyCheckOptionDescriptionColor() { return partyCheckOptionDescriptionColor; }
    public static TextColor getPartyCheckFooterColorColor() { return partyCheckFooterColor; }

    protected static void setCommandPrefixColor(TextColor color) { commandPrefixColor = color; }
    protected static void setCommandValueColor(TextColor color) { commandValueColor = color; }
    protected static void setCommandPlayerColor(TextColor color) { commandPlayerColor = color; }
    protected static void setHoverableBracketErrorColor(TextColor color) { hoverableBracketErrorColor = color; }
    protected static void setHoverableTextErrorColor(TextColor color) { hoverableTextErrorColor = color; }
    protected static void setTooltipLabelColor(TextColor color) { tooltipLabelColor = color; }
    protected static void setTooltipValueColor(TextColor color) { tooltipValueColor = color; }
    protected static void setTooltipEggNicknameColor(TextColor color) { tooltipEggNicknameColor = color; }
    protected static void setTooltipEggHatchStageMessageColor(TextColor color) { tooltipEggHatchStageMessageColor = color; }
    protected static void setPartyCheckButtonTitleColor(TextColor color) { partyCheckButtonTitleColor = color; }
    protected static void setPartyCheckOptionNameColor(TextColor color) { partyCheckOptionNameColor = color; }
    protected static void setPartyCheckOptionSplitterColor(TextColor color) { partyCheckOptionSplitterColor = color; }
    protected static void setPartyCheckOptionDescriptionColor(TextColor color) { partyCheckOptionDescriptionColor = color; }
    protected static void setPartyCheckFooterColorColor(TextColor color) { partyCheckFooterColor = color; }
}
