package io.github.joffrey4.compressedblocks.block;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;

public class BlockCompressedWood extends BlockCompressed {

    public BlockCompressedWood(Material material, int metadata, String name, Main plugin) {
        super(material, metadata, name, plugin);
    }

    @Override
    public String setTypeName() {
        return "Wood";
    }

}
