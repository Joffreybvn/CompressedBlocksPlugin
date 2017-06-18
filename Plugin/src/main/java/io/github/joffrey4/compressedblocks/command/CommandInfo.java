package io.github.joffrey4.compressedblocks.command;


import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo {

    private Main plugin;
    private CommandSender commandSender;

    public CommandInfo(Main plugin, CommandSender commandSender) {
        this.plugin = plugin;
        this.commandSender = commandSender;
    }

    public boolean executeCommand() {
        if (commandSender instanceof Player) {

            // Print the header
            commandSender.sendMessage("");
            commandSender.sendMessage("#----------[ " + ChatColor.GOLD + "Compressed Blocks Plugin" + ChatColor.RESET + " ]----------#");
            commandSender.sendMessage(plugin.getDescription().getDescription());

            // Print the version
            commandSender.sendMessage(ChatColor.GOLD + "Version:" + ChatColor.RESET + " " + plugin.getDescription().getVersion());

            // Print the author
            StringBuilder authors = new StringBuilder();
            for (String author : plugin.getDescription().getAuthors()) {
                authors.append(author);
            }
            commandSender.sendMessage(ChatColor.GOLD + "Author:" + ChatColor.RESET + " " + authors);

            // Print the website:
            commandSender.sendMessage(ChatColor.GOLD + "Website:" + ChatColor.RESET + plugin.getDescription().getWebsite());
            return true;
        }
        return false;
    }
}
