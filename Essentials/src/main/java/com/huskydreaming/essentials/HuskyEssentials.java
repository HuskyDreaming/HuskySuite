package com.huskydreaming.essentials;

import com.huskydreaming.core.resources.Registries;
import com.huskydreaming.essentials.managers.HomeManager;
import com.huskydreaming.essentials.managers.SpawnManager;
import com.huskydreaming.essentials.managers.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HuskyEssentials extends JavaPlugin {

    @Override
    public void onEnable() {

        Registries.register(this,
                HomeManager.class,
                SpawnManager.class,
                WarpManager.class
        );

        Registries.deserialize();
        Registries.log(this);
    }

    @Override
    public void onDisable() {
        Registries.serialize();
    }
}
