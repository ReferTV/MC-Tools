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
        if (!p.hasPermission("tools.time")) {
            return false;
        }
        if (args.length == 0) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
            return true;
        }
        switch (args[0]) {
            case "dzień", "day":
                p.getWorld().setTime(6000);
                sendTimeTitle(p, Settings.IMP.MESSAGES.TIME_DAY);
                return true;
            case "noc", "night":
                p.getWorld().setTime(18000);
                sendTimeTitle(p, Settings.IMP.MESSAGES.TIME_NIGHT);
                return true;
            case "zatrzymaj", "lock":
                p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_LOCK));
                return true;
            case "wznów", "unlock":
                p.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.TIME_UNLOCK));
                return true;
            case "set", "ustaw":
                if (args.length > 1) {
                    try {
                        long time = Long.parseLong(args[1]);
                        p.getWorld().setTime(time);
                        sendTimeTitle(p, Settings.IMP.MESSAGES.TIME_SET.replace("{TIME}", String.valueOf(time)));
                    } catch (NumberFormatException e) {
                        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_ARG_MUST_BE_INT));
                    }
                    return true;
                }
                break;
        }
        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
        return true;
    }

    private void sendTimeTitle(Player player, String message) {
        Bukkit.getOnlinePlayers().forEach(p ->
                p.showTitle(Title.title(
                        Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                        Tools.getSerializer().deserialize(message),
                        Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                ))
        );
    }
}
