package io.github.joffrey4.compressedblocks.util;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public enum Enum {

    // WOODS
    OAK_WOOD("oakwood", "5f50baf8-a634-4b8d-936c-98ccb91aef1a", new ItemStack(Material.LOG, 9, (short) 0)),
    SPRUCE_WOOD("sprucewood", "efe30e15-c655-42fc-bb68-0fa264a5079e", new ItemStack(Material.LOG, 9, (short) 1)),
    BIRCH_WOOD("birchwood", "37250eec-661c-4d15-9596-75a2ee6f3024", new ItemStack(Material.LOG, 9, (short) 2)),
    JUNGLE_WOOD("junglewood", "cb2bf8af-dafe-4bbb-938e-e3a587b1ae82", new ItemStack(Material.LOG, 9, (short) 3)),
    ACACIA_WOOD("acaciawood", "de2fa28c-64f4-4221-aa9c-4e956bb669cd", new ItemStack(Material.LOG_2, 9, (short) 0)),
    DARK_OAK_WOOD("darkoakwood", "a2866167-cb4e-4d12-8683-ce8e680ad53a", new ItemStack(Material.LOG_2, 9, (short) 1)),

    // PLANKS
    OAK_PLANK("oakplank", "3946a2e4-76e6-431c-bac3-343459d3b98e", new ItemStack(Material.WOOD, 9, (short) 0)),
    SPRUCE_PLANK("spruceplank", "5899b4d0-adc3-4b70-a9fb-66c037e985fc", new ItemStack(Material.WOOD, 9, (short) 1)),
    BIRCH_PLANK("birchplank", "f58ed83d-ac0e-4e1e-961a-98f0bb6846b7", new ItemStack(Material.WOOD, 9, (short) 2)),
    JUNGLE_PLANK("jungleplank", "c4e64850-70c4-4f10-abe5-21b92fbea209", new ItemStack(Material.WOOD, 9, (short) 3)),
    ACACIA_PLANK("acaciaplank", "c80051e9-a344-4d9e-a38f-f9dccf250a7b", new ItemStack(Material.WOOD, 9, (short) 4)),
    DARK_OAK_PLANK("darkoakplank", "2e5f7578-5806-479a-9233-39610e7111d0", new ItemStack(Material.WOOD, 9, (short) 5)),

    // FALLING Blocks
    GRAVEL("gravel", "9ed56cd9-be54-48df-ba43-c766d63846a9", new ItemStack(Material.GRAVEL, 9, (short) 0)),
    SAND("sand", "44946429-1ff1-4588-ba67-259c9200cb84", new ItemStack(Material.SAND, 9, (short) 0)),
    RED_SAND("redsand", "684412b1-459f-45eb-945d-0f17e52c3f3f", new ItemStack(Material.SAND, 9, (short) 1)),

    // STONE Blocks
    STONE("stone", "da6379d5-82b4-42e0-8e57-6f72bd5cb6d1", new ItemStack(Material.STONE, 9, (short) 0)),
    GRANITE("granite", "f511269e-e6fc-419a-9c84-e41441c42722", new ItemStack(Material.STONE, 9, (short) 1)),
    DIORITE("diorite", "bfecde1f-ef42-4a47-8712-85419c97edbd", new ItemStack(Material.STONE, 9, (short) 3)),
    ANDESITE("andesite", "d775e83e-5862-48f9-8692-47810609ec76", new ItemStack(Material.STONE, 9, (short) 5)),

    // OTHER Blocks
    DIRT("dirt", "51283055-2380-4aa1-a4a1-ea4b944fb34b", new ItemStack(Material.DIRT, 9, (short) 0)),
    COBBLESTONE("cobblestone", "749579ca-61f3-4de1-b855-00636448a06d", new ItemStack(Material.COBBLESTONE, 9, (short) 0)),
    SOUL_SAND("soulsand", "9530ebab-c532-488d-85e6-f5f0afe2434c", new ItemStack(Material.SOUL_SAND, 9, (short) 0)),
    NETHERRACK("netherrack", "54f4ac29-c030-4c62-ac0c-584af860502b", new ItemStack(Material.NETHERRACK, 9, (short) 0));

    private final String name;
    private final String uuid;
    private final ItemStack uncompBlocks;

    Enum(final String name, final String uuid, final ItemStack uncompBlocks) {
        this.name = name;
        this.uuid = uuid;
        this.uncompBlocks = uncompBlocks;
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return uuid;
    }

    public ItemStack getUncompBlocks(FileConfiguration config, Boolean bypass) {
        if (bypass || config.getBoolean(name + ".uncompressing")) {
            return uncompBlocks;
        } else {
            return new ItemStack(Material.AIR);
        }
    }

    public ItemStack getUncompBlocks(FileConfiguration config) {
        return getUncompBlocks(config, false);
    }

    public ItemStack getRandUncompBlocks(FileConfiguration config, int min, int max) {
        ItemStack itemStack = getUncompBlocks(config, true).clone();

        Random rand = new Random();
        itemStack.setAmount(rand.nextInt(max - min + 1) + min);
        return itemStack;
    }

    public static Enum getByName(final String name) {
        for (final Enum nvp : values()) {
            if (nvp.getName().equals(name)) {
                return nvp;
            }
        }
        return null;
    }
}
