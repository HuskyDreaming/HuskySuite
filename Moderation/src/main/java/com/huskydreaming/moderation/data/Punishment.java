package com.huskydreaming.moderation.data;

import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;

public class Punishment {

    public enum Type {
        BAN,
        KICK,
        MUTE
    }

    private final Type type;
    private String date;
    private String reason;
    private boolean temporary;

    public Punishment(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Punishment date(Date date) {
        return this;
    }

    public String getDate() {
        return date;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public Punishment reason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Punishment temporary(boolean temporary) {
        this.temporary = temporary;
        return this;
    }

    public static Punishment newInstance(Type type) {
        return new Punishment(type);
    }

    public void mute(Player player, OfflinePlayer offlinePlayer, PunishmentManager punishmentManager) {
        if(punishmentManager.hasType(offlinePlayer, Type.MUTE)) return;
        PunishmentLog.newInstance()
                .reason(reason)
                .author(player.getName())
                .type(Type.MUTE)
                .temporary(temporary)
                .send(player, punishmentManager);
    }

    public void kick(Player player, OfflinePlayer offlinePlayer, PunishmentManager punishmentManager) {
        Player target = offlinePlayer.getPlayer();
        if(target != null) {
            offlinePlayer.getPlayer().kickPlayer(reason);
            PunishmentLog.newInstance()
                    .reason(reason)
                    .author(player.getName())
                    .type(Type.KICK)
                    .date(new Date())
                    .send(player, punishmentManager);
        }
    }

    public void ban(Player player, OfflinePlayer offlinePlayer, PunishmentManager punishmentManager) {
        if(punishmentManager.hasType(offlinePlayer, Type.BAN)) return;
        PunishmentLog.newInstance()
                .reason(reason)
                .author(player.getName())
                .type(Type.BAN)
                .temporary(temporary)
                .send(player, punishmentManager);
    }
}
