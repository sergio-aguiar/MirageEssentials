package com.sergioaguiar.mirageessentials.config.chatparser.settings;

public class ChatSettings
{
    public enum PartyCheckLayout
    {
        DEFAULT("default"),
        MIRAGE("mirage");

        private String layoutName;

        PartyCheckLayout(String layoutName)
        {
            this.layoutName = layoutName;
        }

        public String getLayoutName()
        {
            return layoutName;
        }

        public static PartyCheckLayout getLayoutByName(String layoutName)
        {
            String fixedName = layoutName.toLowerCase().trim();

            switch (fixedName) {
                case "default":
                    return PartyCheckLayout.DEFAULT;
                case "mirage":
                    return PartyCheckLayout.MIRAGE;
                default:
                    return PartyCheckLayout.DEFAULT;
            }
        }
    }

    protected static final boolean DEFAULT_PARSE_NON_PLAYER_MESSAGES = false;
    protected static final boolean DEFAULT_SHOW_ORIGINAL_ITEM_NAMES = false;

    protected static final PartyCheckLayout DEFAULT_PARTYCHECK_LAYOUT = PartyCheckLayout.DEFAULT;
    protected static final boolean DEFAULT_SHOW_PARTYCHECK_GUI_FRAME_BLOCKS = true;
    protected static final boolean DEFAULT_SHOW_PARTYCHECK_PLAYER_HEADS = true;

    private static boolean parseNonPlayerMessages;
    private static boolean showOriginalItemNames;

    private static PartyCheckLayout partycheckLayout;
    private static boolean showPartyCheckGUIFrameBlocks;
    private static boolean showPartyCheckPlayerHeads;

    public static void setDefaults()
    {
        parseNonPlayerMessages = DEFAULT_PARSE_NON_PLAYER_MESSAGES;
        showOriginalItemNames = DEFAULT_SHOW_ORIGINAL_ITEM_NAMES;

        partycheckLayout = DEFAULT_PARTYCHECK_LAYOUT;
        showPartyCheckGUIFrameBlocks = DEFAULT_SHOW_PARTYCHECK_GUI_FRAME_BLOCKS;
        showPartyCheckPlayerHeads = DEFAULT_SHOW_PARTYCHECK_PLAYER_HEADS;
    }

    public static boolean shouldParseNonPlayerMessages() { return parseNonPlayerMessages; }
    public static boolean shouldShowOriginalItemNames() { return showOriginalItemNames; }
    public static PartyCheckLayout getPartyCheckLayout() { return partycheckLayout; }
    public static boolean shouldShowPartyCheckGUIFrameBlocks() { return showPartyCheckGUIFrameBlocks; }
    public static boolean shouldShowPartyCheckPlayerHeads() { return showPartyCheckPlayerHeads; }

    protected static void setParseNonPlayerMessages(boolean enabled) { parseNonPlayerMessages = enabled; }
    protected static void setShowOriginalItemNames(boolean enabled) { showOriginalItemNames = enabled; }
    protected static void setPartyCheckLayout(PartyCheckLayout layout) { partycheckLayout = layout; }
    protected static void setShowPartyCheckGUIFrameBlocks(boolean enabled) { showPartyCheckGUIFrameBlocks = enabled; }
    protected static void setShowPartyCheckPlayerHeads(boolean enabled) { showPartyCheckPlayerHeads = enabled; }
}
