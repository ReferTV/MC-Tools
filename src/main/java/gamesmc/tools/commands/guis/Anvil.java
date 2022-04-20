package gamesmc.tools.commands.guis;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import gamesmc.tools.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Anvil extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission("tools.anvil")) {
            p.openAnvil(null, true);
        } if (args.length == 1 && p.hasPermission("tools.anvil.others")) {
            Player gracz = Bukkit.getPlayer(args[0]);
            if (gracz == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                return false; }
            gracz.openAnvil(null, true);
        } if (args.length > 1) {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_MANY_ARGUMENTS));
        }
        return false;
    }
}