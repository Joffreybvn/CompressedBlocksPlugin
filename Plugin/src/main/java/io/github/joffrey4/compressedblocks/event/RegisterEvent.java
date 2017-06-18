package io.github.joffrey4.compressedblocks.event;

import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.api.NMS;
import org.bukkit.event.Listener;

public class RegisterEvent implements Listener {

    public static void init(Main plugin, NMS nmsHandler) {

        // Register the Events
        plugin.getServer().getPluginManager().registerEvents(new EventOnCraft(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EventOnBreak(plugin, nmsHandler), plugin);
    }

}
