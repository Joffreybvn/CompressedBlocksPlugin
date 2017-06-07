package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class EventBase {

    private FileConfiguration config;

    public EventBase(Main plugin) {
        this.config = plugin.getConfig();
    }

    /**
     * Check if a given ItemStack is a compressed block.
     *
     * @param itemStack An item or block stack object.
     * @return true  If the ItemStack is a compressed block
     *         false If it's not a compressed block.
     */
    public Boolean isCompressedBlock(ItemStack itemStack) {
        return itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName().contains("Compressed") && itemStack.getItemMeta().hasLore() && (!itemStack.getItemMeta().getLore().isEmpty());
    }
}
