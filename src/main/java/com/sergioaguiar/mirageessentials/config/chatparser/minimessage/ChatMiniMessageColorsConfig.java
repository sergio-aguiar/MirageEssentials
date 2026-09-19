package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

import net.minecraft.text.TextColor;

public class ChatMiniMessageColorsConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "minimessage", "chat_minimessage_colors.toml");

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
                ModLogger.info("Generated default chat_minimessage_colors.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_minimessage_colors.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

            if (config.contains(ChatMiniMessage.TOML_CUSTOM_COLOR_SECTION_STRING))
            {
                CommentedConfig colorsConfig = config.get(ChatMiniMessage.TOML_CUSTOM_COLOR_SECTION_STRING);
                for (CommentedConfig.Entry colorEntry : colorsConfig.entrySet())
                {
                    String colorName = colorEntry.getKey();
                    String color = config.get("%s".formatted(colorName));
                    if (color != null && !color.isEmpty())
                        ChatMiniMessage.setCustomColor(colorName, color);
                }
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.HP_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.HP_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setHpColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.ATK_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.ATK_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setAtkColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.DEF_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.DEF_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setDefColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPA_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPA_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setSpaColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPD_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPD_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setSpdColor(TextColor.parse(color).getOrThrow());
            }

            if (config.contains("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPE_COLOR_TEMPLATE_STRING)))
            {
                String color = config.get("%s.%s".formatted(ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING, ChatMiniMessage.SPE_COLOR_TEMPLATE_STRING));
                if (color != null && !color.isEmpty())
                    ChatMiniMessage.setSpeColor(TextColor.parse(color).getOrThrow());
            }

            ModLogger.info("MiniMessage configurations successfully loaded from chat_minimessage_colors.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_minimessage_colors.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat MiniMessage Configuration - Colors
            # This is an alternative to chat_strings.toml.
            # Setting useMiniMessage in chat_settings.toml must be enabled.

            [%s]
            # Here, you can create your own colors to use elsewhere.
            # Change the color here and it will affect every place you use it at, no need to manually change them all.
            # If you name a color ColorName, you can then use it with {ColorName}!
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"

            [%s]
            # Here you can configure the colors to use for the specific templates of the same name.
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            %s = \"%s\"
            """.formatted
            (
                MirageEssentials.MOD_NAME,
                ChatMiniMessage.TOML_CUSTOM_COLOR_SECTION_STRING,
                ChatMiniMessage.DIVISION_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DIVISION_COLOR_STRING,
                ChatMiniMessage.LABEL_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_LABEL_COLOR_STRING,
                ChatMiniMessage.SHINY_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SHINY_COLOR_STRING,
                ChatMiniMessage.ALPHA_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ALPHA_COLOR_STRING,
                ChatMiniMessage.HA_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_HA_COLOR_STRING,
                ChatMiniMessage.NATURE_STAT_UP_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_NATURE_STAT_UP_COLOR_STRING,
                ChatMiniMessage.NATURE_STAT_DOWN_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_NATURE_STAT_DOWN_COLOR_STRING,
                ChatMiniMessage.TRUE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_TRUE_COLOR_STRING,
                ChatMiniMessage.FALSE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FALSE_COLOR_STRING,
                ChatMiniMessage.BURN_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_BURN_COLOR_STRING,
                ChatMiniMessage.SLEEP_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SLEEP_COLOR_STRING,
                ChatMiniMessage.PARALYSIS_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARALYSIS_COLOR_STRING,
                ChatMiniMessage.POISON_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_POISON_COLOR_STRING,
                ChatMiniMessage.FREEZE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FREEZE_COLOR_STRING,
                ChatMiniMessage.FAINT_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FAINT_COLOR_STRING,
                ChatMiniMessage.COMMAND_PREFIX_GRADIENT_LEFT_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_COMMAND_PREFIX_GRADIENT_LEFT_COLOR_STRING,
                ChatMiniMessage.COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_COMMAND_PREFIX_GRADIENT_RIGHT_COLOR_STRING,
                ChatMiniMessage.MALE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_MALE_COLOR_STRING,
                ChatMiniMessage.FEMALE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FEMALE_COLOR_STRING,
                ChatMiniMessage.GENDERLESS_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_GENDERLESS_COLOR_STRING,
                ChatMiniMessage.TOML_STAT_COLOR_SECTION_STRING,
                ChatMiniMessage.HP_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_HP_COLOR_STRING,
                ChatMiniMessage.ATK_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ATK_COLOR_STRING,
                ChatMiniMessage.DEF_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DEF_COLOR_STRING,
                ChatMiniMessage.SPA_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SPA_COLOR_STRING,
                ChatMiniMessage.SPD_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SPD_COLOR_STRING,
                ChatMiniMessage.SPE_COLOR_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SPE_COLOR_STRING
            );
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
