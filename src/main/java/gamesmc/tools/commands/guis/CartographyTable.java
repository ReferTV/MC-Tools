package gamesmc.tools.commands.guis;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import gamesmc.tools.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CartographyTable extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission("tools.cartographytable")) {
            p.openCartographyTable(null, true);
        } if (args.length == 1 && p.hasPermission("tools.cartographytable.others")) {
            Player gracz = Bukkit.getPlayer(args[0]);
            if (gracz == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                return false; }
            gracz.openCartographyTable(null, true);
        } if (args.length > 1) {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_MANY_ARGUMENTS));
        }
        return false;
    }
}
