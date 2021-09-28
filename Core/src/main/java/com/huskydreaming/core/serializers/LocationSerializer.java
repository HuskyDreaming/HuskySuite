package com.huskydreaming.core.serializers;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;
import java.util.UUID;

public class LocationSerializer implements JsonSerializer<Location>, JsonDeserializer<Location> {

    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double z = jsonObject.get("z").getAsDouble();
        float pitch = jsonObject.get("pitch").getAsFloat();
        float yaw = jsonObject.get("yaw").getAsFloat();
        UUID worldID = UUID.fromString(jsonObject.get("world").getAsString());
        World world = Bukkit.getWorld(worldID);

        return world == null ? null : new Location(world, x, y, z, pitch, yaw);
    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("x", location.getX());
        jsonObject.addProperty("y", location.getY());
        jsonObject.addProperty("z", location.getZ());
        jsonObject.addProperty("pitch", location.getPitch());
        jsonObject.addProperty("yaw", location.getYaw());
        jsonObject.addProperty("world", location.getWorld().getUID().toString());

        return jsonObject;
    }
}