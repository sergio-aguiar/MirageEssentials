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

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_EMPTY_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_EMPTY_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setDebugShoutEmptySlotTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_SUCCESS_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_SUCCESS_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setDebugShoutSuccessTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ASPECT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ASPECT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setAspectTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.SPECIES_FEATURE_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.SPECIES_FEATURE_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setSpeciesFeatureTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PERSISTENT_DATA_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PERSISTENT_DATA_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPersistentDataTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_INVALID_BOX_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_INVALID_BOX_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPcShoutInvalidBoxTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_EMPTY_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_EMPTY_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPcShoutEmptySlotTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_SUCCESS_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_SUCCESS_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPcShoutSuccessTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_EMPTY_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_EMPTY_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPartyShoutEmptySlotTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_SUCCESS_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_SUCCESS_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPartyShoutSuccessTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_EMPTY_PARTY_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_EMPTY_PARTY_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPartyShoutAllEmptyPartyTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_SUCCESS_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_SUCCESS_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setPartyShoutAllSuccessTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_EMPTY_SLOT_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_EMPTY_SLOT_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setItemShoutEmptySlotTemplate(template);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_SUCCESS_TEMPLATE_STRING)))
                {
                    String template = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_CHAT_REPLIES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_SUCCESS_TEMPLATE_STRING));
                    if (template != null && !template.isEmpty())
                        ChatMiniMessage.setItemShoutSuccessTemplate(template);
                }
            }

            if (config.contains(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING))
            {
                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.DEBUG_SHOUT_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setDebugShoutCommandName(name);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PC_SHOUT_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setPCShoutCommandName(name);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setPartyShoutCommandName(name);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_SHOUT_ALL_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setPartyShoutAllCommandName(name);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_CHECK_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.PARTY_CHECK_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setPartyCheckCommandName(name);
                }

                if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_TEMPLATE_STRING)))
                {
                    String name = config.get("%s.%s".formatted(ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING, ChatMiniMessage.ITEM_SHOUT_TEMPLATE_STRING));
                    if (name != null && !name.isEmpty())
                        ChatMiniMessage.setItemShoutCommandName(name);
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

            [%s]
            # Here you can configure general use templates.
            %s = \"%s\"

            [%s]
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"

            [%s]
            # Here you can configure the chat messages displayed on various actions/command usage.

            # Used for in-chat error hoverables.
            # <errormessage> - The error message to show, using templates configured in [Error]
            %s = \"%s\"

            # Used by the /debugshout command, when a player selects an empty pokémon slot to shout.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            %s = \"%s\"

            # Used by the /debugshout command, to display a pokémon's debug information (command success).
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <aspecttotal> - The number of aspects the pokémon has.
            # <aspects> - The pokémon's aspect list, each line using the configured <aspect> template.
            # <forcedaspecttotal> - The number of forced aspects the pokémon has.
            # <forcedaspects> - The pokémon's forced aspect list, each line using the configured <aspect> template.
            # <speciesfeaturetotal> - The number of species features the pokémon has.
            # <speciesfeatures> - The pokémon's species feature list, each line using the configured <speciesfeature> template.
            # <persistentdatatotal> - The number of persistent data entries the pokémon has.
            # <persistentdatas> - The pokémon's persistent data list, each line using the configured <persistentdata> template.
            %s = \"\"\"
            %s\"\"\"

            # Used by <aspects> and <forcedaspects> to list pokémon aspects (each line uses this template once).
            # You can use the following replacement templates:
            # <aspectname> - The aspect's name.
            %s = \"%s\"

            # Used by <speciesfeatures> to list pokémon species features (each line uses this template once).
            # You can use the following replacement templates:
            # <speciesfeaturename> - The species feature's name.
            # <speciesfeaturevalue> - The species feature's value.
            %s = \"%s\"

            # Used by <persistentdatas> to list pokémon species features (each line uses this template once).
            # You can use the following replacement templates:
            # <persistentdataname> - The persistent data's name.
            # <persistentdatavalue> - The persistent data's value.
            %s = \"%s\"

            # Used by the /pcshout command, when a player selects a box number above the maximum.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <boxcount> - The total number of pc boxes.
            %s = \"%s\"

            # Used by the /pcshout command, when a player selects an empty pc slot.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            %s = \"%s\"

            # Used by the /pcshout command, when a player successfully shouts a pokémon.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <playername> - The player's username.
            # <playerdisplayname> - The player's custom display name.
            # <playerdisplaynamestyled> - The player's custom display name, including styles.
            # <hoverable> - The hoverable with the shouted pokémon information.
            %s = \"%s\"

            # Used by the /partyshout command, when a player selects an empty party slot.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            %s = \"%s\"

            # Used by the /partyshout command, when a player successfully shouts a pokémon.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <playername> - The player's username.
            # <playerdisplayname> - The player's custom display name.
            # <playerdisplaynamestyled> - The player's custom display name, including styles.
            # <hoverable> - The hoverable with the shouted pokémon information.
            %s = \"%s\"

            # Used by the /partyshoutall command, when a player has an empty party.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            %s = \"%s\"

            # Used by the /partyshoutall command, when a player successfully shouts their party pokémon.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <playername> - The player's username.
            # <playerdisplayname> - The player's custom display name.
            # <playerdisplaynamestyled> - The player's custom display name, including styles.
            # <hoverable1> - The hoverable with party slot 1's shouted pokémon information.
            # <hoverable2> - The hoverable with party slot 2's shouted pokémon information.
            # <hoverable3> - The hoverable with party slot 3's shouted pokémon information.
            # <hoverable4> - The hoverable with party slot 4's shouted pokémon information.
            # <hoverable5> - The hoverable with party slot 5's shouted pokémon information.
            # <hoverable6> - The hoverable with party slot 6's shouted pokémon information.
            %s = \"\"\"
            %s\"\"\"

            # Used by the /itemshout command, when a player selects an empty item slot.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            %s = \"%s\"

            # Used by the /itemshout command, when a player successfully shouts an item.
            # You can use the following replacement templates:
            # <commandreplyprefix> - The command chat-reply prefix, using the configured template.
            # <commandname> - The command's name, using the configured template.
            # <playername> - The player's username.
            # <playerdisplayname> - The player's custom display name.
            # <playerdisplaynamestyled> - The player's custom display name, including styles.
            # <hoverable> - The hoverable with the shouted item information.
            %s = \"%s\"

            [%s]
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
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
                ChatMiniMessage.ERROR_HOVERABLE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ERROR_HOVERABLE,
                ChatMiniMessage.DEBUG_SHOUT_EMPTY_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DEBUG_SHOUT_EMPTY_SLOT,
                ChatMiniMessage.DEBUG_SHOUT_SUCCESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DEBUG_SHOUT_SUCCESS,
                ChatMiniMessage.ASPECT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ASPECT,
                ChatMiniMessage.SPECIES_FEATURE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SPECIES_FEATURE,
                ChatMiniMessage.PERSISTENT_DATA_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PERSISTENT_DATA,
                ChatMiniMessage.PC_SHOUT_INVALID_BOX_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PC_SHOUT_INVALID_BOX,
                ChatMiniMessage.PC_SHOUT_EMPTY_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PC_SHOUT_EMPTY_SLOT,
                ChatMiniMessage.PC_SHOUT_SUCCESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PC_SHOUT_SUCCESS,
                ChatMiniMessage.PARTY_SHOUT_EMPTY_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_EMPTY_SLOT,
                ChatMiniMessage.PARTY_SHOUT_SUCCESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_SUCCESS,
                ChatMiniMessage.PARTY_SHOUT_ALL_EMPTY_PARTY_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_ALL_EMPTY_PARTY,
                ChatMiniMessage.PARTY_SHOUT_ALL_SUCCESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_ALL_SUCCESS,
                ChatMiniMessage.ITEM_SHOUT_EMPTY_SLOT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ITEM_SHOUT_EMPTY_SLOT,
                ChatMiniMessage.ITEM_SHOUT_SUCCESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ITEM_SHOUT_SUCCESS,
                ChatMiniMessage.TOML_COMMAND_NAMES_SECTION_STRING,
                ChatMiniMessage.DEBUG_SHOUT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DEBUG_SHOUT_COMMAND_NAME,
                ChatMiniMessage.PC_SHOUT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PC_SHOUT_COMMAND_NAME,
                ChatMiniMessage.PARTY_SHOUT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_COMMAND_NAME,
                ChatMiniMessage.PARTY_SHOUT_ALL_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_SHOUT_ALL_COMMAND_NAME,
                ChatMiniMessage.PARTY_CHECK_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARTY_CHECK_COMMAND_NAME,
                ChatMiniMessage.ITEM_SHOUT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ITEM_SHOUT_COMMAND_NAME
            );

        Files.writeString(file.toPath(), defaultContent);
    }
}
