package com.huskydreaming.essentials.commands.general;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FeedCommand extends CommandBase {

    protected FeedCommand() {
        super("feed");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            if(player.hasPermission("command.fed.others")) {
                String string = strings[0];
                Player target = Bukkit.getPlayer(string);
                if (target == null) {
                    player.sendMessage("The player " + string + " does not seem to be online.");
                } else {
                    feed(target);
                    player.sendMessage("You have fed " + string);
                }
            } else {
                player.sendMessage("You do not seem to have permissions.");
            }
        } else {
            feed(player);
        }
        return false;
    }

    private void feed(Player player) {
        player.setFoodLevel(80);
        player.sendMessage("You have been fed.");
    }
}
