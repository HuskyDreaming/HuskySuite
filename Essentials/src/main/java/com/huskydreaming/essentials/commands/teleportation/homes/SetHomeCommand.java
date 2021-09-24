package com.huskydreaming.essentials.commands.teleportation.homes;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.essentials.managers.HomeManager;
import org.bukkit.entity.Player;

public class SetHomeCommand extends CommandBase {

    private final HomeManager homeManager;

    public SetHomeCommand(HomeManager homeManager) {
        super("sethome");
        this.homeManager = homeManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            String string = strings[0];

            //TODO: Limit number of homes per player.

            homeManager.setHome(string, player);
            player.sendMessage("You have successfully set a home named " + string + " at your location.");
        } else {
            player.sendMessage("/sethome [name]");
        }
        return false;
    }
}
