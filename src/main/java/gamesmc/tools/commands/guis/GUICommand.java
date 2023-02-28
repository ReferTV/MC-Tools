package gamesmc.tools.commands.guis;

import gamesmc.tools.Settings;
import gamesmc.tools.Tools;
import gamesmc.tools.commands.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GUICommand extends CommandBase {

    private final Type type;

    public GUICommand(Type type) {
        this.type = type;
    }

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0 && p.hasPermission(type.getPermission())) {
            type.open(p);
        } else if (args.length == 1 && p.hasPermission(type.getPermissionOthers())) {
            Player gracz = Bukkit.getPlayer(args[0]);
            if (gracz == null) {
                p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_IS_OFFLINE));
                return false;
            }
            type.open(gracz);
        } else if (args.length > 1) {
            p.sendMessage(Tools.getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_MANY_ARGUMENTS));
        }
        return false;
    }

    public enum Type {
        CRAFTING_TABLLE("tools.cratfingtable", "tools.cratfingtable.others") {
            @Override
            public void open(Player player) {
                player.openWorkbench(null, true);
            }
        },
        ENDER_CHEST("tools.ender", "tools.ender.others") {
            @Override
            public void open(Player player) {
                player.openInventory(player.getEnderChest());
            }
        },
        ANVIL("tools.anvil", "tools.anvil.others") {
            @Override
            public void open(Player player) {
                player.openAnvil(null, true);
            }
        },
        GRIDSTONE("tools.gridstone", "tools.gridstone.others") {
            @Override
            public void open(Player player) {
                player.openGrindstone(null, true);
            }
        },
        STONECUTTER("tools.stonecutter", "tools.stonecutter.others") {
            @Override
            public void open(Player player) {
                player.openStonecutter(null, true);
            }
        },
        SMITHING_TABLE("tools.smithingtable", "tools.smithingtable.others") {
            @Override
            public void open(Player player) {
                player.openSmithingTable(null, true);
            }
        },
        CARTOGRAPY_TABLE("tools.cartograpytable", "tools.cartograpytable.others") {
            @Override
            public void open(Player player) {
                player.openCartographyTable(null, true);
            }
        },
        LOOM("tools.loom", "tools.loom.others") {
            @Override
            public void open(Player player) {
                player.openLoom(null, true);
            }
        };

        private final String permission;
        private final String permissionOthers;

        Type(String permission, String permissionOthers) {
            this.permission = permission;
            this.permissionOthers = permissionOthers;
        }

        public String getPermission() {
            return permission;
        }

        public String getPermissionOthers() {
            return permissionOthers;
        }

        public abstract void open(Player player);
    }

}
