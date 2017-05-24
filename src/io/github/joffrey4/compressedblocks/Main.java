package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import io.github.joffrey4.compressedblocks.recipes.RecipesRegistry;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("CompressedStone", true);
        config.options().copyDefaults(true);
        saveConfig();

        BlockRegistry.init();
        RecipesRegistry.init();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {

        // FIRST STEP: Override the default craft with the ones of this plugin
        if (event.getRecipe() instanceof ShapedRecipe) {
            ItemStack[] items = event.getInventory().getMatrix();

            if ((items.length == 9 && areNull(items[0], items[1], items[2], items[3], items[5], items[6], items[7], items[8]))
                    || items.length == 4 && areNull(items[1], items[2], items[3])) {

                if (items[4].hasItemMeta()) {

                    if (!items[4].getItemMeta().getDisplayName().contains("Compressed")) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }

                } else {
                    // SECOND STEP: Re-insert the common craft
                    if (items[4].getType() == Material.STONE) {
                        event.getInventory().setResult(new ItemStack(Material.STONE_BUTTON));
                    } else if (items[4].getType() == Material.WOOD) {
                        event.getInventory().setResult(new ItemStack(Material.WOOD_BUTTON));
                    } else if (items[4].getType() == Material.LOG) {
                        event.getInventory().setResult(new ItemStack(Material.WOOD, 4, items[4].getDurability()));
                    } else if (items[4].getType() == Material.LOG_2) {
                        event.getInventory().setResult(new ItemStack(Material.WOOD, 4, (short) (4 + items[4].getDurability())));
                    } else {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }
                }
            } else {
                // THIRD STEP: Avoid using the compressed blocks in common crafts
                for (ItemStack item : items) {
                    if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Compressed")) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

    private boolean areNull(Object... args) {
        for (Object arg : args) {
            if (arg != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins

    }

}
