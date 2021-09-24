package com.huskydreaming.moderation.commands;

import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.core.utilities.Chat;
import com.huskydreaming.moderation.data.Punishment;
import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Date;


public class KickCommand extends CommandBase {

    private final PunishmentManager punishmentManager;

    public KickCommand(PunishmentManager punishmentManager) {
        super("kick");
        this.punishmentManager = punishmentManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length > 2) {
            OfflinePlayer offlinePlayer = Chat.offlinePlayer(strings[0]);
            if(offlinePlayer == null) {
                player.sendMessage("The player " + strings[0] + " does not seem to exist.");
            } else {
                Player target = offlinePlayer.getPlayer();
                if (target != null) {
                    Punishment.newInstance(Punishment.Type.KICK)
                            .date(new Date())
                            .reason("A random reason")
                            .kick(player, offlinePlayer, punishmentManager);
                    target.kickPlayer("You have been kicked.");
                } else {
                    player.sendMessage("The player " + strings[0] + " does not seem to be online.");
                }
            }
        } else {
            player.sendMessage("/kick [player] [reason]");
        }
        return false;
    }
}
