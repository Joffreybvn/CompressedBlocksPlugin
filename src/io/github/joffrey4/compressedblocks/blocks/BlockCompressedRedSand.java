package io.github.joffrey4.compressedblocks.blocks;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;

public class BlockCompressedRedSand extends BlockCompressed {

    public BlockCompressedRedSand(Material material, int metadata, String name, Main plugin) {
        super(material, metadata, name, plugin);
    }

    @Override
    public String setTypeName() {
        return "RedSand";
    }
}
