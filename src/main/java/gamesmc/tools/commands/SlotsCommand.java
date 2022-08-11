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
        if (p.hasPermission("tools.slots")) {
            if (args.length == 1) {
                if (!SLOTS_PATTERN.matcher(args[0]).matches()) {
                    p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_ARG_MUST_BE_INT));
                }
                try {
                    Bukkit.getServer().setMaxPlayers(Integer.parseInt(args[0]));
                    p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.SLOTS_SET.replace("{SLOTS}", String.valueOf(Bukkit.getServer().getMaxPlayers()))));
                    Properties properties = new Properties();
                    BufferedReader bReader = new BufferedReader(new FileReader("server.properties"));
                    properties.load(bReader);
                    bReader.close();
                    properties.setProperty("max-players", Integer.valueOf(args[0]).toString());
                    BufferedWriter bWriter = new BufferedWriter(new FileWriter("server.properties"));
                    properties.store(bWriter, "");
                    bWriter.close();
                } catch (IOException IOE) {
                    Logger.getLogger("Can't update server.properties!");
                }
            } else {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
            }
        } else {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_NO_PERMISSION));
        }
        return true;
    }
}