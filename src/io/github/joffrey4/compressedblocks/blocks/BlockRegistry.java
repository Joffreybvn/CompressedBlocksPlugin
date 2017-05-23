package io.github.joffrey4.compressedblocks.blocks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlockRegistry {

    // Compressed Stone Blocks
    public static ItemStack compressedStone;
    public static ItemStack compressedGranite;
    public static ItemStack compressedDiorite;
    public static ItemStack compressedAndesite;

    // Compressed Other Blocks
    public static ItemStack compressedCobblestone;

    public static void init() {

        // Compressed Stone Blocks
        compressedStone = compressedBlock(Material.STONE, 0, "Stone");
        compressedGranite = compressedBlock(Material.STONE, 1, "Granite");
        compressedDiorite = compressedBlock(Material.STONE, 3, "Diorite");
        compressedAndesite = compressedBlock(Material.STONE, 5, "Andesite");

        // Compressed Other Blocks
        compressedCobblestone = compressedBlock(Material.COBBLESTONE, 0, "Cobblestone");
    }

    private static ItemStack compressedBlock(Material material, int metadata, String name) {
        ItemStack block = new ItemStack(material, 1, (short) metadata);

        // Set new name
        ItemMeta meta = block.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Compressed " + name);

        // Set a lore
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("A block that contain");
        lore.add( "9x " + name + " !");
        meta.setLore(lore);

        block.setItemMeta(meta);
        return block;
    }
}
