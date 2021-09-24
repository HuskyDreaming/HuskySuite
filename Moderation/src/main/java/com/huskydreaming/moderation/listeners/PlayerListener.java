package com.huskydreaming.moderation.listeners;

import com.huskydreaming.core.resources.base.ListenerBase;
import com.huskydreaming.moderation.data.Punishment;
import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends ListenerBase {

    private final PunishmentManager punishmentManager;

    public PlayerListener(PunishmentManager punishmentManager) {
        this.punishmentManager = punishmentManager;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        punishmentManager.deserialize(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        punishmentManager.serialize(event.getPlayer());
    }

    @EventHandler
    public void onMute(AsyncPlayerChatEvent event) {
        if(punishmentManager.hasType(event.getPlayer(), Punishment.Type.MUTE)) {
            event.setCancelled(true);
        }
    }
}
