package com.sergioaguiar.mirageessentials.config.chatparser.strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

public class ChatStringsConfig
{
    private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "chat_strings.toml");

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
                ModLogger.info("Generated default chat_strings.toml.");
            }
            catch (IOException e)
            {
                ModLogger.error("Failed to create default chat_strings.toml: %s".formatted(e.getMessage()));
                return;
            }
        }

        try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                .preserveInsertionOrder()
                .sync()
                .build())
        {
            config.load();

            if (config.contains("Hoverable.ShinyIcon"))
            {
                String string = config.get("Hoverable.ShinyIcon");
                if (string != null && !string.isEmpty())
                    ChatStrings.setShinyIconString(string);
            }

            if (config.contains("Label.HatchProgress"))
            {
                String string = config.get("Label.HatchProgress");
                if (string != null && !string.isEmpty())
                    ChatStrings.setHatchProgressString(string);
            }

            if (config.contains("Label.StepProgress"))
            {
                String string = config.get("Label.StepProgress");
                if (string != null && !string.isEmpty())
                    ChatStrings.setStepProgressString(string);
            }

            if (config.contains("EggStageMessages.EggStage0Top"))
            {
                String string = config.get("EggStageMessages.EggStage0Top");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage0TopString(string);
            }

            if (config.contains("EggStageMessages.EggStage0Bottom"))
            {
                String string = config.get("EggStageMessages.EggStage0Bottom");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage0BottomString(string);
            }

            if (config.contains("EggStageMessages.EggStage1Top"))
            {
                String string = config.get("EggStageMessages.EggStage1Top");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage1TopString(string);
            }

            if (config.contains("EggStageMessages.EggStage1Bottom"))
            {
                String string = config.get("EggStageMessages.EggStage1Bottom");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage1BottomString(string);
            }

            if (config.contains("EggStageMessages.EggStage2Top"))
            {
                String string = config.get("EggStageMessages.EggStage2Top");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage2TopString(string);
            }

            if (config.contains("EggStageMessages.EggStage2Bottom"))
            {
                String string = config.get("EggStageMessages.EggStage2Bottom");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage2BottomString(string);
            }

            if (config.contains("EggStageMessages.EggStage3Top"))
            {
                String string = config.get("EggStageMessages.EggStage3Top");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage3TopString(string);
            }

            if (config.contains("EggStageMessages.EggStage3Bottom"))
            {
                String string = config.get("EggStageMessages.EggStage3Bottom");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEggHatchStage3BottomString(string);
            }

            if (config.contains("Stat.Health"))
            {
                String string = config.get("Stat.Health");
                if (string != null && !string.isEmpty())
                    ChatStrings.setHealthString(string);
            }

            if (config.contains("Stat.Attack"))
            {
                String string = config.get("Stat.Attack");
                if (string != null && !string.isEmpty())
                    ChatStrings.setAttackString(string);
            }

            if (config.contains("Stat.Defense"))
            {
                String string = config.get("Stat.Defense");
                if (string != null && !string.isEmpty())
                    ChatStrings.setDefenseString(string);
            }

            if (config.contains("Stat.SpecialAttack"))
            {
                String string = config.get("Stat.SpecialAttack");
                if (string != null && !string.isEmpty())
                    ChatStrings.setSpecialAttackString(string);
            }

            if (config.contains("Stat.SpecialDefense"))
            {
                String string = config.get("Stat.SpecialDefense");
                if (string != null && !string.isEmpty())
                    ChatStrings.setSpecialDefenseString(string);
            }

            if (config.contains("Stat.Speed"))
            {
                String string = config.get("Stat.Speed");
                if (string != null && !string.isEmpty())
                    ChatStrings.setSpeedString(string);
            }

            if (config.contains("Value.EmptyHeldItem"))
            {
                String string = config.get("Value.EmptyHeldItem");
                if (string != null && !string.isEmpty())
                    ChatStrings.setEmptyHeldItemString(string);
            }

            if (config.contains("GUI.PartyCheckShoutTypeTitle"))
            {
                String string = config.get("GUI.PartyCheckShoutTypeTitle");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckShoutTypeTitleString(string);
            }

            if (config.contains("GUI.PartyCheckShoutVisibilityTitle"))
            {
                String string = config.get("GUI.PartyCheckShoutVisibilityTitle");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckShoutVisibilityTitleString(string);
            }

            if (config.contains("GUI.PartyCheckPokeShoutAllTitle"))
            {
                String string = config.get("GUI.PartyCheckPokeShoutAllTitle");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckPokeShoutAllTitleString(string);
            }

            if (config.contains("GUI.PartyCheckTitle"))
            {
                String string = config.get("GUI.PartyCheckTitle");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckTitleString(string);
            }

            if (config.contains("GUI.GeneralShoutTypeName"))
            {
                String string = config.get("GUI.GeneralShoutTypeName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setGeneralShoutTypeNameString(string);
            }

            if (config.contains("GUI.GeneralShoutTypeDescription"))
            {
                String string = config.get("GUI.GeneralShoutTypeDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setGeneralShoutTypeDescriptionString(string);
            }

            if (config.contains("GUI.RideShoutTypeName"))
            {
                String string = config.get("GUI.RideShoutTypeName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setRideShoutTypeNameString(string);
            }

            if (config.contains("GUI.RideShoutTypeDescription"))
            {
                String string = config.get("GUI.RideShoutTypeDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setRideShoutTypeDescriptionString(string);
            }

            if (config.contains("GUI.RibbonsShoutTypeName"))
            {
                String string = config.get("GUI.RibbonsShoutTypeName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setRibbonShoutTypeNameString(string);
            }

            if (config.contains("GUI.RibbonsShoutTypeDescription"))
            {
                String string = config.get("GUI.RibbonsShoutTypeDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setRibbonShoutTypeDescriptionString(string);
            }

            if (config.contains("GUI.OpenShoutVisibilityName"))
            {
                String string = config.get("GUI.OpenShoutVisibilityName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setOpenShoutVisibilityNameString(string);
            }

            if (config.contains("GUI.OpenShoutVisibilityDescription"))
            {
                String string = config.get("GUI.OpenShoutVisibilityDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setOpenShoutVisibilityDescriptionString(string);
            }

            if (config.contains("GUI.ClosedShoutVisibilityName"))
            {
                String string = config.get("GUI.ClosedShoutVisibilityName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setClosedShoutVisibilityNameString(string);
            }

            if (config.contains("GUI.ClosedShoutVisibilityDescription"))
            {
                String string = config.get("GUI.ClosedShoutVisibilityDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setClosedShoutVisibilityDescriptionString(string);
            }

            if (config.contains("GUI.SelfShoutVisibilityName"))
            {
                String string = config.get("GUI.SelfShoutVisibilityName");
                if (string != null && !string.isEmpty())
                    ChatStrings.setSelfShoutVisibilityNameString(string);
            }

            if (config.contains("GUI.SelfShoutVisibilityDescription"))
            {
                String string = config.get("GUI.SelfShoutVisibilityDescription");
                if (string != null && !string.isEmpty())
                    ChatStrings.setSelfShoutVisibilityDescriptionString(string);
            }

            if (config.contains("GUI.PartyCheckFooter"))
            {
                String string = config.get("GUI.PartyCheckFooter");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckFooterString(string);
            }

            if (config.contains("GUI.PartyCheckSplitter"))
            {
                String string = config.get("GUI.PartyCheckSplitter");
                if (string != null && !string.isEmpty())
                    ChatStrings.setPartyCheckSplitterString(string);
            }

            ModLogger.info("String configurations successfully loaded from chat_strings.toml.");
        }
        catch (Exception e)
        {
            ModLogger.error("Failed to load chat_strings.toml: %s".formatted(e.getMessage()));
        }
    }

    private static void createDefaultConfig(File file) throws IOException
    {
        String defaultContent = """
            # %s - Chat Strings Configuration

            [Hoverable]
            # Another suggestion "☆ "
            ShinyIcon = "★ "

            [EggStageMessages]
            EggStage0Top = "This Egg doesn't seem close to hatching."
            EggStage0Bottom = "It will probably take a long time."
            EggStage1Top = "What will hatch from this?"
            EggStage1Bottom = "It doesn't seem close to hatching."
            EggStage2Top = "It appears to move occasionally."
            EggStage2Bottom = "It may be close to hatching."
            EggStage3Top = "Sounds can be heard coming from inside!"
            EggStage3Bottom = "It will hatch soon!"

            [Stat]
            Health = "HP"
            Attack = "Atk"
            Defense = "Def"
            SpecialAttack = "SpA"
            SpecialDefense = "SpD"
            Speed = "Spe"

            [Value]
            EmptyHeldItem = "None"

            [GUI]
            PartyCheckTitle = "Party Check"
            PartyCheckShoutTypeTitle = "Shout Type"
            PartyCheckShoutVisibilityTitle = "Shout Visibility"
            PartyCheckPokeShoutAllTitle = "PokeShoutAll"
            GeneralShoutTypeName = "General Stats"
            GeneralShoutTypeDescription = "General species/battle-specific information"
            RideShoutTypeName = "Ride Stats"
            RideShoutTypeDescription = "Mount-specific riding information"
            RibbonsShoutTypeName = "Ribbons"
            RibbonsShoutTypeDescription = "Pokémon ribbon-related information"
            OpenShoutVisibilityName = "Open"
            OpenShoutVisibilityDescription = "All information is displayed publicly"
            ClosedShoutVisibilityName = "Closed"
            ClosedShoutVisibilityDescription = "All battle-relevant information is hidden"
            SelfShoutVisibilityName = "Self"
            SelfShoutVisibilityDescription = "All information is only viewable by you"
            PartyCheckFooter = "Click to cycle through the available options."
            PartyCheckPartyShoutAllFooter = "Click to display all Pokémon in chat."
            PartyCheckSplitter = " - "
            """.formatted(MirageEssentials.MOD_NAME);
        
        Files.writeString(file.toPath(), defaultContent);
    }
}
