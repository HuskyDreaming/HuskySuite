package com.huskydreaming.essentials.managers;

import com.huskydreaming.core.resources.Registries;
import com.huskydreaming.core.resources.interfaces.Manager;
import com.huskydreaming.core.resources.interfaces.Registry;
import com.huskydreaming.core.utilities.Json;
import com.huskydreaming.essentials.commands.teleportation.spawn.DelSpawnCommand;
import com.huskydreaming.essentials.commands.teleportation.spawn.SpawnCommand;
import com.huskydreaming.essentials.commands.teleportation.spawn.SetSpawnCommand;
import com.huskydreaming.essentials.data.Position;
import com.huskydreaming.essentials.listeners.SpawnListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SpawnManager implements Manager {

    private final Plugin plugin;
    private Position spawn;

    public SpawnManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void deserialize() {
        spawn = Json.read(plugin, "spawn", Position.class);
        Registries.register(plugin,
                // Commands
                new DelSpawnCommand(this),
                new SetSpawnCommand(this),
                new SpawnCommand(this),
                // Listeners
                new SpawnListener(this)

        );
    }

    @Override
    public void serialize() {
        Json.write(plugin, "spawn", spawn);
    }

    public void setSpawn(Player player) {
        this.spawn = new Position(player.getLocation());
    }

    public void delSpawn() {
        this.spawn = null;
    }

    public Position getSpawn() {
        return spawn;
    }
}
