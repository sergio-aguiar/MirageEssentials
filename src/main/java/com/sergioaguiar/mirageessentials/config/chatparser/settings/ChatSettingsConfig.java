package com.sergioaguiar.mirageessentials.config.chatparser.settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.config.chatparser.settings.ChatSettings.PartyCheckLayout;
import com.sergioaguiar.mirageessentials.util.ModLogger;

public class ChatSettingsConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "chat_settings.toml");

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
                ModLogger.info("Generated default chat_settings.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_settings.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

            if (config.contains("General.ParseNonPlayerMessages"))
            {
                boolean enabled = config.getOrElse("General.ParseNonPlayerMessages", ChatSettings.DEFAULT_PARSE_NON_PLAYER_MESSAGES);
                ChatSettings.setParseNonPlayerMessages(enabled);
            }

            if (config.contains("General.ShowOriginalItemNames"))
            {
                boolean enabled = config.getOrElse("General.ShowOriginalItemNames", ChatSettings.DEFAULT_SHOW_ORIGINAL_ITEM_NAMES);
                ChatSettings.setShowOriginalItemNames(enabled);
            }

            if (config.contains("EggTooltip.ShowEggNickname"))
            {
                boolean enabled = config.getOrElse("EggTooltip.ShowEggNickname", ChatSettings.DEFAULT_SHOW_EGG_NICKNAME);
                ChatSettings.setShowEggNickname(enabled);
            }

            if (config.contains("EggTooltip.ShowEggHatchStepProgress"))
            {
                boolean enabled = config.getOrElse("EggTooltip.ShowEggHatchStepProgress", ChatSettings.DEFAULT_SHOW_EGG_HATCH_STEP_PROGRESS);
                ChatSettings.setshowEggHatchStepProgress(enabled);
            }

            if (config.contains("EggTooltip.ShowEggHatchStageMessages"))
            {
                boolean enabled = config.getOrElse("EggTooltip.ShowEggHatchStageMessages", ChatSettings.DEFAULT_SHOW_EGG_STAGE_MESSAGE);
                ChatSettings.setShowEggStageMessage(enabled);
            }

            if (config.contains("GUI.PartyCheckLayout"))
            {
                String string = config.get("GUI.PartyCheckLayout");
                if (string != null && !string.isEmpty())
                    ChatSettings.setPartyCheckLayout(PartyCheckLayout.getLayoutByName(string));
            }

            if (config.contains("GUI.ShowPartyCheckGUIFrameBlocks"))
            {
                boolean enabled = config.getOrElse("GUI.ShowPartyCheckGUIFrameBlocks", ChatSettings.DEFAULT_SHOW_PARTYCHECK_GUI_FRAME_BLOCKS);
                ChatSettings.setShowPartyCheckGUIFrameBlocks(enabled);
            }

            if (config.contains("GUI.ShowPartyCheckPlayerHeads"))
            {
                boolean enabled = config.getOrElse("GUI.ShowPartyCheckPlayerHeads", ChatSettings.DEFAULT_SHOW_PARTYCHECK_PLAYER_HEADS);
                ChatSettings.setShowPartyCheckPlayerHeads(enabled);
            }

            ModLogger.info("Setting configurations successfully loaded from chat_settings.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_settings.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat Settings Configuration

            [General]
            # Whether to parse non-player (e.g. server) messages or not (true/false)
            ParseNonPlayerMessages = false
            # Whether item lores should show the original item name (including NBT-changed), rather than changed ones (using an anvil) (true/false)
            ShowOriginalItemNames = false
            
            [EggTooltip]
            # Whether to show an egg's nickname in hover text (true/false)
            ShowEggNickname = true
            # Whether to show an egg's hatch progress in hover text (true/false)
            ShowEggHatchStepProgress = true
            # Whether to show an egg's hatch stage progress messages in hover text (true/false)
            ShowEggHatchStageMessages = true

            [GUI]
            # The name of the layour to use in the GUI for PartyCheck ("default" or "mirage")
            PartyCheckLayout = "default"
            # Whether the PartyCheck command's GUI should display the surrounding block frame (true/false)
            ShowPartyCheckGUIFrameBlocks = true
            # Whether to show player heads in layouts that have one, such as "mirage" (true, false)
            ShowPartyCheckPlayerHeads = true
            """.formatted(MirageEssentials.MOD_NAME);
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
