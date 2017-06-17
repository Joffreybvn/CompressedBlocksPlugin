package io.github.joffrey4.compressedblocks.event;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.Enum;
import io.github.joffrey4.compressedblocks.util.EnumSwitch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
            if (event.getView().getPlayer() instanceof Player && isCompressedBlock(event.getRecipe().getResult())) {
                event.getInventory().setResult(getCompressingResult(event.getRecipe().getResult(), (Player) event.getView().getPlayer()));
            }

        // Shapeless Recipes: uncompressing of netherrack, sand and soulsand.
        } else if (event.getRecipe() instanceof ShapelessRecipe) {

            // Allow uncompressing if the player has the permission
            ImmutablePair recipeData = isShapelessUncompressingCraft(items);

            if ((Boolean) recipeData.getKey() && event.getView().getPlayer() instanceof Player) {
                event.getInventory().setResult(getUncompressingResult((ItemStack) recipeData.getValue(), (Player) event.getView().getPlayer()));
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
    private ItemStack getUncompressingResult(ItemStack itemStack, Player player) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile;

        try {
            profile = getProfile(skullMeta);
        } catch (ProfileNotFoundException e) {
            return new ItemStack(Material.AIR);
        }

        return Enum.getByName(profile.getProperties().get("compBlocksName").iterator().next().getValue()).getUncompBlocks(player);
    }

    /**
     * Return the ItemStack resulting of the compression of some blocks.
     *
     * @param itemStack The compressed block ItemStack.
     * @return ItemStack The stack of compressed blocks resulting of the compression.
     */
    private ItemStack getCompressingResult(ItemStack itemStack, Player player) {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile;

        try {
            profile = getProfile(skullMeta);
        } catch (ProfileNotFoundException e) {
            return new ItemStack(Material.AIR);
        }

        Enum compBlock = Enum.getByName(profile.getProperties().get("compBlocksName").iterator().next().getValue());
        return new EnumSwitch(compBlock).getCraftedCompressedBlocks(player);
    }
}
