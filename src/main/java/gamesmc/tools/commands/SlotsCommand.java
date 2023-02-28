package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SlotsCommand extends CommandBase {
    private static final Pattern SLOTS_PATTERN = Pattern.compile("[1-9]\\d*");

    @Override
    public boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (!p.hasPermission("tools.slots")) {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_NO_PERMISSION));
            return true;
        }
        if (args.length != 1 || !SLOTS_PATTERN.matcher(args[0]).matches()) {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
            return true;
        }
        try {
            int newSlots = Integer.parseInt(args[0]);
            Bukkit.getServer().setMaxPlayers(newSlots);
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.SLOTS_SET.replace("{SLOTS}", String.valueOf(newSlots))));
            Properties properties = new Properties();
            BufferedReader bReader = new BufferedReader(new FileReader("server.properties"));
            properties.load(bReader);
            bReader.close();
            properties.setProperty("max-players", Integer.toString(newSlots));
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("server.properties"));
            properties.store(bWriter, "");
            bWriter.close();
        } catch (IOException e) {
            Logger.getLogger("Can't update server.properties!");
        }
        return true;
    }
}