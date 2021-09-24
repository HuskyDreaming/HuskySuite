package com.huskydreaming.essentials.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Position {

    private final double x, y, z;
    private final float yaw, pitch;
    private final String world;

    public Position(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.world = location.getWorld().getName();
    }

    public Location serialize() {
        World world = Bukkit.getWorld(this.world);
        if(world == null) return null;
        Location location = new Location(world, x, y, z);
        location.setYaw(yaw);
        location.setPitch(pitch);
        return location;
    }
}
