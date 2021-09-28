package com.huskydreaming.proxy;

import com.huskydreaming.proxy.commands.HubCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class HuskyProxy extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new HubCommand(this));
    }
}
