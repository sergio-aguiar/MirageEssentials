package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

public class ChatMiniMessageItemConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "minimessage", "chat_minimessage_items.toml");

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
                ModLogger.info("Generated default chat_minimessage_items.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_minimessage_items.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

        ModLogger.info("MiniMessage configurations successfully loaded from chat_minimessage_items.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_minimessage_items.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat MiniMessage Configuration - Items

            [%s]
            # Here you can configure the appearance of in-chat pokémon hoverables.
            # You can use the following replacement templates:
            # <itembasename> - The item's base minecraft name (will not show anvil or NBT changes).
            # <itemanvilname> - The item's custom name (shows the anvil name, or base item if not changed).
            # <itemcustomname> - The item's custom name (shows the anvil name, or the NBT-changed name if changed, otherwise the base one).
            # <itemcolor> - The item's color if NBT-changed, showing the default-configured item color otherwise.
            %s = \"%s\"
            
            """.formatted
            (
                MirageEssentials.MOD_NAME,
                ChatMiniMessage.TOML_ITEM_HOVERABLE_SECTION_STRING,
                ChatMiniMessage.ITEM_HOVERABLE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_ITEM_HOVERABLE
            );
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
