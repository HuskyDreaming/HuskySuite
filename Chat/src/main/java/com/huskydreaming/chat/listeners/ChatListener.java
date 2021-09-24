package com.huskydreaming.chat.listeners;

import com.huskydreaming.chat.HuskyChat;
import com.huskydreaming.chat.data.Config;
import com.huskydreaming.core.resources.base.ListenerBase;
import com.huskydreaming.core.utilities.Chat;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.StringJoiner;
import java.util.logging.Level;

public class ChatListener extends ListenerBase {

    private final HuskyChat huskyChat;
    private final String format;
    private final String command;
    private final String hover;

    public ChatListener(HuskyChat huskyChat) {
        this.huskyChat = huskyChat;
        this.format = huskyChat.getConfig().getString(Config.CHAT_FORMAT.format());
        this.command = huskyChat.getConfig().getString(Config.CHAT_COMMAND.format());

        StringJoiner stringJoiner = new StringJoiner("\n");
        huskyChat.getConfig().getStringList(Config.CHAT_HOVER.format()).forEach(stringJoiner::add);
        this.hover = Chat.color(stringJoiner.toString());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (huskyChat.isLocked() && !event.getPlayer().hasPermission("chat.override")) return;

        Player player = event.getPlayer();
        String message = event.getMessage();

        TextComponent displayName = new TextComponent(player.getDisplayName() + ": ");
        TextComponent textMessage = new TextComponent(message);

        Text text = new Text(hover);
        if(huskyChat.hasPlaceholderAPI()) {
            text = new Text(Chat.color(PlaceholderAPI.setPlaceholders(event.getPlayer(), hover)));
            textMessage = new TextComponent(PlaceholderAPI.setPlaceholders(event.getPlayer(), message));
        }

        displayName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, text));
        displayName.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command.replace("{player}", player.getName())));

        for (Player online : huskyChat.getServer().getOnlinePlayers()) {
            if (message.contains(online.getName())) {

                message = message.replace(online.getName(), ChatColor.AQUA + "@" + online.getName() + ChatColor.WHITE);
                online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                textMessage = new TextComponent(message);
                if(huskyChat.hasPlaceholderAPI()) {
                    textMessage = new TextComponent(PlaceholderAPI.setPlaceholders(event.getPlayer(), message));
                }
            }
            online.spigot().sendMessage(displayName, textMessage);
            Bukkit.getLogger().log(Level.ALL, displayName.getText() + textMessage.getText());
        }

        event.setCancelled(true);
    }
}
