package com.huskydreaming.essentials.data;

import org.bukkit.Location;

public class Home {

    private final String name;
    private final Position position;

    public Home(String name, Location location) {
        this.name = name;
        this.position = new Position(location);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
