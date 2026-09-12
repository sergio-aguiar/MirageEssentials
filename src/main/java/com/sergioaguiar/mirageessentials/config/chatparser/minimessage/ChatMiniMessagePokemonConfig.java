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

                [PokeInfo]
                # Used for general pokemon info chat hoverables and GUI item lores (such as the /partycheck command).
                # You can use the following replacement templates:
                # {name} - The pokémon's nickname.
                # {title} - The pokémon's ribbon/mark title.
                # {gender} - The pokémon's gender.
                # {caughtBall} - The pokémon's caught ball.
                # {currentHealth} - The pokémon's current health points.
                # {maxHealth} - The pokémon's maximum health points.
                # {status} - The pokémon's current status effect or fainted status.
                # {customStatus} - The pokémon's current status condition, using the configured template (not shown is healthy).
                # {customCondition} - The pokémon's condition, using the configured template.
                # {type1} - The pokémon's first type.
                # {type1Color} - The pokémon's first type's color.
                # {type2} - The pokémon's second type (or first again, if monotype).
                # {type2Color} - The pokémon's second type's color (or first again, if monotype).
                # {teraType} - The pokémon's tera type.
                # {teraTypeColor} - The pokémon's tera type's color.
                # {customTypes} - The pokémon's types, using the configured templates (customMonotype and customDuotype).
                # {customShininess} - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # {customAlphaness} - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # {form} - The pokémon's base Cobblemon form (can appear as Regular).
                # {formsExpanded} - The pokémon's form, expanded to account for some additional aspects.
                # {formsFullyExpanded} - The pokémon's forms, expanded, also including custom configured aspects.
                # {customForms} - The pokémon's forms, each using the configured template (not shown if the only form is Normal).
                # {customSpecies} - The pokémon's custom species info, using the configured template.
                # {heldItem} - The pokémon's held item, ignoring custom name (or None if empty).
                # {heldItemCustomName} - The pokémon's held item, including custom name (or None if empty).
                # {customHeldItem} - The pokémon's held item, using the configured template (not shown if empty).
                # {cosmeticItem} - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # {cosmeticItemCustomName} - The pokémon's cosmetic item, including custom name (or None if empty).
                # {customCosmeticItem} - The pokémon's cosmetic item, using the configured template (not shown if empty).
                # {ability} - The pokémon's ability.
                # {customHiddenAbility} - Whether the pokémon's ability is hidden ability or not, using the configured template (not shown if not hidden ability).
                # {nature} - The pokémon's nature.
                # {natureStatUp} - The pokémon's nature's increased stat.
                # {natureStatDown} - The pokémon's nature's decreased stat.
                # {customNatureStats} - The pokémon's nature's increased and decreased stats, using the configured template (not shown if neutral nature).
                # {customMintness} - Whether the pokémon was minted or not, using the configured template (not shown if not minted).
                # {level} - The pokémon's level.
                # {currentExperience} - The pokémon's current experience.
                # {requiredExperience} - The pokémon's next level's experience requirement.
                # {remainingExperience} - The pokémon's remaining experience for a level-up.
                # {customExperience} - The pokémon's experience information, using the configured template (not shown if max level).
                # {friendship} - The pokémon's friendship value.
                # {move1} - The pokémon's move in slot 1 (empty if no move).
                # {move1Color} - The color of the pokemon's move in slot 1 (#FFFFFF if no move).
                # {move1UsedPP} - The amount of used PP for the pokémon's move in slot 1 (0 if no move).
                # {move1RemainingPP} - The amount of remaining PP for the pokémon's move in slot 1 (0 if no move).
                # {move1TotalPP} - The amount of total PP for the pokémon's move in slot 1 (0 if no move).
                # {move1RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 1.
                # {move1CustomPP} - The pokémon's move in slot 1's PP information, using the configured template (not shown if no move).
                # {move2} - The pokémon's move in slot 2 (empty if no move).
                # {move2Color} - The color of the pokemon's move in slot 2 (#FFFFFF if no move).
                # {move2UsedPP} - The amount of used PP for the pokémon's move in slot 2 (0 if no move).
                # {move2RemainingPP} - The amount of remaining PP for the pokémon's move in slot 2 (0 if no move).
                # {move2TotalPP} - The amount of total PP for the pokémon's move in slot 2 (0 if no move).
                # {move2RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 2.
                # {move2CustomPP} - The pokémon's move in slot 2's PP information, using the configured template (not shown if no move).
                # {move3} - The pokémon's move in slot 3 (empty if no move).
                # {move3Color} - The color of the pokemon's move in slot 3 (#FFFFFF if no move).
                # {move3UsedPP} - The amount of used PP for the pokémon's move in slot 3 (0 if no move).
                # {move3RemainingPP} - The amount of remaining PP for the pokémon's move in slot 3 (0 if no move).
                # {move3TotalPP} - The amount of total PP for the pokémon's move in slot 3 (0 if no move).
                # {move3RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 3.
                # {move3CustomPP} - The pokémon's move in slot 3's PP information, using the configured template (not shown if no move).
                # {move4} - The pokémon's move in slot 4 (empty if no move).
                # {move4Color} - The color of the pokemon's move in slot 4 (#FFFFFF if no move).
                # {move4UsedPP} - The amount of used PP for the pokémon's move in slot 4 (0 if no move).
                # {move4RemainingPP} - The amount of remaining PP for the pokémon's move in slot 4 (0 if no move).
                # {move4TotalPP} - The amount of total PP for the pokémon's move in slot 4 (0 if no move).
                # {move4RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 4.
                # {move4CustomPP} - The pokémon's move in slot 4's PP information, using the configured template (not shown if no move).
                # {customMoves} - The pokémon's moves, using the configured template (one customMove template used per existing move).
                # {realIVTotal} - The pokémon's total base IVs.
                # {effectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {realIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # {effectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # {customRealIVPercentage} - The pokémon's real IV percentage information, using the configured template.
                # {customEffectiveIVPercentage} - The pokémon's effective IV percentage information, using the configured template.
                # {customGeneralIVs} - The pokémon's general IV information, using the configured template.
                # {realHpIVs} - The pokémon's real Hp IV value (0 to 31).
                # {effectiveHpIVs} - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # {customEffectiveHpIVs} - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                # {customHpIVs} - The pokémon's Hp IV information, using the configured template.
                # {realAtkIVs} - The pokémon's real Atk IV value (0 to 31).
                # {effectivAtkIVs} - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # {customEffectiveAtkIVs} - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                # {customAtkIVs} - The pokémon's Atk IV information, using the configured template.
                # {realdefIVs} - The pokémon's real def IV value (0 to 31).
                # {effectivedefIVs} - The pokémon's effective def IV value (0 to 31, counts hypertraining).
                # {customEffectivedefIVs} - The pokémon's effective def IV value, using the configured template (not shown if not hypertrained).
                # {customdefIVs} - The pokémon's def IV information, using the configured template.
                # {realspaIVs} - The pokémon's real spa IV value (0 to 31).
                # {effectivespaIVs} - The pokémon's effective spa IV value (0 to 31, counts hypertraining).
                # {customEffectivespaIVs} - The pokémon's effective spa IV value, using the configured template (not shown if not hypertrained).
                # {customspaIVs} - The pokémon's spa IV information, using the configured template.
                # {realspdIVs} - The pokémon's real spd IV value (0 to 31).
                # {effectivespdIVs} - The pokémon's effective spd IV value (0 to 31, counts hypertraining).
                # {customEffectivespdIVs} - The pokémon's effective spd IV value, using the configured template (not shown if not hypertrained).
                # {customspdIVs} - The pokémon's spd IV information, using the configured template.
                # {realspeIVs} - The pokémon's real spe IV value (0 to 31).
                # {effectivespeIVs} - The pokémon's effective spe IV value (0 to 31, counts hypertraining).
                # {customEffectivespeIVs} - The pokémon's effective spe IV value, using the configured template (not shown if not hypertrained).
                # {customspeIVs} - The pokémon's spe IV information, using the configured template.
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # {customEVPercentage} - The pokémon's EV percentage information, using the configured template.
                # {customGeneralEVs} - The pokémon's general EV information, using the configured template.
                # {HpEVs} - The pokémon's Hp EV value (0 to 255).
                # {customHpEVs} - The pokémon's Hp EV information, using the configured template.
                # {AtkEVs} - The pokémon's Atk EV value (0 to 255).
                # {customAtkEVs} - The pokémon's Atk EV information, using the configured template.
                # {defEVs} - The pokémon's def EV value (0 to 255).
                # {customdefEVs} - The pokémon's def EV information, using the configured template.
                # {spaEVs} - The pokémon's spa EV value (0 to 255).
                # {customspaEVs} - The pokémon's spa EV information, using the configured template.
                # {spdEVs} - The pokémon's spd EV value (0 to 255).
                # {customspdEVs} - The pokémon's spd EV information, using the configured template.
                # {speEVs} - The pokémon's spe EV value (0 to 255).
                # {customspeEVs} - The pokémon's spe EV information, using the configured template.
                # {size} - The pokémon's size's name.
                # {scaleModifier} - The pokémon's scale modifier (2 decimals).
                # {scaleModifier100} - The pokémon's scale modifier (multiplied by 100, no decimals).
                # {eggGroups} - The pokémon's egg groups, following the configured template.
                # {neutered} - The pokémon's neuter state, following the configured {true} and {false} templates.
                # {customNeutered} - The pokémon's neuter state, following the configured template (not shown if not neutered).
                # {originalTrainerName} - The pokémon's original trainer's player name.
                # {originalTrainerDisplayName} - The pokémon's original trainer's custom display player name.
                pokemonInfo = \"\"\"
                <gradient:{type1Color}:{type2Color}>{name}, {title}</gradient>
                {customTypes} <color:{divisionColor}>│</color> {gender} <color:{divisionColor}>│</color> {caughtBall}
                <color:{divisionColor}>────────────────────────────
                <color:{labelColor}>Condition:</color> {customCondition}
                <color:{labelColor}>Species:</color> {customSpecies}
                <color:{labelColor}>Held Item:</color> <white>{heldItem}</white> {customCosmeticItem}
                <color:{labelColor}>Ability:</color> <white>{ability}</white> {customHiddenAbility}
                <color:{labelColor}>Nature</color>{customMintness}<color:{labelColor}>:</color> <white>{nature}</white> {customNatureStats}
                <color:{labelColor}>Level:</color> <white>{level}</white> {customExperience}
                <color:{labelColor}>Friendship:</color> <white>{friendship}</white>
                <dark_gray>────────────────────────────
                {customMoves}
                <dark_gray>────────────────────────────
                <color:{labelColor}>IVs:</color> {customGeneralIVs}
                    {customHpIVs} {customAtkIVs} {customdefIVs}
                    {customspaIVs} {customspdIVs} {customspeIVs}
                <dark_gray>────────────────────────────
                <color:{labelColor}>EVs:</color> {customGeneralEVs}
                    {customHpEV} {customAtkEV} {customdefEV}
                    {customspaEV} {customspdEV} {customspeEV}
                <dark_gray>────────────────────────────
                <color:{labelColor}>Size:</color> <white>{size}</white> <color:{labelColor}>(</color><white>{scaleModifier100}%% scale</white><color:{labelColor}>)</color>
                <color:{labelColor}>Egg Groups:</color> <white>{eggGroups}</white>
                {customNeutered}
                <color:{labelColor}>OT:</color> <white>{originalTrainerName}</white>
                \"\"\"

                # Used to display a Pokémon's current status condition or fainted status.
                # If the pokémon is healthy (status-wise) then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {status} - The pokémon's current status effect or fainted status.
                customStatus = \"\"\"
                <color:{labelColor}>(</color>{status}<color:{labelColor}>)</color>
                \"\"\"

                # Used to display a Pokémon's general condition information.
                # {currentHealth} - The pokémon's current health points.
                # {maxHealth} - The pokémon's maximum health points.
                # {status} - The pokémon's current status effect or fainted status.
                # {customStatus} - The pokémon's current status condition, using the configured template (not shown is healthy).
                customCondition = \"\"\"
                <green>{currentHealth}</green><color:{labelColor}>/</color><green>{maxHealth}</green> {customStatus}
                \"\"\"

                # Used to display a Pokémon's types
                # This template is used by {customTypes} when the pokémon only has 1 type.
                # You can use the following replacement templates:
                # {type1} - The pokémon's first type.
                # {type1Color} - The pokémon's first type's color.
                # {teraType} - The pokémon's tera type.
                # {teraTypeColor} - The pokémon's tera type's color.
                customTypesMonotype = \"\"\"
                <color:{type1Color}>{type1}</color> <color:{teraTypeColor}>{teraType}</color>
                \"\"\"

                # Used to display a Pokémon's types.
                # This template is used by {customTypes} when the pokémon has 2 types.
                # You can use the following replacement templates:
                # {type1} - The pokémon's first type.
                # {type1Color} - The pokémon's first type's color.
                # {type2} - The pokémon's second type.
                # {type2Color} - The pokémon's second type's color.
                # {teraType} - The pokémon's tera type.
                # {teraTypeColor} - The pokémon's tera type's color.
                customTypesDuotype = \"\"\"
                <color:{type1Color}>{type1}</color><color:{labelColor}>/</color><color:{type2Color}>{type2}</color> <color:{teraTypeColor}>{teraType}</color>
                \"\"\"

                # Used as the pokémon's shininess indicator.
                # If the pokémon is not shiny then this template is not shown (treated as empty).
                customShininess = \"\"\"
                <color:{shinyColor}> ★ </color>
                \"\"\"

                # Used as the pokémon's alphaness indicator.
                # If the pokémon is not an alpha then this template is not shown (treated as empty).
                customAlphaness = \"\"\"
                <color:{alphaColor}> α </color>
                \"\"\"

                # Used to display a Pokémon's forms.
                # Shows base form and aspect-related forms, including specified custom ones.
                # This template will be used in {customForms} once per available form, in a row, with a space between each.
                # You can use the following replacement templates:
                # {form} - One of the pokémon's forms or form-impacting aspects.
                customForm = \"\"\"
                <color:{labelColor}>(</color><white>{form}</white><color:{labelColor}>)</color>
                \"\"\"

                # Used to display a pokémon's species information.
                # You can use the following replacement templates:
                # {customShininess} - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # {customAlphaness} - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # {form} - The pokémon's base Cobblemon form (can appear as Regular).
                # {formsExpanded} - The pokémon's form, expanded to account for some additional aspects.
                # {formsFullyExpanded} - The pokémon's forms, expanded, also including custom configured aspects.
                # {customForms} - The pokémon's forms, each using the configured template (not shown if the only form is Normal).
                customSpecies = \"\"\"
                {customShininess}{customAlphaness} <white>{species}</white> {customForms}
                \"\"\"

                # Used to display a Pokémon's held item.
                # If the pokémon's held item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {heldItem} - The pokémon's held item, ignoring custom name (or None if empty).
                # {heldItemCustomName} - The pokémon's held item, including custom name (or None if empty).
                customHeldItem = \"\"\"
                <color:{labelColor}>(</color><white>{heldItem}</white><color:{labelColor}>)</color>
                \"\"\"

                # Used to display a Pokémon's cosmetic item.
                # If the pokémon's cosmetic item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {cosmeticItem} - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # {cosmeticItemCustomName} - The pokémon's cosmetic item, including custom name (or None if empty).
                customCosmeticItem = \"\"\"
                <color:{labelColor}>(</color><white>{cosmeticItem}</white><color:{labelColor}>)</color>
                \"\"\"

                # Used to display that a pokémon's ability is a hidden ability.
                # If the pokémon's ability is not a hidden ability then this template is not shown (treated as empty).
                customHiddenAbility = \"\"\"
                <color:{labelColor}>(</color><color:{haColor}>HA</color><color:{labelColor}>)</color>
                \"\"\"

                # Used to display the pokémon's nature's increased and decreased stats.
                # If a pokémon's nature is neutral then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # {natureStatUp} - The pokémon's nature's increased stat.
                # {natureStatDown} - The pokémon's nature's decreased stat.
                customNatureStats = \"\"\"
                <color:{labelColor}>(</color><color:{natureStatUpColor}>↑{natureStatUp}</color><color:{labelColor}>/</color><color:{natureStatDownColor}>↓{natureStatDown}</color><color:{labelColor}>)</color>
                \"\"\"

                # Used to display whether the pokémon has been minted or not (nature was hypertrained).
                # If a pokémon's nature has been hypertrained then this template will not be shown (treated as empty).
                customMintness = \"\"\"
                <color:{labelColor}> (Minted)</color>
                \"\"\"

                # Used to display the pokémon's experience information.
                # If a pokémon is already max level then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # {currentExperience} - The pokémon's current experience.
                # {requiredExperience} - The pokémon's next level's experience requirement.
                # {remainingExperience} - The pokémon's remaining experience for a level-up.
                customExperience = \"\"\"
                <color:{labelColor}>(</color><white>{currentExperience}</white><color:{labelColor}>/</color><white>{requiredExperience}</white><color:{labelColor}>)</color>
                \"\"\"

                # Used to display the pokémon's PP information.
                # This is meant to be used in the {customMove} template to be used for every move.
                # You can use the following replacement templates:
                # {moveUsedPP} - The amount of used PP for the pokémon's move.
                # {moveRemainingPP} - The amount of remaining PP for the pokémon's move.
                # {moveTotalPP} - The amount of total PP for the pokémon's move.
                # {moveRemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move.
                moveCustomPP = \"\"\"
                <color:{labelColor}>(</color><color:{moveRemainingPPColor}>{moveRemainingPP}</color><color:{labelColor}>/</color><color:{moveRemainingPPColor}>{moveTotalPP}</color><color:{labelColor}>)</color>
                \"\"\"

                # Used to display the pokémon's move information.
                # This is used by each move that gets displayed in {customMoves}.
                # You can use the following replacement templates:
                # {move} - The pokémon's move.
                # {moveColor} - The color of the pokemon's move.
                # {moveUsedPP} - The amount of used PP for the pokémon's move.
                # {moveRemainingPP} - The amount of remaining PP for the pokémon's move.
                # {moveTotalPP} - The amount of total PP for the pokémon's move.
                # {moveRemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move.
                # {moveCustomPP} - The pokémon's move's PP information, using the configured {moveCustomPP} template.
                customMove = \"\"\"
                <color:{labelColor}> ▶ </color><color:{moveColor}>{move}</color> {moveCustomPP}
                \"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # You can use the following replacement templates:
                # {realIVTotal} - The pokémon's total base IVs.
                # {realIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                customRealIVPercentage = \"\"\"
                <color:{labelColor}>(</color><white>{realIVPercentage}%%</white><color:{labelColor}>)</color>
                \"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # You can use the following replacement templates:
                # {effectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {effectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                customEffectiveIVPercentage = \"\"\"
                <color:{labelColor}>(</color><white>{effectiveIVPercentage}%%</white><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's general IV information.
                # You can use the following replacement templates:
                # {realIVTotal} - The pokémon's total base IVs.
                # {effectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {realIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # {effectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # {customRealIVPercentage} - The pokémon's real IV percentage information, using the configured template.
                # {customEffectiveIVPercentage} - The pokémon's effective IV percentage information, using the configured template.
                customGeneralIVs = \"\"\"
                <white>{effectiveIVTotal}</white><color:{labelColor}>/</color><white>186</white> {customEffectiveIVPercentage}
                \"\"\"

                # The pokémon's effective Hp IV value, using the configured template.
                # If the pokémon's Hp IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectiveHpIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {HpColor} - The Hp stat color.
                customEffectiveHpIVs = \"\"\"
                <color:{labelColor}>(</color><color:{HpColor}>{effectiveHpIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's Hp IV information.
                # You can use the following replacement templates:
                # {realHpIVs} - The pokémon's real Hp IV value (0 to 31).
                # {effectiveHpIVs} - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # {HpColor} - The Hp stat color.
                # {customEffectiveHpIVs} - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                customHpIVs = \"\"\"
                <color:{HpColor}>Hp {realHpIVs}</color> {customEffectiveHpIVs}
                \"\"\"

                # The pokémon's effective Atk IV value, using the configured template.
                # If the pokémon's Atk IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectiveAtkIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {AtkColor} - The Atk stat color.
                customEffectiveAtkIVs = \"\"\"
                <color:{labelColor}>(</color><color:{AtkColor}>{effectiveAtkIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's Atk IV information.
                # You can use the following replacement templates:
                # {realAtkIVs} - The pokémon's real Atk IV value (0 to 31).
                # {effectiveAtkIVs} - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # {AtkColor} - The Atk stat color.
                # {customEffectiveAtkIVs} - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                customAtkIVs = \"\"\"
                <color:{AtkColor}>Atk {realAtkIVs}</color> {customEffectiveAtkIVs}
                \"\"\"

                # The pokémon's effective def IV value, using the configured template.
                # If the pokémon's def IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectivedefIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {defColor} - The def stat color.
                customEffectivedefIVs = \"\"\"
                <color:{labelColor}>(</color><color:{defColor}>{effectivedefIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's def IV information.
                # You can use the following replacement templates:
                # {realdefIVs} - The pokémon's real def IV value (0 to 31).
                # {effectivedefIVs} - The pokémon's effective def IV value (0 to 31, counts hypertraining).
                # {defColor} - The def stat color.
                # {customEffectivedefIVs} - The pokémon's effective def IV value, using the configured template (not shown if not hypertrained).
                customdefIVs = \"\"\"
                <color:{defColor}>def {realdefIVs}</color> {customEffectivedefIVs}
                \"\"\"

                # The pokémon's effective spa IV value, using the configured template.
                # If the pokémon's spa IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectivespaIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {spaColor} - The spa stat color.
                customEffectivespaIVs = \"\"\"
                <color:{labelColor}>(</color><color:{spaColor}>{effectivespaIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's spa IV information.
                # You can use the following replacement templates:
                # {realspaIVs} - The pokémon's real spa IV value (0 to 31).
                # {effectivespaIVs} - The pokémon's effective spa IV value (0 to 31, counts hypertraining).
                # {spaColor} - The spa stat color.
                # {customEffectivespaIVs} - The pokémon's effective spa IV value, using the configured template (not shown if not hypertrained).
                customspaIVs = \"\"\"
                <color:{spaColor}>spa {realspaIVs}</color> {customEffectivespaIVs}
                \"\"\"

                # The pokémon's effective spd IV value, using the configured template.
                # If the pokémon's spd IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectivespdIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {spdColor} - The spd stat color.
                customEffectivespdIVs = \"\"\"
                <color:{labelColor}>(</color><color:{spdColor}>{effectivespdIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's spd IV information.
                # You can use the following replacement templates:
                # {realspdIVs} - The pokémon's real spd IV value (0 to 31).
                # {effectivespdIVs} - The pokémon's effective spd IV value (0 to 31, counts hypertraining).
                # {spdColor} - The spd stat color.
                # {customEffectivespdIVs} - The pokémon's effective spd IV value, using the configured template (not shown if not hypertrained).
                customspdIVs = \"\"\"
                <color:{spdColor}>spd {realspdIVs}</color> {customEffectivespdIVs}
                \"\"\"

                # The pokémon's effective spe IV value, using the configured template.
                # If the pokémon's spe IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {effectivespeIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {speColor} - The spe stat color.
                customEffectivespeIVs = \"\"\"
                <color:{labelColor}>(</color><color:{speColor}>{effectivespeIVs}</color><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's spe IV information.
                # You can use the following replacement templates:
                # {realspeIVs} - The pokémon's real spe IV value (0 to 31).
                # {effectivespeIVs} - The pokémon's effective spe IV value (0 to 31, counts hypertraining).
                # {speColor} - The spe stat color.
                # {customEffectivespeIVs} - The pokémon's effective spe IV value, using the configured template (not shown if not hypertrained).
                customspeIVs = \"\"\"
                <color:{speColor}>spe {realspeIVs}</color> {customEffectivespeIVs}
                \"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # You can use the following replacement templates:
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                customEVPercentage = \"\"\"
                <color:{labelColor}>(</color><white>{EVPercentage}%%</white><color:{labelColor}>)</color>
                \"\"\"

                # The pokémon's general EV information.
                # You can use the following replacement templates:
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # {customEVPercentage} - The pokémon's EV percentage information, using the configured template.
                customGeneralIVs = \"\"\"
                <white>{EVTotal}</white><color:{labelColor}>/</color><white>510</white> {customEVPercentage}
                \"\"\"

                # The pokémon's Hp EV information.
                # You can use the following replacement templates:
                # {HpEVs} - The pokémon's Hp EV value (0 to 255).
                # {HpColor} - The Hp stat color.
                customHpIVs = \"\"\"
                <color:{HpColor}>Hp {HpEVs}</color>
                \"\"\"

                # The pokémon's Atk EV information.
                # You can use the following replacement templates:
                # {AtkEVs} - The pokémon's Atk EV value (0 to 255).
                # {AtkColor} - The Atk stat color.
                customAtkIVs = \"\"\"
                <color:{AtkColor}>Atk {AtkEVs}</color>
                \"\"\"

                # The pokémon's Def EV information.
                # You can use the following replacement templates:
                # {DefEVs} - The pokémon's Def EV value (0 to 255).
                # {DefColor} - The Def stat color.
                customDefIVs = \"\"\"
                <color:{DefColor}>Def {DefEVs}</color>
                \"\"\"

                # The pokémon's Spa EV information.
                # You can use the following replacement templates:
                # {SpaEVs} - The pokémon's Spa EV value (0 to 255).
                # {SpaColor} - The Spa stat color.
                customSpaIVs = \"\"\"
                <color:{SpaColor}>Spa {SpaEVs}</color>
                \"\"\"

                # The pokémon's Spd EV information.
                # You can use the following replacement templates:
                # {SpdEVs} - The pokémon's Spd EV value (0 to 255).
                # {SpdColor} - The Spd stat color.
                customSpdIVs = \"\"\"
                <color:{SpdColor}>Spd {SpdEVs}</color>
                \"\"\"

                # The pokémon's Spe EV information.
                # You can use the following replacement templates:
                # {SpeEVs} - The pokémon's Spe EV value (0 to 255).
                # {SpeColor} - The Spe stat color.
                customSpeIVs = \"\"\"
                <color:{SpeColor}>Spe {SpeEVs}</color>
                \"\"\"

                # The pokémon's neuter state.
                # You can use the following replacement templates:
                # {neutered} - The pokémon's neuter state, following the configured {true} and {false} templates.
                customNeutered = \"\"\"
                <color:{labelColor}>Neutered:</color> {neutered}
                \"\"\"

                [Statuses]
                # Here you can configure how statuses get shown in the {status} template.
                burn = \"<color:{burnColor}>[BRN]</color>\"
                sleep = \"<color:{sleepColor}>[SLP]</color>\"
                paralysis = \"<color:{paralysisColor}>[PAR]</color>\"
                poison = \"<color:{poisonColor}>[PSN]</color>\"
                faint = \"<color:{faintColor}>[FNT]</color>\"

                [EggGroups]
                # Here you can configure how egg groups get shown in the {eggGroups} template.
                Amorphous = \"<white>Amorphous</white>\"
                Bug = \"<white>Bug</white>\"
                Ditto = \"<white>Ditto</white>\"
                Dragon = \"<white>Dragon</white>\"
                Fairy = \"<white>Fairy</white>\"
                Field = \"<white>Field</white>\"
                Flying = \"<white>Flying</white>\"
                Grass = \"<white>Grass</white>\"
                Human-Like = \"<white>Human-Like</white>\"
                Mineral = \"<white>Mineral</white>\"
                Monster = \"<white>Monster</white>\"
                Undiscovered = \"<white>Undiscovered</white>\"
                Water1 = \"<white>Water1</white>\"
                Water2 = \"<white>Water2</white>\"
                Water3 = \"<white>Water3</white>\"

                [Booleans]
                # Here you can configure how true/false messages appear as, such in the {neutered} template.
                true = \"<color:{trueColor}>Yes</color>\"
                false = \"<color:{falseColor}>No</color>\"
                """.formatted(MirageEssentials.MOD_NAME);
            
            Files.writeString(file.toPath(), defaultContent);
        }
    }
