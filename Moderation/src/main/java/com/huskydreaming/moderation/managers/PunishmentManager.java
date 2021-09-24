package com.huskydreaming.moderation.managers;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.gson.reflect.TypeToken;
import com.huskydreaming.core.resources.interfaces.Manager;
import com.huskydreaming.core.resources.interfaces.Registry;
import com.huskydreaming.core.utilities.Json;
import com.huskydreaming.moderation.commands.BanCommand;
import com.huskydreaming.moderation.commands.KickCommand;
import com.huskydreaming.moderation.commands.MuteCommand;
import com.huskydreaming.moderation.commands.PunishmentCommand;
import com.huskydreaming.moderation.data.Punishment;
import com.huskydreaming.moderation.data.PunishmentLog;
import com.huskydreaming.moderation.listeners.PlayerListener;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PunishmentManager implements Manager {

    private final Plugin plugin;
    private final Map<UUID, Set<Punishment>> punishments;
    private final Cache<UUID, Set<PunishmentLog>> logCache;

    public PunishmentManager(Plugin plugin) {
        this.plugin = plugin;
        punishments = new HashMap<>();
        logCache = CacheBuilder
                .newBuilder()
                .removalListener(removalListener())
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    public RemovalListener<UUID, Set<PunishmentLog>> removalListener() {
        return notification -> Json.write(plugin, "punishments/logs/" + notification.getKey(), notification.getValue());
    }

    public void deserialize(Player player) {
        Type type = new TypeToken<Set<Punishment>>(){}.getType();
        Set<Punishment> punishments = Json.read(plugin, "punishments/" + player.getUniqueId(), type);
        this.punishments.put(player.getUniqueId(), punishments != null ? punishments : new HashSet<>());
    }

    public void serialize(Player player) {
        Json.write(plugin, "punishments/" + player.getUniqueId(), punishments.get(player.getUniqueId()));
    }

    public Set<PunishmentLog> getLogs(OfflinePlayer offlinePlayer) {
        try {
            return logCache.get(offlinePlayer.getUniqueId(), ()-> {
                Type type = new TypeToken<Set<PunishmentLog>>(){}.getType();
                Set<PunishmentLog> punishments = Json.read(plugin, "punishments/logs/" + offlinePlayer.getUniqueId(), type);
                if(punishments == null) punishments = new HashSet<>();
                return punishments;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<PunishmentLog> getLogs(OfflinePlayer offlinePlayer, Punishment.Type type) {
        return getLogs(offlinePlayer).stream()
                .filter(punishment -> punishment.getPunishmentType() == type)
                .collect(Collectors.toSet());
    }

    public boolean hasType(OfflinePlayer offlinePlayer, Punishment.Type type) {
        return punishments.get(offlinePlayer.getUniqueId()).stream().anyMatch(punishment -> punishment.getType() == type);
    }

    @Override
    public void serialize() {
        plugin.getServer().getOnlinePlayers().forEach(this::serialize);

        logCache.invalidateAll();
        logCache.cleanUp();
    }

    @Override
    public void deserialize() {
        plugin.getServer().getOnlinePlayers().forEach(this::deserialize);

        Registry.Register(plugin,
                new KickCommand(this),
                new BanCommand(this),
                new MuteCommand(this),
                new PunishmentCommand(this),
                new PlayerListener(this)
        );
    }
}
