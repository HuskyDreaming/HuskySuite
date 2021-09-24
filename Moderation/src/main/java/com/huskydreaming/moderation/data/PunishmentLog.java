package com.huskydreaming.moderation.data;

import com.huskydreaming.core.utilities.Chat;
import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.OfflinePlayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PunishmentLog {

    private String date;
    private String author;
    private String reason;
    private Punishment.Type punishmentType;
    private boolean temporary;

    public Punishment.Type getPunishmentType() {
        return punishmentType;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public PunishmentLog type(Punishment.Type punishmentType) {
        this.punishmentType = punishmentType;
        return this;
    }

    public PunishmentLog date(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.date = dateFormat.format(date);
        return this;
    }

    public PunishmentLog author(String author) {
        this.author = author;
        return this;
    }

    public PunishmentLog reason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public PunishmentLog temporary(boolean temporary) {
        this.temporary = temporary;
        return this;
    }

    public void send(OfflinePlayer offlinePlayer, PunishmentManager punishmentManager) {
        punishmentManager.getLogs(offlinePlayer).add(this);
    }

    public static PunishmentLog newInstance() {
        return new PunishmentLog();
    }

    @Override
    public String toString() {
        return Chat.color("&8Date:\n&7&o" +date + "\n\n" +
                "&8Author:&7 " + author + "\n" +
                "&8Type: &7" + punishmentType.name() + "\n" +
                "&8Temporary: &7" + (temporary ? "yes" : "no") +
                "\n&8Reason:&7 \n" + reason + "\n");
    }
}
