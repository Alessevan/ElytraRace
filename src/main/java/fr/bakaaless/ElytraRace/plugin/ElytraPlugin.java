/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.plugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.game.GameManager;
import fr.bakaaless.ElytraRace.lang.LangManager;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.listeners.Listening;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.perms.PermissionsManager;
import fr.bakaaless.ElytraRace.utils.*;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class ElytraPlugin extends JavaPlugin {

    private static List<ElytraRace> instances;

    static {
        ElytraPlugin.instances = new ArrayList<>();
    }

    public static List<ElytraRace> getInstances() {
        return instances;
    }
    public static @NotNull Optional<ElytraRace> getInstanceByName(final String name) {
        return ElytraPlugin.getInstances().parallelStream().filter(elytraRace -> elytraRace.getName().equalsIgnoreCase(name)).findFirst();
    }
    private static ElytraPlugin instance;
    private static File instancesDir;
    public static ElytraPlugin getInstance() {
        return ElytraPlugin.instance;
    }
    public static File getInstancesDir() {
        return instancesDir;
    }
    private List<IFileManager> fileManagers;
    private Gson gson;
    private Scheduler scheduler;

    @Override
    public void onEnable() {
        super.onEnable();
        ElytraPlugin.instance = this;
        ElytraPlugin.instancesDir = new File(this.getDataFolder(), "/instances/");
        if (!ElytraPlugin.instancesDir.exists())
            ElytraPlugin.instancesDir.mkdirs();
        final Type typeLocation = new TypeToken<Location>() {}.getType();
        final Type typeColor = new TypeToken<Color>() {}.getType();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(typeLocation, new LocationJSON())
                .registerTypeAdapter(typeColor, new ColorJSON())
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
        this.fileManagers = new ArrayList<>();
        this.getServer().getScheduler().runTaskAsynchronously(this, () -> {
            this.fileManagers.add(new LangManager(this));
            this.fileManagers.add(new PermissionsManager(this));
            for (File file : ElytraPlugin.instancesDir.listFiles()) {
                if (file.getName().toLowerCase().endsWith(".json")) {
                    this.getServer().getConsoleSender().sendMessage(LangMessages.DEBUG_READING_FILE.get(file.getName()));
                    for (final Player player : this.getServer().getOnlinePlayers()) {
                        if (Permissions.hasPermission(player, Permissions.DEBUG))
                            player.sendMessage(LangMessages.DEBUG_READING_FILE.get(file.getName()));
                    }
                    try {
                        final ElytraRace elytraRace = GameManager.load(file);
                        elytraRace.refreshCirclesId();
                        ElytraPlugin.getInstances().add(elytraRace);
                        this.getServer().getConsoleSender().sendMessage(LangMessages.DEBUG_READ_FILE.get(file.getName()));
                        for (final Player player : this.getServer().getOnlinePlayers()) {
                            if (Permissions.hasPermission(player, Permissions.DEBUG))
                                player.sendMessage(LangMessages.DEBUG_READ_FILE.get(file.getName()));
                        }
                    }
                    catch (Exception e) {
                        Message.sendError(this.getServer().getConsoleSender(), LangMessages.DEBUG_CANT_READ.get(file.getName()), e);
                        for (final Player player : this.getServer().getOnlinePlayers()) {
                            if(Permissions.hasPermission(player, Permissions.DEBUG))
                                Message.sendError(player, LangMessages.DEBUG_CANT_READ.get(file.getName()), e);

                        }
                    }
                }
            }
            this.scheduler = new Scheduler();
            this.scheduler.start();
        });
        new Executor();
        new Listening();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.scheduler.stop();
        for (final ElytraRace elytraRace : ElytraPlugin.getInstances()) {
            this.getServer().getConsoleSender().sendMessage(LangMessages.DEBUG_SAVING_FILE.get(elytraRace.getUuid().toString() + ".json"));
            for (final Player player : this.getServer().getOnlinePlayers()) {
                if (Permissions.hasPermission(player, Permissions.DEBUG))
                    player.sendMessage(LangMessages.DEBUG_SAVING_FILE.get(elytraRace.getUuid().toString() + ".json"));
            }
            try {
                GameManager.save(elytraRace);
                this.getServer().getConsoleSender().sendMessage(LangMessages.DEBUG_SAVED_FILE.get(elytraRace.getUuid().toString() + ".json"));
                for (final Player player : this.getServer().getOnlinePlayers()) {
                    if (Permissions.hasPermission(player, Permissions.DEBUG))
                        player.sendMessage(LangMessages.DEBUG_SAVED_FILE.get(elytraRace.getUuid().toString() + ".json"));
                }
            } catch (IOException e) {
                this.getLogger().log(Level.INFO, LangMessages.DEBUG_CANT_SAVE.get(elytraRace.getUuid().toString() + ".json"));
                for (final Player player : this.getServer().getOnlinePlayers()) {
                    if (Permissions.hasPermission(player, Permissions.DEBUG))
                        player.sendMessage(LangMessages.DEBUG_CANT_SAVE.get(elytraRace.getUuid().toString() + ".json"));
                }
            }
        }
    }

    public Gson getGson() {
        return gson;
    }

    public List<IFileManager> getFileManagers() {
        return fileManagers;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
