/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Edit {

    public static boolean handle(final @NotNull Player player, final String name) {
        if (!Permissions.hasPermission(player, Permissions.EDIT)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        for (final ElytraRace elytraRace : ElytraPlugin.getInstances()) {
            if (elytraRace.getName().equalsIgnoreCase(name.replace(" ", ""))) {
                if (Executor.editor.containsValue(elytraRace)) {
                    player.sendMessage(LangMessages.ERROR_EDITING.get());
                    return false;
                }
                Executor.editor.put(player, elytraRace);
                player.sendMessage(LangMessages.INFO_EDITING.get());
                return true;
            }
        }
        player.sendMessage(LangMessages.ERROR_NOT_FOUND.get());
        return false;
    }

}
