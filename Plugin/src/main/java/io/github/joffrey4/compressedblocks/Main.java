package io.github.joffrey4.compressedblocks;

import io.github.joffrey4.compressedblocks.api.NMS;
import io.github.joffrey4.compressedblocks.block.RegisterBlocks;
import io.github.joffrey4.compressedblocks.command.RegisterCommand;
import io.github.joffrey4.compressedblocks.event.RegisterEvent;
import io.github.joffrey4.compressedblocks.nms.RegisterNMS;
import io.github.joffrey4.compressedblocks.recipe.RegisterRecipes;
import io.github.joffrey4.compressedblocks.util.VersionChecker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileConfiguration config = getConfig();
    private static NMS nmsHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Check is a new version exists
        VersionChecker.init(this);

        // Initialize NMS - Stop the plugin if it's an incompatible NMS version.
        nmsHandler = RegisterNMS.init(this);
        if (nmsHandler == null) {
            this.getLogger().info("Disabling CompressedBlocks");
            this.setEnabled(false);

        } else {

            // Initialize blocks & recipes
            RegisterBlocks.init(this);
            RegisterRecipes.init(config);

            // Initialize plugin mechanics
            RegisterEvent.init(this, nmsHandler);

            // Initialize commands
            RegisterCommand.init(this);

        }
    }


    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

}
