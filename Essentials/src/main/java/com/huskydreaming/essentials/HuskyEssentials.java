package com.huskydreaming.essentials;

import com.huskydreaming.core.resources.interfaces.Registry;
import com.huskydreaming.essentials.managers.HomeManager;
import com.huskydreaming.essentials.managers.SpawnManager;
import com.huskydreaming.essentials.managers.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HuskyEssentials extends JavaPlugin {

    @Override
    public void onEnable() {

        Registry.registerManagers(this,
                HomeManager.class,
                SpawnManager.class,
                WarpManager.class
        );

        Registry.deserializeManagers();
        Registry.registries(this);
    }

    @Override
    public void onDisable() {
        Registry.serializeManagers();
    }
}
