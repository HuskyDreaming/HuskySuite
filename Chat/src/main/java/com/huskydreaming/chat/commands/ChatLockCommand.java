package com.huskydreaming.chat.commands;

import com.huskydreaming.chat.HuskyChat;
import com.huskydreaming.core.resources.base.CommandBase;
import org.bukkit.entity.Player;

public class ChatLockCommand extends CommandBase {

    private final HuskyChat huskyChat;

    public ChatLockCommand(HuskyChat huskyChat) {
        super("chatlock", "cl");
        this.huskyChat = huskyChat;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(huskyChat.isLocked()) {
            huskyChat.setLocked(false);
            huskyChat.getServer().broadcastMessage("The server chat has been un-muted.");
        } else {
            huskyChat.setLocked(true);
            huskyChat.getServer().broadcastMessage("The server chat has been muted.");
        }
        return false;
    }
}
