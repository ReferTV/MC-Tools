package gamesmc.tools.listeners;

import gamesmc.tools.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Settings.IMP.JOIN_LEAVE_LISTENERS) {
            e.quitMessage(null);
        }
    }
}