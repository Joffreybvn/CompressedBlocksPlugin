package io.github.joffrey4.compressedblocks.block;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.github.joffrey4.compressedblocks.Main;
import io.github.joffrey4.compressedblocks.util.Enum;
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
    private static String displayname;
    private static String name;
    private static String typeName;
    private static FileConfiguration config;

    public BlockCompressed(Material material, int metadata, String typeName, Main plugin) {
        BlockCompressed.config = plugin.getConfig();
        BlockCompressed.material = material;
        BlockCompressed.metadata = metadata;

        // Name of the compressed block item (ex: Compressed Oak Wood)
        BlockCompressed.displayname = setDisplayName(typeName);

        // Internal name of the compressed block (ex: oakwood)
        BlockCompressed.name = setName(typeName);

        // Name of the type of the block (ex: Oak Wood)
        BlockCompressed.typeName = typeName;
    }

    public String setName(String name) {
        return name.replaceAll("\\s+","").toLowerCase();
    }

    public String setDisplayName(String typeName) {
        return "Compressed " + typeName;
    }

    public ItemStack getItemStack() {

        // Get the skin image and initialize an itemStack (skull)
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);

        String skinURL = "http://textures.minecraft.net/texture/";
        if (config.getString("texture." + name).isEmpty()) {
            return skull;
        } else {
            skinURL += config.getString("texture." + name);
        }

        // Get the skull metadata and initialize a texture profile
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.fromString(Enum.getByName(name).getUUID()), null);
        profile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
        profile.getProperties().put("compBlocksName", new Property("compBlocksName", name));

        // Set the texture profile to the skull metadata
        Field profileField;
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
        return displayname;
    }

    public List<String> getLore() {
        List<String> lore;

        if ((!config.getBoolean("lore.usecommon")) && (!config.getStringList("lore.detailed." + name).isEmpty())) {
            lore = config.getStringList("lore.detailed." + name);
        } else {
            lore = config.getStringList("lore.common");
        }

        for (final ListIterator<String> i = lore.listIterator(); i.hasNext();) {
            final String line = i.next();

            if (line.contains("&type")) {
                i.set(line.replace("&type", typeName));
            }
        }
        return lore;
    }
}
