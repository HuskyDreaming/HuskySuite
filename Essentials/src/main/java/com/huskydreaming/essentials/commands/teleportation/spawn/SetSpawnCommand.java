package com.huskydreaming.essentials.commands.teleportation.spawn;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.managers.SpawnManager;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends CommandBase {

    private final SpawnManager spawnManager;

    public SetSpawnCommand(SpawnManager spawnManager) {
        super("setspawn");
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        spawnManager.setSpawn(player);
        player.sendMessage("You have set the global spawn.");
        return false;
    }
}
