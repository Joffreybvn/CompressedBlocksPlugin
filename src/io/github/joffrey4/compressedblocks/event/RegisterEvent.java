package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.event.Listener;

public class RegisterEvent implements Listener {

    public static void init(Main plugin) {

        // Register the Events
        plugin.getServer().getPluginManager().registerEvents(new EventOnCraft(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EventOnBreak(plugin), plugin);
    }

}
