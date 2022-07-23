package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.regex.Pattern;

public class HeadCommand extends CommandBase {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {

        if (p.hasPermission("tools.head")) {
            if (args.length == 0) {
                p.showTitle(Title.title(
                        Tools.getSerializer().deserialize(Settings.IMP.MAIN_TITLE),
                        Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PROVIDE_PLAYER_NAME),
                        Settings.IMP.MAIN.TITLE_SETTINGS.toTimes()
                ));
            }
            if (args.length == 1) {
                if (!NAME_PATTERN.matcher(args[0]).matches()) {
                    p.sendMessage(Settings.IMP.MESSAGES.INVAILD_PLAYER_NAME);
                } else {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta head = (SkullMeta) skull.getItemMeta();
                    head.setOwner(args[0]);
                    head.setDisplayName("§eGłowa gracza " + args[0]);
                    skull.setItemMeta(head);
                    p.getPlayer().getInventory().addItem(skull);
                }
            }
        }
        return false;
    }
}
