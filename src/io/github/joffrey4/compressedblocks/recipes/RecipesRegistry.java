package io.github.joffrey4.compressedblocks.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import org.bukkit.material.MaterialData;

public class RecipesRegistry {

    // Granite Recipes declaration
    public static ShapedRecipe graniteCompressing;
    public static ShapedRecipe graniteUncompressing;

    // Diorite Recipes declaration
    public static ShapedRecipe dioriteCompressing;
    public static ShapedRecipe dioriteUncompressing;

    // Andesite Recipes declaration
    public static ShapedRecipe andesiteCompressing;
    public static ShapedRecipe andesiteUncompressing;

    // Cobblestone Recipes declaration
    public static ShapedRecipe cobblestoneCompressing;
    public static ShapedRecipe cobblestoneUncompressing;

    public static void init() {
        Server server = Bukkit.getServer();

        // Granite compressing and uncompressing
        server.addRecipe(graniteCompressing = Compressing(BlockRegistry.compressedGranite, Material.STONE, 1));
        server.addRecipe(graniteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 1), BlockRegistry.compressedGranite.getData()));

        // Diorite compressing and uncompressing
        server.addRecipe(dioriteCompressing = Compressing(BlockRegistry.compressedDiorite, Material.STONE, 3));
        server.addRecipe(dioriteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 3), BlockRegistry.compressedDiorite.getData()));

        // Andesite compressing and uncompressing
        server.addRecipe(andesiteCompressing = Compressing(BlockRegistry.compressedAndesite, Material.STONE, 5));
        server.addRecipe(andesiteUncompressing = UnCompressing(new ItemStack(Material.STONE, 9, (short) 5), BlockRegistry.compressedAndesite.getData()));

        // Cobblestone compressing and uncompressing
        server.addRecipe(cobblestoneCompressing = Compressing(BlockRegistry.compressedCobblestone, Material.COBBLESTONE, 0));
        server.addRecipe(cobblestoneUncompressing = UnCompressing(new ItemStack(Material.COBBLESTONE, 9), BlockRegistry.compressedCobblestone.getData()));
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
