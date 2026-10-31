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

            if (config.contains(ChatMiniMessage.TOML_COMMAND_GENERAL_SECTION_STRING))
            {
                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_GENERAL_SECTION_STRING, ChatMiniMessage.COMMAND_REPLY_PREFIX_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_GENERAL_SECTION_STRING, ChatMiniMessage.COMMAND_REPLY_PREFIX_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setCommandReplyPrefixTemplate(template);
                }
            }

            if (config.contains(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING))
            {
                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_BOX_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_BOX_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setInvalidBoxTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setInvalidSlotTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_PLAYER_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.INVALID_PLAYER_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setInvalidPlayerTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.EMPTY_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING, ChatMiniMessage.EMPTY_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setEmptySlotTemplate(template);
                }
            }

            if (config.contains(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING))
            {
                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ERROR_HOVERABLE_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ERROR_HOVERABLE_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setErrorHoverableTemplate(template);
                }
            }

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

            [General]
            # Here you can configure general use templates.
            CommandReplyPrefix = \"<gradient:<commandprefixgradientleftcolor>:<commandprefixgradientrightcolor>>Mie » </gradient>\"

            [Error]
            InvalidBox = \"Invalid Box\"
            InvalidSlot = \"Invalid Slot\"
            InvalidPlayer = \"Invalid Player\"
            EmptySlot = \"Empty Slot\"

            [ChatReplies]
            # Here you can configure the chat messages displayed on various actions/command usage.

            # Used for in-chat error hoverables.
            # <errormessage> - The error message to show, using templates configured in [Error]
            ErrorHoverable = \"<color:<errorbracketcolor>>[</color><color:<errorcolor>><errormessage></color><color:<errorbracketcolor>>]</color>\"

            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <playername> - The player's username.
            # <playerdisplayname> - The player's custom display name.
            
            """.formatted
            (
                MirageEssentials.MOD_NAME,
                ChatMiniMessage.TOML_COMMAND_GENERAL_SECTION_STRING,
                ChatMiniMessage.COMMAND_REPLY_PREFIX_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_COMMAND_REPLY_PREFIX,
                ChatMiniMessage.TOML_COMMAND_ERROR_SECTION_STRING,
                ChatMiniMessage.INVALID_BOX_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_INVALID_BOX,
                ChatMiniMessage.INVALID_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_INVALID_SLOT,
                ChatMiniMessage.INVALID_PLAYER_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_INVALID_PLAYER,
                ChatMiniMessage.EMPTY_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_EMPTY_SLOT,
                ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING,
                ChatMiniMessage.ERROR_HOVERABLE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ERROR_HOVERABLE
            );
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
