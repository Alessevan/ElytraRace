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
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Destroy {

    public static boolean handle(final @NotNull Player player) {
        if (!Permissions.hasPermission(player, Permissions.DESTROY)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        final ElytraRace elytraRace = Executor.editor.get(player);
        elytraRace.destroy();
        Executor.editor.remove(player);
        player.sendMessage(LangMessages.INFO_DELETED.get());
        return true;
    }

}
