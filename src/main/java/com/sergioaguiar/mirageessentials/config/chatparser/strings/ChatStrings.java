package com.sergioaguiar.mirageessentials.config.chatparser.strings;

import com.sergioaguiar.mirageessentials.gui.IShoutOption;

public class ChatStrings
{
    public enum DefaultShoutType implements IShoutOption
    {
        GENERAL("General Stats", "General species/battle-specific information"),
        RIDE("Ride Stats", "Mount-specific riding information"),
        RIBBON("Ribbons", "Pokémon ribbon-related information");

        private final String name;
        private final String description;

        private DefaultShoutType(String name, String description)
        {
            this.name = name;
            this.description = description;
        }

        public String getName()
        {
            return name;
        }

        public String getDescription()
        {
            return description;
        }
    }

    public enum DefaultShoutVisibility implements IShoutOption
    {
        OPEN("Open", "All information is displayed publicly"),
        CLOSED("Closed", "All battle-relevant information is hidden"),
        SELF("Self", "All information is only viewable by you");

        private final String name;
        private final String description;

        private DefaultShoutVisibility(String name, String description)
        {
            this.name = name;
            this.description = description;
        }

        public String getName()
        {
            return name;
        }

        public String getDescription()
        {
            return description;
        }
    }

    private static final String DEFAULT_SHINY_ICON_STRING = "★ ";

    private static final String DEFAULT_HEALTH_STRING = "HP";
    private static final String DEFAULT_ATTACK_STRING = "Atk";
    private static final String DEFAULT_DEFENSE_STRING = "Def";
    private static final String DEFAULT_SPECIAL_ATTACK_STRING = "SpA";
    private static final String DEFAULT_SPECIAL_DEFENSE_STRING = "SpD";
    private static final String DEFAULT_SPEED_STRING = "Spe";

    private static final String DEFAULT_EMPTY_HELD_ITEM_STRING = "None";

    private static final String DEFAULT_PARTYCHECK_TITLE_STRING = "Party Check";
    private static final String DEFAULT_PARTYCHECK_SHOUT_TYPE_TITLE_STRING = "Shout Type";
    private static final String DEFAULT_PARTYCHECK_SHOUT_VISIBILITY_TITLE_STRING = "Shout Visibility";
    private static final String DEFAULT_PARTYCHECK_POKESHOUTALL_TITLE_STRING = "PartyShoutAll";
    private static final String DEFAULT_PARTYCHECK_OPTIONED_FOOTER_STRING = "Click to cycle through the available options.";
    private static final String DEFAULT_PARTYCHECK_PARTYSHOUTALL_FOOTER_STRING = "Click to display all Pokémon in chat.";
    private static final String DEFAULT_PARTYCHECK_SPLITTER_STRING = " - ";

    private static String shinyIconString;

    private static String healthString;
    private static String attackString;
    private static String defenseString;
    private static String specialAttackString;
    private static String specialDefenseString;
    private static String speedString;

    private static String emptyHeldItemString;

    private static String partyCheckTitleString;
    private static String partyCheckShoutTypeTitleString;
    private static String partyCheckShoutVisibilityTitleString;
    private static String partyCheckPokeShoutAllTitleString;
    private static String generalShoutTypeNameString;
    private static String generalShoutTypeDescriptionString;
    private static String rideShoutTypeNameString;
    private static String rideShoutTypeDescriptionString;
    private static String ribbonShoutTypeNameString;
    private static String ribbonShoutTypeDescriptionString;
    private static String openShoutVisibilityNameString;
    private static String openShoutVisibilityDescriptionString;
    private static String closedShoutVisibilityNameString;
    private static String closedShoutVisibilityDescriptionString;
    private static String selfShoutVisibilityNameString;
    private static String selfShoutVisibilityDescriptionString;
    private static String partyCheckFooterString;
    private static String partyCheckPartyShoutAllFooterString;
    private static String partyCheckSplitterString;

    public static void setDefaults()
    {
        shinyIconString = DEFAULT_SHINY_ICON_STRING;

        healthString = DEFAULT_HEALTH_STRING;
        attackString = DEFAULT_ATTACK_STRING;
        defenseString = DEFAULT_DEFENSE_STRING;
        specialAttackString = DEFAULT_SPECIAL_ATTACK_STRING;
        specialDefenseString = DEFAULT_SPECIAL_DEFENSE_STRING;
        speedString = DEFAULT_SPEED_STRING;

        emptyHeldItemString = DEFAULT_EMPTY_HELD_ITEM_STRING;

        partyCheckTitleString = DEFAULT_PARTYCHECK_TITLE_STRING;
        partyCheckShoutTypeTitleString = DEFAULT_PARTYCHECK_SHOUT_TYPE_TITLE_STRING;
        partyCheckShoutVisibilityTitleString = DEFAULT_PARTYCHECK_SHOUT_VISIBILITY_TITLE_STRING;
        partyCheckPokeShoutAllTitleString = DEFAULT_PARTYCHECK_POKESHOUTALL_TITLE_STRING;
        generalShoutTypeNameString = DefaultShoutType.GENERAL.getName();
        generalShoutTypeDescriptionString = DefaultShoutType.GENERAL.getDescription();
        rideShoutTypeNameString = DefaultShoutType.RIDE.getName();
        rideShoutTypeDescriptionString = DefaultShoutType.RIDE.getDescription();
        ribbonShoutTypeNameString = DefaultShoutType.RIBBON.getName();
        ribbonShoutTypeDescriptionString = DefaultShoutType.RIBBON.getDescription();
        openShoutVisibilityNameString = DefaultShoutVisibility.OPEN.getName();
        openShoutVisibilityDescriptionString = DefaultShoutVisibility.OPEN.getDescription();
        closedShoutVisibilityNameString = DefaultShoutVisibility.CLOSED.getName();
        closedShoutVisibilityDescriptionString = DefaultShoutVisibility.CLOSED.getDescription();
        selfShoutVisibilityNameString = DefaultShoutVisibility.SELF.getName();
        selfShoutVisibilityDescriptionString = DefaultShoutVisibility.SELF.getDescription();
        partyCheckFooterString = DEFAULT_PARTYCHECK_OPTIONED_FOOTER_STRING;
        partyCheckPartyShoutAllFooterString = DEFAULT_PARTYCHECK_PARTYSHOUTALL_FOOTER_STRING;
        partyCheckSplitterString = DEFAULT_PARTYCHECK_SPLITTER_STRING;
    }

    public static String getShinyIconString() { return shinyIconString; }
    public static String getHealthString() { return healthString; }
    public static String getAttackString() { return attackString; }
    public static String getDefenseString() { return defenseString; }
    public static String getSpecialAttackString() { return specialAttackString; }
    public static String getSpecialDefenseString() { return specialDefenseString; }
    public static String getSpeedString() { return speedString; }
    public static String getEmptyHeldItemString() { return emptyHeldItemString; }
    public static String getPartyCheckTitleString() { return partyCheckTitleString; }
    public static String getPartyCheckShoutTypeTitleString() { return partyCheckShoutTypeTitleString; }
    public static String getPartyCheckShoutVisibilityTitleString() { return partyCheckShoutVisibilityTitleString; }
    public static String getPartyCheckPokeShoutAllTitleString() { return partyCheckPokeShoutAllTitleString; }
    public static String getGeneralShoutTypeNameString() { return generalShoutTypeNameString; }
    public static String getGeneralShoutTypeDescriptionString() { return generalShoutTypeDescriptionString; }
    public static String getRideShoutTypeNameString() { return rideShoutTypeNameString; }
    public static String getRideShoutTypeDescriptionString() { return rideShoutTypeDescriptionString; }
    public static String getRibbonShoutTypeNameString() { return ribbonShoutTypeNameString; }
    public static String getRibbonShoutTypeDescriptionString() { return ribbonShoutTypeDescriptionString; }
    public static String getOpenShoutVisibilityNameString() { return openShoutVisibilityNameString; }
    public static String getOpenShoutVisibilityDescriptionString() { return openShoutVisibilityDescriptionString; }
    public static String getClosedShoutVisibilityNameString() { return closedShoutVisibilityNameString; }
    public static String getClosedShoutVisibilityDescriptionString() { return closedShoutVisibilityDescriptionString; }
    public static String getSelfShoutVisibilityNameString() { return selfShoutVisibilityNameString; }
    public static String getSelfShoutVisibilityDescriptionString() { return selfShoutVisibilityDescriptionString; }
    public static String getPartyCheckFooterString() { return partyCheckFooterString; }
    public static String getPartyCheckPartyShoutAllFooterString() { return partyCheckPartyShoutAllFooterString; }
    public static String getPartyCheckSplitterString() { return partyCheckSplitterString; }

    protected static void setShinyIconString(String string) { shinyIconString = string; }
    protected static void setHealthString(String string) { healthString = string; }
    protected static void setAttackString(String string) { attackString = string; }
    protected static void setDefenseString(String string) { defenseString = string; }
    protected static void setSpecialAttackString(String string) { specialAttackString = string; }
    protected static void setSpecialDefenseString(String string) { specialDefenseString = string; }
    protected static void setSpeedString(String string) { speedString = string; }
    protected static void setEmptyHeldItemString(String string) { emptyHeldItemString = string; }
    protected static void setPartyCheckTitleString(String string) { partyCheckTitleString = string; }
    protected static void setPartyCheckShoutTypeTitleString(String string) { partyCheckShoutTypeTitleString = string; }
    protected static void setPartyCheckShoutVisibilityTitleString(String string) { partyCheckShoutVisibilityTitleString = string; }
    protected static void setPartyCheckPokeShoutAllTitleString(String string) { partyCheckPokeShoutAllTitleString = string; }
    protected static void setGeneralShoutTypeNameString(String string) { generalShoutTypeNameString = string; }
    protected static void setGeneralShoutTypeDescriptionString(String string) { generalShoutTypeDescriptionString = string; }
    protected static void setRideShoutTypeNameString(String string) { rideShoutTypeNameString = string; }
    protected static void setRideShoutTypeDescriptionString(String string) { rideShoutTypeDescriptionString = string; }
    protected static void setRibbonShoutTypeNameString(String string) { ribbonShoutTypeNameString = string; }
    protected static void setRibbonShoutTypeDescriptionString(String string) { ribbonShoutTypeDescriptionString = string; }
    protected static void setOpenShoutVisibilityNameString(String string) { openShoutVisibilityNameString = string; }
    protected static void setOpenShoutVisibilityDescriptionString(String string) { openShoutVisibilityDescriptionString = string; }
    protected static void setClosedShoutVisibilityNameString(String string) { closedShoutVisibilityNameString = string; }
    protected static void setClosedShoutVisibilityDescriptionString(String string) { closedShoutVisibilityDescriptionString = string; }
    protected static void setSelfShoutVisibilityNameString(String string) { selfShoutVisibilityNameString = string; }
    protected static void setSelfShoutVisibilityDescriptionString(String string) { selfShoutVisibilityDescriptionString = string; }
    protected static void setPartyCheckFooterString(String string) { partyCheckFooterString = string; }
    protected static void setPartyCheckPartyShoutAllFooterString(String string) { partyCheckPartyShoutAllFooterString = string; }
    protected static void setPartyCheckSplitterString(String string) { partyCheckSplitterString = string; }
}
