package com.sergioaguiar.mirageessentials.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import com.cobblemon.mod.common.api.pokemon.feature.SpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.config.antiafk.colors.AntiAFKColors;
import com.sergioaguiar.mirageessentials.config.antiafk.settings.AntiAFKSettings;
import com.sergioaguiar.mirageessentials.config.chatparser.colors.ChatColors;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings;
import com.sergioaguiar.mirageessentials.config.chatparser.strings.ChatStrings;
import com.sergioaguiar.mirageessentials.manager.AntiAFKManager;
import com.sergioaguiar.mirageessentials.manager.AntiAFKManager.KickReason;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class TextUtils 
{
    public static class CustomTextBuilder
    {
        private MutableText textObject;

        public CustomTextBuilder()
        {
            textObject = Text.literal("")
                .setStyle
                (
                    Style.EMPTY
                        .withItalic(false)
                );
        }

        public void append(String textContent, TextColor textColor)
        {
            append(textContent, textColor, false, false);
        }

        public void append(String textContent, TextColor textColor, boolean isBold, boolean isItalic)
        {
            textObject = textObject.append
            (
                Text.literal(textContent)
                    .setStyle
                    (
                        Style.EMPTY
                            .withColor(textColor)
                            .withBold(isBold)
                            .withItalic(isItalic)
                    )
            );
        }

        public void append(Text text)
        {
            textObject = textObject.append(text);
        }

        public void setHoverEvent(HoverEvent hoverEvent)
        {
            textObject = textObject.setStyle(textObject.getStyle().withHoverEvent(hoverEvent));
        }

        public MutableText getText()
        {
            return textObject;
        }
    }

    public static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\[(.*?)\\]");

    public static final String NORMAL_FORM_STRING = "Normal";

    public static String toTitleCase(String input)
    {
        if (input == null || input.isEmpty()) return input;

        String[] words = input.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String word : words)
        {
            if (!word.isEmpty())
            {
                sb.append(Character.toUpperCase(word.charAt(0)))
                .append(word.substring(1))
                .append(" ");
            }
        }

        return sb.toString().trim();
    }

    public static String toTitleCaseWithDelimiters(String input) {
        if (input == null || input.isEmpty()) return input;

        input = input.replace('-', ' ').replace('_', ' ');
        return toTitleCase(input);
    }

    public static String formatStatName(String stat)
    {
        return switch (stat.toUpperCase()) 
        {
            case "HP" -> ChatStrings.getHealthString();
            case "ATTACK" -> ChatStrings.getAttackString();
            case "DEFENSE", "DEFENCE" -> ChatStrings.getDefenseString();
            case "SPECIAL_ATTACK" -> ChatStrings.getSpecialAttackString();
            case "SPECIAL_DEFENSE", "SPECIAL_DEFENCE" -> ChatStrings.getSpecialDefenseString();
            case "SPEED" -> ChatStrings.getSpeedString();
            default -> toTitleCase(stat);
        };
    }

    public static String fromTranslationKey(String key)
    {
        if (key == null || key.isEmpty()) return key;

        String[] parts = key.split("\\.");
        String lastPart = parts[parts.length - 1];

        lastPart = lastPart.replace('_', ' ').replace('-', ' ');
        return toTitleCase(lastPart);
    }

    public static Text coloredStepsLine(Pokemon pokemon)
    {
        List<SpeciesFeature> speciesFeatures = pokemon.getFeatures();

        int hatchPercentage = NeoDaycareUtils.getHatchPercentage(speciesFeatures);
        Integer remainingSteps = NeoDaycareUtils.getRemainingSteps(pokemon);
        Integer totalSteps = NeoDaycareUtils.getTotalSteps(pokemon);

        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (remainingSteps == null || totalSteps == null)
        {
            textBuilder.append
            (
                ChatStrings.getHatchProgressString(),
                ChatColors.getTooltipLabelColor()
            );

            textBuilder.append
            (
                "%d%%".formatted(hatchPercentage),
                ChatColors.getTooltipValueColor()
            );

            return textBuilder.getText();
        }

        textBuilder.append
        (
            ChatStrings.getStepProgressString(),
            ChatColors.getTooltipLabelColor()
        );

        textBuilder.append
        (
            String.valueOf(totalSteps - remainingSteps),
            ChatColors.getTooltipValueColor()
        );

        textBuilder.append
        (
            "/",
            ChatColors.getTooltipValueColor()
        );

        textBuilder.append
        (
            String.valueOf(totalSteps),
            ChatColors.getTooltipValueColor()
        );

        textBuilder.append
        (
            " (",
            ChatColors.getTooltipValueColor()
        );

        textBuilder.append
        (
            "%d%%".formatted(hatchPercentage),
            ChatColors.getTooltipValueColor()
        );

        textBuilder.append
        (
            ")",
            ChatColors.getTooltipValueColor()
        );

        return textBuilder.getText();
    }

    public static Text coloredEggStageLine(Pokemon pokemon)
    {
        List<SpeciesFeature> speciesFeatures = pokemon.getFeatures();

        int hatchPercentage = NeoDaycareUtils.getHatchPercentage(speciesFeatures);

        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            hatchPercentage < 40
                ? ChatStrings.getEggHatchStage0TopString()
                : hatchPercentage < 70
                    ? ChatStrings.getEggHatchStage1TopString()
                    : hatchPercentage < 90
                        ? ChatStrings.getEggHatchStage2TopString()
                        : ChatStrings.getEggHatchStage3TopString(),
            ChatColors.getTooltipEggHatchStageMessageColor()
        );

        textBuilder.append(Text.literal("\n").setStyle(Style.EMPTY));

        textBuilder.append
        (
            hatchPercentage < 40
                ? ChatStrings.getEggHatchStage0BottomString()
                : hatchPercentage < 70
                    ? ChatStrings.getEggHatchStage1BottomString()
                    : hatchPercentage < 90
                        ? ChatStrings.getEggHatchStage2BottomString()
                        : ChatStrings.getEggHatchStage3BottomString(),
            ChatColors.getTooltipEggHatchStageMessageColor()
        );

        return textBuilder.getText();
    }

    public static List<Text> coloredEggStageLineList(Pokemon pokemon)
    {
        List<Text> eggStageLines = new ArrayList<>();

        List<SpeciesFeature> speciesFeatures = pokemon.getFeatures();

        int hatchPercentage = NeoDaycareUtils.getHatchPercentage(speciesFeatures);

        CustomTextBuilder textBuilderTop = new CustomTextBuilder();
        CustomTextBuilder textBuilderBottom = new CustomTextBuilder();

        if (hatchPercentage < 40)
        {
            textBuilderTop.append
            (
                ChatStrings.getEggHatchStage0TopString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );

            textBuilderBottom.append
            (
                ChatStrings.getEggHatchStage0BottomString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );
        }
        else if (hatchPercentage < 70)
        {
            textBuilderTop.append
            (
                ChatStrings.getEggHatchStage1TopString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );

            textBuilderBottom.append
            (
                ChatStrings.getEggHatchStage1BottomString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );
        }
        else if (hatchPercentage < 90)
        {
            textBuilderTop.append
            (
                ChatStrings.getEggHatchStage2TopString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );

            textBuilderBottom.append
            (
                ChatStrings.getEggHatchStage2BottomString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );
        }
        else
        {
            textBuilderTop.append
            (
                ChatStrings.getEggHatchStage3TopString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );

            textBuilderBottom.append
            (
                ChatStrings.getEggHatchStage3BottomString(),
                ChatColors.getTooltipEggHatchStageMessageColor()
            );
        }

        eggStageLines.add(Text.literal("").setStyle(Style.EMPTY));
        eggStageLines.add(textBuilderTop.getText());
        eggStageLines.add(textBuilderBottom.getText());

        return eggStageLines;
    }

    public static MutableText gradientBetweenRGB(String text, int rgb1, int rgb2)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();
        int length = text.length();

         if (length == 0) return textBuilder.getText();

        for (int i = 0; i < length; i++) 
        {
            float t = (float) i / Math.max(1, length - 1);

            int r = (int) ((1 - t) * ((rgb1 >> 16) & 0xFF) + t * ((rgb2 >> 16) & 0xFF));
            int g = (int) ((1 - t) * ((rgb1 >> 8) & 0xFF) + t * ((rgb2 >> 8) & 0xFF));
            int b = (int) ((1 - t) * (rgb1 & 0xFF) + t * (rgb2 & 0xFF));

            int rgb = (r << 16) | (g << 8) | b;
            TextColor blended = TextColor.fromRgb(rgb);

            textBuilder.append
            (
                String.valueOf(text.charAt(i)),
                blended
            );
        }

        return textBuilder.getText();
    }

    public static MutableText hoverableText(String speciesName, Text tooltip, boolean isShiny, boolean isLegendary, boolean isMythical, boolean isUltraBeast, boolean isCustom)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (ChatSettings.shouldUsePokemonCategoryHoverables())
        {
            if (isShiny && ChatSettings.shouldUseShinyGradientHoverables())
            {
                textBuilder.append
                (
                    gradientBetweenRGB
                    (
                        "[%s%s]".formatted(isShiny ? ChatStrings.getShinyIconString() : "", speciesName), 
                        ChatColors.getHoverableTextShinyColor().getRgb(),
                        isCustom
                            ? ChatColors.getHoverableBracketCustomColor().getRgb()
                            : isLegendary 
                                ? ChatColors.getHoverableBracketLegendaryColor().getRgb()
                                : isMythical 
                                    ? ChatColors.getHoverableBracketMythicalColor().getRgb()
                                    : isUltraBeast
                                        ? ChatColors.getHoverableBracketUltraBeastColor().getRgb()
                                        : ChatColors.getHoverableBracketRegularColor().getRgb()
                    )
                );
            }
            else
            {
                textBuilder.append
                (
                    "[",
                    isShiny 
                        ? ChatColors.getHoverableBracketShinyColor() 
                        : isCustom
                            ? ChatColors.getHoverableBracketCustomColor()
                            : isLegendary 
                                ? ChatColors.getHoverableBracketLegendaryColor()
                                : isMythical 
                                    ? ChatColors.getHoverableBracketMythicalColor()
                                    : isUltraBeast
                                        ? ChatColors.getHoverableBracketUltraBeastColor()
                                        : ChatColors.getHoverableBracketRegularColor()
                );

                textBuilder.append
                (
                    "%s%s".formatted(isShiny ? ChatStrings.getShinyIconString() : "", speciesName),
                    isShiny 
                        ? ChatColors.getHoverableTextShinyColor() 
                        : isCustom
                            ? ChatColors.getHoverableTextCustomColor()
                            : isLegendary 
                                ? ChatColors.getHoverableTextLegendaryColor()
                                : isMythical 
                                    ? ChatColors.getHoverableTextMythicalColor()
                                    : isUltraBeast
                                        ? ChatColors.getHoverableTextUltraBeastColor()
                                        : ChatColors.getHoverableTextRegularColor()
                );

                textBuilder.append
                (
                    "]",
                    isShiny 
                        ? ChatColors.getHoverableBracketShinyColor() 
                        : isCustom
                            ? ChatColors.getHoverableBracketCustomColor()
                            : isLegendary 
                                ? ChatColors.getHoverableBracketLegendaryColor()
                                : isMythical 
                                    ? ChatColors.getHoverableBracketMythicalColor()
                                    : isUltraBeast
                                        ? ChatColors.getHoverableBracketUltraBeastColor()
                                        : ChatColors.getHoverableBracketRegularColor()
                );
            }
        }
        else
        {
            if (isShiny && ChatSettings.shouldUseShinyGradientHoverables())
            {
                textBuilder.append
                (
                    gradientBetweenRGB
                    (
                        "[%s%s]".formatted(isShiny ? ChatStrings.getShinyIconString() : "", speciesName), 
                        ChatColors.getHoverableTextShinyColor().getRgb(),
                        ChatColors.getHoverableTextColor().getRgb()
                    )
                );
            }
            else
            {
                textBuilder.append
                (
                    "[",
                    isShiny ? ChatColors.getHoverableBracketShinyColor() : ChatColors.getHoverableBracketColor()
                );

                textBuilder.append
                (
                    "%s%s".formatted(isShiny ? ChatStrings.getShinyIconString() : "", speciesName),
                    isShiny ? ChatColors.getHoverableTextShinyColor() : ChatColors.getHoverableTextColor()
                );

                textBuilder.append
                (
                    "]",
                    isShiny ? ChatColors.getHoverableBracketShinyColor() : ChatColors.getHoverableBracketColor()
                );
            }
        }

        textBuilder.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip));

        return textBuilder.getText();
    }

    public static MutableText errorPlaceholder(String errorMessage)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            "[",
            ChatColors.getHoverableBracketErrorColor()
        );

        textBuilder.append
        (
            errorMessage,
            ChatColors.getHoverableTextErrorColor()
        );

        textBuilder.append
        (
            "]",
            ChatColors.getHoverableBracketErrorColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerAFKMessage(String playerName)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCheckerMessagePrefix())
        {
            textBuilder.append
            (
                "AFKChecker » ",
                AntiAFKColors.getAFKCheckerPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCheckerTextColor()
            );
        }

        textBuilder.append
        (
            playerName,
            AntiAFKColors.getAFKCheckerPlayerColor()
        );

        textBuilder.append
        (
            " is now AFK.",
            AntiAFKColors.getAFKCheckerTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerNotAFKMessage(ServerPlayerEntity player, String timeAway)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCheckerMessagePrefix())
        {
            textBuilder.append
            (
                "AFKChecker » ",
                AntiAFKColors.getAFKCheckerPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCheckerTextColor()
            );
        }

        textBuilder.append
        (
            player.getDisplayName().getString(),
            AntiAFKColors.getAFKCheckerPlayerColor()
        );

        textBuilder.append
        (
            " is no longer AFK.",
            AntiAFKColors.getAFKCheckerTextColor()
        );

        if (AntiAFKSettings.shouldHideAFKTimesWhenBypassingKicks() && LuckPermsUtils.hasPermission(player, "mirageantiafk.bypass.kick"))
        {
            return textBuilder.getText();
        }

        textBuilder.append
        (
            " (Gone for ",
            AntiAFKColors.getAFKCheckerGoneColor()
        );

        textBuilder.append
        (
            String.format("%s", timeAway),
            AntiAFKColors.getAFKCheckerTimeColor()
        );

        textBuilder.append
        (
            ")",
            AntiAFKColors.getAFKCheckerGoneColor()
        );

        return textBuilder.getText();
    }

    public static MutableText provedActivityMessage()
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCaptchaMessagePrefix())
        {
            textBuilder.append
            (
                "AFKaptcha » ",
                AntiAFKColors.getAFKCaptchaPrefixColor()
            );
        }

        textBuilder.append
        (
            "Thank you for proving you are active!",
            AntiAFKColors.getAFKCaptchaTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerKickMessage(KickReason kickReason)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            "AFKChecker\n",
            AntiAFKColors.getKickTitleColor()
        );

        textBuilder.append
        (
            kickReason.getPlayerKickMessage(),
            AntiAFKColors.getKickDescriptionColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerChatKickMessage(String playerName, String kickMessage)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCheckerMessagePrefix())
        {
            textBuilder.append
            (
                "AFKChecker » ",
                AntiAFKColors.getAFKCheckerPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCheckerTextColor()
            );
        }

        textBuilder.append
        (
            playerName,
            AntiAFKColors.getAFKCheckerPlayerColor()
        );

        textBuilder.append
        (
            " has been kicked.\n",
            AntiAFKColors.getAFKCheckerTextColor()
        );

        textBuilder.append
        (
            "Reason: ",
            AntiAFKColors.getKickReasonTitleColor()
        );

        textBuilder.append
        (
            kickMessage,
            AntiAFKColors.getKickReasonDescriptionColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerPermKickMessage(ServerPlayerEntity player, int currentTicks)
    {
        UUID playerUUID = player.getUuid();
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            "====================\n",
            AntiAFKColors.getKickInfoBorderColor()
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Is Marked as AFK:",
                " %b ".formatted(AntiAFKManager.isPlayerAFK(player)),
                "\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Is in a Vehicle:",
                " %b ".formatted(player.hasVehicle()),
                "\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Is in a fluid (water/lava):",
                " %b ".formatted(player.isTouchingWater() || player.isInFluid()),
                "\n\n"
            )
        );

        textBuilder.append
        (
            "Last action info:\n",
            AntiAFKColors.getKickInfoTitleColor()
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Position Movement:",
                " %s ".formatted(secondsToReadableTimeString((int) AntiAFKManager.getSecondsSinceLastPositionMovement(playerUUID, currentTicks))),
                "seconds ago\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Camera Movement:",
                " %s ".formatted(secondsToReadableTimeString((int) AntiAFKManager.getSecondsSinceLastCameraMovement(playerUUID, currentTicks))),
                "seconds ago\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Chat Message:",
                " %s ".formatted(secondsToReadableTimeString((int) AntiAFKManager.getSecondsSinceLastMessageSent(playerUUID, currentTicks))),
                "seconds ago\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "CAPTCHA Answer:",
                " %s ".formatted(secondsToReadableTimeString((int) AntiAFKManager.getSecondsSinceLastCaptchaAnswerSent(playerUUID, currentTicks))),
                "seconds ago\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "CAPTCHA Ignored:",
                " %d ".formatted(AntiAFKManager.getIgnoredCaptchas(playerUUID)),
                "times\n"
            )
        );

        textBuilder.append
        (
            playerPermKickMessageLine
            (
                "Suspicious CAPTCHA:",
                " %d ".formatted(AntiAFKManager.getPlayerSuspiciousActionCount(playerUUID)),
                "times"
            )
        );

        textBuilder.append
        (
            "\n====================\n",
            AntiAFKColors.getKickInfoBorderColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerIgnoredForcedCaptcha(String playerName)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCaptchaMessagePrefix())
        {
            textBuilder.append
            (
                "AFKaptcha » ",
                AntiAFKColors.getAFKCaptchaPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCaptchaTextColor()
            );
        }

        textBuilder.append
        (
            playerName,
            AntiAFKColors.getAFKCaptchaPlayerColor()
        );

        textBuilder.append
        (
            " ignored the CAPTCHA you forced on them.",
            AntiAFKColors.getAFKCaptchaTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerAnsweredForcedCaptcha(String playerName)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCaptchaMessagePrefix())
        {
            textBuilder.append
            (
                "AFKaptcha » ",
                AntiAFKColors.getAFKCaptchaPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCaptchaTextColor()
            );
        }

        textBuilder.append
        (
            playerName,
            AntiAFKColors.getAFKCaptchaPlayerColor()
        );

        textBuilder.append
        (
            " answered the CAPTCHA you forced on them.",
            AntiAFKColors.getAFKCaptchaTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerPerformedSuspiciousCaptchaAction(String playerName, String susAction)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        if (!AntiAFKSettings.shouldHideAFKCaptchaMessagePrefix())
        {
            textBuilder.append
            (
                "AFKaptcha » ",
                AntiAFKColors.getAFKCaptchaPrefixColor()
            );
        }

        if (!AntiAFKSettings.shouldHidePlayerWordStart())
        {
            textBuilder.append
            (
                "Player ",
                AntiAFKColors.getAFKCaptchaTextColor()
            );
        }

        textBuilder.append
        (
            playerName,
            AntiAFKColors.getAFKCaptchaPlayerColor()
        );

        textBuilder.append
        (
            " performed a suspicious CAPTCHA answer: %s.".formatted(susAction),
            AntiAFKColors.getAFKCaptchaTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText playerPermKickMessageLine(String title, String value, String units)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            title,
            AntiAFKColors.getKickInfoTitleColor()
        );

        textBuilder.append
        (
            value,
            AntiAFKColors.getKickInfoTimeColor()
        );

        textBuilder.append
        (
            units,
            AntiAFKColors.getKickInfoTextColor()
        );

        return textBuilder.getText();
    }

    public static MutableText infoCommandMessage()
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            "Info » ",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "%s ".formatted(MirageEssentials.MOD_NAME),
            ChatColors.getCommandValueColor()
        );

        textBuilder.append
        (
            "v%s".formatted(FabricLoader.getInstance().getModContainer(MirageEssentials.MOD_ID).orElseThrow().getMetadata().getVersion().getFriendlyString()),
            ChatColors.getCommandPlayerColor()
        );

        textBuilder.append
        (
            " is developed by ",
            ChatColors.getCommandValueColor()
        );

        textBuilder.append
        (
            "pioavenger",
            ChatColors.getCommandPlayerColor()
        );

        return textBuilder.getText();
    }

    public static MutableText afkListCommand(MinecraftServer server)
    {
        CustomTextBuilder textBuilder = new CustomTextBuilder();

        textBuilder.append
        (
            "AFKList » ",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "List of currently-AFK players:\n",
            ChatColors.getCommandValueColor()
        );

        List<MutableText> afkPlayerNames = AntiAFKManager.getAFKPlayerNames(server);

        if (afkPlayerNames.isEmpty())
        {
            textBuilder.append
            (
                "None...",
                ChatColors.getCommandValueColor()
            );

            return textBuilder.getText();
        }

        MutableText firstPlayerName = afkPlayerNames.get(0);
        textBuilder.append(firstPlayerName);

        if (afkPlayerNames.size() >= 2)
        {
            for (int i = 1; i < afkPlayerNames.size(); i++)
            {
                MutableText iPlayerName = afkPlayerNames.get(i);

                textBuilder.append
                (
                    ", ",
                    ChatColors.getCommandValueColor()
                );

                textBuilder.append(iPlayerName);
            }
        }

        return textBuilder.getText();
    }

    public static String secondsToReadableTimeString(int totalSeconds)
    {
        Duration duration = Duration.ofSeconds(totalSeconds);
    
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        List<String> resultPartList = new ArrayList<>();

        if (days > 0) resultPartList.add(days + " Day" + (days > 1 ? "s" : ""));
        if (hours > 0) resultPartList.add(hours + " Hour" + (hours > 1 ? "s" : ""));
        if (minutes > 0) resultPartList.add(minutes + " Minute" + (minutes > 1 ? "s" : ""));
        if (seconds > 0 || resultPartList.isEmpty()) resultPartList.add(seconds + " Second" + (seconds != 1 ? "s" : ""));

        return String.join(", ", resultPartList);
    }

    public static Text getItemText(ItemStack stack)
    {
        TextUtils.CustomTextBuilder textBuilder = new TextUtils.CustomTextBuilder();

        textBuilder.append
        (
            "[",
            ChatColors.getHoverableItemBracketColor()
        );

        textBuilder.append
        (
            getItemName(stack),
            ChatColors.getHoverableItemTextColor()
        );

        textBuilder.append
        (
            "]",
            ChatColors.getHoverableItemBracketColor()
        );

        ItemStack changedStack = fixItemStackName(stack.copy());

        textBuilder.setHoverEvent
        (
            new HoverEvent
            (
                HoverEvent.Action.SHOW_ITEM,
                new HoverEvent.ItemStackContent(ChatSettings.shouldShowOriginalItemNames() ? changedStack : stack)
            )
        );

        return textBuilder.getText();
    }

    public static String getItemName(ItemStack stack)
    {
        if (stack == null || stack.isEmpty()) return ChatStrings.getEmptyHeldItemString();

        Text itemName = stack.get(DataComponentTypes.ITEM_NAME);

        return itemName != null && itemName.getString().length() > 0
            ? itemName.getString()
            : ChatSettings.shouldShowOriginalItemNames()
                ? stack.getItem().getName().getString()
                : stack.getName().getString();
    }

    public static ItemStack fixItemStackName(ItemStack stack)
    {
        Text itemName = stack.get(DataComponentTypes.CUSTOM_NAME);

        if (itemName == null || itemName.getString().length() == 0) return stack;

        stack.remove(DataComponentTypes.CUSTOM_NAME);
        return stack;
    }

    public static MutableText getHelpText()
    {
        CustomTextBuilder textBuilder = new TextUtils.CustomTextBuilder();

        textBuilder.append
        (
            "[",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "Help",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "]",
            ChatColors.getCommandPrefixColor()
        );

        CustomTextBuilder tooltip = new TextUtils.CustomTextBuilder();

        tooltip.append
        (
            "Help Parsing:",
            ChatColors.getCommandPrefixColor()
        );

        tooltip.append
        (
            "\n\n[ help ]",
            ChatColors.getCommandPlayerColor()
        );

        tooltip.append
        (
            "\nShout the list of help parsers.",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\n\n[ help : shout ] / [ help : shouts ] / [ help : shouting ]",
            ChatColors.getCommandPlayerColor()
        );

        tooltip.append
        (
            "\nShout the list of shout parsers.",
            ChatColors.getCommandValueColor()
        );

        textBuilder.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip.getText()));

        return textBuilder.getText();
    }

    public static MutableText getHelpShoutText()
    {
        CustomTextBuilder textBuilder = new TextUtils.CustomTextBuilder();

        textBuilder.append
        (
            "[",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "Help : Shouting",
            ChatColors.getCommandPrefixColor()
        );

        textBuilder.append
        (
            "]",
            ChatColors.getCommandPrefixColor()
        );

        CustomTextBuilder tooltip = new TextUtils.CustomTextBuilder();

        tooltip.append
        (
            "Chat Parsing:",
            ChatColors.getCommandPrefixColor()
        );

        tooltip.append
        (
            "\n\n[ party : <slot> : [closed] ] / [ poke : <slot> : [closed] ]",
            ChatColors.getCommandPlayerColor()
        );

        tooltip.append
        (
            "\nShout Pokémon at party slot <slot>, optionally using the [closed] sheet option.",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\nExamples: ",
            ChatColors.getCommandPrefixColor()
        );

        tooltip.append
        (
            "[party:1] / [party:6:closed] / [poke:5:closed]",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\n\n[ pc : <box> : <slot> : [closed] ]",
            ChatColors.getCommandPlayerColor()
        );

        tooltip.append
        (
            "\nShout Pokémon at pc box <box> and slot <slot>, optionally using the [closed] sheet option.",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\nExamples: ",
            ChatColors.getCommandPrefixColor()
        );

        tooltip.append
        (
            "[pc:1:1] / [pc:2:15:closed]",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\n\n[ item : [slot] ]",
            ChatColors.getCommandPlayerColor()
        );

        tooltip.append
        (
            "\nShout Item in your main hand, optionally specifying a different hotbar slot with <slot>.",
            ChatColors.getCommandValueColor()
        );

        tooltip.append
        (
            "\nExamples: ",
            ChatColors.getCommandPrefixColor()
        );

        tooltip.append
        (
            "[item] / [item:9]",
            ChatColors.getCommandValueColor()
        );

        textBuilder.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip.getText()));

        return textBuilder.getText();
    }
}
