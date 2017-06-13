package io.github.joffrey4.compressedblocks.command;

import io.github.joffrey4.compressedblocks.util.Enum;
import io.github.joffrey4.compressedblocks.util.EnumSwitch;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGive {

    private FileConfiguration config;
    private CommandSender commandSender;
    private String[] strings;

    public CommandGive (FileConfiguration config, CommandSender commandSender, String[] strings) {
        this.config = config;
        this.commandSender = commandSender;
        this.strings = strings;
    }

    public boolean executeCommand() {

        // Check if it's a player that send a command with 2 or 3 arguments
        if (strings.length == 3 || strings.length == 4) {

            // Check if the targeted player exists, and if the required block exists
            Player target = Bukkit.getServer().getPlayer(strings[1]);
            if (target != null && Enum.getByName(strings[2]) != null) {

                // Check the if the third parameter exists as positive int
                int amount = 1;
                if (strings.length == 4) {
                    try {
                        amount = Integer.parseInt(strings[3]);
                        if (amount <= 0) {
                            amount = 1;
                        } else if (amount > 2304) {
                            amount = 2304;
                        }
                    } catch (NumberFormatException ignored) {}
                }

                // Give the block and send notifications
                Enum compBlock = Enum.getByName(strings[2]);
                ItemStack item = new EnumSwitch(compBlock).getAmountCompressedBlock(amount);
                target.getInventory().addItem(item);

                // Send messages to sender and receiver
                String message = ChatColor.translateAlternateColorCodes('&', "%s " + amount + "x" + item.getItemMeta().getDisplayName() + "&r %s &7&o" + target.getDisplayName());
                if (commandSender instanceof Player && commandSender == target) {
                    target.sendMessage(String.format(message, "Receive", "from"));
                } else {
                    commandSender.sendMessage(String.format(message, "Give", "to"));
                    target.sendMessage(String.format(message, "Receive", "from"));
                }
                return true;
            }
            commandSender.sendMessage("Invalid player or block name.");
        }
        commandSender.sendMessage("Missing parameters:");
        return false;
    }

}
