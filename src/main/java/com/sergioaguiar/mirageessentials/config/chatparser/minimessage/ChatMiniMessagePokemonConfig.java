package com.sergioaguiar.mirageessentials.config.chatparser.minimessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.sergioaguiar.mirageessentials.MirageEssentials;
import com.sergioaguiar.mirageessentials.util.ModLogger;

public class ChatMiniMessagePokemonConfig
    {
        private static final Path CONFIG_PATH = Paths.get("config", "mirageessentials", "chat_module", "minimessage", "chat_minimessage_pokemon.toml");

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
                    ModLogger.info("Generated default chat_minimessage_pokemon.toml.");
                }
                catch (IOException e)
                {
                    ModLogger.error("Failed to create default chat_minimessage_pokemon.toml: %s".formatted(e.getMessage()));
                    return;
                }
            }

            try (CommentedFileConfig config = CommentedFileConfig.builder(file)
                    .preserveInsertionOrder()
                    .sync()
                    .build())
            {
                config.load();



            ModLogger.info("MiniMessage configurations successfully loaded from chat_minimessage_pokemon.toml.");
            }
            catch (Exception e)
            {
                ModLogger.error("Failed to load chat_minimessage_pokemon.toml: %s".formatted(e.getMessage()));
            }
        }

        private static void createDefaultConfig(File file) throws IOException
        {
            String defaultContent = """
                # %s - Chat MiniMessage Configuration - Pokémon
                # This is an alternative to chat_strings.toml.
                # Setting useMiniMessage in chat_settings.toml must be enabled.

                [%s]
                # Here you can configure the default Pokémon template set.
                # Used for general pokemon info chat hoverables and GUI item lores (such as the /partycheck command).
                # You can use the following replacement templates:
                # <name> - The pokémon's nickname.
                # <title> - The pokémon's ribbon/mark title.
                # <titlecolor> - The pokémon's title's color.
                # <customname> - The pokémon's name, using the configured template (hidden in partychecks, used as the item name instead).
                # <gender> - The pokémon's gender.
                # <caughtball> - The pokémon's caught ball.
                # <currenthealth> - The pokémon's current health points.
                # <maxhealth> - The pokémon's maximum health points.
                # <status> - The pokémon's current status effect or fainted status.
                # <customstatus> - The pokémon's current status condition, using the configured template (not shown is healthy).
                # <customcondition> - The pokémon's condition, using the configured template.
                # <type1> - The pokémon's first type.
                # <type1color> - The pokémon's first type's color.
                # <type2> - The pokémon's second type (or first again, if monotype).
                # <type2color> - The pokémon's second type's color (or first again, if monotype).
                # <teratype> - The pokémon's tera type.
                # <teratypecolor> - The pokémon's tera type's color.
                # <customtypes> - The pokémon's types, using the configured templates (CustomTypesMonotype and CustomTypesDuotype).
                # <species> - The pokémon's species.
                # <customshininess> - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # <customalphaness> - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # <form> - The pokémon's base Cobblemon form (can appear as Normal).
                # <formsexpanded> - The pokémon's form, expanded to account for some additional aspects.
                # <formsfullyexpanded> - The pokémon's forms, expanded, also including custom configured aspects.
                # <customspecies> - The pokémon's custom species info, using the configured template.
                # <helditem> - The pokémon's held item, ignoring custom name (or None if empty).
                # <helditemcustomname> - The pokémon's held item, including custom name (or None if empty).
                # <customhelditem> - The pokémon's held item, using the configured template (not shown if empty).
                # <cosmeticitem> - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # <cosmeticitemcustomname> - The pokémon's cosmetic item, including custom name (or None if empty).
                # <customcosmeticitem> - The pokémon's cosmetic item, using the configured template (not shown if empty).
                # <ability> - The pokémon's ability.
                # <customhiddenability> - Whether the pokémon's ability is hidden ability or not, using the configured template (not shown if not hidden ability).
                # <naturereal> - The pokémon's base nature.
                # <naturerealstatup> - The pokémon's real nature's increased stat.
                # <naturerealstatdown> - The pokémon's real nature's decreased stat.
                # <natureeffective> - The pokémon's effective nature (real or minted).
                # <natureeffectivestatup> - The pokémon's effective nature's increased stat.
                # <natureeffectivestatdown> - The pokémon's effective nature's decreased stat.
                # <customnaturerealstats> - The pokémon's real nature's increased and decreased stats, using the configured template (not shown if neutral nature).
                # <customnatureeffectivestats> - The pokémon's effective nature's increased and decreased stats, using the configured template (not shown if neutral nature).
                # <custommintness> - Whether the pokémon was minted or not, using the configured template (not shown if not minted).
                # <level> - The pokémon's level.
                # <currentexperience> - The pokémon's current experience.
                # <requiredexperience> - The pokémon's next level's experience requirement.
                # <remainingexperience> - The pokémon's remaining experience for a level-up.
                # <customexperience> - The pokémon's experience information, using the configured template (not shown if max level).
                # <friendship> - The pokémon's friendship value.
                # <move1> - The pokémon's move in slot 1 (empty if no move).
                # <move1color> - The color of the pokemon's move in slot 1 (#FFFFFF if no move).
                # <move1usedpp> - The amount of used PP for the pokémon's move in slot 1 (0 if no move).
                # <move1remainingpp> - The amount of remaining PP for the pokémon's move in slot 1 (0 if no move).
                # <move1totalpp> - The amount of total PP for the pokémon's move in slot 1 (0 if no move).
                # <move1custompp> - The pokémon's move in slot 1's PP information, using the configured template (not shown if no move).
                # <move2> - The pokémon's move in slot 2 (empty if no move).
                # <move2color> - The color of the pokemon's move in slot 2 (#FFFFFF if no move).
                # <move2usedpp> - The amount of used PP for the pokémon's move in slot 2 (0 if no move).
                # <move2remainingpp> - The amount of remaining PP for the pokémon's move in slot 2 (0 if no move).
                # <move2totalpp> - The amount of total PP for the pokémon's move in slot 2 (0 if no move).
                # <move2custompp> - The pokémon's move in slot 2's PP information, using the configured template (not shown if no move).
                # <move3> - The pokémon's move in slot 3 (empty if no move).
                # <move3color> - The color of the pokemon's move in slot 3 (#FFFFFF if no move).
                # <move3usedpp> - The amount of used PP for the pokémon's move in slot 3 (0 if no move).
                # <move3remainingpp> - The amount of remaining PP for the pokémon's move in slot 3 (0 if no move).
                # <move3totalpp> - The amount of total PP for the pokémon's move in slot 3 (0 if no move).
                # <move3custompp> - The pokémon's move in slot 3's PP information, using the configured template (not shown if no move).
                # <move4> - The pokémon's move in slot 4 (empty if no move).
                # <move4color> - The color of the pokemon's move in slot 4 (#FFFFFF if no move).
                # <move4usedpp> - The amount of used PP for the pokémon's move in slot 4 (0 if no move).
                # <move4remainingpp> - The amount of remaining PP for the pokémon's move in slot 4 (0 if no move).
                # <move4totalpp> - The amount of total PP for the pokémon's move in slot 4 (0 if no move).
                # <move4custompp> - The pokémon's move in slot 4's PP information, using the configured template (not shown if no move).
                # <custommoves> - The pokémon's moves, using the configured template (one customMove template used per existing move).
                # <realivtotal> - The pokémon's total base IVs.
                # <effectiveivtotal> - The pokémon's total effective IVs (counts hypertraining).
                # <realivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # <effectiveivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # <customrealivpercentage> - The pokémon's real IV percentage information, using the configured template.
                # <customeffectiveivpercentage> - The pokémon's effective IV percentage information, using the configured template.
                # <customgeneralivs> - The pokémon's general IV information, using the configured template.
                # <realhpivs> - The pokémon's real Hp IV value (0 to 31).
                # <effectivehpivs> - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # <hpcolor> - The Hp stat color.
                # <customeffectivehpivs> - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                # <customhpivs> - The pokémon's Hp IV information, using the configured template.
                # <realatkivs> - The pokémon's real Atk IV value (0 to 31).
                # <effectiveatkivs> - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # <atkcolor> - The Atk stat color.
                # <customeffectiveatkivs> - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                # <customatkivs> - The pokémon's Atk IV information, using the configured template.
                # <realdefivs> - The pokémon's real Def IV value (0 to 31).
                # <effectivedefivs> - The pokémon's effective Def IV value (0 to 31, counts hypertraining).
                # <defcolor> - The Def stat color.
                # <customeffectivedefivs> - The pokémon's effective Def IV value, using the configured template (not shown if not hypertrained).
                # <customdefivs> - The pokémon's Def IV information, using the configured template.
                # <realspaivs> - The pokémon's real Spa IV value (0 to 31).
                # <effectivespaivs> - The pokémon's effective Spa IV value (0 to 31, counts hypertraining).
                # <spacolor> - The Spa stat color.
                # <customeffectivespaivs> - The pokémon's effective Spa IV value, using the configured template (not shown if not hypertrained).
                # <customspaivs> - The pokémon's Spa IV information, using the configured template.
                # <realspdivs> - The pokémon's real Spd IV value (0 to 31).
                # <effectivespdivs> - The pokémon's effective Spd IV value (0 to 31, counts hypertraining).
                # <spdcolor> - The Spd stat color.
                # <customeffectivespdivs> - The pokémon's effective Spd IV value, using the configured template (not shown if not hypertrained).
                # <customspdivs> - The pokémon's Spd IV information, using the configured template.
                # <realspeivs> - The pokémon's real Spe IV value (0 to 31).
                # <effectivespeivs> - The pokémon's effective Spe IV value (0 to 31, counts hypertraining).
                # <specolor> - The Spe stat color.
                # <customeffectivespeivs> - The pokémon's effective Spe IV value, using the configured template (not shown if not hypertrained).
                # <customspeivs> - The pokémon's Spe IV information, using the configured template.
                # <evtotal> - The pokémon's total EVs.
                # <evpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # <customevpercentage> - The pokémon's EV percentage information, using the configured template.
                # <customgeneralevs> - The pokémon's general EV information, using the configured template.
                # <hpevs> - The pokémon's Hp EV value (0 to 255).
                # <customhpevs> - The pokémon's Hp EV information, using the configured template.
                # <atkevs> - The pokémon's Atk EV value (0 to 255).
                # <customatkevs> - The pokémon's Atk EV information, using the configured template.
                # <defevs> - The pokémon's Def EV value (0 to 255).
                # <customdefevs> - The pokémon's Def EV information, using the configured template.
                # <spaevs> - The pokémon's Spa EV value (0 to 255).
                # <customspaevs> - The pokémon's Spa EV information, using the configured template.
                # <spdevs> - The pokémon's Spd EV value (0 to 255).
                # <customspdevs> - The pokémon's Spd EV information, using the configured template.
                # <speevs> - The pokémon's Spe EV value (0 to 255).
                # <customspeevs> - The pokémon's Spe EV information, using the configured template.
                # <size> - The pokémon's size's name.
                # <scalemodifier> - The pokémon's scale modifier (2 decimals).
                # <scalemodifier100> - The pokémon's scale modifier (multiplied by 100, no decimals).
                # <egggroups> - The pokémon's egg groups, following the configured template.
                # <neutered> - The pokémon's neuter state, following the configured <true> and <false> templates.
                # <customneutered> - The pokémon's neuter state, following the configured template (not shown if not neutered).
                # <originaltrainername> - The pokémon's original trainer's username (or UUID).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's name and title.
                # If used in a partycheck command, it will not be shown in the item lore, being instead shown in the item name.
                # You can use the following replacement templates:
                # <name> - The pokémon's nickname.
                # <title> - The pokémon's ribbon/mark title.
                # <titlecolor> - The pokémon's title's color.
                # <type1color> - The pokémon's first type's color.
                # <type2color> - The pokémon's second type's color.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's current status condition or fainted status.
                # If the pokémon is healthy (status-wise) then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <status> - The pokémon's current status effect or fainted status.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's general condition information.
                # <currenthealth> - The pokémon's current health points.
                # <maxhealth> - The pokémon's maximum health points.
                # <status> - The pokémon's current status effect or fainted status.
                # <customstatus> - The pokémon's current status condition, using the configured template (not shown is healthy).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's types
                # This template is used by <customtypes> when the pokémon only has 1 type.
                # You can use the following replacement templates:
                # <type1> - The pokémon's first type.
                # <type1color> - The pokémon's first type's color.
                # <teratype> - The pokémon's tera type.
                # <teratypecolor> - The pokémon's tera type's color.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's types.
                # This template is used by <customtypes> when the pokémon has 2 types.
                # You can use the following replacement templates:
                # <type1> - The pokémon's first type.
                # <type1color> - The pokémon's first type's color.
                # <type2> - The pokémon's second type.
                # <type2color> - The pokémon's second type's color.
                # <teratype> - The pokémon's tera type.
                # <teratypecolor> - The pokémon's tera type's color.
                %s = \"\"\"
                %s\"\"\"

                # Used as the pokémon's shininess indicator.
                # If the pokémon is not shiny then this template is not shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used as the pokémon's alphaness indicator.
                # If the pokémon is not an alpha then this template is not shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's forms.
                # Shows base form and aspect-related forms, including specified custom ones.
                # This template will be used in <formsexpanded> and <formsfullyexpanded> once per available form, in a row, with a space between each.
                # You can use the following replacement templates:
                # <form> - One of the pokémon's forms or form-impacting aspects.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a pokémon's species information.
                # You can use the following replacement templates:
                # <species> - The pokémon's species.
                # <customshininess> - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # <customalphaness> - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # <form> - The pokémon's base Cobblemon form (can appear as Normal).
                # <formsexpanded> - The pokémon's form, expanded to account for some additional aspects.
                # <formsfullyexpanded> - The pokémon's forms, expanded, also including custom configured aspects.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's held item.
                # If the pokémon's held item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <helditem> - The pokémon's held item, ignoring custom name (or None if empty).
                # <helditemcustomname> - The pokémon's held item, including custom name (or None if empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's cosmetic item.
                # If the pokémon's cosmetic item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <cosmeticitem> - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # <cosmeticitemcustomname> - The pokémon's cosmetic item, including custom name (or None if empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display that a pokémon's ability is a hidden ability.
                # If the pokémon's ability is not a hidden ability then this template is not shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's real nature's increased and decreased stats.
                # If a pokémon's nature is neutral then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # <naturerealstatup> - The pokémon's real nature's increased stat.
                # <naturerealstatdown> - The pokémon's real nature's decreased stat.
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's effective nature's increased and decreased stats.
                # If a pokémon's nature is neutral then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # <natureeffectivestatup> - The pokémon's effective nature's increased stat.
                # <natureeffectivestatdown> - The pokémon's effective nature's decreased stat.
                %s = \"\"\"
                %s\"\"\"

                # Used to display whether the pokémon has been minted or not (nature was hypertrained).
                # If a pokémon's nature has been hypertrained then this template will not be shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's experience information.
                # If a pokémon is already max level then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # <currentexperience> - The pokémon's current experience.
                # <requiredexperience> - The pokémon's next level's experience requirement.
                # <remainingexperience> - The pokémon's remaining experience for a level-up.
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's PP information.
                # This is meant to be used in the <custommove> template to be used for every move.
                # You can use the following replacement templates:
                # <moveusedpp> - The amount of used PP for the pokémon's move.
                # <moveremainingpp> - The amount of remaining PP for the pokémon's move.
                # <movetotalpp> - The amount of total PP for the pokémon's move.
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's move information.
                # This is used by each move that gets displayed in <custommoves>.
                # You can use the following replacement templates:
                # <move> - The pokémon's move.
                # <movecolor> - The color of the pokemon's move.
                # <moveusedpp> - The amount of used PP for the pokémon's move.
                # <moveremainingpp> - The amount of remaining PP for the pokémon's move.
                # <movetotalpp> - The amount of total PP for the pokémon's move.
                # <movecustompp> - The pokémon's move's PP information, using the configured <movecustompp> template.
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # You can use the following replacement templates:
                # <realivtotal> - The pokémon's total base IVs.
                # <realivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # You can use the following replacement templates:
                # <effectiveivtotal> - The pokémon's total effective IVs (counts hypertraining).
                # <effectiveivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's general IV information.
                # You can use the following replacement templates:
                # <realivtotal> - The pokémon's total base IVs.
                # <effectiveivtotal> - The pokémon's total effective IVs (counts hypertraining).
                # <realivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # <effectiveivpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # <customrealivpercentage> - The pokémon's real IV percentage information, using the configured template.
                # <customeffectiveivpercentage> - The pokémon's effective IV percentage information, using the configured template.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Hp IV value, using the configured template.
                # If the pokémon's Hp IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectivehpivs> - The pokémon's total effective IVs (counts hypertraining).
                # <hpcolor> - The Hp stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Hp IV information.
                # You can use the following replacement templates:
                # <realhpivs> - The pokémon's real Hp IV value (0 to 31).
                # <effectivehpivs> - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # <hpcolor> - The Hp stat color.
                # <customeffectivehpivs> - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Atk IV value, using the configured template.
                # If the pokémon's Atk IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectiveatkivs> - The pokémon's total effective IVs (counts hypertraining).
                # <atkcolor> - The Atk stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Atk IV information.
                # You can use the following replacement templates:
                # <realatkivs> - The pokémon's real Atk IV value (0 to 31).
                # <effectiveatkivs> - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # <atkcolor> - The Atk stat color.
                # <customeffectiveatkivs> - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Def IV value, using the configured template.
                # If the pokémon's Def IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectivedefivs> - The pokémon's total effective IVs (counts hypertraining).
                # <defcolor> - The Def stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Def IV information.
                # You can use the following replacement templates:
                # <realdefivs> - The pokémon's real Def IV value (0 to 31).
                # <effectivedefivs> - The pokémon's effective Def IV value (0 to 31, counts hypertraining).
                # <defcolor> - The Def stat color.
                # <customeffectivedefivs> - The pokémon's effective Def IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spa IV value, using the configured template.
                # If the pokémon's Spa IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectivespaivs> - The pokémon's total effective IVs (counts hypertraining).
                # <spacolor> - The Spa stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spa IV information.
                # You can use the following replacement templates:
                # <realspaivs> - The pokémon's real Spa IV value (0 to 31).
                # <effectivespaivs> - The pokémon's effective Spa IV value (0 to 31, counts hypertraining).
                # <spacolor> - The Spa stat color.
                # <customeffectivespaivs> - The pokémon's effective Spa IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spd IV value, using the configured template.
                # If the pokémon's Spd IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectivespdivs> - The pokémon's total effective IVs (counts hypertraining).
                # <spdcolor> - The Spd stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spd IV information.
                # You can use the following replacement templates:
                # <realspdivs> - The pokémon's real Spd IV value (0 to 31).
                # <effectivespdivs> - The pokémon's effective Spd IV value (0 to 31, counts hypertraining).
                # <spdcolor> - The Spd stat color.
                # <customeffectivespdivs> - The pokémon's effective Spd IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spe IV value, using the configured template.
                # If the pokémon's Spe IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # <effectivespeivs> - The pokémon's total effective IVs (counts hypertraining).
                # <specolor> - The Spe stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spe IV information.
                # You can use the following replacement templates:
                # <realspeivs> - The pokémon's real Spe IV value (0 to 31).
                # <effectivespeivs> - The pokémon's effective Spe IV value (0 to 31, counts hypertraining).
                # <specolor> - The Spe stat color.
                # <customeffectivespeivs> - The pokémon's effective Spe IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # You can use the following replacement templates:
                # <evtotal> - The pokémon's total EVs.
                # <evpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's general EV information.
                # You can use the following replacement templates:
                # <evtotal> - The pokémon's total EVs.
                # <evpercentage> - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # <customevpercentage> - The pokémon's EV percentage information, using the configured template.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Hp EV information.
                # You can use the following replacement templates:
                # <hpevs> - The pokémon's Hp EV value (0 to 255).
                # <hpcolor> - The Hp stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Atk EV information.
                # You can use the following replacement templates:
                # <atkevs> - The pokémon's Atk EV value (0 to 255).
                # <atkcolor> - The Atk stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Def EV information.
                # You can use the following replacement templates:
                # <defevs> - The pokémon's Def EV value (0 to 255).
                # <defcolor> - The Def stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spa EV information.
                # You can use the following replacement templates:
                # <spaevs> - The pokémon's Spa EV value (0 to 255).
                # <spacolor> - The Spa stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spd EV information.
                # You can use the following replacement templates:
                # <spdevs> - The pokémon's Spd EV value (0 to 255).
                # <spdcolor> - The Spd stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spe EV information.
                # You can use the following replacement templates:
                # <speevs> - The pokémon's Spe EV value (0 to 255).
                # <specolor> - The Spe stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's neuter state.
                # You can use the following replacement templates:
                # <neutered> - The pokémon's neuter state, following the configured <true> and <false> templates.
                %s = \"\"\"
                %s\"\"\"

                [%s]
                # Here you can configure how statuses get shown in the <status> template.
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"

                [%s]
                # Here you can configure how egg groups get shown in the <egggroups> template.
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"

                [%s]
                # Here you can configure how genders appear in the <gender> template
                %s = "%s"
                %s = "%s"
                %s = "%s"

                [%s]
                # Here you can configure how true/false messages appear as, such in the <neutered> template.
                %s = "%s"
                %s = "%s"
                """.formatted(
                    MirageEssentials.MOD_NAME,
                    ChatMiniMessage.TOML_POKE_INFO_SECTION_STRING,
                    ChatMiniMessage.POKEMON_INFO_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_POKEMON_INFO,
                    ChatMiniMessage.CUSTOM_NAME_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_NAME,
                    ChatMiniMessage.CUSTOM_STATUS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_STATUS,
                    ChatMiniMessage.CUSTOM_CONDITION_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_CONDITION,
                    ChatMiniMessage.CUSTOM_TYPES_MONOTYPE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_TYPES_MONOTYPE,
                    ChatMiniMessage.CUSTOM_TYPES_DUOTYPE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_TYPES_DUOTYPE,
                    ChatMiniMessage.CUSTOM_SHININESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SHININESS,
                    ChatMiniMessage.CUSTOM_ALPHANESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_ALPHANESS,
                    ChatMiniMessage.CUSTOM_FORM_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_FORM,
                    ChatMiniMessage.CUSTOM_SPECIES_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPECIES,
                    ChatMiniMessage.CUSTOM_HELD_ITEM_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_HELD_ITEM,
                    ChatMiniMessage.CUSTOM_COSMETIC_ITEM_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_COSMETIC_ITEM,
                    ChatMiniMessage.CUSTOM_HIDDEN_ABILITY_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_HIDDEN_ABILITY,
                    ChatMiniMessage.CUSTOM_NATURE_REAL_STATS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_NATURE_REAL_STATS,
                    ChatMiniMessage.CUSTOM_NATURE_EFFECTIVE_STATS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_NATURE_EFFECTIVE_STATS,
                    ChatMiniMessage.CUSTOM_MINTNESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_MINTNESS,
                    ChatMiniMessage.CUSTOM_EXPERIENCE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EXPERIENCE,
                    ChatMiniMessage.MOVE_CUSTOM_PP_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_MOVE_CUSTOM_PP,
                    ChatMiniMessage.CUSTOM_MOVE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_MOVE,
                    ChatMiniMessage.CUSTOM_REAL_IV_PERCENTAGE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_REAL_IV_PERCENTAGE,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_IV_PERCENTAGE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_IV_PERCENTAGE,
                    ChatMiniMessage.CUSTOM_GENERAL_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_GENERAL_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_HP_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_HP_IVS,
                    ChatMiniMessage.CUSTOM_HP_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_HP_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_ATK_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_ATK_IVS,
                    ChatMiniMessage.CUSTOM_ATK_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_ATK_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_DEF_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_DEF_IVS,
                    ChatMiniMessage.CUSTOM_DEF_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_DEF_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_SPA_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_SPA_IVS,
                    ChatMiniMessage.CUSTOM_SPA_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPA_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_SPD_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_SPD_IVS,
                    ChatMiniMessage.CUSTOM_SPD_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPD_IVS,
                    ChatMiniMessage.CUSTOM_EFFECTIVE_SPE_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EFFECTIVE_SPE_IVS,
                    ChatMiniMessage.CUSTOM_SPE_IVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPE_IVS,
                    ChatMiniMessage.CUSTOM_EV_PERCENTAGE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_EV_PERCENTAGE,
                    ChatMiniMessage.CUSTOM_GENERAL_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_GENERAL_EVS,
                    ChatMiniMessage.CUSTOM_HP_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_HP_EVS,
                    ChatMiniMessage.CUSTOM_ATK_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_ATK_EVS,
                    ChatMiniMessage.CUSTOM_DEF_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_DEF_EVS,
                    ChatMiniMessage.CUSTOM_SPA_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPA_EVS,
                    ChatMiniMessage.CUSTOM_SPD_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPD_EVS,
                    ChatMiniMessage.CUSTOM_SPE_EVS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_SPE_EVS,
                    ChatMiniMessage.CUSTOM_NEUTERED_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_NEUTERED,
                    ChatMiniMessage.TOML_STATUSES_SECTION_STRING,
                    ChatMiniMessage.BURN_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_BURN,
                    ChatMiniMessage.SLEEP_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_SLEEP,
                    ChatMiniMessage.PARALYSIS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_PARALYSIS,
                    ChatMiniMessage.POISON_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_POISON,
                    ChatMiniMessage.FREEZE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FREEZE,
                    ChatMiniMessage.FAINT_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FAINT,
                    ChatMiniMessage.TOML_EGG_GROUPS_SECTION_STRING,
                    ChatMiniMessage.AMORPHOUS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_AMORPHOUS,
                    ChatMiniMessage.BUG_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_BUG,
                    ChatMiniMessage.DITTO_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DITTO,
                    ChatMiniMessage.DRAGON_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_DRAGON,
                    ChatMiniMessage.FAIRY_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FAIRY,
                    ChatMiniMessage.FIELD_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FIELD,
                    ChatMiniMessage.FLYING_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FLYING,
                    ChatMiniMessage.GRASS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_GRASS,
                    ChatMiniMessage.HUMAN_LIKE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_HUMAN_LIKE,
                    ChatMiniMessage.MINERAL_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_MINERAL,
                    ChatMiniMessage.MONSTER_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_MONSTER,
                    ChatMiniMessage.UNDISCOVERED_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_UNDISCOVERED,
                    ChatMiniMessage.WATER1_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_WATER1,
                    ChatMiniMessage.WATER2_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_WATER2,
                    ChatMiniMessage.WATER3_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_WATER3,
                    ChatMiniMessage.TOML_GENDERS_SECTION_STRING,
                    ChatMiniMessage.GENDER_MALE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_MALE_GENDER,
                    ChatMiniMessage.GENDER_FEMALE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FEMALE_GENDER,
                    ChatMiniMessage.GENDER_GENDERLESS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_GENDERLESS_GENDER,
                    ChatMiniMessage.TOML_BOOLEANS_SECTION_STRING,
                    ChatMiniMessage.TRUE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_TRUE,
                    ChatMiniMessage.FALSE_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_FALSE
                );
            
            Files.writeString(file.toPath(), defaultContent);
        }
    }
