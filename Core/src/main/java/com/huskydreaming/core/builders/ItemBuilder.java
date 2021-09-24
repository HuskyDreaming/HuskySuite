package com.huskydreaming.core.builders;

import com.huskydreaming.core.utilities.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {

    private String displayName;
    private Material material = Material.AIR;
    private List<String> lore = new ArrayList<>();
    private int amount = 1;

    public static ItemBuilder newInstance() {
        return new ItemBuilder();
    }

    public ItemBuilder displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta == null) return itemStack;
        if(displayName!= null) itemMeta.setDisplayName(displayName);
        if(lore != null) itemMeta.setLore(lore.stream().map(Chat::color).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }
}