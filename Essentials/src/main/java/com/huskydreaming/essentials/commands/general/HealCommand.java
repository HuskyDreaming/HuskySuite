package com.huskydreaming.essentials.commands.general;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCommand extends CommandBase {

    protected HealCommand() {
        super("heal");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            if(player.hasPermission("command.heal.others")) {
                String string = strings[0];
                Player target = Bukkit.getPlayer(string);
                if(target == null) {
                    player.sendMessage("The player " + string + " does not seem to be online.");
                } else {
                    heal(target);
                    player.sendMessage("You have healed " + string);
                }
            } else {
                player.sendMessage("You do not seem to have permissions.");
            }
        } else {
            heal(player);
        }
        return false;
    }

    private void heal(Player player) {
        player.setFoodLevel(80);
        player.setHealth(20);
        player.sendMessage("You have been healed.");
    }
}
