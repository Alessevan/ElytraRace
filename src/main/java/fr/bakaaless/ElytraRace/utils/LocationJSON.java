/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class LocationJSON implements JsonSerializer<Location>, JsonDeserializer<Location> {

    private static final String WORLD = "world";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";
    private static final String YAW = "yaw";
    private static final String PITCH = "pitch";

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject obj = json.getAsJsonObject();

            String worldName = obj.get(WORLD).getAsString();
            double x = obj.get(X).getAsDouble();
            double y = obj.get(Y).getAsDouble();
            double z = obj.get(Z).getAsDouble();
            float yaw = obj.get(YAW).getAsFloat();
            float pitch = obj.get(PITCH).getAsFloat();

            return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        try {
            obj.addProperty(WORLD, src.getWorld().getName());
            obj.addProperty(X, src.getX());
            obj.addProperty(Y, src.getY());
            obj.addProperty(Z, src.getZ());
            obj.addProperty(YAW, src.getYaw());
            obj.addProperty(PITCH, src.getPitch());

            return obj;
        } catch (Exception ex) {
            return obj;
        }
    }

}
