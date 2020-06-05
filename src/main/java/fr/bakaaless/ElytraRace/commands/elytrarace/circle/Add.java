/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace.circle;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.Circle;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Add {

    public static boolean handle(final @NotNull Player player) {
        if (!Permissions.hasPermission(player, Permissions.CIRCLE_ADD)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        Executor.editor.get(player).refreshCirclesId();
        Executor.editor.get(player).getCircles().add(
                new Circle(
                        Executor.editor.get(player).getCircles().size(),
                        player.getLocation().clone().add(new Vector(0, 1, 0)),
                        2.5,
                        Color.SILVER,
                        1.00,
                        0.5,
                        false
                ));

        player.sendMessage(LangMessages.INFO_CIRCLE_ADDED.get());
        return true;
    }

}
