package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventOnPlace extends EventBase implements Listener {

    private FileConfiguration config;

    public EventOnPlace(Main plugin) {
        super(plugin);
        this.config = plugin.getConfig();
    }

    // Avoid placing a compressed block on the ground
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (isCompressedBlock(event.getItemInHand())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + config.getString("Lang.PlacingImpossible"));
        }
    }
}
