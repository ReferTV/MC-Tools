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
                p.sendMessage(cmd.getUsage());
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
            if (args.length == 2) {
                Player gracz = Bukkit.getPlayer(args[1]);
                if (gracz == null) {
                    p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                    return false;
                }
                gracz.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())));
                gracz.setGameMode(mode);
                gracz.sendTitle(Title.title(Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE), Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())), 20, 20, 20));
                return true;
            }
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())));
            p.sendTitle(Title.title(Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE), Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.GAMEMODE_CHANGE.replace("{MODE}", mode.name())), 20, 20, 20));
            p.setGameMode(mode);
            return true;
        }
        return false;
    }
}