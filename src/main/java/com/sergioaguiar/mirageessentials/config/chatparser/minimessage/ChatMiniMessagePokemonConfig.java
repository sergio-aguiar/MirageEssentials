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
                # {Name} - The pokémon's nickname.
                # {Title} - The pokémon's ribbon/mark title.
                # {Gender} - The pokémon's gender.
                # {CaughtBall} - The pokémon's caught ball.
                # {CurrentHealth} - The pokémon's current health points.
                # {MaxHealth} - The pokémon's maximum health points.
                # {Status} - The pokémon's current status effect or fainted status.
                # {CustomStatus} - The pokémon's current status condition, using the configured template (not shown is healthy).
                # {CustomCondition} - The pokémon's condition, using the configured template.
                # {Type1} - The pokémon's first type.
                # {Type1Color} - The pokémon's first type's color.
                # {Type2} - The pokémon's second type (or first again, if monotype).
                # {Type2Color} - The pokémon's second type's color (or first again, if monotype).
                # {TeraType} - The pokémon's tera type.
                # {TeraTypeColor} - The pokémon's tera type's color.
                # {CustomTypes} - The pokémon's types, using the configured templates (customMonotype and customDuotype).
                # {CustomShininess} - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # {CustomAlphaness} - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # {Form} - The pokémon's base Cobblemon form (can appear as Regular).
                # {FormsExpanded} - The pokémon's form, expanded to account for some additional aspects.
                # {FormsFullyExpanded} - The pokémon's forms, expanded, also including custom configured aspects.
                # {CustomForms} - The pokémon's forms, each using the configured template (not shown if the only form is Normal).
                # {CustomSpecies} - The pokémon's custom species info, using the configured template.
                # {HeldItem} - The pokémon's held item, ignoring custom name (or None if empty).
                # {HeldItemCustomName} - The pokémon's held item, including custom name (or None if empty).
                # {CustomHeldItem} - The pokémon's held item, using the configured template (not shown if empty).
                # {CosmeticItem} - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # {CosmeticItemCustomName} - The pokémon's cosmetic item, including custom name (or None if empty).
                # {CustomCosmeticItem} - The pokémon's cosmetic item, using the configured template (not shown if empty).
                # {Ability} - The pokémon's ability.
                # {CustomHiddenAbility} - Whether the pokémon's ability is hidden ability or not, using the configured template (not shown if not hidden ability).
                # {Nature} - The pokémon's nature.
                # {NatureStatUp} - The pokémon's nature's increased stat.
                # {NatureStatDown} - The pokémon's nature's decreased stat.
                # {CustomNatureStats} - The pokémon's nature's increased and decreased stats, using the configured template (not shown if neutral nature).
                # {CustomMintness} - Whether the pokémon was minted or not, using the configured template (not shown if not minted).
                # {Level} - The pokémon's level.
                # {CurrentExperience} - The pokémon's current experience.
                # {RequiredExperience} - The pokémon's next level's experience requirement.
                # {RemainingExperience} - The pokémon's remaining experience for a level-up.
                # {CustomExperience} - The pokémon's experience information, using the configured template (not shown if max level).
                # {Friendship} - The pokémon's friendship value.
                # {Move1} - The pokémon's move in slot 1 (empty if no move).
                # {Move1Color} - The color of the pokemon's move in slot 1 (#FFFFFF if no move).
                # {Move1UsedPP} - The amount of used PP for the pokémon's move in slot 1 (0 if no move).
                # {Move1RemainingPP} - The amount of remaining PP for the pokémon's move in slot 1 (0 if no move).
                # {Move1TotalPP} - The amount of total PP for the pokémon's move in slot 1 (0 if no move).
                # {Move1RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 1.
                # {Move1CustomPP} - The pokémon's move in slot 1's PP information, using the configured template (not shown if no move).
                # {Move2} - The pokémon's move in slot 2 (empty if no move).
                # {Move2Color} - The color of the pokemon's move in slot 2 (#FFFFFF if no move).
                # {Move2UsedPP} - The amount of used PP for the pokémon's move in slot 2 (0 if no move).
                # {Move2RemainingPP} - The amount of remaining PP for the pokémon's move in slot 2 (0 if no move).
                # {Move2TotalPP} - The amount of total PP for the pokémon's move in slot 2 (0 if no move).
                # {Move2RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 2.
                # {Move2CustomPP} - The pokémon's move in slot 2's PP information, using the configured template (not shown if no move).
                # {Move3} - The pokémon's move in slot 3 (empty if no move).
                # {Move3Color} - The color of the pokemon's move in slot 3 (#FFFFFF if no move).
                # {Move3UsedPP} - The amount of used PP for the pokémon's move in slot 3 (0 if no move).
                # {Move3RemainingPP} - The amount of remaining PP for the pokémon's move in slot 3 (0 if no move).
                # {Move3TotalPP} - The amount of total PP for the pokémon's move in slot 3 (0 if no move).
                # {Move3RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 3.
                # {Move3CustomPP} - The pokémon's move in slot 3's PP information, using the configured template (not shown if no move).
                # {Move4} - The pokémon's move in slot 4 (empty if no move).
                # {Move4Color} - The color of the pokemon's move in slot 4 (#FFFFFF if no move).
                # {Move4UsedPP} - The amount of used PP for the pokémon's move in slot 4 (0 if no move).
                # {Move4RemainingPP} - The amount of remaining PP for the pokémon's move in slot 4 (0 if no move).
                # {Move4TotalPP} - The amount of total PP for the pokémon's move in slot 4 (0 if no move).
                # {Move4RemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move in slot 4.
                # {Move4CustomPP} - The pokémon's move in slot 4's PP information, using the configured template (not shown if no move).
                # {CustomMoves} - The pokémon's moves, using the configured template (one customMove template used per existing move).
                # {RealIVTotal} - The pokémon's total base IVs.
                # {EffectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {RealIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # {EffectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # {CustomRealIVPercentage} - The pokémon's real IV percentage information, using the configured template.
                # {CustomEffectiveIVPercentage} - The pokémon's effective IV percentage information, using the configured template.
                # {CustomGeneralIVs} - The pokémon's general IV information, using the configured template.
                # {RealHpIVs} - The pokémon's real Hp IV value (0 to 31).
                # {EffectiveHpIVs} - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveHpIVs} - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                # {CustomHpIVs} - The pokémon's Hp IV information, using the configured template.
                # {RealAtkIVs} - The pokémon's real Atk IV value (0 to 31).
                # {EffectivAtkIVs} - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveAtkIVs} - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                # {CustomAtkIVs} - The pokémon's Atk IV information, using the configured template.
                # {RealDefIVs} - The pokémon's real Def IV value (0 to 31).
                # {EffectiveDefIVs} - The pokémon's effective Def IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveDefIVs} - The pokémon's effective Def IV value, using the configured template (not shown if not hypertrained).
                # {CustomDefIVs} - The pokémon's Def IV information, using the configured template.
                # {RealSpaIVs} - The pokémon's real Spa IV value (0 to 31).
                # {EffectiveSpaIVs} - The pokémon's effective Spa IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveSpaIVs} - The pokémon's effective Spa IV value, using the configured template (not shown if not hypertrained).
                # {CustomSpaIVs} - The pokémon's Spa IV information, using the configured template.
                # {RealSpdIVs} - The pokémon's real Spd IV value (0 to 31).
                # {EffectiveSpdIVs} - The pokémon's effective Spd IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveSpdIVs} - The pokémon's effective Spd IV value, using the configured template (not shown if not hypertrained).
                # {CustomSpdIVs} - The pokémon's Spd IV information, using the configured template.
                # {RealSpeIVs} - The pokémon's real Spe IV value (0 to 31).
                # {EffectiveSpeIVs} - The pokémon's effective Spe IV value (0 to 31, counts hypertraining).
                # {CustomEffectiveSpeIVs} - The pokémon's effective Spe IV value, using the configured template (not shown if not hypertrained).
                # {CustomSpeIVs} - The pokémon's Spe IV information, using the configured template.
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # {CustomEVPercentage} - The pokémon's EV percentage information, using the configured template.
                # {CustomGeneralEVs} - The pokémon's general EV information, using the configured template.
                # {HpEVs} - The pokémon's Hp EV value (0 to 255).
                # {CustomHpEVs} - The pokémon's Hp EV information, using the configured template.
                # {AtkEVs} - The pokémon's Atk EV value (0 to 255).
                # {CustomAtkEVs} - The pokémon's Atk EV information, using the configured template.
                # {DefEVs} - The pokémon's Def EV value (0 to 255).
                # {CustomDefEVs} - The pokémon's Def EV information, using the configured template.
                # {SpaEVs} - The pokémon's Spa EV value (0 to 255).
                # {CustomSpaEVs} - The pokémon's Spa EV information, using the configured template.
                # {SpdEVs} - The pokémon's Spd EV value (0 to 255).
                # {CustomSpdEVs} - The pokémon's Spd EV information, using the configured template.
                # {SpeEVs} - The pokémon's Spe EV value (0 to 255).
                # {CustomSpeEVs} - The pokémon's Spe EV information, using the configured template.
                # {Size} - The pokémon's size's name.
                # {ScaleModifier} - The pokémon's scale modifier (2 decimals).
                # {ScaleModifier100} - The pokémon's scale modifier (multiplied by 100, no decimals).
                # {EggGroups} - The pokémon's egg groups, following the configured template.
                # {Neutered} - The pokémon's neuter state, following the configured {True} and {False} templates.
                # {CustomNeutered} - The pokémon's neuter state, following the configured template (not shown if not neutered).
                # {OriginalTrainerName} - The pokémon's original trainer's username.
                # {OriginalTrainerDisplayName} - The pokémon's original trainer's custom display name.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's current status condition or fainted status.
                # If the pokémon is healthy (status-wise) then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {Status} - The pokémon's current status effect or fainted status.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's general condition information.
                # {CurrentHealth} - The pokémon's current health points.
                # {MaxHealth} - The pokémon's maximum health points.
                # {Status} - The pokémon's current status effect or fainted status.
                # {CustomStatus} - The pokémon's current status condition, using the configured template (not shown is healthy).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's types
                # This template is used by {CustomTypes} when the pokémon only has 1 type.
                # You can use the following replacement templates:
                # {Type1} - The pokémon's first type.
                # {Type1Color} - The pokémon's first type's color.
                # {TeraType} - The pokémon's tera type.
                # {TeraTypeColor} - The pokémon's tera type's color.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's types.
                # This template is used by {CustomTypes} when the pokémon has 2 types.
                # You can use the following replacement templates:
                # {Type1} - The pokémon's first type.
                # {Type1Color} - The pokémon's first type's color.
                # {Type2} - The pokémon's second type.
                # {Type2Color} - The pokémon's second type's color.
                # {TeraType} - The pokémon's tera type.
                # {TeraTypeColor} - The pokémon's tera type's color.
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
                # This template will be used in {CustomForms} once per available form, in a row, with a space between each.
                # You can use the following replacement templates:
                # {Form} - One of the pokémon's forms or form-impacting aspects.
                %s = \"\"\"
                %s\"\"\"

                # Used to display a pokémon's species information.
                # You can use the following replacement templates:
                # {CustomShininess} - The pokémon's shininess indicator, using the configured template (not shown if not shiny).
                # {CustomAlphaness} - The pokémon's alphaness indicator, using the configured template (not shown if not alpha).
                # {Form} - The pokémon's base Cobblemon form (can appear as Regular).
                # {FormsExpanded} - The pokémon's form, expanded to account for some additional aspects.
                # {FormsFullyExpanded} - The pokémon's forms, expanded, also including custom configured aspects.
                # {CustomForms} - The pokémon's forms, each using the configured template (not shown if the only form is Normal).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's held item.
                # If the pokémon's held item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {HeldItem} - The pokémon's held item, ignoring custom name (or None if empty).
                # {HeldItemCustomName} - The pokémon's held item, including custom name (or None if empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display a Pokémon's cosmetic item.
                # If the pokémon's cosmetic item slot is empty then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {CosmeticItem} - The pokémon's cosmetic item, ignoring custom name (or None if empty).
                # {CosmeticItemCustomName} - The pokémon's cosmetic item, including custom name (or None if empty).
                %s = \"\"\"
                %s
                \"\"\"

                # Used to display that a pokémon's ability is a hidden ability.
                # If the pokémon's ability is not a hidden ability then this template is not shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's nature's increased and decreased stats.
                # If a pokémon's nature is neutral then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # {NatureStatUp} - The pokémon's nature's increased stat.
                # {NatureStatDown} - The pokémon's nature's decreased stat.
                %s = \"\"\"
                %s\"\"\"

                # Used to display whether the pokémon has been minted or not (nature was hypertrained).
                # If a pokémon's nature has been hypertrained then this template will not be shown (treated as empty).
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's experience information.
                # If a pokémon is already max level then this template will not be shown (treated as empty).
                # You can use the following replacement templates:
                # {CurrentExperience} - The pokémon's current experience.
                # {RequiredExperience} - The pokémon's next level's experience requirement.
                # {RemainingExperience} - The pokémon's remaining experience for a level-up.
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's PP information.
                # This is meant to be used in the {CustomMove} template to be used for every move.
                # You can use the following replacement templates:
                # {MoveUsedPP} - The amount of used PP for the pokémon's move.
                # {MoveRemainingPP} - The amount of remaining PP for the pokémon's move.
                # {MoveTotalPP} - The amount of total PP for the pokémon's move.
                # {MoveRemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move.
                %s = \"\"\"
                %s\"\"\"

                # Used to display the pokémon's move information.
                # This is used by each move that gets displayed in {CustomMoves}.
                # You can use the following replacement templates:
                # {Move} - The pokémon's move.
                # {MoveColor} - The color of the pokemon's move.
                # {MoveUsedPP} - The amount of used PP for the pokémon's move.
                # {MoveRemainingPP} - The amount of remaining PP for the pokémon's move.
                # {MoveTotalPP} - The amount of total PP for the pokémon's move.
                # {MoveRemainingPPColor} - The color that dynamically changes depending on how much PP is remaining for the move.
                # {MoveCustomPP} - The pokémon's move's PP information, using the configured {MoveCustomPP} template.
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # You can use the following replacement templates:
                # {RealIVTotal} - The pokémon's total base IVs.
                # {RealIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # You can use the following replacement templates:
                # {EffectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {EffectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's general IV information.
                # You can use the following replacement templates:
                # {RealIVTotal} - The pokémon's total base IVs.
                # {EffectiveIVTotal} - The pokémon's total effective IVs (counts hypertraining).
                # {RealIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total base IVs out of 186.
                # {EffectiveIVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total effective IVs out of 186.
                # {CustomRealIVPercentage} - The pokémon's real IV percentage information, using the configured template.
                # {CustomEffectiveIVPercentage} - The pokémon's effective IV percentage information, using the configured template.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Hp IV value, using the configured template.
                # If the pokémon's Hp IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveHpIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {HpColor} - The Hp stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Hp IV information.
                # You can use the following replacement templates:
                # {RealHpIVs} - The pokémon's real Hp IV value (0 to 31).
                # {EffectiveHpIVs} - The pokémon's effective Hp IV value (0 to 31, counts hypertraining).
                # {HpColor} - The Hp stat color.
                # {CustomEffectiveHpIVs} - The pokémon's effective Hp IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Atk IV value, using the configured template.
                # If the pokémon's Atk IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveAtkIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {AtkColor} - The Atk stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Atk IV information.
                # You can use the following replacement templates:
                # {RealAtkIVs} - The pokémon's real Atk IV value (0 to 31).
                # {EffectiveAtkIVs} - The pokémon's effective Atk IV value (0 to 31, counts hypertraining).
                # {AtkColor} - The Atk stat color.
                # {CustomEffectiveAtkIVs} - The pokémon's effective Atk IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Def IV value, using the configured template.
                # If the pokémon's Def IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveDefIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {DefColor} - The Def stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Def IV information.
                # You can use the following replacement templates:
                # {RealDefIVs} - The pokémon's real Def IV value (0 to 31).
                # {EffectiveDefIVs} - The pokémon's effective Def IV value (0 to 31, counts hypertraining).
                # {DefColor} - The Def stat color.
                # {CustomEffectiveDefIVs} - The pokémon's effective Def IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spa IV value, using the configured template.
                # If the pokémon's Spa IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveSpaIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {SpaColor} - The Spa stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spa IV information.
                # You can use the following replacement templates:
                # {RealSpaIVs} - The pokémon's real Spa IV value (0 to 31).
                # {EffectiveSpaIVs} - The pokémon's effective Spa IV value (0 to 31, counts hypertraining).
                # {SpaColor} - The Spa stat color.
                # {CustomEffectiveSpaIVs} - The pokémon's effective Spa IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spd IV value, using the configured template.
                # If the pokémon's Spd IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveSpdIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {SpdColor} - The Spd stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spd IV information.
                # You can use the following replacement templates:
                # {RealSpdIVs} - The pokémon's real Spd IV value (0 to 31).
                # {EffectiveSpdIVs} - The pokémon's effective Spd IV value (0 to 31, counts hypertraining).
                # {SpdColor} - The Spd stat color.
                # {CustomEffectiveSpdIVs} - The pokémon's effective Spd IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's effective Spe IV value, using the configured template.
                # If the pokémon's Spe IV has not been hypertrained then this template is not shown (treated as empty).
                # You can use the following replacement templates:
                # {EffectiveSpeIVs} - The pokémon's total effective IVs (counts hypertraining).
                # {SpeColor} - The Spe stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spe IV information.
                # You can use the following replacement templates:
                # {RealSpeIVs} - The pokémon's real Spe IV value (0 to 31).
                # {EffectiveSpeIVs} - The pokémon's effective Spe IV value (0 to 31, counts hypertraining).
                # {SpeColor} - The Spe stat color.
                # {CustomEffectiveSpeIVs} - The pokémon's effective Spe IV value, using the configured template (not shown if not hypertrained).
                %s = \"\"\"
                %s\"\"\"

                # The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # You can use the following replacement templates:
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's general EV information.
                # You can use the following replacement templates:
                # {EVTotal} - The pokémon's total EVs.
                # {EVPercentage} - The percentage (2 decimals) that corresponds to the pokémon's total EVs out of 510.
                # {CustomEVPercentage} - The pokémon's EV percentage information, using the configured template.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Hp EV information.
                # You can use the following replacement templates:
                # {HpEVs} - The pokémon's Hp EV value (0 to 255).
                # {HpColor} - The Hp stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Atk EV information.
                # You can use the following replacement templates:
                # {AtkEVs} - The pokémon's Atk EV value (0 to 255).
                # {AtkColor} - The Atk stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Def EV information.
                # You can use the following replacement templates:
                # {DefEVs} - The pokémon's Def EV value (0 to 255).
                # {DefColor} - The Def stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spa EV information.
                # You can use the following replacement templates:
                # {SpaEVs} - The pokémon's Spa EV value (0 to 255).
                # {SpaColor} - The Spa stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spd EV information.
                # You can use the following replacement templates:
                # {SpdEVs} - The pokémon's Spd EV value (0 to 255).
                # {SpdColor} - The Spd stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's Spe EV information.
                # You can use the following replacement templates:
                # {SpeEVs} - The pokémon's Spe EV value (0 to 255).
                # {SpeColor} - The Spe stat color.
                %s = \"\"\"
                %s\"\"\"

                # The pokémon's neuter state.
                # You can use the following replacement templates:
                # {Neutered} - The pokémon's neuter state, following the configured {True} and {False} templates.
                %s = \"\"\"
                %s\"\"\"

                [%s]
                # Here you can configure how statuses get shown in the {Status} template.
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"
                %s = "%s"

                [%s]
                # Here you can configure how egg groups get shown in the {EggGroups} template.
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
                # Here you can configure how genders appear in the {Gender} template
                %s = "%s"
                %s = "%s"
                %s = "%s"

                [%s]
                # Here you can configure how true/false messages appear as, such in the {Neutered} template.
                %s = "%s"
                %s = "%s"
                """.formatted(
                    MirageEssentials.MOD_NAME,
                    ChatMiniMessage.TOML_POKE_INFO_SECTION_STRING,
                    ChatMiniMessage.POKEMON_INFO_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_POKEMON_INFO,
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
                    ChatMiniMessage.CUSTOM_NATURE_STATS_TEMPLATE_STRING, ChatMiniMessage.DEFAULT_CUSTOM_NATURE_STATS,
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
