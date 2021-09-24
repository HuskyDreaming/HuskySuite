package com.huskydreaming.essentials.commands.general;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FlyCommand extends CommandBase {

    protected FlyCommand() {
        super("fly");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            if(player.hasPermission("command.fly.others")) {
                String string = strings[0];
                Player target = Bukkit.getPlayer(string);
                if (target == null) {
                    player.sendMessage("The player " + string + " does not seem to be online.");
                } else {
                    fly(target);
                    player.sendMessage("You have set " + string + "'s flight mode to " + status(target));
                }
            } else {
                player.sendMessage("You do not seem to have permissions.");
            }
        } else {
            fly(player);
        }
        return false;
    }

    private String status(Player player) {
        return player.getAllowFlight() ? "enabled" : "disabled";
    }

    private void fly(Player player) {
        if(player.isFlying()) {
            player.setFlying(false);
            player.setAllowFlight(false);
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        player.sendMessage("Your flight mode has been set to " + status(player));
    }
}
