/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace.circle;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Remove {

    public static boolean handle(final @NotNull Player player, final @NotNull String id) {
        if (!Permissions.hasPermission(player, Permissions.CIRCLE_REMOVE)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        int i;
        try {
            i = Integer.parseInt(id);
        }
        catch (Exception e) {
            Message.sendError(player, LangMessages.ERROR_INVALID_INTEGER.get(), e);
            return false;
        }
        Executor.editor.get(player).refreshCirclesId();
        Executor.editor.get(player).getCircles().remove(i);
        player.sendMessage(LangMessages.INFO_CIRCLE_REMOVED.get());
        return true;
    }

}
