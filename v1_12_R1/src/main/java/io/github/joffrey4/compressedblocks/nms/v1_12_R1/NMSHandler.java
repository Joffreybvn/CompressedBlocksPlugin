package io.github.joffrey4.compressedblocks.nms.v1_12_R1;

import com.mojang.authlib.GameProfile;
import io.github.joffrey4.compressedblocks.api.NMS;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.bukkit.Location;
import org.bukkit.SkullType;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_12_R1.block.CraftSkull;
import org.bukkit.event.block.BlockBreakEvent;

public class NMSHandler implements NMS {

    @Override
    public ImmutableTriple<Boolean, Location, String> eventOnBreak (BlockBreakEvent event) {
        BlockState block = event.getBlock().getState();

        // Check if the block is an instance of CraftSkull
        if (block instanceof CraftSkull) {
            CraftSkull skull = (CraftSkull) block;

            // Check if the skull is a PLAYER skull (type 3)
            if (skull.getSkullType().equals(SkullType.PLAYER)) {
                GameProfile skullProfile = skull.getTileEntity().getGameProfile();

                // Check if the skull is a compressed block
                if (skullProfile != null && skullProfile.getProperties().containsKey("compBlocksName")) {

                    // Drop a stack of the uncompressed blocks
                    Location location = event.getBlock().getLocation().add(0.5F, 0.5F, 0.5F);
                    String dropTypeName = skullProfile.getProperties().get("compBlocksName").iterator().next().getValue();

                    return new ImmutableTriple<>(true, location, dropTypeName);
                }
            }
        }
        return new ImmutableTriple<>(false, null, null);
    }
}
