package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static gamesmc.tools.Tools.getSerializer;

public class TimeCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (p.hasPermission("tools.time")) {
            if (args.length == 0) {
                p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
            } else {
                switch (args[0]) {
                    case "dzień", "day": {
                        p.getWorld().setTime(6000);
                        Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                                Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_DAY),
                                Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                        )));
                        return true;
                    }
                    case "noc", "night": {
                        p.getWorld().setTime(18000);
                        Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                                Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_NIGHT),
                                Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                        )));
                        return true;
                    }
                    case "zatrzymaj", "lock": {
                        p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_LOCK));
                        return true;
                    }
                    case "wznów", "unlock": {
                        p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_UNLOCK));
                        return true;
                    }
                    case "set", "ustaw": {
                        try {
                            p.getWorld().setTime(Integer.parseInt(args[1]));
                            Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                    Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                                    Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_SET.replace("{TIME}", args[1])),
                                    Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                            )));
                        } catch (NumberFormatException e) {
                            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_ARG_MUST_BE_INT));
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
