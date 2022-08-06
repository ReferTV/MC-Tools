package gamesmc.tools;

import net.elytrium.java.commons.config.YamlConfig;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.Ticks;

public class Settings extends YamlConfig {

    @Ignore
    public static final Settings IMP = new Settings();

    public String PREFIX = "<red>Games<white>MC<yellow>.pl <dark_gray>»";
    public String MAIN_TITLE = "<dark_gray>•● <gold>☆ Games<white>MC<yellow>.pl <gold>☆ <dark_gray>●•";
    public String JOIN_SUBTITLE = "<gold>Lobby <white>#<aqua>1 <dark_gray>|</dark_gray> <white>Kliknij w kompas aby wybrać tryb";
    @Comment("Set false if you wan't to show join and quit messages from this plugin.")
    public boolean JOIN_LEAVE_LISTENERS = true;
    public boolean MOTD_MESSAGE = true;

    public boolean RANK_LOBBY_FLY = false;

    public boolean PREVENT_SWAPPING_TO_THE_OFFHAND = false;


    @Comment({
            "Available serializers:",
            "LEGACY_AMPERSAND - \"&c&lExample &c&9Text\".",
            "LEGACY_SECTION - \"§c§lExample §c§9Text\".",
            "MINIMESSAGE - \"<bold><red>Example</red> <blue>Text</blue></bold>\". (https://webui.adventure.kyori.net/)",
            "GSON - \"[{\"text\":\"Example\",\"bold\":true,\"color\":\"red\"},{\"text\":\" \",\"bold\":true},{\"text\":\"Text\",\"bold\":true,\"color\":\"blue\"}]\". (https://minecraft.tools/en/json_text.php/)",
            "GSON_COLOR_DOWNSAMPLING - Same as GSON, but uses downsampling."
    })
    public String SERIALIZER = "MINIMESSAGE";

    @Create
    public MAIN MAIN;

    @Comment("Don't use \\n, use {NL} for new line, and {PRFX} for prefix.")
    public static class MAIN {

        @Create
        public Settings.MAIN.TITLE_SETTINGS TITLE_SETTINGS;

        public static class TITLE_SETTINGS {

            public int FADE_IN = 10;
            public int STAY = 70;
            public int FADE_OUT = 20;

            public Title.Times toTimes() {
                return Title.Times.times(Ticks.duration(this.FADE_IN), Ticks.duration(this.STAY), Ticks.duration(this.FADE_OUT));
            }
        }
    }


    @Create
    public MESSAGES MESSAGES;

    public static class MESSAGES {
        public String ERROR_NO_PERMISSION = "<color:#ff2317>Nie posiadasz uprawnień do tego polecenia!</color>";
        public String ERROR_TOO_MANY_ARGUMENTS = "<red>Wpisałeś za dużo argumentów";
        public String ERROR_TOO_ARG_MUST_BE_INT = "<red>Argument musi być liczbą całkowitą.";
        public String INVALID_ITEM = "<red>Nie możesz tego zrobić na tym przedmiocie.";
        public String COMMAND_SYNTAX = "<red>Poprawne argumenty to: {ARGS}";
        public String INVALID_ARGUMENT = "<red>Niepoprawny argument.";
        public String INVAILD_PLAYER_NAME = "<red>Niepoprawna nazwa gracza.";
        public String PROVIDE_PLAYER_NAME = "<red>Podaj nazwę gracza jako argument.";
        public String PLAYER_IS_OFFLINE = "<red>Ten gracz jest offline, upewnij się czy wpisałeś dobry nick.";
        public String ONLINE_COMMAND = "<yellow>Aktualnie na serwerze jest <green>{ONLINE} <yellow>graczy online.<newline><newline><white>{LIST}";
        public String GAMEMODE_CHANGE = "<gray>Twój tryb został zmieniony na <green>{MODE}";
        public String SLOTS_SET = "<green>Ustawiłeś liczbę slotów na <white>{SLOTS}.";
        public String HEAL = "<white>Zostałeś uleczony!";
        public String TIME_SET = "<green>Czas na serwerze został zmieniony na {TIME}.";
        public String TIME_DAY = "<green>Czas na serwerze został zmieniony na dzień.";
        public String TIME_NIGHT = "<green>Czas na serwerze został zmieniony na noc.";
        public String TIME_LOCK = "<green>Wstrzymałeś czas na serwerze";
        public String TIME_UNLOCK = "<green>Ruszyłeś czas na serwerze";

        public String PLAYER_INFO = "<green>Nazwa gracza <white>{PLAYER} <gray>»{NL}<gray>› <gray>Nazwa hosta: <gold>{host}{NL}<gray>› <gray>Client brand:<gold> {brand}{NL}<gray>› <gray>Nazwa texturepacka: <gold>{texturepack}{NL}<gray>› <gray>Render distance: <gold>{viewdistance}{NL}<gray>› <gray>Simulation distance: <gold>{simdistance}{NL}";

    }

    @Create
    public JOIN_MESSAGES JOIN_MESSAGES;
    public static class JOIN_MESSAGES {
        public String MOTD = "<white>Witaj <gold>{PLAYER} <white>na <gold>Games<white>MC<yellow>.pl{NL}" +
                "{NL}<gradient:white:red:1>Serwer jest w trakcie przekodowywania, aby dowiedzieć się więcej, dołącz na naszego discorda " +
                "<b><hover:show_text:'Kliknij tutaj aby dołączyć na nasz serwer discord.'>" +
                "<click:open_url:'https://discord.gg/2pqaQxF'>KLIKNIJ TUTAJ</click></hover></b>";
        public String VIP = "<dark_gray>* <gradient:white:yellow:1>VIP <gold>{PLAYER} <gray>dołączył na serwer.";
        public String VIPPLUS = "<dark_gray>* <gradient:white:yellow:1>VIP+ <gold>{PLAYER} <gray>dołączył na serwer.";
        public String SVIP = "<dark_gray>* <gradient:white:gold:1>SVIP <gold>{PLAYER} <gray>dołączył na serwer.";
        public String SVIPPLUS = "<dark_gray>* <gradient:white:gold:1>SVIP+ <gold>{PLAYER} <gray>dołączył na serwer.";
        public String MVIP = "<dark_gray>* <gradient:white:blue:1>MVIP <gold>{PLAYER} <gray>dołączył na serwer.";
        public String MVIPPLUS = "<dark_gray>* <gradient:white:blue:1>MVIP+ <gold>{PLAYER} <gray>dołączył na serwer.";
        public String EVIP = "<dark_gray>* <gradient:white:dark_purple:1>EVIP <gold>{PLAYER} <gray>dołączył na serwer.";
        public String SPONSOR = "{NL}<dark_gray>* <gold>☆ <yellow>SPONSOR <gold>☆ <gold>{PLAYER} <gray>dołączył na serwer.{NL}";
    }
}