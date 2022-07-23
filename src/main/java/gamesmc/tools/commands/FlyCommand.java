package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class FlyCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("tools.fly") && args.length == 0) {
            if (!p.getAllowFlight()) {
                p.setAllowFlight(true);
            } else {
                p.setAllowFlight(false);
            }
        }
        if (p.hasPermission("tools.fly.others") && args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
            } else if (!Bukkit.getPlayer(args[0]).getAllowFlight()) {
                Bukkit.getPlayer(args[0]).setAllowFlight(true);
            } else {
                Bukkit.getPlayer(args[0]).setAllowFlight(false);
            }
        }
        return false;
    }
}