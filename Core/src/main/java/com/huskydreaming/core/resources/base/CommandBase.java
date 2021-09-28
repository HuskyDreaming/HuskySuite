package com.huskydreaming.core.resources.base;

import com.huskydreaming.core.resources.interfaces.Registry;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public abstract class CommandBase extends Command implements Registry {

    protected CommandBase(String name, String... aliases) {
        super(name);
        setAliases(Arrays.asList(aliases));
        setPermission("command." + getName());
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if (!player.hasPermission(Objects.requireNonNull(getPermission()))) {
                player.sendMessage("You do not seem to have permissions.");
                return true;
            }

            return run(player, strings);
        }
        return false;
    }

    public abstract boolean run(Player player, String[] strings);

    @Override
    public void register(Plugin plugin) {
        Registry.super.register(plugin);

        try {

            final Server server = plugin.getServer();
            final Field commandMapField = server.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandMapField.get(server);
            commandMap.register(getName(), this);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}