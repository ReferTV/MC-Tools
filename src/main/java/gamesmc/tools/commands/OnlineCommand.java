package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class OnlineCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("tools.online")) {
            String online = Bukkit.getOnlinePlayers().stream()
                    .filter(player -> p.canSee(player))
                    .map(player -> player.getName())
                    .collect(Collectors.joining(", "));
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ONLINE_COMMAND
                    .replace("{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replace("{MAX}", String.valueOf(Bukkit.getMaxPlayers()))
                    .replace("{LIST}", online)));
        }
        return false;
    }
}
