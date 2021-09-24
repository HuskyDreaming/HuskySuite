package com.huskydreaming.chat.commands;

import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatClearCommand extends CommandBase {

    public ChatClearCommand() {
        super("chatclear", "cc");
    }

    @Override
    public boolean run(Player player, String[] strings) {
        for(int i = 0; i < 100; i++) {
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage("The chat has been cleared by " + player.getName());
        return false;
    }
}
