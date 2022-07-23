package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static gamesmc.tools.Tools.getSerializer;

public class ToolsCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
    if (args.length == 0) {
        p.sendMessage(getSerializer().deserialize("<color:#b6eb17>This server using GamesMC Tools.</color>\n" +
                "Designed by " + Tools.getInstance().getDescription().getAuthors() + " \n" +
                "\n" +
                "Avaliable subcommands:\n" +
                "⁜ <click:run_command:/tools reload>/reload</click>\n" +
                "⁜ <hover:show_text:'<gray>Check the text with the selected serializer in the configuration.'>/tools parse <text> "));
        return false;
    }
    switch(args[0]) {
        case "reload" -> {
            if (p.hasPermission("tools.reload")) {
                try {
                    Tools.getInstance().reloadPlugin();
                    Tools.getInstance().getLogger().info("Reloaded config & messages.");
                    p.sendMessage("Configuration reloaded!");
                } catch (Exception e) {
                    p.sendMessage("Configuration reloaded failed. Check console to see errors.");
                    e.printStackTrace();
                }
            } else {
                p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_NO_PERMISSION));
                return false;
            }
            }
        case "parse" -> {
            if (p.hasPermission("tools.parse")) {
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < args.length; ++i) {
                    str.append(args[i] + " ");
                }
                p.sendMessage(getSerializer().deserialize(str.toString()));
            }
        }
        default -> {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
        }
        }
        return false;
    }
}