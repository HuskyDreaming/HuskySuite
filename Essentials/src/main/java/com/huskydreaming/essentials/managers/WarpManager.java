package com.huskydreaming.essentials.managers;

import com.google.gson.reflect.TypeToken;
import com.huskydreaming.core.resources.interfaces.Manager;
import com.huskydreaming.core.resources.interfaces.Registry;
import com.huskydreaming.core.utilities.Json;
import com.huskydreaming.essentials.commands.teleportation.warps.SetWarpCommand;
import com.huskydreaming.essentials.commands.teleportation.warps.DelWarpCommand;
import com.huskydreaming.essentials.commands.teleportation.warps.WarpCommand;
import com.huskydreaming.essentials.data.Position;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WarpManager implements Manager {

    private final Plugin plugin;
    private Map<String, Position> warps;

    public WarpManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void deserialize() {
        warps = Json.read(plugin, "warps", new TypeToken<Map<String, Position>>(){}.getType());
        if(warps == null) warps = new HashMap<>();

        Registry.Register(plugin,
                new DelWarpCommand(this),
                new SetWarpCommand(this),
                new WarpCommand(this)
        );
    }

    @Override
    public void serialize() {
        Json.write(plugin, "warps", warps);
    }

    public void set(String name, Player player) {
        warps.put(name, new Position(player.getLocation()));
    }

    public void remove(String name) {
        warps.remove(name);
    }

    public Position getWarp(String name) {
        return warps.get(name);
    }

    public Map<String, Position> getWarps() {
        return Collections.unmodifiableMap(warps);
    }
}
