package com.sergioaguiar.mirageessentials.config.chatparser.colors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

import net.minecraft.text.TextColor;

public class ChatColorsConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "chat_colors.toml");

    public static void load()
    {
        File file = CONFIG_PATH.toFile();
        file.getParentFile().mkdirs();

        if (!file.exists()) 
        {
            try
            {
                Files.createDirectories(file.getParentFile().toPath());
                createDefaultConfig(file);
                ModLogger.info("Generated default chat_colors.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_colors.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

            if (config.contains("Command.PrefixColor"))
            {
                String color = config.get("Command.PrefixColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setCommandPrefixColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Command.ValueColor"))
            {
                String color = config.get("Command.ValueColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setCommandValueColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Command.PlayerColor"))
            {
                String color = config.get("Command.PlayerColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setCommandPlayerColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Hoverable.BracketErrorColor"))
            {
                String color = config.get("Hoverable.BracketErrorColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setHoverableBracketErrorColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Hoverable.TextErrorColor"))
            {
                String color = config.get("Hoverable.TextErrorColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setHoverableTextErrorColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Tooltip.LabelColor"))
            {
                String color = config.get("Tooltip.LabelColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setTooltipLabelColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Tooltip.ValueColor"))
            {
                String color = config.get("Tooltip.ValueColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setTooltipValueColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Tooltip.EggNicknameColor"))
            {
                String color = config.get("Tooltip.EggNicknameColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setTooltipEggNicknameColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("Tooltip.EggHatchStageMessageColor"))
            {
                String color = config.get("Tooltip.EggHatchStageMessageColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setTooltipEggHatchStageMessageColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("GUI.PartyCheckButtonTitleColor"))
            {
                String color = config.get("GUI.PartyCheckButtonTitleColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setPartyCheckButtonTitleColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("GUI.PartyCheckOptionNameColor"))
            {
                String color = config.get("GUI.PartyCheckOptionNameColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setPartyCheckOptionNameColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("GUI.PartyCheckOptionSplitterColor"))
            {
                String color = config.get("GUI.PartyCheckOptionSplitterColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setPartyCheckOptionSplitterColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("GUI.PartyCheckOptionDescriptionColor"))
            {
                String color = config.get("GUI.PartyCheckOptionDescriptionColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setPartyCheckOptionDescriptionColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("GUI.PartyCheckFooterColor"))
            {
                String color = config.get("GUI.PartyCheckFooterColor");
                if (color != null && !color.isEmpty())
                    ChatColors.setPartyCheckFooterColorColor(TextColor.parse(color).getOrThrow());
            }

            ModLogger.info("Color configurations successfully loaded from chat_colors.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_colors.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat Color Configuration
            # You can use standard hex color codes like "#aabbcc"

            [Command]
            PrefixColor = "#2facdd"
            ValueColor = "#d1d8eb"
            PlayerColor = '#cfe95e'

            [Hoverable]
            BracketErrorColor = "#c00303"
            TextErrorColor = "#e21e1e"

            [Tooltip]
            LabelColor = "#3463e6"
            ValueColor = "#d1d8eb"

            [GUI]
            PartyCheckButtonTitleColor = "#1f22b8"
            PartyCheckOptionNameColor = "#3463e6"
            PartyCheckOptionSplitterColor = "#3463e6"
            PartyCheckOptionDescriptionColor = "#d1d8eb"
            PartyCheckFooterColor = "#d1d8eb"
            """.formatted(MirageEssentials.MOD_NAME);
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
