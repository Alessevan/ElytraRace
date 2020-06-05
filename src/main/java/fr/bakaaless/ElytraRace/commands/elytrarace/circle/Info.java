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
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Info {

    public static boolean handle(final @NotNull Player player, final @NotNull String id) {
        if (!Permissions.hasPermission(player, Permissions.CIRCLE_INFO)) {
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

        String stringBuilder = "\n§7Location : §6" + circle.getLocation().getWorld().getName() + "§7, §6" + format(circle.getLocation().getX()) + "§7, §6" + format(circle.getLocation().getY()) + "§7, §6" + format(circle.getLocation().getZ()) + "§7, §6" + format(circle.getLocation().getYaw()) + "§7, §6" + format(circle.getLocation().getPitch()) + "\n" +
                "§7Radius : §6" + circle.getRadius() + "\n" +
                "§7RGB : §6" + circle.getColor().getRed() + "§7,§6" + circle.getColor().getGreen() + "§7,§6" + circle.getColor().getBlue() + "\n" +
                "§7Boost : §6" + ((int) circle.getSpeed() * 100) + "%" + "\n" +
                "§7Depth : §6" + circle.getDepth() + "\n" +
                "§7ShowHitBox : §6" + circle.isShowingHitBox();
        player.sendMessage(LangMessages.INFO_CIRCLE_INFO.get(stringBuilder));
        return true;
    }

    private static String format(final double dOuble) {
        return String.valueOf(Math.round(dOuble * 100)/100);
    }

}
