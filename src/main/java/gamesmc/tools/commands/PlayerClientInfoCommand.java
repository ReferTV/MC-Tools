package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static gamesmc.tools.Tools.getSerializer;

public class PlayerClientInfoCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (!p.hasPermission("tools.playerinfo")) {
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.PROVIDE_PLAYER_NAME));
            return true;
        }

        if (args.length == 1) {
            p.sendMessage(getPlayerInfo(p, args[0]));
            return true;
        }

        return false;
    }

    private @NotNull Component getPlayerInfo(Player p, String playerName) {
        return getSerializer().deserialize(Settings.IMP.MESSAGES.PLAYER_INFO
                .replace("{host}", Objects.requireNonNull(p.getVirtualHost()).getHostName())
                .replace("{brand}", Objects.requireNonNull(p.getClientBrandName()))
                .replace("{texturepack}", String.valueOf(p.getResourcePackStatus()))
                .replace("{viewdistance}", String.valueOf(p.getClientViewDistance()))
                .replace("{simdistance}", String.valueOf(p.getSimulationDistance()))
                .replace("{PLAYER}", playerName)
        );
    }
}
