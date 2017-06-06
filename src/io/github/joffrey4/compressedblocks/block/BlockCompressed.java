package io.github.joffrey4.compressedblocks.block;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.ListIterator;

public class BlockCompressed {

    private static Material material;
    private static int metadata;
    private static String name;
    private static String typeName;
    private static FileConfiguration config;

    public BlockCompressed(Material material, int metadata, String name, Main plugin) {
        BlockCompressed.material = material;
        BlockCompressed.metadata = metadata;
        BlockCompressed.name = name;
        BlockCompressed.typeName = setTypeName();
        BlockCompressed.config = plugin.getConfig();
    }

    public String setTypeName() {
        return name;
    }

    public ItemStack getItemStack() {
        return new ItemStack(material, 1, (short) metadata);
    }

    public String getDisplayName() {
        return ChatColor.GOLD + "Compressed " + name;
    }

    public List<String> getLore() {
        List<String> lore;

        if ((!config.getBoolean("UseCommonLore")) && (!config.getStringList("DetailedLore." + typeName).isEmpty())) {
            lore = config.getStringList("DetailedLore." + typeName);
        } else {
            lore = config.getStringList("CommonLore");
        }

        for (final ListIterator<String> i = lore.listIterator(); i.hasNext();) {
            final String line = i.next();

            if (line.contains("&type")) {
                i.set(line.replace("&type", name));
            }
        }
        return lore;
    }
}
