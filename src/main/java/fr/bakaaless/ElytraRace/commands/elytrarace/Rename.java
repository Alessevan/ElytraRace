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

public class Rename {

    public static boolean handle(final @NotNull Player player, final @NotNull String newName) {
        if (!Permissions.hasPermission(player, Permissions.RENAME)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        for (final ElytraRace elytraRace : ElytraPlugin.getInstances()) {
            if (elytraRace.getName().equalsIgnoreCase(newName)) {
                player.sendMessage("§c§lElytraRace §4§l» §cCe nom est déjà pris.");
                return false;
            }
        }
        Executor.editor.get(player).setName(newName);
        player.sendMessage("§3§lElytraRace §8§l» §7Nom changé.");
        return true;
    }
}
