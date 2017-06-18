package io.github.joffrey4.compressedblocks.nms;

import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.api.NMS;

import java.lang.reflect.InvocationTargetException;

public class RegisterNMS {

    private static NMS nmsHandler;

    public static NMS init(Main plugin) {

        // Get package version
        String packageName = plugin.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);

        System.out.print(version);

        try {
            final Class<?> clazz = Class.forName("io.github.joffrey4.compressedblocks.nms." + version + ".NMSHandler");

            // Check if we have a NMSHandler class at that location
            if (NMS.class.isAssignableFrom(clazz)) {
                nmsHandler = (NMS) clazz.getConstructor().newInstance();
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            plugin.getLogger().severe("Could not find support for this CraftBukkit/Spigot version.");
            return null;
        }
        plugin.getLogger().info("Loading support for " + version);
        return nmsHandler;
    }
}
