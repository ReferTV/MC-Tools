package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import net.elytrium.java.commons.mc.serialization.Serializers;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Objects;

public class tools extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        p.sendMessage(Serializers.valueOf(Settings.IMP.SERIALIZER).getSerializer().deserialize("<blue>Test</blue>"));
        return true;
    }
}