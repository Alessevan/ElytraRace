/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands.elytrarace;

import fr.bakaaless.ElytraRace.commands.Executor;
import fr.bakaaless.ElytraRace.game.Circle;
import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Info {

    public static boolean handle(final @NotNull Player player, final @NotNull String name) {
        if (!Permissions.hasPermission(player, Permissions.INFO)) {
            player.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        if(!Executor.editor.containsKey(player)){
            player.sendMessage(LangMessages.ERROR_EDIT.get());
            return false;
        }
        final Optional<ElytraRace> elytraRace = ElytraPlugin.getInstanceByName(name);
        if (!elytraRace.isPresent()) {
            player.sendMessage(LangMessages.ERROR_NOT_FOUND.get());
           return false;
        }
        final String response =
                "\n§7UUID : §6" + elytraRace.get().getUuid().toString()
                + "\n§7Waiting location : " + (elytraRace.get().getWaiting() == null ? "§cNone" : format(elytraRace.get().getWaiting()))
                + "\n§7Start location : " + (elytraRace.get().getStart() == null ? "§cNone" : format(elytraRace.get().getStart()))
                + "\n§7Return location : " + (elytraRace.get().getReturnLocation() == null ? "§cNone" : format(elytraRace.get().getReturnLocation()))
                + "\n§7Practice mode : §6" + elytraRace.get().isPracticeMode()
                + "\n§7Number of circles : §6" + elytraRace.get().getCircles().size();
        player.sendMessage(LangMessages.INFO_INFO.get(name, response));
        return true;
    }

    private static String format(final double dOuble) {
        return String.valueOf(((int) Math.round(dOuble * 100))/100D);
    }

    private static String format(final Location location) {
        return "§6" + location.getWorld().getName() + "§7, §6" + format(location.getX()) + "§7, §6" + format(location.getY()) + "§7, §6" + format(location.getZ()) + "§7, §6" + format(location.getYaw()) + "§7, §6" + format(location.getPitch());
    }

}
