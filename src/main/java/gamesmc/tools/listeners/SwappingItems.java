package gamesmc.tools.listeners;

import gamesmc.tools.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwappingItems implements Listener {

    @EventHandler
    protected void onClick(PlayerSwapHandItemsEvent e) {
        if (Settings.IMP.PREVENT_SWAPPING_TO_THE_OFFHAND) {
            e.setCancelled(true);
        }
    }
}
