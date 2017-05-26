package io.github.joffrey4.compressedblocks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigGenerator extends JavaPlugin {

    public static void init(FileConfiguration config) {

        /*************************************************************************************************
         * Compressed WOODS & PLANKS config                                                              *
         *************************************************************************************************/

        // Wood config
        config.addDefault("Wood.Compressing", true);
        config.addDefault("Wood.Uncompressing", true);

        // Planks config
        config.addDefault("Planks.Compressing", true);
        config.addDefault("Planks.Uncompressing", true);

        /*************************************************************************************************
         * Compressed FALLING BLOCKS config                                                              *
         *************************************************************************************************/

        // Gravel config
        config.addDefault("Gravel.Compressing", true);
        config.addDefault("Gravel.Uncompressing", true);

        // Sand config
        config.addDefault("Sand.Compressing", true);
        config.addDefault("Sand.Uncompressing", true);

        /*************************************************************************************************
         * Compressed STONE config                                                                       *
         *************************************************************************************************/

        // Stone config
        config.addDefault("Stone.Compressing", true);
        config.addDefault("Stone.Uncompressing", true);

        // Granite config
        config.addDefault("Granite.Compressing", true);
        config.addDefault("Granite.Uncompressing", true);

        // Diorite config
        config.addDefault("Diorite.Compressing", true);
        config.addDefault("Diorite.Uncompressing", true);

        // Andesite config
        config.addDefault("Andesite.Compressing", true);
        config.addDefault("Andesite.Uncompressing", true);

        /*************************************************************************************************
         * Compressed OTHER BLOCKS config                                                                *
         *************************************************************************************************/

        // Dirt config
        config.addDefault("Dirt.Compressing", true);
        config.addDefault("Dirt.Uncompressing", true);

        // Cobblestone config
        config.addDefault("Cobblestone.Compressing", true);
        config.addDefault("Cobblestone.Uncompressing", true);

        // SoulSand config
        config.addDefault("SoulSand.Compressing", true);
        config.addDefault("SoulSand.Uncompressing", true);

        // Netherrack config
        config.addDefault("Netherrack.Compressing", true);
        config.addDefault("Netherrack.Uncompressing", true);

        config.addDefault("Lang.PlacingImpossible", "You can not place a compressed block in the world !");
        config.addDefault("Lang.FurnaceImpossible", "You can not use a compressed block in a furnace !");

        config.options().copyDefaults(true);

    }
}
