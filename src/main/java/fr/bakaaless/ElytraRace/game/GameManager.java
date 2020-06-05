/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.game;

import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.UUID;

public class GameManager {

    public static void save(final @NotNull ElytraRace elytraRace) throws IOException {
        final File file = new File(ElytraPlugin.getInstancesDir(), elytraRace.getUuid().toString() + ".json");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        final FileWriter fileWriter = new FileWriter(file);
        final String json = ElytraPlugin.getInstance().getGson().toJson(elytraRace);
        fileWriter.write(json);
        fileWriter.flush();
        fileWriter.close();
    }

    public static void remove(final @NotNull ElytraRace elytraRace) throws IOException {
        final File file = new File(ElytraPlugin.getInstancesDir(), elytraRace.getUuid().toString() + ".json");
        if(!file.exists()){
            return;
        }
        final FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
        deleteFile(ElytraPlugin.getInstancesDir().getAbsolutePath(), elytraRace.getUuid().toString() + ".json");
    }

    public static @Nullable ElytraRace load(final String id) throws IOException {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        }
        catch (Exception ignored){
            return null;
        }
        return GameManager.load(uuid);
    }

    public static ElytraRace load(final @NotNull UUID uuid) throws IOException {
        final File file = new File(ElytraPlugin.getInstancesDir(), uuid.toString() + ".json");
        return GameManager.load(file);
    }

    public static @Nullable ElytraRace load(final @NotNull File file) throws IOException {
        if (!file.exists()) {
            return null;
        }
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return ElytraPlugin.getInstance().getGson().fromJson(stringBuilder.toString(), ElytraRace.class);
        }
        catch (IOException e) {
            e.printStackTrace();
            bufferedReader.close();
        }
        return null;
    }

    private static void deleteFile(String path, final String name) throws IOException {
        java.nio.file.Files.walk(Paths.get(path))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .filter(file -> file.getName().equalsIgnoreCase(name))
                .forEach(File::delete);
    }
}
