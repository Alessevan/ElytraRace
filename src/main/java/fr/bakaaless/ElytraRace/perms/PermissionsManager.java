/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.perms;

import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.utils.IFileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class PermissionsManager extends IFileManager {

    public PermissionsManager(final JavaPlugin javaPlugin) {
        super(javaPlugin, "perms");
        this.init();
    }

    @Override
    public void init() {
        try {
            final File file = new File(javaPlugin.getDataFolder(), this.name + ".yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            final YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.load(file);
            for (final Permissions configMessages : Permissions.values()) {
                if (yamlConfiguration.get(configMessages.getPath()) == null) {
                    yamlConfiguration.set(configMessages.getPath(), configMessages.toString());
                }
                else {
                    configMessages.set(yamlConfiguration.getString(configMessages.getPath()));
                }
            }
            yamlConfiguration.save(file);
            yamlConfiguration.load(file);
        }
        catch (IOException | InvalidConfigurationException e) {
            this.javaPlugin.getLogger().log(Level.SEVERE, "Unable to create config.yml", e);
        }
    }
}
