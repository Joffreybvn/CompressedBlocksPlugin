package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import io.github.joffrey4.compressedblocks.recipes.RecipesRegistry;
import org.bukkit.ChatColor;
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

        if (event.getRecipe() instanceof ShapedRecipe) {

            ItemStack[] items = event.getInventory().getMatrix();
            if (areNull(items[0], items[1], items[2], items[3], items[5], items[6], items[7], items[8])) {

                if (items[4].hasItemMeta()) {

                    if(!items[4].getItemMeta().getDisplayName().contains("Compressed")) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }

                } else {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }

    boolean areNull(Object... args) {
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
