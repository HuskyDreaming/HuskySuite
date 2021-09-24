package com.huskydreaming.essentials.commands.teleportation.warps;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.managers.WarpManager;
import org.bukkit.entity.Player;

public class SetWarpCommand extends CommandBase {

    private final WarpManager warpManager;

    public SetWarpCommand(WarpManager warpManager) {
        super("setwarp");
        this.warpManager = warpManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            String string = strings[0];
            warpManager.set(string, player);
            player.sendMessage("You have set the " + string + " warp at your location.");
        } else {
            player.sendMessage("/setwarp [name]");
        }
        return false;
    }
}
