package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class EventOnCraft implements Listener {

    private FileConfiguration config;

    public EventOnCraft(Main plugin) {
        this.config = plugin.getConfig();
    }

    // Implements the recipe in crafts
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getMatrix();

        // Shaped Recipes: Block compressing, and some uncompressing recipe
        if (event.getRecipe() instanceof ShapedRecipe) {

            // Allow compressing only if the player has the permission
            if (isCompressedBlock(event.getRecipe().getResult())) {
                if (!event.getView().getPlayer().hasPermission("compressedblocks.compress")) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                } else {
                    //System.out.print("Compressing allowed");
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
                // Avoid to duplicate standard block.
            } else if (canCraftShapeless(items) == 0) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            } else {
                //System.out.print("Shapeless Recipe == 2");
            }
        }
    }

    private int itemMetaCounter(ItemStack[] items) {

        int itemCompressed = 0;
        int itemCompressedId = 0;
        int itemNormal = 0;

        // Loop each slot of the table and count items
        for (int i = 0; i < items.length; ++i) {
            if (items[i] != null && items[i].getType() != Material.AIR) {
                if (isCompressedBlock(items[i])) {
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
            if (item != null && item.getType() != Material.AIR) {

                if (isCompressedBlock(item)) {
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

    /**
     * Check if a given ItemStack is a compressed block.
     *
     * @param itemStack An item or block stack object.
     * @return true  If the ItemStack is a compressed block
     *         false If it's not a compressed block.
     */
    private Boolean isCompressedBlock(ItemStack itemStack) {
        return itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName().contains("Compressed") && itemStack.getItemMeta().hasLore() && (!itemStack.getItemMeta().getLore().isEmpty());
    }
}
