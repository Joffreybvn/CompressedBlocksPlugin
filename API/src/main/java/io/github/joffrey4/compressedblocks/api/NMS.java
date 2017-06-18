package io.github.joffrey4.compressedblocks.api;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;

public interface NMS {

    // Event OnBreak
    public ImmutableTriple<Boolean, Location, String> eventOnBreak (BlockBreakEvent event);

}
