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

public class Location {

    public static boolean handle(final @NotNull Player player, final @NotNull String id) {
        if (!Permissions.hasPermission(player, Permissions.CIRCLE_MODIFY_LOCATION)) {
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
        final org.bukkit.Location location = player.getLocation();
            circle.setLocation(location);
            player.sendMessage(LangMessages.INFO_CIRCLE_MODIFY_LOCATION.get(id, location.getWorld().getName(), format(location.getX()), format(location.getY()), format(location.getZ()), format(location.getYaw()), format(location.getPitch())));
            return true;
    }
    private static String format(final double dOuble) {
        return String.valueOf(Math.round(dOuble * 100)/100);
    }


}
