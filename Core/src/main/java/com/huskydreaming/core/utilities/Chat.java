package com.huskydreaming.core.utilities;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {

    private static final Pattern pattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Capitalizes the first word of a string.
     *
     * @param message String
     * @return String
     */
    public static String Capitalize(String message) {
        return message.substring(0, 1).toUpperCase() + message.substring(1);
    }

    /**
     * Converts parameters into specified strings.
     *
     * @param message    String
     * @param parameters String Array
     * @return String
     */
    public static String parameterize(String message, String... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            message = message.replace("{" + i + "}", parameters[i]);
        }
        return message;
    }

    /**
     * Get the offline player based on name.
     *
     * @param name String
     * @return OfflinePlayer
     */
    public static OfflinePlayer offlinePlayer(String name) {
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (offlinePlayer.hasPlayedBefore()) {
                String offlinePlayerName = offlinePlayer.getName();
                if (offlinePlayerName != null && offlinePlayerName.equalsIgnoreCase(name)) {
                    return offlinePlayer;
                }
            }
        }
        return null;
    }

    public static String format(String message) {
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, ChatColor.of(color) + "");
        }
        return message;
    }
}