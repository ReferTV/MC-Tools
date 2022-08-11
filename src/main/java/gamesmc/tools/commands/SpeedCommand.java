package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static gamesmc.tools.Tools.getSerializer;

public class SpeedCommand extends CommandBase {

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
        } try {
            if (p.isFlying()) {
                setFlySpeed(p, Integer.parseInt(args[0]));
            } else if (!p.isFlying()) {
                setWalkSpeed(p, Integer.parseInt(args[0]));
            }
        } catch (Exception e) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_ARG_MUST_BE_INT));
        }
        return false;
    }
    public void setFlySpeed(Player p, double speed) {
        if (speed > 10) {
            double b = speed - 10;
            speed -= b;
        } if (speed < 0) {
            speed -= speed;
        }
        double a = speed / 10;
        float value = (float) a;
        p.setFlySpeed(value);
        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.FLY_SPEED.replace("{1}", String.valueOf(value))));
    }
    public void setWalkSpeed(Player p, double speed) {
        if (speed > 10) {
            double b = speed - 10;
            speed -= b;
        }
        if (speed < 0) {
            speed -= speed;
        }
        double a = speed / 10;
        float value = (float) a;
        p.setWalkSpeed(value);
        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.WALK_SPEED.replace("{1}", String.valueOf(value))));
    }
}