package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class HealCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission("tools.heal")) {
            p.setHealth(20);
            p.setFoodLevel(20);
            p.showTitle(Title.title(Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE), Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.HEAL), Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()));
        } else if (p.hasPermission("tools.heal.others") && args.length == 1) {
            if (Bukkit.getPlayer(args[0]) == null) { p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE)); }
                Bukkit.getPlayer(args[0]).setHealth(20);
                Bukkit.getPlayer(args[0]).setFoodLevel(20);
                Bukkit.getPlayer(args[0]).showTitle(Title.title(Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE), Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.HEAL), Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()));
        }
        return true;
    }
}
