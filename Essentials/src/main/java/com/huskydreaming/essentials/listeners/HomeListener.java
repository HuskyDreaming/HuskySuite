package com.huskydreaming.essentials.listeners;

import com.huskydreaming.core.resources.base.ListenerBase;
import com.huskydreaming.essentials.managers.HomeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HomeListener extends ListenerBase {

    private final HomeManager homeManager;

    public HomeListener(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        homeManager.deserialize(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        homeManager.serialize(event.getPlayer());
    }
}
