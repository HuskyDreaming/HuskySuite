package com.huskydreaming.essentials.commands.teleportation.spawn;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.managers.SpawnManager;
import org.bukkit.entity.Player;

public class DelSpawnCommand extends CommandBase {

    private final SpawnManager spawnManager;

    public DelSpawnCommand(SpawnManager spawnManager) {
        super("delspawn");
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(spawnManager.getSpawn() == null) {
            player.sendMessage("The spawn location has not yet been set.");
        } else {
            player.sendMessage("You have removed the spawn location.");
            spawnManager.delSpawn();
        }
        return false;
    }
}
