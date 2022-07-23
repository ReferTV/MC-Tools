package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Collection;

public class OnlineCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
    if (p.hasPermission("tools.online")) {
        StringBuilder online = new StringBuilder();
        final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            if (!((Player) p).canSee(player))
                continue;
            if (online.length() > 0) {
                online.append(", ");
            }
            online.append(player.getDisplayName());
        }
        p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ONLINE_COMMAND.replace("{ONLINE}", String.valueOf(players.size())).replace("{LIST}", String.valueOf(online))));
    }
    return false;
    }
}
