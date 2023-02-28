package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission("tools.heal")) {
            healPlayer(p);
        } else if (args.length == 1 && p.hasPermission("tools.heal.others")) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
            } else {
                healPlayer(targetPlayer);
            }
        } else {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_NO_PERMISSION));
        }
        return true;
    }

    private void healPlayer(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.showTitle(Title.title(
                Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.HEAL),
                Settings.IMP.MAIN.TITLE_SETTINGS.toTimes())
        );
    }
}
