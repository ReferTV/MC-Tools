package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FlyCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("tools.fly")) {
            if (args.length == 0) {
                toggleFlight(p);
            } else if (p.hasPermission("tools.fly.others")) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                } else {
                    toggleFlight(target);
                }
            } else {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.COMMAND_SYNTAX.replace("{ARGS}", cmd.getUsage())));
            }
        }
        return true;
    }

    private void toggleFlight(Player player) {
        player.setAllowFlight(!player.getAllowFlight());
        String message = player.getAllowFlight() ?
                Settings.IMP.MESSAGES.FLY_ENABLE.replace("{PLAYER}", player.getName())
                : Settings.IMP.MESSAGES.FLY_DISABLE.replace("{PLAYER}", player.getName());
        player.sendMessage(Tools.getSerializer().deserialize(message));
    }
}