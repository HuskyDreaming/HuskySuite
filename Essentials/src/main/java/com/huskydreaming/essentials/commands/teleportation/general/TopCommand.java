package com.huskydreaming.essentials.commands.teleportation.general;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TopCommand extends CommandBase {

    protected TopCommand() {
        super("top");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        Location location = player.getLocation();
        World world = location.getWorld();
        if(world != null) {
            Block block = world.getHighestBlockAt(location);
            player.teleport(block.getLocation().add(0.5, 1, 0.5));
            player.sendMessage("You have been teleported to the top.");
        }
        return false;
    }
}
