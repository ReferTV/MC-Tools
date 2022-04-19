package gamesmc.tools;

import java.util.List;
import net.elytrium.java.commons.config.YamlConfig;

public class Settings extends YamlConfig {

    public static final Settings IMP = new Settings();

    public String PREFIX = "ayo";

    @Create
    public MAIN MAIN;

    public static class MAIN {

        public String FIELD = "hi";
    }
}