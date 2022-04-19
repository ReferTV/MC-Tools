package gamesmc.tools;

import org.bukkit.plugin.java.JavaPlugin;
import gamesmc.tools.Settings;

import java.io.File;

public final class Tools extends JavaPlugin {


    @Override
    public void onEnable() {
        Settings.IMP.reload(new File(this.getDataFolder(), "config.yml"), Settings.IMP.PREFIX);
    }

    @Override
    public void onDisable() {
    }
}
