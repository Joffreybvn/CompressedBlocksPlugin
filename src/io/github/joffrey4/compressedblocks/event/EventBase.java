package io.github.joffrey4.compressedblocks.event;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import io.github.joffrey4.compressedblocks.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;

public class EventBase {

    private FileConfiguration config;

    public EventBase(Main plugin) {
        this.config = plugin.getConfig();
    }

    /**
     * Check if a given ItemStack is a compressed block.
     *
     * @param itemStack An item or block stack object.
     * @return true  If the ItemStack is a compressed block
     *         false If it's not a compressed block.
     */
    public Boolean isCompressedBlock(ItemStack itemStack) {
        SkullMeta skullMeta;

        // Check if the itemStack is a Skull item of player
        if (itemStack != null && itemStack.getType() == Material.SKULL_ITEM && itemStack.getDurability() == 3) {

            // Check if the skull has metadata and a displayName
            if (itemStack.hasItemMeta()) {

                // Retrieve the Gameprofile from metadata
                skullMeta = (SkullMeta) itemStack.getItemMeta();
                GameProfile profile;

                try {
                    profile = getProfile(skullMeta);
                } catch (ProfileNotFoundException e) {
                    return false;
                }

                return profile.getProperties().containsKey("compBlocksName");
            }
        }
        return false;
    }

    /**
     * Return the GameProfile of a given SkullMeta
     *
     * @param skullMeta The skullMeta of an skull ItemStack.
     * @return profile The GameProfile of the skullMeta.
     */
    public GameProfile getProfile(SkullMeta skullMeta) {
        Field profileField;
        GameProfile profile;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profile = (GameProfile) profileField.get(skullMeta);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new ProfileNotFoundException("Invalid SkullMeta: " + skullMeta);
        }
        return profile;
    }
}
