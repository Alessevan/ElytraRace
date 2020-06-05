/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace;

import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class List {

    public static boolean handle(final @NotNull Player player) {
        if (!Permissions.hasPermission(player, Permissions.LIST)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (final ElytraRace elytraRace : ElytraPlugin.getInstances()) {
            stringBuilder.append("§6").append(elytraRace.getName());
            if (!elytraRace.equals(ElytraPlugin.getInstances().get(ElytraPlugin.getInstances().size() - 1))) {
                stringBuilder.append("§7, ");
            }
        }
        player.sendMessage(LangMessages.INFO_LIST.get(stringBuilder.toString()));
        return true;
    }

}
