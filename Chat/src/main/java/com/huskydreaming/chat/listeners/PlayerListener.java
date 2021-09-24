package com.huskydreaming.chat.listeners;

import com.huskydreaming.chat.HuskyChat;
import com.huskydreaming.chat.data.Config;
import com.huskydreaming.chat.data.PlaceHolder;
import com.huskydreaming.core.resources.base.ListenerBase;
import com.huskydreaming.core.utilities.Chat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerListener extends ListenerBase {

    private final HuskyChat huskyChat;

    public PlayerListener(HuskyChat huskyChat) {
        this.huskyChat = huskyChat;
    }

    @EventHandler
    public void onTitle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = huskyChat.getConfig();

        if(player.hasPlayedBefore()) {
            sendTitle(player,
                    config.getString(Config.TITLE_JOIN_HEADER.format()),
                    config.getString(Config.TITLE_JOIN_FOOTER.format()),
                    config.getBoolean(Config.TITLE_JOIN_ENABLED.format())
            );

        } else {
            sendTitle(player,
                    config.getString(Config.TITLE_WELCOME_HEADER.format()),
                    config.getString(Config.TITLE_WELCOME_FOOTER.format()),
                    config.getBoolean(Config.TITLE_WELCOME_ENABLED.format())
            );
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = huskyChat.getConfig();

        // Join Message
        boolean joinEnabled = config.getBoolean(Config.MESSAGES_JOIN_ENABLED.format());
        if(joinEnabled) {
            String joinMessage = config.getString(Config.MESSAGES_JOIN_MESSAGE.format());
            if(joinMessage != null) event.setJoinMessage(setPlaceHolders(player, joinMessage));
        }

        // Welcome Message
        boolean welcomeEnabled = config.getBoolean(Config.MESSAGES_WELCOME_ENABLED.format());
        if(welcomeEnabled) {
            for(String message : config.getStringList(Config.MESSAGES_WELCOME_MESSAGES.format())) {
                player.sendMessage(setPlaceHolders(player, message));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = huskyChat.getConfig();

        boolean quitEnabled = config.getBoolean(Config.MESSAGES_QUIT_ENABLED.format());
        if(quitEnabled) {
            String quit = config.getString(Config.MESSAGES_QUIT_MESSAGE.format());
            if (quit != null && quit.length() > 0) event.setQuitMessage(setPlaceHolders(player, quit));
        }
    }

    private String setPlaceHolders(Player player, String message) {
        if(huskyChat.hasPlaceholderAPI()) message = PlaceholderAPI.setPlaceholders(player, message);

        message = message.replace(PlaceHolder.PLAYER.format(), player.getName());
        message = message.replace(PlaceHolder.DISPLAYNAME.format(), player.getDisplayName());
        message = message.replace(PlaceHolder.WORLD.format(), player.getWorld().getName());
        message = message.replace(PlaceHolder.ONLINE.format(), String.valueOf(huskyChat.getServer().getOnlinePlayers().size()));
        message = message.replace(PlaceHolder.MAX.format(), String.valueOf(huskyChat.getServer().getMaxPlayers()));
        message = message.replace(PlaceHolder.DATE.format(), LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return Chat.color(message);
    }

    private void sendTitle(Player player, String header, String footer, boolean enabled) {
        if(enabled) {
            if(header != null) header = setPlaceHolders(player, header);
            if(footer != null) footer = setPlaceHolders(player, footer);
            player.sendTitle(header, footer, 20, 60, 20);
        }
    }
}
