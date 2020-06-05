/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands;

import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.game.GameManager;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.perms.Permissions;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import fr.bakaaless.ElytraRace.utils.IFileManager;
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.command.CommandSender;

import java.io.File;

public class Reload {

    public static boolean handle(final CommandSender commandSender) {
        if (!Permissions.hasPermission(commandSender, Permissions.RELOAD)) {
            commandSender.sendMessage(LangMessages.ERROR_PERMISSION.get());
            return false;
        }
        commandSender.sendMessage(LangMessages.INFO_RELOADING.get());
        for (final IFileManager iFileManager : ElytraPlugin.getInstance().getFileManagers()) {
            try {
                iFileManager.init();
            }
            catch (Exception e) {
                if(Permissions.hasPermission(commandSender, Permissions.DEBUG))
                    Message.sendError(commandSender, LangMessages.ERROR_RELOAD.get(iFileManager.name + ".yml"), e);
            }
        }
        for (File file : ElytraPlugin.getInstancesDir().listFiles()) {
            ElytraPlugin.getInstances().clear();
            if (file.getName().toLowerCase().endsWith(".json")) {

                try {
                    final ElytraRace elytraRace = GameManager.load(file);
                    ElytraPlugin.getInstances().add(elytraRace);
                }
                catch (Exception e) {
                    if(Permissions.hasPermission(commandSender, Permissions.DEBUG))
                        Message.sendError(commandSender, LangMessages.ERROR_RELOAD.get(file.getName()), e);
                }

            }
        }
        commandSender.sendMessage(LangMessages.INFO_RELOADED.get());
        return true;
    }

}
