/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.perms;

import org.bukkit.command.CommandSender;

public enum Permissions {

    ADMIN("permissions.admin", "elytrarace.admin"),
    DEBUG("permissions.debug", "elytrarace.debug"),
    RELOAD("permissions.commands.reload", "elytrarace.reload"),
    CREATE("permissions.commands.create", "elytrarace.create"),
    DESTROY("permissions.commands.destroy", "elytrarace.destroy"),
    EDIT("permissions.commands.edit", "elytrarace.edit"),
    INFO("permissions.commands.info", "elytrarace.info"),
    LIST("permissions.commands.list", "elytrarace.list"),
    RENAME("permissions.commands.rename", "elytrarace.rename"),
    SAVE("permissions.commands.save", "elytrarace.save"),
    CIRCLE_ADD("permissions.commands.circle.add", "elytrarace.circle.add"),
    CIRCLE_INFO("permissions.commands.circle.info", "elytrarace.circle.info"),
    CIRCLE_REMOVE("permissions.commands.circle.remove", "elytrarace.circle.remove"),
    CIRCLE_MODIFY_DEPTH("permissions.commands.circle.modify.depth", "elytrarace.circle.modify.depth"),
    CIRCLE_MODIFY_HITBOX("permissions.commands.circle.modify.hitbox", "elytrarace.circle.modify.hitbox"),
    CIRCLE_MODIFY_LOCATION("permissions.commands.circle.modify.location", "elytrarace.circle.modify.location"),
    CIRCLE_MODIFY_RADIUS("permissions.commands.circle.modify.radius", "elytrarace.circle.modify.radius"),
    CIRCLE_MODIFY_RGB("permissions.commands.circle.modify.rgb", "elytrarace.circle.modify.rgb"),
    CIRCLE_MODIFY_BOOST("permissions.commands.circle.modify.boost", "elytrarace.circle.modify.boost");

    private final String path;
    private String message;

    Permissions(final String path, final String message){
        this.path = path;
        this.message = message;
    }

    public String getPath(){
        return this.path;
    }

    public String get(){
        return this.message;
    }

    public void set(final String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    public static boolean hasPermission(final CommandSender commandSender, final Permissions permission) {
        return hasPermission(commandSender, permission.get());
    }
    public static boolean hasPermission(final CommandSender commandSender, final String permission) {
        return commandSender.hasPermission(permission) || commandSender.hasPermission(Permissions.ADMIN.get());
    }

}
