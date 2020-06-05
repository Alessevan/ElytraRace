/*
 * This file is a part of ElytraRace.
 * This software is under GNU General Public License.
 * Copyright 2020-present
 */

package fr.bakaaless.ElytraRace.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static @NotNull ItemStack getItem(final Material material) {
        return ItemUtils.getItem(material, material.name());
    }

    public static @NotNull ItemStack getItem(final Material material, final String displayName) {
        return ItemUtils.getItem(material, displayName, 1);
    }

    public static @NotNull ItemStack getItem(final Material material, final int amount) {
        return ItemUtils.getItem(material, material.name(), amount);
    }

    public static @NotNull ItemStack getItem(final Material material, final String displayName, final int amount) {
        return ItemUtils.getItem(material, displayName, amount, new ArrayList<>());
    }

    public static @NotNull ItemStack getItem(final Material material, final String displayName, final int amount, final List<String> lore){
        final ItemStack itemStack = new ItemStack(material, amount);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
