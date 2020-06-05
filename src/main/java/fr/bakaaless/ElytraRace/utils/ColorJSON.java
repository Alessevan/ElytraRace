/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import com.google.gson.*;
import org.bukkit.Color;

import java.lang.reflect.Type;

public class ColorJSON implements JsonSerializer<Color>, JsonDeserializer<Color> {

    private static final String red = "red";
    private static final String green = "green";
    private static final String blue = "blue";

    @Override
    public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject obj = json.getAsJsonObject();

            final int red = obj.get(ColorJSON.red).getAsInt();
            final int green = obj.get(ColorJSON.green).getAsInt();
            final int blue = obj.get(ColorJSON.blue).getAsInt();

            return Color.fromRGB(red, green, blue);

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        try {
            obj.addProperty(red, src.getRed());
            obj.addProperty(green, src.getGreen());
            obj.addProperty(blue, src.getBlue());

            return obj;
        } catch (Exception ex) {
            return obj;
        }
    }

}
