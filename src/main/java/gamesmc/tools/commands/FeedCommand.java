package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FeedCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission("tools.food")) {
            p.setFoodLevel(20);
            p.showTitle(Title.title(
                    Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                    Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.HEAL),
                    Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
            ));
            return true;
        } else if (args.length == 1 && p.hasPermission("tools.food.others")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                return true;
            }
            target.setFoodLevel(20);
            target.showTitle(Title.title(
                    Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                    Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.HEAL),
                    Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
            ));
            return true;
        }
        return false;
    }
}
