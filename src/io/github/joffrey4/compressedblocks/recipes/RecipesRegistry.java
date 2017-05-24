package io.github.joffrey4.compressedblocks.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;

public class RecipesRegistry {

    /*
    // Stone declaration
    public static ShapedRecipe stoneCompressing;
    private static ShapelessRecipe stoneUncompressing;
    */

    // Cobblestone declaration
    public static ShapedRecipe cobblestoneCompressing;
    public static ShapedRecipe cobblestoneUncompressing;

    public static void init() {

        Server server = Bukkit.getServer();

        /*
        // Stone compressing and uncompressing
        stoneCompressing = Compressing(BlockRegistry.compressedStone, new MaterialData(Material.STONE, (byte) 0));
        stoneUncompressing = UnCompressing(new ItemStack(Material.STONE), BlockRegistry.compressedStone.getData());
        server.addRecipe(stoneCompressing);
        server.addRecipe(stoneUncompressing);


        server.addRecipe(Compressing(BlockRegistry.compressedGranite, new MaterialData(Material.STONE, (byte) 1)));
        server.addRecipe(Compressing(BlockRegistry.compressedDiorite, new MaterialData(Material.STONE, (byte) 3)));
        server.addRecipe(Compressing(BlockRegistry.compressedAndesite, new MaterialData(Material.STONE, (byte) 5)));
        */

        // Cobblestone compressing and uncompressing
        cobblestoneCompressing = Compressing(BlockRegistry.compressedCobblestone, new MaterialData(Material.COBBLESTONE));
        cobblestoneUncompressing = UnCompressing(new ItemStack(Material.COBBLESTONE, 9), BlockRegistry.compressedCobblestone.getData());

        //cobblestoneUncompressing = UnCompressing(new ItemStack(Material.COBBLESTONE, 9), BlockRegistry.compressedCobblestone.getData());
        server.addRecipe(cobblestoneCompressing);
        server.addRecipe(cobblestoneUncompressing);
    }

    private static ShapedRecipe Compressing(ItemStack itemResult, MaterialData ingredient) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(itemResult);
        shapedRecipe.shape("III", "III", "III");
        shapedRecipe.setIngredient('I', ingredient);
        return shapedRecipe;
    }

    private static ShapedRecipe UnCompressing(ItemStack itemResult, MaterialData ingredient) {
        ShapedRecipe shapedRecipe = new ShapedRecipe(itemResult);
        shapedRecipe.shape("   ", " I ", "   ");
        shapedRecipe.setIngredient('I', ingredient);
        //shapedRecipe.setIngredient('A', Material.AIR);
        return shapedRecipe;
    }

    /*
    private static ShapelessRecipe UnCompressing(ItemStack itemResult, MaterialData ingredient) {
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(itemResult);
        shapelessRecipe.addIngredient(1, ingredient);
        return shapelessRecipe;
    }
    */
}
