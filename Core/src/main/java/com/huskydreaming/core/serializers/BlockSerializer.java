package com.huskydreaming.core.serializers;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.lang.reflect.Type;
import java.util.UUID;

public class BlockSerializer implements JsonSerializer<Block>, JsonDeserializer<Block> {

    @Override
    public JsonElement serialize(Block block, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("x", block.getX());
        jsonObject.addProperty("y", block.getY());
        jsonObject.addProperty("z", block.getZ());
        jsonObject.addProperty("world", block.getWorld().getUID().toString());

        return jsonObject;
    }

    @Override
    public Block deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();
        int z = jsonObject.get("z").getAsInt();
        UUID worldID = UUID.fromString(jsonObject.get("world").getAsString());
        World world = Bukkit.getWorld(worldID);

        return world == null ? null : world.getBlockAt(x, y, z);
    }
}
