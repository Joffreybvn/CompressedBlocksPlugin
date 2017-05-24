package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import io.github.joffrey4.compressedblocks.recipes.RecipesRegistry;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
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

    /*
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getInventory() instanceof CraftingInventory){
            CraftingInventory inv = (CraftingInventory) event.getInventory();
            System.out.println(event.getRecipe());
            System.out.println(RecipesRegistry.cobblestoneUncompressing);
            System.out.println(event.getRecipe().equals(RecipesRegistry.cobblestoneUncompressing));
            System.out.println("----------------------------------------------------------------");

            if(event.getRecipe().equals(RecipesRegistry.cobblestoneUncompressing)){
                ItemStack item = inv.getMatrix()[4];//get the middle item in the bench, which is the helmet
                System.out.println("recipe ok");

                if(item.hasItemMeta()){//make sure the helmet has item meta
                    System.out.println("meta ok");
                    if(item.getItemMeta().getDisplayName().equals("Compressed Cobblestone")){//make sure the helmet's display name is 'Better Helmet'
                        //you're done! It works! Do something like tell the player "you have crafted better armor" or something here.
                        System.out.println("craft ok");
                        return;
                    }
                }
                //the return; above would have been called if the crafting had succeeded. When it got called, the remainder of this method would not run (this part will not be run if the crafting has succeeded)
                //send the player a message to make it more realistic here. For my wizardry server I use: 'One of thee items used was incorrect, and unbalanced the energy. The death block hath been destroyed'
                event.setCancelled(true);
            }
        }
    }
    */

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getInventory() instanceof CraftingInventory){
            CraftingInventory inv = (CraftingInventory) event.getInventory();
            System.out.println(event.getRecipe().getResult().hasItemMeta());

            if(event.getRecipe().getResult().hasItemMeta()){
                ItemStack item = event.getRecipe().getResult();
                System.out.println(item.getItemMeta().getDisplayName().equals("Compressed Cobblestone"));

                if (item.getItemMeta().getDisplayName().equals("Compressed Cobblestone")) {
                    System.out.println("craft ok");
                    return;
                }
            }
            event.setCancelled(true);
        }
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins

    }

}
