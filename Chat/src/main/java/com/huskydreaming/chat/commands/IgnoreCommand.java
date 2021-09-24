package com.huskydreaming.chat.commands;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.entity.Player;

public class IgnoreCommand extends CommandBase {

    protected IgnoreCommand() {
        super("ignore");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        return false;
    }
}
