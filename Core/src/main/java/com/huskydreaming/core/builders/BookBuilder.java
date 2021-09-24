package com.huskydreaming.core.builders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class BookBuilder extends ItemBuilder {

    private String title;
    private String author;
    private BookMeta.Generation generation;
    private final List<String> pages = new ArrayList<>();

    public static BookBuilder newInstance() {
        return new BookBuilder();
    }

    public BookBuilder addPage(String sentence) {
        pages.add(sentence);
        return this;
    }

    public BookBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder generation(BookMeta.Generation generation) {
        this.generation = generation;
        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = super.build();
        itemStack.setType(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        if (bookMeta != null) {
            if(title != null) {
                bookMeta.setTitle(title);
                bookMeta.setDisplayName(title);
            }
            if(author != null) bookMeta.setAuthor(author);
            if(generation != null) bookMeta.setGeneration(generation);
            pages.forEach(bookMeta::addPage);
            itemStack.setItemMeta(bookMeta);
        }
        return itemStack;
    }
}

