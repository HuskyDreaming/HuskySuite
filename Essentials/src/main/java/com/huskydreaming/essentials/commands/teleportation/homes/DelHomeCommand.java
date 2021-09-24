package com.huskydreaming.essentials.commands.teleportation.homes;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.data.Home;
import com.huskydreaming.essentials.managers.HomeManager;
import org.bukkit.entity.Player;

public class DelHomeCommand extends CommandBase {

    private final HomeManager homeManager;

    public DelHomeCommand(HomeManager homeManager) {
        super("delhome");
        this.homeManager = homeManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            String string = strings[0];
            Home home = homeManager.getHome(string, player);
            if(home != null) {
                homeManager.delHome(home, player);
                player.sendMessage("You have successfully removed the " + string + " home.");
            } else {
                player.sendMessage("The home " + string + " does not seem to exist.");
            }
        } else {
            player.sendMessage("/delhome [name]");
        }
        return false;
    }
}
