package com.huskydreaming.proxy.commands;

import com.huskydreaming.proxy.HuskyProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {

    private final HuskyProxy huskyProxy;

    public HubCommand(HuskyProxy huskyProxy) {
        super("hub", "command.hub", "lobby");
        this.huskyProxy = huskyProxy;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if ((commandSender instanceof ProxiedPlayer player)) {
            ServerInfo serverInfo =  huskyProxy.getProxy().getServerInfo("hub");
            if(serverInfo != null) player.connect(serverInfo);
        }
    }
}
