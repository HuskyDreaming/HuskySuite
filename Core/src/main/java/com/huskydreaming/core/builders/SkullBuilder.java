package com.huskydreaming.core.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class SkullBuilder extends ItemBuilder {

    private UUID uniqueId;

    public SkullBuilder owner(UUID uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = super.build();
        itemStack.setType(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        if(skullMeta != null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uniqueId);
            skullMeta.setOwningPlayer(offlinePlayer);
            itemStack.setItemMeta(skullMeta);
        }
        return itemStack;
    }
}
