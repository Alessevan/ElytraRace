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

public class Create {

    public static boolean handle(final @NotNull Player player, final String name) {
        if (!Permissions.hasPermission(player, Permissions.CREATE)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        for (final ElytraRace elytraRace : ElytraPlugin.getInstances()) {
            if (elytraRace.getName().equalsIgnoreCase(name.replace(" ", ""))) {
                player.sendMessage("§c§lElytraRace §4§l» §cCe nom est déjà pris.");
                return false;
            }
        }
        final ElytraRace elytraRace = new ElytraRace(name.replace(" ", ""));
        ElytraPlugin.getInstances().add(elytraRace);
        Executor.editor.put(player, elytraRace);
        player.sendMessage("§3§lElytraRace §8§l» §7Parcours \"§6" + name.replace(" ", "") + "§7\" créé.");
        return true;
    }
}
