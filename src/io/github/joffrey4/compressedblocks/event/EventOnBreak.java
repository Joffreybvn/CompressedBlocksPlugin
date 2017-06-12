package io.github.joffrey4.compressedblocks.event;

import com.mojang.authlib.GameProfile;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.EnumBlockType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_11_R1.block.CraftSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EventOnBreak extends EventBase implements Listener {

    private FileConfiguration config;

    public EventOnBreak(Main plugin) {
        super(plugin);
        this.config = plugin.getConfig();
    }

    /**
     * Placed compressed blocks drops a stack of 9 uncompressed blocks when they are destroyed.
     * @param event BlockBreakEvent
     */
    @EventHandler
    public void onBreak (BlockBreakEvent event) {
        BlockState block = event.getBlock().getState();

        // Check if the block is an instance of CraftSkull
        if (block instanceof CraftSkull) {
            CraftSkull skull = (CraftSkull) block;

            // Check if the skull is a PLAYER skull (type 3)
            if (skull.getSkullType().equals(SkullType.PLAYER)) {
                GameProfile skullProfile = skull.getTileEntity().getGameProfile();

                // Check if the skull is a compressed block
                if (skullProfile != null && skullProfile.getProperties().containsKey("typeName")) {

                    // Reset the block to AIR to avoid it to drop anything
                    event.getBlock().setType(Material.AIR);

                    // Drop a stack of the uncompressed blocks
                    Location location = event.getBlock().getLocation().add(0.5F, 0.5F, 0.5F);
                    ItemStack dropStack = EnumBlockType.getByName(skullProfile.getProperties().get("typeName").iterator().next().getValue()).getRandomAmountItemStack(config, 5, 8);

                    event.getBlock().getWorld().dropItemNaturally(location, dropStack);
                }
            }
        }

    }
}
