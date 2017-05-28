package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.blocks.BlockRegistry;
import io.github.joffrey4.compressedblocks.recipes.RecipesRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        //ConfigGenerator.init(config);
        //saveConfig();
        saveDefaultConfig();

        BlockRegistry.init();
        RecipesRegistry.init(config);

        getServer().getPluginManager().registerEvents(this, this);
    }

    // Implements the compressed blocks in crafts
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        // System.out.println("[DEBUG] Oncraft Event started ============= ");
        ItemStack[] items = event.getInventory().getMatrix();

        // Override the default craft with the ones of this plugin
        if (event.getRecipe() instanceof ShapedRecipe) {
            // System.out.println("ShapedRecipe OK");

            int id = itemMetaCounter(items);
            // System.out.println("ID =" + id);

            if (id >= 0) {
                event.getInventory().setResult(new ItemStack(items[id].getType(), 9, items[id].getDurability()));
                // System.out.println("Craft OK");
            } else if (id == -1) {
                System.out.println("Craft Null");
                // event.getInventory().setResult(new ItemStack(Material.AIR));
            } else {
                // System.out.println("Craft Pass");
            }

        } else if (event.getRecipe() instanceof ShapelessRecipe) {
            // System.out.println("ShapedLess OK");
            // Avoid duplicate block, and let the player uncompress netherrack, soulsand, sand
            if (!canCraftShapeless(items)) {
                // System.out.println("Craft Null");
                event.getInventory().setResult(new ItemStack(Material.AIR));
            } else {
                // System.out.println("Craft Pass");
            }
        }
        // System.out.println("[DEBUG] Oncraft Event stopped ============= ");
    }

    private int itemMetaCounter(ItemStack[] items) {

        int itemCompressed = 0;
        int itemCompressedId = 0;
        int itemNormal = 0;

        // Loop each slot of the table and count items
        for (int i = 0; i < items.length; ++i) {
            if (items[i] != null) {
                if (items[i].hasItemMeta() && items[i].getItemMeta().getDisplayName().contains("Compressed")) {
                    itemCompressed += 1;

                    // If a compressed item were already found, stop the loop
                    if (itemCompressedId == 0) {
                        itemCompressedId = i;
                    } else {
                        break;
                    }

                } else {
                    itemNormal += 1;
                }
            }
        }

        // Return the id of the item (on the craft table), if it's a compressed item alone on the craft table.
        if (itemCompressed == 1 && itemNormal == 0) {
            return itemCompressedId;
        } else if (itemCompressed >= 1 && itemNormal >= 0) {
            return -1;
        } else {
            return -2;
        }
    }

    private boolean canCraftShapeless(ItemStack[] items) {
        int itemAmount = 0;
        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {
                if (!(item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Compressed")) &&
                        item.getType() != Material.RED_ROSE && item.getType() != Material.YELLOW_FLOWER &&
                        item.getType() != Material.BONE && item.getType() != Material.BLAZE_ROD) {
                    itemAmount += 1;
                }
            }
        }
        // System.out.println("ItemAmount =" + itemAmount);
        return itemAmount != 1;
    }

    //Avoid using a compressed block in a furnace
    @EventHandler
    public void onFurnaceClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getType() == InventoryType.FURNACE) {
            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().contains("Compressed")) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage(ChatColor.RED + config.getString("Lang.FurnaceImpossible"));
            }
        }
    }

    // Avoid placing a compressed block on the ground
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().hasItemMeta() && event.getItemInHand().getItemMeta().getDisplayName().contains("Compressed")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + config.getString("Lang.PlacingImpossible"));
        }
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

}
