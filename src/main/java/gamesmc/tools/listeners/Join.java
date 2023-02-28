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

        if (e.getPlayer().hasPermission("gamesmc.admin") || e.getPlayer().hasPermission("*")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.SPONSOR);
        } else if (e.getPlayer().hasPermission("gamesmc.sponsor")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.SPONSOR);
        } else if (e.getPlayer().hasPermission("gamesmc.evip")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.EVIP);
        } else if (e.getPlayer().hasPermission("gamesmc.mvip+")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.MVIPPLUS);
        } else if (e.getPlayer().hasPermission("gamesmc.mvip")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.MVIP);
        } else if (e.getPlayer().hasPermission("gamesmc.svip+")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.SVIPPLUS);
        } else if (e.getPlayer().hasPermission("gamesmc.svip")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.SVIP);
        } else if (e.getPlayer().hasPermission("gamesmc.vip+")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.VIPPLUS);
        } else if (e.getPlayer().hasPermission("gamesmc.vip")) {
            sendJoinMessage(e, IMP.JOIN_MESSAGES.VIP);
        } else {
            e.setJoinMessage(null);
        }

        if (IMP.RANK_LOBBY_FLY && e.getPlayer().hasPermission("gamesmc.lobbyfly")) {
            e.getPlayer().setAllowFlight(true);
            e.getPlayer().setFlying(true);
        }

        if (IMP.MOTD_MESSAGE) {
            e.getPlayer().sendMessage(getSerializer().deserialize(IMP.JOIN_MESSAGES.MOTD.replace("{PLAYER}", e.getPlayer().getName())));
        }
    }

    private void sendJoinMessage(PlayerJoinEvent e, String message) {
        Component joinMessage = getSerializer().deserialize(message.replace("{PLAYER}", e.getPlayer().getName()));
        e.joinMessage(joinMessage);
    }
}
