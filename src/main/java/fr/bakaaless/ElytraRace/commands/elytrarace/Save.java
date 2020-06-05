/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.GameManager;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Save {

    public static boolean handle(final @NotNull Player player) {
        if (!Permissions.hasPermission(player, Permissions.SAVE)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        try {
            GameManager.save(Executor.editor.get(player));
            Executor.editor.remove(player);
            player.sendMessage(LangMessages.INFO_SAVED.get());
            return true;
        }
        catch (Exception e) {
            Message.sendError(player, LangMessages.ERROR_SAVING.get(), e);
        }
        return false;
    }

}
