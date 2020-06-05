/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.listeners;

import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listening implements Listener {

    public Listening() {
        ElytraPlugin.getInstance().getServer().getPluginManager().registerEvents(this, ElytraPlugin.getInstance());
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e){
        PlayerQuit.handle(e.getPlayer());
    }
}
