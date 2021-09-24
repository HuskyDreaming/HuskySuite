package com.huskydreaming.essentials.commands.teleportation.spawn;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.data.Position;
import com.huskydreaming.essentials.managers.SpawnManager;
import org.bukkit.entity.Player;

public class SpawnCommand extends CommandBase {

    private final SpawnManager spawnManager;

    public SpawnCommand(SpawnManager spawnManager) {
        super("spawn");
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        Position position = spawnManager.getSpawn();
        if(position == null) {
            player.sendMessage("The spawn location has not yet been set.");
        } else {
            player.teleport(position.serialize());
            player.sendMessage("You have been teleported to spawn.");
        }
        return false;
    }
}
