package io.github.joffrey4.compressedblocks.command;

import io.github.joffrey4.compressedblocks.Main;

public class RegisterCommand {

    public static void init(Main plugin) {
        plugin.getCommand("compressedblocks").setExecutor(new CommandBase(plugin));
    }
}
