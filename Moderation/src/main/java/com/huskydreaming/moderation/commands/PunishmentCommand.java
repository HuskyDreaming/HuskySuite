package com.huskydreaming.moderation.commands;

import com.huskydreaming.core.builders.BookBuilder;
import com.huskydreaming.core.resources.base.CommandBase;
import com.huskydreaming.core.utilities.Chat;
import com.huskydreaming.moderation.data.PunishmentLog;
import com.huskydreaming.moderation.managers.PunishmentManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

public class PunishmentCommand extends CommandBase {

    private final PunishmentManager punishmentManager;

    public PunishmentCommand(PunishmentManager punishmentManager) {
        super("punishment");
        this.punishmentManager = punishmentManager;
    }

    @Override
    public boolean run(Player player, String[] strings) {
        if(strings.length == 1) {
            OfflinePlayer offlinePlayer = Chat.offlinePlayer(strings[0]);
            if(offlinePlayer == null) {
                player.sendMessage("The player " + strings[0] + " does not seem to exist.");
            } else {
                BookBuilder bookBuilder = new BookBuilder()
                        .title("Punishments")
                        .author("Husky")
                        .generation(BookMeta.Generation.ORIGINAL);

                for (PunishmentLog punishmentLog : punishmentManager.getLogs(offlinePlayer)) {
                    bookBuilder.addPage(punishmentLog.toString());
                }
                player.openBook(bookBuilder.build());
            }
        }
        return false;
    }
}
