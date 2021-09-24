package com.huskydreaming.essentials.managers;

import com.google.gson.reflect.TypeToken;
import com.huskydreaming.core.resources.interfaces.Manager;
import com.huskydreaming.core.resources.interfaces.Registry;
import com.huskydreaming.core.utilities.Json;
import com.huskydreaming.essentials.commands.teleportation.homes.DelHomeCommand;
import com.huskydreaming.essentials.commands.teleportation.homes.HomeCommand;
import com.huskydreaming.essentials.commands.teleportation.homes.SetHomeCommand;
import com.huskydreaming.essentials.data.Home;
import com.huskydreaming.essentials.listeners.HomeListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Type;
import java.util.*;

public class HomeManager implements Manager {

    private final Plugin plugin;
    private final Map<UUID, Set<Home>> homes = new HashMap<>();

    public HomeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setHome(String name, Player player) {
        Set<Home> homes = this.homes.get(player.getUniqueId());
        homes.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().ifPresent(homes::remove);
        homes.add(new Home(name, player.getLocation()));
    }

    public void delHome(Home home, Player player) {
        homes.get(player.getUniqueId()).remove(home);
    }

    public Set<Home> getHomes(Player player) {
        return homes.get(player.getUniqueId());
    }

    public Home getHome(String name, Player player) {
        return homes.get(player.getUniqueId()).stream()
                .filter(home -> home.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deserialize() {
        Registry.Register(plugin,
                // Commands
                new DelHomeCommand(this),
                new HomeCommand(this),
                new SetHomeCommand(this),
                // Listeners
                new HomeListener(this)
        );

        plugin.getServer().getOnlinePlayers().forEach(this::deserialize);
    }

    @Override
    public void serialize() {
        plugin.getServer().getOnlinePlayers().forEach(this::serialize);
    }

    public void deserialize(Player player) {
        Type type = new TypeToken<Set<Home>>(){}.getType();
        UUID uuid = player.getUniqueId();
        Set<Home> homes = Json.read(plugin, "homes/" + uuid, type);

        if(homes == null) homes = new HashSet<>();
        this.homes.put(uuid, homes);
    }

    public void serialize(Player player) {
        UUID uuid = player.getUniqueId();
        Json.write(plugin, "homes/" + uuid, homes.get(uuid));
    }
}
