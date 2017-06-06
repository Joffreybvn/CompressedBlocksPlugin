package io.github.joffrey4.compressedblocks.block;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegisterBlocks {

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
    public static ItemStack compressedStone;
    public static ItemStack compressedGranite;
    public static ItemStack compressedDiorite;
    public static ItemStack compressedAndesite;

    // Others compressed Blocks
    public static ItemStack compressedDirt;
    public static ItemStack compressedCobblestone;
    public static ItemStack compressedSoulSand;
    public static ItemStack compressedNetherrack;

    public static void init(Main plugin) {

        // Compressed Woods
        compressedOak = register(new BlockCompressedWood(Material.LOG, 0, "Oak Wood", plugin));
        compressedSpruce = register(new BlockCompressedWood(Material.LOG, 1, "Spruce Wood", plugin));
        compressedBirch = register(new BlockCompressedWood(Material.LOG, 2, "Birch Wood", plugin));
        compressedJungle = register(new BlockCompressedWood(Material.LOG, 3, "Jungle Wood", plugin));
        compressedAcacia = register(new BlockCompressedWood(Material.LOG_2, 0, "Acacia Wood", plugin));
        compressedDarkOak = register(new BlockCompressedWood(Material.LOG_2, 1, "Dark Oak Wood", plugin));

        // Compressed Planks
        compressedOakPlank = register(new BlockCompressedPlank(Material.WOOD, 0, "Oak Plank", plugin));
        compressedSprucePlank = register(new BlockCompressedPlank(Material.WOOD, 1, "Spruce Plank", plugin));
        compressedBirchPlank = register(new BlockCompressedPlank(Material.WOOD, 2, "Birch Plank", plugin));
        compressedJunglePlank = register(new BlockCompressedPlank(Material.WOOD, 3, "Jungle Plank", plugin));
        compressedAcaciaPlank = register(new BlockCompressedPlank(Material.WOOD, 4, "Acacia Plank", plugin));
        compressedDarkOakPlank = register(new BlockCompressedPlank(Material.WOOD, 5, "Dark Oak Plank", plugin));

        // Compressed Falling Blocks
        compressedGravel = register(new BlockCompressed(Material.GRAVEL, 0, "Gravel", plugin));
        compressedSand = register(new BlockCompressed(Material.SAND, 0, "Sand", plugin));
        compressedRedSand = register(new BlockCompressedRedSand(Material.SAND, 1, "Red Sand", plugin));

        // Compressed Stone Blocks
        compressedStone = register(new BlockCompressed(Material.STONE, 0, "Stone", plugin));
        compressedGranite = register(new BlockCompressed(Material.STONE, 1, "Granite", plugin));
        compressedDiorite = register(new BlockCompressed(Material.STONE, 3, "Diorite", plugin));
        compressedAndesite = register(new BlockCompressed(Material.STONE, 5, "Andesite", plugin));

        // Others compressed Blocks
        compressedDirt = register(new BlockCompressed(Material.DIRT, 0, "Dirt", plugin));
        compressedCobblestone = register(new BlockCompressed(Material.COBBLESTONE, 0, "Cobblestone", plugin));
        compressedSoulSand = register(new BlockCompressedSoulSand(Material.SOUL_SAND, 0, "Soul Sand", plugin));
        compressedNetherrack = register(new BlockCompressed(Material.NETHERRACK, 0, "Netherrack", plugin));
    }

    private static ItemStack register(BlockCompressed block) {
        // Create a stack and get its meta
        ItemStack itemStack = block.getItemStack();
        ItemMeta meta = itemStack.getItemMeta();

        // Write the DisplayName & the Lore
        meta.setDisplayName(block.getDisplayName());
        meta.setLore(block.getLore());

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
