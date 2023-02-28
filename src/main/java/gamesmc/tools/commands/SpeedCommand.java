package gamesmc.tools.commands;

import gamesmc.tools.Settings;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static gamesmc.tools.Tools.getSerializer;

public class SpeedCommand extends CommandBase {

    private static final float MAX_SPEED = 1f;
    private static final float MIN_SPEED = 0f;

    @Override
    protected boolean onCommand(Player p, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.INVALID_ARGUMENT));
            return true;
        }
        try {
            float speed = Float.parseFloat(args[0]);
            if (p.isFlying()) {
                setFlySpeed(p, speed);
            } else {
                setWalkSpeed(p, speed);
            }
        } catch (NumberFormatException e) {
            p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.ERROR_TOO_ARG_MUST_BE_INT));
        }
        return true;
    }

    private void setFlySpeed(Player p, float speed) {
        speed = Math.max(Math.min(speed, MAX_SPEED), MIN_SPEED);
        p.setFlySpeed(speed);
        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.FLY_SPEED.replace("{1}", String.valueOf(speed))));
    }

    private void setWalkSpeed(Player p, float speed) {
        speed = Math.max(Math.min(speed, MAX_SPEED), MIN_SPEED);
        p.setWalkSpeed(speed);
        p.sendMessage(getSerializer().deserialize(Settings.IMP.MESSAGES.WALK_SPEED.replace("{1}", String.valueOf(speed))));
    }
}
