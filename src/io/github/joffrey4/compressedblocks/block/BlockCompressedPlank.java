package io.github.joffrey4.compressedblocks.block;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;

public class BlockCompressedPlank extends BlockCompressed {

    public BlockCompressedPlank(Material material, int metadata, String name, Main plugin) {
        super(material, metadata, name, plugin);
    }

    @Override
    public String setTypeName() {
        return "Plank";
    }
}
