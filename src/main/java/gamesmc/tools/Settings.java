package gamesmc.tools;

import net.elytrium.java.commons.config.YamlConfig;

public class Settings extends YamlConfig {

    @Ignore
    public static final Settings IMP = new Settings();

    public String PREFIX = "ayo";

    @Create
    public MAIN MAIN;

    public static class MAIN {

        public String FIELD = "hi";
    }
}