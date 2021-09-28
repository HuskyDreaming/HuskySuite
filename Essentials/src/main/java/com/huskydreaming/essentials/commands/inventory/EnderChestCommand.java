package com.huskydreaming.essentials.commands.inventory;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EnderChestCommand extends CommandBase {

    protected EnderChestCommand() {
        super("enderchest", "echest", "enderc");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 0) {
            player.openInventory(player.getEnderChest());
        } else if(strings.length == 1) {
            if(player.hasPermission(getPermission() + ".other")) {
                Player target = Bukkit.getPlayer(strings[0]);
                if (target == null) {
                    player.sendMessage("The player " + strings[0] + " does not seem to be online.");
                } else {
                    player.openInventory(target.getEnderChest());
                }
            } else {
                player.sendMessage("You do not seem to have permissions.");
            }
        }
        return false;
    }
}
