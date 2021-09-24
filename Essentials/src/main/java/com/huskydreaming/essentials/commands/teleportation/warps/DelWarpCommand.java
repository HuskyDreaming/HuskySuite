package com.huskydreaming.essentials.commands.teleportation.warps;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.data.Position;
import com.huskydreaming.essentials.managers.WarpManager;
import org.bukkit.entity.Player;

public class DelWarpCommand extends CommandBase {

    private final WarpManager warpManager;

    public DelWarpCommand(WarpManager warpManager) {
        super("delwarp");
        this.warpManager = warpManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            String string = strings[0];
            Position position = warpManager.getWarp(string);
            if(position == null) {
                player.sendMessage("The warp " + string + " does not seem to exist.");
            } else {
                warpManager.remove(string);
                player.sendMessage("You have successfully deleted the warp " + string);
            }
        } else {
            player.sendMessage("/delwarp [name]");
        }
        return false;
    }
}
