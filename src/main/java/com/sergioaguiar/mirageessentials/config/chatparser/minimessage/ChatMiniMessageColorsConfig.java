package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

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

                [Colors]
                # Here, you can create your own colors to use elsewhere.
                # Change the color here and it will affect every place you use it at, no need to manually change them all.
                # If you name a color colorName, you can then use it with {colorName}!
                divisionColor = \"#646464\"
                labelColor = \"#10F2F2\"
                shinyColor = \"#e7e436\"
                alphaColor = \"#d14040\"
                haColor = \"#31d6e2\"
                natureStatUpColor = \"#60d651\"
                natureStatDownColor = \"#d14040\"
                trueColor = \"#40d440\"
                falseColor = \"#d12828\"
                burnColor = \"#ff3333\"
                sleepColor = \"#66ffff\"
                paralysisColor = \"#ffff00\"
                poisonColor = \"#990099\"
                faintColor = \"#808080\"

                [StatColors]
                # Here you can configure the colors to use for the specific templates of the same name.
                HpColor = \"#4f9c45\"
                AtkColor = \"#b33f3f\"
                DefColor = \"#d1842c\"
                SpaColor = \"#d140ca\"
                SpdColor = \"#ddda36\"
                SpeColor = \"#3bd8dd\"
                """.formatted(MirageEssentials.MOD_NAME);
            
            Files.writeString(file.toPath(), defaultContent);
        }
    }
