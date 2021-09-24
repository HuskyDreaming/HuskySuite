package com.huskydreaming.essentials.commands.general;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.entity.Player;

public class GameModeCommand extends CommandBase {

    protected GameModeCommand() {
        super("gamemode");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        return false;
    }
}
