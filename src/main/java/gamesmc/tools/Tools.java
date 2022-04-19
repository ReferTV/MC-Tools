package gamesmc.tools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tools extends JavaPlugin {

    // GamesMC Tools - Recode of 2022

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage("\n" +
                "§f  ____                               __  __   ____   _____                _      \n" +
                " / ___|  __ _  _ __ ___    ___  ___ |  \\/  | / ___| |_   _|  ___    ___  | | ___ \n" +
                "| |  _  / _` || '_ ` _ \\  / _ \\/ __|| |\\/| || |       | |   / _ \\  / _ \\ | |/ __|\n" +
                "| |_| || (_| || | | | | ||  __/\\__ \\| |  | || |___    | |  | (_) || (_) || |\\__ \\\n" +
                " \\____| \\__,_||_| |_| |_| \\___||___/|_|  |_| \\____|   |_|   \\___/  \\___/ |_||___/\n" +
                "                                                        §fby " + this.getDescription().getAuthors() + " §a" + this.getDescription().getVersion() + "\n§7Plugin z narzędziami dla administratorów\n§7Załadowałem plugin w §a" + (System.currentTimeMillis() - startTime) + "ms§7.\n§7Wykryta wersja serwera: " + Bukkit.getVersion().split("-")[1]);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
