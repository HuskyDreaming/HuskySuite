package com.huskydreaming.chat;

import com.huskydreaming.chat.commands.ChatClearCommand;
import com.huskydreaming.chat.commands.ChatLockCommand;
import com.huskydreaming.chat.listeners.ChatListener;
import com.huskydreaming.chat.listeners.PlayerListener;
import com.huskydreaming.core.resources.Registries;
import com.huskydreaming.core.resources.interfaces.Registry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HuskyChat extends JavaPlugin {

    private boolean locked;
    private boolean placeholderAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        getLogger().info((placeholderAPI ?
                "Successfully hooked into PlaceHolderAPI." :
                "Did not find PlaceHolderAPI. Ignoring placeholders...")
        );

        Registries.register(this,
                new ChatClearCommand(),
                new ChatListener(this),
                new ChatLockCommand(this),
                new PlayerListener(this)
        );
        Registries.log(this);
    }

    @Override
    public void onDisable() {

    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean hasPlaceholderAPI() {
        return placeholderAPI;
    }
}