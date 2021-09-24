package com.huskydreaming.essentials.commands.teleportation.warps;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.data.Position;
import com.huskydreaming.essentials.managers.WarpManager;
import org.bukkit.entity.Player;

public class WarpCommand extends CommandBase {

    private final WarpManager warpManager;

    public WarpCommand(WarpManager warpManager) {
        super("warp");
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
                player.teleport(position.serialize());
                player.sendMessage("You have teleported to the "  + string + " warp.");
            }
        } else {
            player.sendMessage(String.valueOf(warpManager.getWarps().keySet()));
        }
        return false;
    }
}
