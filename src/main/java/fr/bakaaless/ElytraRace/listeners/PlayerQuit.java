/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.listeners;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.GameManager;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PlayerQuit {

    public static void handle(final Player player) {
        if (Executor.editor.containsKey(player)) {
            try {
                GameManager.save(Executor.editor.get(player));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Executor.editor.remove(player);
        }
    }
}
