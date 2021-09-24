package com.huskydreaming.moderation;

import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HuskyModeration extends JavaPlugin {

    private final PunishmentManager punishmentManager = new PunishmentManager(this);

    @Override
    public void onEnable() {
        punishmentManager.deserialize();
    }

    @Override
    public void onDisable() {
        punishmentManager.serialize();
    }
}
