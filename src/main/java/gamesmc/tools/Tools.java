package gamesmc.tools;

import gamesmc.tools.commands.*;
import gamesmc.tools.commands.guis.*;
import gamesmc.tools.listeners.Join;
import gamesmc.tools.listeners.Leave;
import net.elytrium.java.commons.mc.serialization.Serializer;
import net.elytrium.java.commons.mc.serialization.Serializers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Tools extends JavaPlugin {

    private static Tools instance;
    private static Serializer serializer;

    public static Tools getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        Settings.IMP.reload(new File(this.getDataFolder(), "config.yml"), Settings.IMP.PREFIX);

        instance = this;

        this.getLogger().info("GamesMC Tools " + this.getDescription().getVersion() + " by " + this.getDescription().getAuthors());
        this.registerCommands();

        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Leave(), this);
        getLogger().info("Ładuję listenery...");

        ComponentSerializer<Component, Component, String> serializer = Serializers.valueOf(Settings.IMP.SERIALIZER).getSerializer();
        if (serializer == null) {
            this.getLogger().info("The specified serializer could not be founded, using default. (LEGACY_AMPERSAND)");
            setSerializer(new Serializer(Objects.requireNonNull(Serializers.LEGACY_AMPERSAND.getSerializer())));
        } else {
            setSerializer(new Serializer(serializer));
        }

    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        // GUIS
        new CraftingTable().register(getCommand("crafting"));
        new Anvil().register(getCommand("anvil"));
        new CartographyTable().register(getCommand("cartographytable"));
        new SmithingTable().register(getCommand("smitchingtable"));
        new Loom().register(getCommand("loom"));
        new Grindstone().register(getCommand("grindstone"));
        new Ender().register(getCommand("ender"));
        new Invsee().register(getCommand("invsee"));

        //Commands
        new ToolsCommand().register(getCommand("tools"));
        new OnlineCommand().register(getCommand("online"));
        new SlotsCommand().register(getCommand("setslots"));
        new GamemodeCommand().register(getCommand("gamemode"));
        new HeadCommand().register(getCommand("head"));
        new FlyCommand().register(getCommand("fly"));
        new VanishCommand().register(getCommand("vanish"));
        new HealCommand().register(getCommand("heal"));
        new FeedCommand().register(getCommand("feed"));
        new TimeCommand().register(getCommand("time"));
    }

    public void reloadPlugin() {
        Settings.IMP.reload(new File(this.getDataFolder().toPath().toFile().getAbsoluteFile(), "config.yml"));
    }

    private static void setSerializer(Serializer serializer) {
        Tools.serializer = serializer;
    }

    public static Serializer getSerializer() {
        return serializer;
    }
}
