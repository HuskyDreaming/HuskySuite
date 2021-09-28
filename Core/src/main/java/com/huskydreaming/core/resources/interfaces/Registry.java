package com.huskydreaming.core.resources.interfaces;

import com.huskydreaming.core.resources.Registries;
import org.bukkit.plugin.Plugin;

public interface Registry {

    default void register(Plugin plugin) {
        Registries.register(plugin, getClass().getSimpleName());
    }
}
