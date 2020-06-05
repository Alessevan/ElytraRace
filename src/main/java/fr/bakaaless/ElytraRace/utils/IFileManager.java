/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class IFileManager {

    public JavaPlugin javaPlugin;
    public String name;

    public IFileManager(final JavaPlugin javaPlugin, final String name){
        this.javaPlugin = javaPlugin;
        this.name = name;
    }

    public abstract void init();

}
