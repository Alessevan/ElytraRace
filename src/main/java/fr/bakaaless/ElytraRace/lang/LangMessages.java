/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.lang;

import org.bukkit.ChatColor;

public enum LangMessages {

    /**
     * PREFIX
     */
    DEBUG_PREFIX("messages.prefix.debug", "&3&lElytraRace &4&l»&r"),
    INFO_PREFIX("messages.prefix.info", "&3&lElytraRace &8&l»&r"),
    ERROR_PREFIX("messages.prefix.error", "&c&lElytraRace &4&l»&r"),

    /**
     * DEBUG
     */
    DEBUG_READING_FILE("messages.debug.reading", "%prefix_debug% &7Reading file &6{0}&7."),
    DEBUG_CANT_READ("messages.debug.cant.read", "%prefix_debug% &cCan't read file &4{0}&c."),
    DEBUG_READ_FILE("messages.debug.read", "%prefix_debug% &7File &6{0} &7read."),
    DEBUG_SAVING_FILE("messages.debug.saving", "%prefix_debug% &7Saving file &6{0}&7."),
    DEBUG_CANT_SAVE("messages.debug.cant.save", "%prefix_debug% &cCan't save file &4{0}&c."),
    DEBUG_SAVED_FILE("messages.debug.saved", "%prefix_debug% &7File &6{0} &7saved."),

    /**
     * INFO
     */
    INFO_RELOADING("messages.info.commands.reloading", "%prefix_info% &7Rechargement..."),
    INFO_RELOADED("messages.info.commands.reloaded", "%prefix_info% &7Rechargement terminé"),
    INFO_EDITING("messages.info.commands.edit", "%prefix_info% &7Édition en cours."),
    INFO_SAVED("messages.info.commands.save", "%prefix_info% &7Instance sauvegardée, vous ne l'éditez plus."),
    INFO_DELETED("messages.info.commands.destroy", "%prefix_info% &7Parcours supprimé."),
    INFO_LIST("messages.info.commands.list", "%prefix_info% &7Liste des instances : {0}"),
    INFO_INFO("messages.info.commands.info", "%prefix_info% &7Info sur l'instance &6{0} &7: {1}"),
    INFO_CIRCLE_ADDED("messages.info.commands.circle.add", "%prefix_info% &7Ajout d'un cercle."),
    INFO_CIRCLE_REMOVED("messages.info.commands.circle.remove", "%prefix_info% &7Cercle enlevé."),
    INFO_CIRCLE_LIST("messages.info.commands.circle.list", "%prefix_info% &7Il y a {0} cercle(s) dans ce parcours."),
    INFO_CIRCLE_INFO("messages.info.commands.circle.info", "%prefix_info% &7Informations sur le cercle : {0}"),
    INFO_CIRCLE_MODIFY_RGB("messages.info.commands.circle.modify.rgb", "%prefix_info% &7Le cercle {0} a maintenant un RGB de {1}."),
    INFO_CIRCLE_MODIFY_LOCATION("messages.info.commands.circle.modify.location", "%prefix_info% &7Le cercle {0} se situe maintenant en &6{1}&7, &6{2}&7, &6{3}&7, &6{4}&7, &6{5}&7, &6{6}&7."),
    INFO_CIRCLE_MODIFY_HITBOX("messages.info.commands.circle.modify.hitbox", "%prefix_info% &7La hitbox du cercle {0} est en : &6{1}"),
    INFO_CIRCLE_MODIFY_RADIUS("messages.info.commands.circle.modify.radius", "%prefix_info% &7Le cercle {0} a maintenant un rayon de &6{1}"),
    INFO_CIRCLE_MODIFY_DEPTH("messages.info.commands.circle.modify.depth", "%prefix_info% &7Le cercle {0} a maintenant une profondeur de &6{1}"),
    INFO_CIRCLE_MODIFY_SPEED("messages.info.commands.circle.modify.boost", "%prefix_info% &7Le cercle {0} a maintenant un boost de &6{1}"),

    /**
     * ERRORS
     */
    ERROR_PERMISSION("messages.error.permission", "%prefix_error% &cVous n'avez pas la permission de faire cela."),
    ERROR_ARGUMENTS("messages.error.arguments", "%prefix_error% &cArguments invalides."),
    ERROR_NOT_FOUND("messages.error.not_found", "%prefix_error% &cParcours non trouvé."),
    ERROR_EDIT("messages.error.commands.edit", "%prefix_error% &cVous n'éditez pas d'instance."),
    ERROR_RELOAD("messages.error.commands.reload", "%prefix_error% &cErreur lors du chargement du fichier : &4{0}&c."),
    ERROR_EDITING("messages.error.commands.editing", "%prefix_error% &cQuelqu'un est déjà en train d'éditer ce parcours."),
    ERROR_SAVING("messages.error.commands.save", "%prefix_error% &cErreur lors de la sauvegarde de l'instance."),
    ERROR_INVALID_NUMBER("messages.error.invalid.number", "%prefix_error% &cVous devez rentrer un nombre."),
    ERROR_INVALID_INTEGER("messages.error.invalid.integer", "%prefix_error% &cVous devez rentrer un entier."),
    ERROR_INVALID_INDEX("messages.error.invalid.index", "%prefix_error% &cVous n'avez pas entrer un id valide."),
    ERROR_INVALID_RGB("messages.error.invalid.rgb", "%prefix_error% &cLe RGB doit avoir un format du type R,G,B (e.g. 192,192,192)");

    private final String path;
    private String message;

    LangMessages(final String path, final String message){
        this.path = path;
        this.message = message;
    }

    public String getPath(){
        return this.path;
    }

    public String get(){
        return ChatColor.translateAlternateColorCodes('&',
                this.message
                        .replace("%prefix_debug%", DEBUG_PREFIX.toString())
                        .replace("%prefix_info%", INFO_PREFIX.toString())
                        .replace("%prefix_error%", ERROR_PREFIX.toString())
        );
    }

    public String get(final String... str){
        String message = this.get();
        for (int i = 0; i < str.length; i++) {
            message = message.replace("{" + i + "}", str[i]);
        }
        return message;
    }

    public void set(final String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
