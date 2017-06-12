package io.github.joffrey4.compressedblocks.event;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.EnumBlockType;
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

            // Allow compressing only if the player has the permission
            if (isCompressedBlock(event.getRecipe().getResult())) {

                if (!event.getView().getPlayer().hasPermission("compressedblocks.compress")) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
            }

        // Shapeless Recipes: uncompressing of netherrack, sand and soulsand.
        } else if (event.getRecipe() instanceof ShapelessRecipe) {

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
     * Check if the shapeless recipe is a registered uncompressing craft.
     * If true, return also the ItemStack of the compressed block.
     *
     * @param items The items' list on the crafting table of a shaped recipe.
     * @return Boolean true if the recipe is unCompression.
     *                 false if the recipe is not unCompression.
     *         ItemStack The compressed block.
     *                   null if Boolean is false.
     */
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

    /**
     * Return the ItemStack resulting of the un-compression of a compressed block.
     *
     * @param itemStack The compressed block ItemStack.
     * @return ItemStack The stack of uncompressed blocks resulting of the un-compression.
     */
    private ItemStack getResultItemStack(ItemStack itemStack) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile;

        try {
            profile = getProfile(skullMeta);
        } catch (ProfileNotFoundException e) {
            return new ItemStack(Material.AIR);
        }

        return EnumBlockType.getByName(profile.getProperties().get("typeName").iterator().next().getValue()).getItemStack(config);
    }
}
