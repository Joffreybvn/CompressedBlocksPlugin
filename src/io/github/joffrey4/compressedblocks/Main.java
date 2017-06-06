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
        saveDefaultConfig();

        BlockRegistry.init(this);
        RecipesRegistry.init(config);

        getServer().getPluginManager().registerEvents(this, this);
    }

    // Implements the recipes in crafts
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getMatrix();

        // Shaped Recipes: Block compressing, and some uncompressing recipes
        if (event.getRecipe() instanceof ShapedRecipe) {

            // Allow compressing only if the player has the permission
            if (event.getRecipe().getResult().hasItemMeta() && event.getRecipe().getResult().getItemMeta().getDisplayName().contains("Compressed")) {
                if (!event.getView().getPlayer().hasPermission("compressedblocks.compress")) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }

            // Allow uncompressing only for the compressed items, and if the player has the permission
            } else {
                int id = itemMetaCounter(items);

                if (id >= 0) {
                    if (event.getView().getPlayer().hasPermission("compressedblocks.uncompress")) {
                        event.getInventory().setResult(new ItemStack(items[id].getType(), 9, items[id].getDurability()));
                    } else {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }
                } else if (id == -1) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }

        // Shapeless Recipes: uncompressing of netherrack, sand and soulsand.
        } else if (event.getRecipe() instanceof ShapelessRecipe) {

            // Allow uncompressing if the player has the permission
            if (canCraftShapeless(items) == 1) {
                if (!event.getView().getPlayer().hasPermission("compressedblocks.uncompress")) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            // Avoid to duplicate standard blocks.
            } else if (canCraftShapeless(items) == 0) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
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

    private int canCraftShapeless(ItemStack[] items) {
        int itemNormal = 0;
        int itemforbidden = 0;
        int itemCompressed = 0;

        for (ItemStack item : items) {
            if (item != null) {

                if (item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Compressed")) {
                    itemCompressed +=1;
                } else if (item.getType() == Material.RED_ROSE || item.getType() == Material.YELLOW_FLOWER ||
                        item.getType() == Material.BONE || item.getType() == Material.BLAZE_ROD) {
                    itemNormal += 1;
                } else {
                    itemforbidden += 1;
                }
            }
        }

        if (itemCompressed == 1 && (itemforbidden + itemNormal) == 0) {
            return 1;
        } else if (itemforbidden == 1 && (itemCompressed + itemNormal) == 0) {
            return 0;
        } else {
            return 2;
        }
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
