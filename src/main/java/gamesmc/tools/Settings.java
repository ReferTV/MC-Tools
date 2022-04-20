package gamesmc.tools;

import net.elytrium.java.commons.config.YamlConfig;

public class Settings extends YamlConfig {

    @Ignore
    public static final Settings IMP = new Settings();

    public String PREFIX = "&cGames&fMC&e.pl &8»";
    public String MAIN_TITLE = "&8•● &6☆ Games&fMC&e.pl &6☆ &8●•";

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
    public MESSAGES MESSAGES;

    public static class MESSAGES {

        public String ERROR_NO_PERMISSION = "<color:#ff2317>Nie posiadasz uprawnień do tego polecenia!</color>";
        public String ERROR_TOO_MANY_ARGUMENTS = "<red>Wpisałeś za dużo argumentów";
        public String INVALID_ARGUMENT = "<red>Niepoprawny argument.";
        public String PLAYER_IS_OFFLINE = "<red>Ten gracz jest offline, upewnij się czy wpisałeś dobry nick.";
        public String ONLINE_COMMAND = "<yellow>Aktualnie na serwerze jest <green>{ONLINE} <yellow>graczy online.<newline><newline><white>{LISTA}";
        public String GAMEMODE_CHANGE = "<gray>Twój tryb został zmieniony na <green>{MODE}";
    }
}