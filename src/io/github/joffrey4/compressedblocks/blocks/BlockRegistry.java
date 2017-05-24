package io.github.joffrey4.compressedblocks.blocks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlockRegistry {

    // Compressed Woods
    public static ItemStack compressedOak;
    public static ItemStack compressedSpruce;
    public static ItemStack compressedBirch;
    public static ItemStack compressedJungle;
    public static ItemStack compressedAcacia;
    public static ItemStack compressedDarkOak;

    // Compressed Planks
    public static ItemStack compressedOakPlank;
    public static ItemStack compressedSprucePlank;
    public static ItemStack compressedBirchPlank;
    public static ItemStack compressedJunglePlank;
    public static ItemStack compressedAcaciaPlank;
    public static ItemStack compressedDarkOakPlank;

    // Compressed Falling Blocks
    public static ItemStack compressedGravel;
    public static ItemStack compressedSand;
    public static ItemStack compressedRedSand;

    // Compressed Stone Blocks
    public static ItemStack compressedGranite;
    public static ItemStack compressedDiorite;
    public static ItemStack compressedAndesite;

    // Others compressed Blocks
    public static ItemStack compressedDirt;
    public static ItemStack compressedCobblestone;
    public static ItemStack compressedSoulSand;
    public static ItemStack compressedNetherrack;

    public static void init() {

        // Compressed Woods
        compressedOak = compressedBlock(Material.LOG, 0, "Oak Wood");
        compressedSpruce = compressedBlock(Material.LOG, 1, "Spruce Wood");
        compressedBirch = compressedBlock(Material.LOG, 2, "Birch Wood");
        compressedJungle = compressedBlock(Material.LOG, 3, "Jungle Wood");
        compressedAcacia = compressedBlock(Material.LOG_2, 0, "Acacia Wood");
        compressedDarkOak = compressedBlock(Material.LOG_2, 1, "Dark Oak Wood");

        // Compressed Planks
        compressedOakPlank = compressedBlock(Material.WOOD, 0, "Oak Plank");
        compressedSprucePlank = compressedBlock(Material.WOOD, 1, "Spruce Plank");
        compressedBirchPlank = compressedBlock(Material.WOOD, 2, "Birch Plank");
        compressedJunglePlank = compressedBlock(Material.WOOD, 3, "Jungle Plank");
        compressedAcaciaPlank = compressedBlock(Material.WOOD, 4, "Acacia Plank");
        compressedDarkOakPlank = compressedBlock(Material.WOOD, 5, "Dark Oak Plank");

        // Compressed Falling Blocks
        compressedGravel = compressedBlock(Material.GRAVEL, 0, "Gravel");
        compressedSand = compressedBlock(Material.SAND, 0, "Sand");
        compressedRedSand = compressedBlock(Material.SAND, 1, "Red Sand");

        // Compressed Stone Blocks
        // TODO: compressed Stone -> Craft override for Stone Button
        compressedGranite = compressedBlock(Material.STONE, 1, "Granite");
        compressedDiorite = compressedBlock(Material.STONE, 3, "Diorite");
        compressedAndesite = compressedBlock(Material.STONE, 5, "Andesite");

        // Others compressed Blocks
        compressedDirt = compressedBlock(Material.DIRT, 0, "Dirt");
        compressedCobblestone = compressedBlock(Material.COBBLESTONE, 0, "Cobblestone");
        compressedSoulSand = compressedBlock(Material.SOUL_SAND, 0, "SoulSand");
        compressedNetherrack = compressedBlock(Material.NETHERRACK, 0, "Netherrack");
    }

    private static ItemStack compressedBlock(Material material, int metadata, String name) {
        ItemStack block = new ItemStack(material, 1, (short) metadata);

        // Replace the Displayname
        ItemMeta meta = block.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Compressed " + name);

        // Add a Lore
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("A block that contain");
        lore.add( "9x " + name + " !");
        meta.setLore(lore);

        block.setItemMeta(meta);
        return block;
    }
}
