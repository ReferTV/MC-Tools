package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GamemodeCommand extends CommandBase {
    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {

        if (p.hasPermission("gamesmc.gamemode")) {
            if (args.length == 0) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.COMMAND_SYNTAX.replace("{ARGS}", cmd.getUsage())));
                return true;
            }
            GameMode mode = null;
            try {
                mode = GameMode.getByValue(Integer.parseInt(args[0]));
            } catch (Exception e) {
                for (GameMode modes : GameMode.values()) {
                    if (modes.name().startsWith(args[0].toUpperCase())) {
                        mode = modes;
                        break;
                    }
                }
            }
            if (mode == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
                return true;
            }
            if (args.length == 2 && p.hasPermission("gamesmc.gamemode.others")) {
                Player gracz = Bukkit.getPlayer(args[1]);
                if (gracz == null) {
                    p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                    return false;
                }
                gracz.setGameMode(mode);
                gracz.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())));
                gracz.showTitle(Title.title(
                        Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                        Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())),
                        Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                ));
                return true;
            }
            p.setGameMode(mode);
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())));
            p.showTitle(Title.title(
                    Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                    Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())),
                    Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
            ));
           return true;
        }
        return false;
    }
}