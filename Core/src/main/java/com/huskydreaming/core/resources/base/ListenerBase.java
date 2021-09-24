package com.huskydreaming.core.resources.base;

import com.huskydreaming.core.resources.interfaces.Registry;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerBase implements Listener, Registry {

    @Override
    public void register(Plugin plugin) {
        Registry.super.register(plugin);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
