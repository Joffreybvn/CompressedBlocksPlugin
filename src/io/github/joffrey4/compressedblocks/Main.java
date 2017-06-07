package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.block.RegisterBlocks;
import io.github.joffrey4.compressedblocks.event.RegisterEvent;
import io.github.joffrey4.compressedblocks.recipe.RegisterRecipes;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Initialize blocks & recipes
        RegisterBlocks.init(this);
        RegisterRecipes.init(config);

        // Initialize plugin mechanics
        RegisterEvent.init(this);
    }


    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

}
