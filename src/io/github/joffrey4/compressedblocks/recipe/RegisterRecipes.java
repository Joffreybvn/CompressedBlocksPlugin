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

    public static void init(FileConfiguration config) {
        Server server = Bukkit.getServer();

        // Uncompressed Blocks (ALL)
        server.addRecipe(UnCompressing(new ItemStack(Material.AIR), new ItemStack(Material.SKULL_ITEM, 1, (short)3).getData()));

        // Compressed WOODS (Logs)
        server.addRecipe(Compressing(RegisterBlocks.compressedOak, Material.LOG, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedSpruce, Material.LOG, 1));
        server.addRecipe(Compressing(RegisterBlocks.compressedBirch, Material.LOG, 2));
        server.addRecipe(Compressing(RegisterBlocks.compressedJungle, Material.LOG, 3));
        server.addRecipe(Compressing(RegisterBlocks.compressedAcacia, Material.LOG_2, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedDarkOak, Material.LOG_2, 1));

        // Compressed PLANKS
        server.addRecipe(Compressing(RegisterBlocks.compressedOakPlank, Material.WOOD, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedSprucePlank, Material.WOOD, 1));
        server.addRecipe(Compressing(RegisterBlocks.compressedBirchPlank, Material.WOOD, 2));
        server.addRecipe(Compressing(RegisterBlocks.compressedJunglePlank, Material.WOOD, 3));
        server.addRecipe(Compressing(RegisterBlocks.compressedAcaciaPlank, Material.WOOD, 4));
        server.addRecipe(Compressing(RegisterBlocks.compressedDarkOakPlank, Material.WOOD, 5));

        // Compressed FALLING Blocks
        server.addRecipe(Compressing(RegisterBlocks.compressedGravel, Material.GRAVEL, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedSand, Material.SAND, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedRedSand, Material.SAND, 1));

        // Compressed STONE Blocks
        server.addRecipe(Compressing(RegisterBlocks.compressedStone, Material.STONE, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedGranite, Material.STONE, 1));
        server.addRecipe(Compressing(RegisterBlocks.compressedDiorite, Material.STONE, 3));
        server.addRecipe(Compressing(RegisterBlocks.compressedAndesite, Material.STONE, 5));

        // Compressed OTHER Blocks
        server.addRecipe(Compressing(RegisterBlocks.compressedDirt, Material.DIRT, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedCobblestone, Material.COBBLESTONE, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedSoulSand, Material.SOUL_SAND, 0));
        server.addRecipe(Compressing(RegisterBlocks.compressedNetherrack, Material.NETHERRACK, 0));
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
