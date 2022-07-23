package gamesmc.tools.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class VanishCommand extends CommandBase {
    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            p.hidePlayer(p);
        } else {
            p.showPlayer(p);
        }
        return true;
    }
}
