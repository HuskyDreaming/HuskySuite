package com.huskydreaming.essentials.listeners;

import com.huskydreaming.core.resources.base.ListenerBase;
import com.huskydreaming.essentials.data.Position;
import com.huskydreaming.essentials.managers.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnListener extends ListenerBase {

    private final SpawnManager spawnManager;

    public SpawnListener(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPlayedBefore()) {
            Position position = spawnManager.getSpawn();
            if(position != null) player.teleport(position.serialize());
        }
    }
}
