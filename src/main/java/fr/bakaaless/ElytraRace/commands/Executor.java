/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.commands;

import fr.bakaaless.ElytraRace.commands.elytrarace.*;
import fr.bakaaless.ElytraRace.commands.elytrarace.circle.Add;
import fr.bakaaless.ElytraRace.commands.elytrarace.circle.Remove;
import fr.bakaaless.ElytraRace.commands.elytrarace.circle.modify.*;
import fr.bakaaless.ElytraRace.game.ElytraRace;
import fr.bakaaless.ElytraRace.lang.LangMessages;
import fr.bakaaless.ElytraRace.plugin.ElytraPlugin;
import fr.bakaaless.ElytraRace.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor implements CommandExecutor, TabCompleter {

    public static Map<Player, ElytraRace> editor;

    static {
        Executor.editor = new HashMap<>();
    }

    public Executor() {
        ElytraPlugin.getInstance().getCommand("elytrarace").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if(!(commandSender instanceof Player))
            return Reload.handle(commandSender);
        final Player player = (Player) commandSender;
        try {
            if (args[0].equalsIgnoreCase("create"))
                return Create.handle(player, args[1]);
            if (args[0].equalsIgnoreCase("destroy"))
                return Destroy.handle(player);
            if (args[0].equalsIgnoreCase("edit"))
                return Edit.handle(player, args[1]);
            if (args[0].equalsIgnoreCase("info"))
                return fr.bakaaless.ElytraRace.commands.elytrarace.Info.handle(player, args[1]);
            if (args[0].equalsIgnoreCase("list"))
                return fr.bakaaless.ElytraRace.commands.elytrarace.List.handle(player);
            if (args[0].equalsIgnoreCase("reload"))
                return Reload.handle(commandSender);
            if (args[0].equalsIgnoreCase("rename"))
                return Rename.handle(player, args[1]);
            if (args[0].equalsIgnoreCase("save"))
                return Save.handle(player);

            if (args[0].equalsIgnoreCase("circle")) {
                if (args[1].equalsIgnoreCase("add"))
                    return Add.handle(player);
                if (args[1].equalsIgnoreCase("remove"))
                    return Remove.handle(player, args[2]);
                if (args[1].equalsIgnoreCase("info"))
                    return fr.bakaaless.ElytraRace.commands.elytrarace.circle.Info.handle(player, args[2]);
                if (args[1].equalsIgnoreCase("modify")) {
                    if (args[3].equalsIgnoreCase("depth"))
                        return Depth.handle(player, args[2], args[4]);
                    if (args[3].equalsIgnoreCase("hitbox"))
                        return HitBox.handle(player, args[2], args[4]);
                    if (args[3].equalsIgnoreCase("location"))
                        return Location.handle(player, args[2]);
                    if (args[3].equalsIgnoreCase("radius"))
                        return Radius.handle(player, args[2], args[4]);
                    if (args[3].equalsIgnoreCase("color"))
                        return RGB.handle(player, args[2], args[4]);
                    if (args[3].equalsIgnoreCase("boost"))
                        return Speed.handle(player, args[2], args[4]);
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
            Message.sendError(player, LangMessages.ERROR_ARGUMENTS.get(), e);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
