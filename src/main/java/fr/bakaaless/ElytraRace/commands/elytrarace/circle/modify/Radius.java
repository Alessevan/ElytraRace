/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace.circle.modify;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.Circle;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Radius {

    public static boolean handle(final @NotNull Player player, final @NotNull String id, final String radius) {
        if (!Permissions.hasPermission(player, Permissions.CIRCLE_MODIFY_RADIUS)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        Circle circle;
        try {
            final int index = Integer.parseInt(id);
            try {
                circle = Executor.editor.get(player).getCircles().get(index);
            }
            catch (Exception e) {
                Message.sendError(player, LangMessages.ERROR_INVALID_INDEX.get(), e);
                return false;
            }
        }
        catch (Exception e) {
            Message.sendError(player, LangMessages.ERROR_INVALID_INTEGER.get(), e);
            return false;
        }
        try {
            circle.setRadius(Double.parseDouble(radius));
            player.sendMessage(LangMessages.INFO_CIRCLE_MODIFY_RADIUS.get(id, radius));
            return true;
        }
        catch (Exception e) {
            Message.sendError(player, LangMessages.ERROR_INVALID_NUMBER.get(), e);
            return false;
        }
    }
}
