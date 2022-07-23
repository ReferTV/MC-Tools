package gamesmc.tools.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static gamesmc.tools.Settings.IMP;
import static gamesmc.tools.Tools.getSerializer;

public class Join implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().showTitle(Title.title(getSerializer().deserialize(IMP.MAIN_TITLE), getSerializer().deserialize(IMP.JOIN_SUBTITLE), IMP.MAIN.TITLE_SETTINGS.toTimes()));
        if (IMP.RANK_LOBBY_FLY) {
            if (e.getPlayer().hasPermission("gamesmc.vip")) {
                e.getPlayer().setAllowFlight(true);
                e.getPlayer().setFlying(true);
            }
        if (IMP.MOTD_MESSAGE) { e.getPlayer().sendMessage(getSerializer().deserialize(IMP.JOIN_MESSAGES.MOTD.replace("{PLAYER}", e.getPlayer().getName()))); }
        } if (e.getPlayer().hasPermission("gamesmc.admin") || e.getPlayer().hasPermission("*")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.SPONSOR.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.sponsor")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.SPONSOR.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.evip")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.EVIP.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.mvip+")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.MVIPPLUS.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.mvip")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.MVIP.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.svip+")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.SVIPPLUS.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.svip")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.SVIP.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.vip+")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.VIPPLUS.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else if (e.getPlayer().hasPermission("gamesmc.vip")) {
            Component joinMessage = getSerializer().deserialize(IMP.JOIN_MESSAGES.VIP.replace("{PLAYER}", e.getPlayer().getName()));
            e.joinMessage(joinMessage);
        } else { e.setJoinMessage(null); }
    }
}