package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class EventOnFurnace implements Listener {

    private FileConfiguration config;

    public EventOnFurnace(Main plugin) {
        this.config = plugin.getConfig();
    }

    //Avoid using a compressed block in a furnace
    @EventHandler
    public void onFurnace(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getType() == InventoryType.FURNACE) {
            if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName().contains("Compressed")) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage(ChatColor.RED + config.getString("Lang.FurnaceImpossible"));
            }
        }
    }

}
