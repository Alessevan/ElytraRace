/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    public static void sendError(final Player player, final String message, final Exception error){
        final TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c" + error.toString()).create()));
        player.spigot().sendMessage(textComponent);
    }
    public static void sendError(final CommandSender commandSender, final String message, final Exception error){
        if(commandSender instanceof Player){
            Message.sendError((Player) commandSender, message, error);
            return;
        }
        commandSender.sendMessage(message);
        error.printStackTrace();
    }
}
