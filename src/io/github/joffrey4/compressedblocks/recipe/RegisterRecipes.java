package io.github.joffrey4.compressedblocks.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import io.github.joffrey4.compressedblocks.block.RegisterBlocks;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;

public class RegisterRecipes {

    public static ShapelessRecipe uncompression;

    // Wood Recipes declaration
    public static ShapedRecipe oakCompressing;
    public static ShapedRecipe spruceCompressing;
    public static ShapedRecipe birchCompressing;
    public static ShapedRecipe jungleCompressing;
    public static ShapedRecipe acaciaCompressing;
    public static ShapedRecipe darkOakCompressing;

    // Planks Recipes declaration
    public static ShapedRecipe oakPlankCompressing;
    public static ShapedRecipe sprucePlankCompressing;
    public static ShapedRecipe birchPlankCompressing;
    public static ShapedRecipe junglePlankCompressing;
    public static ShapedRecipe acaciaPlankCompressing;
    public static ShapedRecipe darkOakPlankCompressing;

    // Gravel Recipes declaration
    public static ShapedRecipe gravelCompressing;

    // Sand Recipes declaration
    public static ShapedRecipe sandCompressing;

    public static ShapedRecipe redSandCompressing;

    // Stone Recipes declaration
    public static ShapedRecipe stoneCompressing;

    // Granite Recipes declaration
    public static ShapedRecipe graniteCompressing;

    // Diorite Recipes declaration
    public static ShapedRecipe dioriteCompressing;

    // Andesite Recipes declaration
    public static ShapedRecipe andesiteCompressing;

    // Dirt Recipes declaration
    public static ShapedRecipe dirtCompressing;

    // Cobblestone Recipes declaration
    public static ShapedRecipe cobblestoneCompressing;

    // Soul Sand Recipes declaration
    public static ShapedRecipe soulSandCompressing;

    // Netherrack Recipes declaration
    public static ShapedRecipe netherrackCompressing;

    public static void init(FileConfiguration config) {
        Server server = Bukkit.getServer();

        server.addRecipe(uncompression = UnCompressing(new ItemStack(Material.AIR), new ItemStack(Material.SKULL_ITEM, 1, (short)3).getData()));

        /*************************************************************************************************
         * Compressed WOODS - Recipes                                                                    *
         *************************************************************************************************/

        // Oak Wood compressing
        if (config.getBoolean("oakwood.compressing")) {
            server.addRecipe(oakCompressing = Compressing(RegisterBlocks.compressedOak, Material.LOG, 0));
        }

        // Spruce Wood compressing
        if (config.getBoolean("sprucewood.compressing")) {
            server.addRecipe(spruceCompressing = Compressing(RegisterBlocks.compressedSpruce, Material.LOG, 1));
        }

        // Birch Wood compressing
        if (config.getBoolean("birchwood.compressing")) {
            server.addRecipe(birchCompressing = Compressing(RegisterBlocks.compressedBirch, Material.LOG, 2));
        }

        // Jungle Wood compressing
        if (config.getBoolean("junglewood.compressing")) {
            server.addRecipe(jungleCompressing = Compressing(RegisterBlocks.compressedJungle, Material.LOG, 3));
        }

        // Acacia Wood compressing
        if (config.getBoolean("acaciawood.compressing")) {
            server.addRecipe(acaciaCompressing = Compressing(RegisterBlocks.compressedAcacia, Material.LOG_2, 0));
        }

        // Dark Oak Wood compressing
        if (config.getBoolean("darkoakwood.compressing")) {
            server.addRecipe(darkOakCompressing = Compressing(RegisterBlocks.compressedDarkOak, Material.LOG_2, 1));
        }

        /*************************************************************************************************
         * Compressed PLANKS - Recipes                                                                   *
         *************************************************************************************************/

        // Oak Plank compressing
        if (config.getBoolean("oakplank.compressing")) {
            server.addRecipe(oakPlankCompressing = Compressing(RegisterBlocks.compressedOakPlank, Material.WOOD, 0));
        }

        // Spruce Plank compressing
        if (config.getBoolean("spruceplank.compressing")) {
            server.addRecipe(sprucePlankCompressing = Compressing(RegisterBlocks.compressedSprucePlank, Material.WOOD, 1));
        }

        // Birch Plank compressing
        if (config.getBoolean("birchplank.compressing")) {
            server.addRecipe(birchPlankCompressing = Compressing(RegisterBlocks.compressedBirchPlank, Material.WOOD, 2));
        }

        // Jungle Plank compressing
        if (config.getBoolean("jungleplank.compressing")) {
            server.addRecipe(junglePlankCompressing = Compressing(RegisterBlocks.compressedJunglePlank, Material.WOOD, 3));
        }

        // Acacia Plank compressing
        if (config.getBoolean("acaciaplank.compressing")) {
            server.addRecipe(acaciaPlankCompressing = Compressing(RegisterBlocks.compressedAcaciaPlank, Material.WOOD, 4));
        }

        // Dark Oak Plank compressing
        if (config.getBoolean("darkoakplank.compressing")) {
            server.addRecipe(darkOakPlankCompressing = Compressing(RegisterBlocks.compressedDarkOakPlank, Material.WOOD, 5));
        }

        /*************************************************************************************************
         * Compressed FALLING Blocks - Recipes                                                           *
         *************************************************************************************************/

        // Gravel compressing
        if (config.getBoolean("gravel.compressing")) {
            server.addRecipe(gravelCompressing = Compressing(RegisterBlocks.compressedGravel, Material.GRAVEL, 0));
        }

        // Sand compressing
        if (config.getBoolean("sand.compressing")) {
            server.addRecipe(sandCompressing = Compressing(RegisterBlocks.compressedSand, Material.SAND, 0));
        }

        // Red Sand compressing
        if (config.getBoolean("redsand.compressing")) {
            server.addRecipe(redSandCompressing = Compressing(RegisterBlocks.compressedRedSand, Material.SAND, 1));
        }


        /*************************************************************************************************
         * Compressed STONE Blocks - Recipes                                                             *
         *************************************************************************************************/

        // Stone compressing
        if (config.getBoolean("stone.compressing")) {
            server.addRecipe(stoneCompressing = Compressing(RegisterBlocks.compressedStone, Material.STONE, 0));
        }

        // Granite compressing
        if (config.getBoolean("granite.compressing")) {
            server.addRecipe(graniteCompressing = Compressing(RegisterBlocks.compressedGranite, Material.STONE, 1));
        }

        // Diorite compressing
        if (config.getBoolean("diorite.compressing")) {
            server.addRecipe(dioriteCompressing = Compressing(RegisterBlocks.compressedDiorite, Material.STONE, 3));
        }

        // Andesite compressing
        if (config.getBoolean("andesite.compressing")) {
            server.addRecipe(andesiteCompressing = Compressing(RegisterBlocks.compressedAndesite, Material.STONE, 5));
        }

        /*************************************************************************************************
         * Compressed OTHER Blocks - Recipes                                                             *
         *************************************************************************************************/

        // Dirt compressing
        if (config.getBoolean("dirt.compressing")) {
            server.addRecipe(dirtCompressing = Compressing(RegisterBlocks.compressedDirt, Material.DIRT, 0));
        }

        // Cobblestone compressing
        if (config.getBoolean("cobblestone.compressing")) {
            server.addRecipe(cobblestoneCompressing = Compressing(RegisterBlocks.compressedCobblestone, Material.COBBLESTONE, 0));
        }

        // Soul Sand compressing
        if (config.getBoolean("soulsand.compressing")) {
            server.addRecipe(soulSandCompressing = Compressing(RegisterBlocks.compressedSoulSand, Material.SOUL_SAND, 0));
        }

        // Netherrack compressing
        if (config.getBoolean("netherrack.compressing")) {
            server.addRecipe(netherrackCompressing = Compressing(RegisterBlocks.compressedNetherrack, Material.NETHERRACK, 0));
        }

    }

    private static ShapedRecipe Compressing(ItemStack itemResult, Material ingredient, int meta) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(itemResult);
        shapedRecipe.shape("III", "III", "III");
        shapedRecipe.setIngredient('I', ingredient, meta);
        return shapedRecipe;
    }

    private static ShapelessRecipe UnCompressing(ItemStack itemResult, MaterialData ingredient) {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(itemResult);
        shapelessRecipe.addIngredient(ingredient);
        return shapelessRecipe;
    }
}
