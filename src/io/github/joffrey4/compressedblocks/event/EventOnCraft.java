package io.github.joffrey4.compressedblocks.event;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.EnumUUID;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.SkullMeta;

public class EventOnCraft extends EventBase implements Listener {

    private FileConfiguration config;

    public EventOnCraft(Main plugin) {
        super(plugin);
        this.config = plugin.getConfig();
    }

    // Implements the recipe in crafts
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        ItemStack[] items = event.getInventory().getMatrix();

        // Shaped Recipes: Block compressing, and some uncompressing recipe
        if (event.getRecipe() instanceof ShapedRecipe) {
            System.out.print("ShapedRecipe");

            // Allow compressing only if the player has the permission
            if (isCompressedBlock(event.getRecipe().getResult())) {
                System.out.print("IsCompressing");

                if (!event.getView().getPlayer().hasPermission("compressedblocks.compress")) {
                    System.out.print("HasNotPermission");
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }

            // Allow uncompressing only for the compressed items, and if the player has the permission
            } else {
                ImmutablePair recipeData = isShapedUncompressingCraft(items);
                if ((Boolean) recipeData.getKey() && event.getView().getPlayer().hasPermission("compressedblocks.uncompress")) {
                    System.out.print("IsUnCompressing");
                    int id = (int) recipeData.getValue();
                    event.getInventory().setResult(getResultItemStack(items[id]));
                } else {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                    System.out.print("IsNotUnCompressing");
                }
            }

        // Shapeless Recipes: uncompressing of netherrack, sand and soulsand.
        } else if (event.getRecipe() instanceof ShapelessRecipe) {
            System.out.print("ShapelessRecipe");

            // Allow uncompressing if the player has the permission
            ImmutablePair recipeData = isShapelessUncompressingCraft(items);
            if ((Boolean) recipeData.getKey()) {
                if (event.getView().getPlayer().hasPermission("compressedblocks.uncompress")) {
                    event.getInventory().setResult(getResultItemStack((ItemStack) recipeData.getValue()));
                } else {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }
        }
    }

    /**
     * Check if the shaped recipe is a registered uncompressing craft.
     * And return the location (id) of the compressed block on the crafting table.
     *
     * @param items The items' list on the crafting table of a shaped recipe.
     * @return Boolean true if the recipe is unCompression.
     *                 false if the recipe is not unCompression.
     *         Integer the location of the compressed block on the table.
     *                 null if Boolean is false.
     */
    private ImmutablePair<Boolean, Integer> isShapedUncompressingCraft(ItemStack[] items) {
        int itemCompressed = 0;
        int itemCompressedId = 0;

        // Loop each slot of the crafting table
        for (int i = 0; i < items.length; ++i) {
            if (isCompressedBlock(items[i])) {
                if (itemCompressed != 0) {
                    return new ImmutablePair<>(false, null);
                } else {
                    itemCompressed += 1;
                    itemCompressedId = i;
                }
            } else {
                return new ImmutablePair<>(false, null);
            }
        }
        return new ImmutablePair<>(true, itemCompressedId);
    }

    private ImmutablePair<Boolean, ItemStack> isShapelessUncompressingCraft(ItemStack[] items) {
        ItemStack itemCompressed = null;

        for (ItemStack item : items) {
            if (item != null && item.getType() != Material.AIR) {

                if (isCompressedBlock(item)) {
                    if (itemCompressed != null) {
                        return new ImmutablePair<>(false, null);
                    }
                    itemCompressed = item;
                } else {
                    return new ImmutablePair<>(false, null);
                }
            }
        }
        return new ImmutablePair<>(true, itemCompressed);
    }

    private ItemStack getResultItemStack(ItemStack itemStack) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile;

        try {
            profile = getProfile(skullMeta);
        } catch (ProfileNotFoundException e) {
            return new ItemStack(Material.AIR);
        }

        return EnumUUID.getByName(profile.getProperties().get("typeName").iterator().next().getValue()).getItemStack();
    }
}
