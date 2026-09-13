package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

public class ChatMiniMessageCommandsConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "minimessage", "chat_minimessage_commands.toml");

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
                ModLogger.info("Generated default chat_minimessage_commands.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_minimessage_commands.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

        ModLogger.info("MiniMessage configurations successfully loaded from chat_minimessage_commands.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_minimessage_commands.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat MiniMessage Configuration - Commands
            # This is an alternative to chat_strings.toml.
            # Setting useMiniMessage in chat_settings.toml must be enabled.

            [General]
            # Here you can configure general use templates.
            CommandReplyPrefix = \"<gradient:{CommandPrefixGradientLeftColor}:{CommandPrefixGradientRightColor}>Mie » </gradient>\"

            [ChatReplies]
            # Here you can configure the chat messages displayed on various actions/command usage.
            # You can use the following replacement templates:
            # {CommandReplyPrefix} - The command chat-reply prefix, using the configured template.
            # {PlayerName} - The player's username.
            # {PlayerDisplayName} - The player's custom display name.
            
            """.formatted(MirageEssentials.MOD_NAME);
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
