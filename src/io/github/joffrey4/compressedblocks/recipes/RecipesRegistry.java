package io.github.joffrey4.compressedblocks.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import org.bukkit.material.MaterialData;

public class RecipesRegistry {

    // Wood Recipes declaration
    public static ShapedRecipe oakCompressing;
    public static ShapedRecipe oakUncompressing;

    public static ShapedRecipe spruceCompressing;
    public static ShapedRecipe spruceUncompressing;

    public static ShapedRecipe birchCompressing;
    public static ShapedRecipe birchUncompressing;

    public static ShapedRecipe jungleCompressing;
    public static ShapedRecipe jungleUncompressing;

    public static ShapedRecipe acaciaCompressing;
    public static ShapedRecipe acaciaUncompressing;

    public static ShapedRecipe darkOakCompressing;
    public static ShapedRecipe odarkOakUncompressing;

    // Planks Recipes declaration
    public static ShapedRecipe oakPlankCompressing;
    public static ShapedRecipe oakPlankUncompressing;

    public static ShapedRecipe sprucePlankCompressing;
    public static ShapedRecipe sprucePlankUncompressing;

    public static ShapedRecipe birchPlankCompressing;
    public static ShapedRecipe birchPlankUncompressing;

    public static ShapedRecipe junglePlankCompressing;
    public static ShapedRecipe junglePlankUncompressing;

    public static ShapedRecipe acaciaPlankCompressing;
    public static ShapedRecipe acaciaPlankUncompressing;

    public static ShapedRecipe darkOakPlankCompressing;
    public static ShapedRecipe odarkOakPlankUncompressing;

    // Gravel Recipes declaration
    public static ShapedRecipe gravelCompressing;
    public static ShapedRecipe gravelUncompressing;

    // Sand Recipes declaration
    public static ShapedRecipe sandCompressing;
    public static ShapedRecipe sandUncompressing;

    public static ShapedRecipe redSandCompressing;
    public static ShapedRecipe redSandUncompressing;

    // Stone Recipes declaration
    public static ShapedRecipe stoneCompressing;
    public static ShapedRecipe stoneUncompressing;

    // Granite Recipes declaration
    public static ShapedRecipe graniteCompressing;
    public static ShapedRecipe graniteUncompressing;

    // Diorite Recipes declaration
    public static ShapedRecipe dioriteCompressing;
    public static ShapedRecipe dioriteUncompressing;

    // Andesite Recipes declaration
    public static ShapedRecipe andesiteCompressing;
    public static ShapedRecipe andesiteUncompressing;

    // Dirt Recipes declaration
    public static ShapedRecipe dirtCompressing;
    public static ShapedRecipe dirtUncompressing;

    // Cobblestone Recipes declaration
    public static ShapedRecipe cobblestoneCompressing;
    public static ShapedRecipe cobblestoneUncompressing;

    // Soul Sand Recipes declaration
    public static ShapedRecipe soulSandCompressing;
    public static ShapedRecipe soulSandUncompressing;

    // Netherrack Recipes declaration
    public static ShapedRecipe netherrackCompressing;
    public static ShapedRecipe netherrackUncompressing;

    public static void init() {
        Server server = Bukkit.getServer();

        /*************************************************************************************************
         * Compressed WOODS - Recipes                                                                    *
         *************************************************************************************************/
        // Oak Wood compressing and uncompressing
        server.addRecipe(oakCompressing = Compressing(BlockRegistry.compressedOak, Material.LOG, 0));
        server.addRecipe(oakUncompressing = UnCompressing(new ItemStack(Material.LOG, 9, (short) 0), BlockRegistry.compressedOak.getData()));

        // Spruce Wood compressing and uncompressing
        server.addRecipe(spruceCompressing = Compressing(BlockRegistry.compressedSpruce, Material.LOG, 1));
        server.addRecipe(spruceUncompressing = UnCompressing(new ItemStack(Material.LOG, 9, (short) 1), BlockRegistry.compressedSpruce.getData()));

        // Birch Wood compressing and uncompressing
        server.addRecipe(birchCompressing = Compressing(BlockRegistry.compressedBirch, Material.LOG, 2));
        server.addRecipe(birchUncompressing = UnCompressing(new ItemStack(Material.LOG, 9, (short) 2), BlockRegistry.compressedBirch.getData()));

        // Jungle Wood compressing and uncompressing
        server.addRecipe(jungleCompressing = Compressing(BlockRegistry.compressedJungle, Material.LOG, 3));
        server.addRecipe(jungleUncompressing = UnCompressing(new ItemStack(Material.LOG, 9, (short) 3), BlockRegistry.compressedJungle.getData()));

        // Acacia Wood compressing and uncompressing
        server.addRecipe(acaciaCompressing = Compressing(BlockRegistry.compressedAcacia, Material.LOG_2, 0));
        server.addRecipe(acaciaUncompressing = UnCompressing(new ItemStack(Material.LOG_2, 9, (short) 0), BlockRegistry.compressedAcacia.getData()));

        // Dark Oak Wood compressing and uncompressing
        server.addRecipe(darkOakCompressing = Compressing(BlockRegistry.compressedDarkOak, Material.LOG_2, 1));
        server.addRecipe(odarkOakUncompressing = UnCompressing(new ItemStack(Material.LOG_2, 9, (short) 1), BlockRegistry.compressedDarkOak.getData()));

        /*************************************************************************************************
         * Compressed PLANKS - Recipes                                                                   *
         *************************************************************************************************/

        // Oak Plank compressing and uncompressing
        server.addRecipe(oakPlankCompressing = Compressing(BlockRegistry.compressedOakPlank, Material.WOOD, 0));
        server.addRecipe(oakPlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 0), BlockRegistry.compressedOakPlank.getData()));

        // Spruce Plank compressing and uncompressing
        server.addRecipe(sprucePlankCompressing = Compressing(BlockRegistry.compressedSprucePlank, Material.WOOD, 1));
        server.addRecipe(sprucePlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 1), BlockRegistry.compressedSprucePlank.getData()));

        // Birch Plank compressing and uncompressing
        server.addRecipe(birchPlankCompressing = Compressing(BlockRegistry.compressedBirchPlank, Material.WOOD, 2));
        server.addRecipe(birchPlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 2), BlockRegistry.compressedBirchPlank.getData()));

        // Jungle Plank compressing and uncompressing
        server.addRecipe(junglePlankCompressing = Compressing(BlockRegistry.compressedJunglePlank, Material.WOOD, 3));
        server.addRecipe(junglePlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 3), BlockRegistry.compressedJunglePlank.getData()));

        // Acacia Plank compressing and uncompressing
        server.addRecipe(acaciaPlankCompressing = Compressing(BlockRegistry.compressedAcaciaPlank, Material.WOOD, 4));
        server.addRecipe(acaciaPlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 4), BlockRegistry.compressedAcaciaPlank.getData()));

        // Dark Oak Plank compressing and uncompressing
        server.addRecipe(darkOakPlankCompressing = Compressing(BlockRegistry.compressedDarkOakPlank, Material.WOOD, 5));
        server.addRecipe(odarkOakPlankUncompressing = UnCompressing(new ItemStack(Material.WOOD, 9, (short) 5), BlockRegistry.compressedDarkOakPlank.getData()));

        /*************************************************************************************************
         * Compressed FALLING Blocks - Recipes                                                           *
         *************************************************************************************************/

        // Gravel compressing and uncompressing
        server.addRecipe(gravelCompressing = Compressing(BlockRegistry.compressedGravel, Material.GRAVEL, 0));
        server.addRecipe(gravelUncompressing = UnCompressing(new ItemStack(Material.GRAVEL, 9), BlockRegistry.compressedGravel.getData()));

        // Sand compressing and uncompressing
        server.addRecipe(sandCompressing = Compressing(BlockRegistry.compressedSand, Material.SAND, 0));
        server.addRecipe(sandUncompressing = UnCompressing(new ItemStack(Material.SAND, 9, (short) 0), BlockRegistry.compressedSand.getData()));

        // Red Sand compressing and uncompressing
        server.addRecipe(redSandCompressing = Compressing(BlockRegistry.compressedRedSand, Material.SAND, 1));
        server.addRecipe(redSandUncompressing = UnCompressing(new ItemStack(Material.SAND, 9, (short) 1), BlockRegistry.compressedRedSand.getData()));

        /*************************************************************************************************
         * Compressed STONE Blocks - Recipes                                                             *
         *************************************************************************************************/

        // Stone compressing and uncompressing
        server.addRecipe(stoneCompressing = Compressing(BlockRegistry.compressedStone, Material.STONE, 0));
        server.addRecipe(stoneUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 0), BlockRegistry.compressedStone.getData()));

        // Granite compressing and uncompressing
        server.addRecipe(graniteCompressing = Compressing(BlockRegistry.compressedGranite, Material.STONE, 1));
        server.addRecipe(graniteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 1), BlockRegistry.compressedGranite.getData()));

        // Diorite compressing and uncompressing
        server.addRecipe(dioriteCompressing = Compressing(BlockRegistry.compressedDiorite, Material.STONE, 3));
        server.addRecipe(dioriteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 3), BlockRegistry.compressedDiorite.getData()));

        // Andesite compressing and uncompressing
        server.addRecipe(andesiteCompressing = Compressing(BlockRegistry.compressedAndesite, Material.STONE, 5));
        server.addRecipe(andesiteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 5), BlockRegistry.compressedAndesite.getData()));

        /*************************************************************************************************
         * Compressed OTHER Blocks - Recipes                                                             *
         *************************************************************************************************/

        // Dirt compressing and uncompressing
        server.addRecipe(dirtCompressing = Compressing(BlockRegistry.compressedCobblestone, Material.COBBLESTONE, 0));
        server.addRecipe(dirtUncompressing = UnCompressing(new ItemStack(Material.COBBLESTONE, 9), BlockRegistry.compressedCobblestone.getData()));

        // Cobblestone compressing and uncompressing
        server.addRecipe(cobblestoneCompressing = Compressing(BlockRegistry.compressedDirt, Material.DIRT, 0));
        server.addRecipe(cobblestoneUncompressing = UnCompressing(new ItemStack(Material.DIRT, 9), BlockRegistry.compressedDirt.getData()));

        // Soul Sand compressing and uncompressing
        server.addRecipe(soulSandCompressing = Compressing(BlockRegistry.compressedSoulSand, Material.SOUL_SAND, 0));
        server.addRecipe(soulSandUncompressing = UnCompressing(new ItemStack(Material.SOUL_SAND, 9), BlockRegistry.compressedSoulSand.getData()));

        // Netherrack compressing and uncompressing
        server.addRecipe(netherrackCompressing = Compressing(BlockRegistry.compressedNetherrack, Material.NETHERRACK, 0));
        server.addRecipe(netherrackUncompressing = UnCompressing(new ItemStack(Material.NETHERRACK, 9), BlockRegistry.compressedNetherrack.getData()));
    }

    private static ShapedRecipe Compressing(ItemStack itemResult, Material ingredient, int meta) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(itemResult);
        shapedRecipe.shape("III", "III", "III");
        shapedRecipe.setIngredient('I', ingredient, meta);
        return shapedRecipe;
    }

    private static ShapedRecipe UnCompressing(ItemStack itemResult, MaterialData ingredient) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(itemResult);
        shapedRecipe.shape("   ", " I ", "   ");
        shapedRecipe.setIngredient('I', ingredient);
        return shapedRecipe;
    }
}
