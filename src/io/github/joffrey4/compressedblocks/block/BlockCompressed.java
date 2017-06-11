package io.github.joffrey4.compressedblocks.block;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.EnumUUID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class BlockCompressed {

    private static Material material;
    private static int metadata;
    private static String name;
    private static String typeName;
    private static FileConfiguration config;

    public BlockCompressed(Material material, int metadata, String name, Main plugin) {
        BlockCompressed.material = material;
        BlockCompressed.metadata = metadata;
        BlockCompressed.name = name;
        BlockCompressed.typeName = setTypeName(name);
        BlockCompressed.config = plugin.getConfig();
    }

    public String setTypeName(String name) {
        return name.replaceAll("\\s+","");
    }

    public ItemStack getItemStack() {

        // Get the skin image and initialize an itemStack (skull)
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);

        String skinURL = "http://textures.minecraft.net/texture/";
        if (config.getString(typeName + ".Texture").isEmpty()) {
            return skull;
        } else {
            skinURL += config.getString(typeName + ".Texture");
        }

        // Get the skull metadata and initialize a texture profile
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString(EnumUUID.getByName(typeName).getValue()), null);
        profile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
        profile.getProperties().put("typeName", new Property("typeName", typeName));

        // Set the texture profile to the skull metadata
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Save the metadata on the skull and return it
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public String getDisplayName() {
        return ChatColor.GOLD + "Compressed " + name;
    }

    public List<String> getLore() {
        List<String> lore;

        if ((!config.getBoolean("UseCommonLore")) && (!config.getStringList("DetailedLore." + typeName).isEmpty())) {
            lore = config.getStringList("DetailedLore." + typeName);
        } else {
            lore = config.getStringList("CommonLore");
        }

        for (final ListIterator<String> i = lore.listIterator(); i.hasNext();) {
            final String line = i.next();

            if (line.contains("&type")) {
                i.set(line.replace("&type", name));
            }
        }
        return lore;
    }
}
