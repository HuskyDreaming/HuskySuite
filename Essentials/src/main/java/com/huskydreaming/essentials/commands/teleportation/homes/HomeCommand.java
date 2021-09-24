package com.huskydreaming.essentials.commands.teleportation.homes;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.data.Home;
import com.huskydreaming.essentials.managers.HomeManager;
import org.bukkit.entity.Player;

public class HomeCommand extends CommandBase {

    private final HomeManager homeManager;

    public HomeCommand(HomeManager homeManager) {
        super("home");
        this.homeManager = homeManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            String string = strings[0];
            Home home = homeManager.getHome(string, player);
            if(home != null) {
                player.teleport(home.getPosition().serialize());
                player.sendMessage("You have successfully teleported to the " + string + " home.");
            } else {
                player.sendMessage("The home " + string + " does not seem to exist.");
            }
        } else {
            player.sendMessage(homeManager.getHomes(player).toString());
        }
        return false;
    }
}
