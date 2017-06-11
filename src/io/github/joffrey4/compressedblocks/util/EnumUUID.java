package io.github.joffrey4.compressedblocks.util;

public enum EnumUUID {

    // WOODS
    OAK_WOOD("OakWood", "5f50baf8-a634-4b8d-936c-98ccb91aef1a"),
    SPRUCE_WOOD("SpruceWood", "efe30e15-c655-42fc-bb68-0fa264a5079e"),
    BIRCH_WOOD("BirchWood", "37250eec-661c-4d15-9596-75a2ee6f3024"),
    JUNGLE_WOOD("JungleWood", "cb2bf8af-dafe-4bbb-938e-e3a587b1ae82"),
    ACACIA_WOOD("AcaciaWood", "de2fa28c-64f4-4221-aa9c-4e956bb669cd"),
    DARK_OAK_WOOD("DarkOakWood", "a2866167-cb4e-4d12-8683-ce8e680ad53a"),

    // PLANKS
    OAK_PLANK("OakPlank", "3946a2e4-76e6-431c-bac3-343459d3b98e"),
    SPRUCE_PLANK("SprucePlank", "5899b4d0-adc3-4b70-a9fb-66c037e985fc"),
    BIRCH_PLANK("BirchPlank", "f58ed83d-ac0e-4e1e-961a-98f0bb6846b7"),
    JUNGLE_PLANK("JunglePlank", "c4e64850-70c4-4f10-abe5-21b92fbea209"),
    ACACIA_PLANK("AcaciaPlank", "c80051e9-a344-4d9e-a38f-f9dccf250a7b"),
    DARK_OAK_PLANK("DarkOakPlank", "2e5f7578-5806-479a-9233-39610e7111d0"),

    // FALLING Blocks
    GRAVEL("Gravel", "9ed56cd9-be54-48df-ba43-c766d63846a9"),
    SAND("Sand", "44946429-1ff1-4588-ba67-259c9200cb84"),
    RED_SAND("RedSand", "684412b1-459f-45eb-945d-0f17e52c3f3f"),

    // STONE Blocks
    STONE("Stone", "da6379d5-82b4-42e0-8e57-6f72bd5cb6d1"),
    GRANITE("Granite", "f511269e-e6fc-419a-9c84-e41441c42722"),
    DIORITE("Diorite", "bfecde1f-ef42-4a47-8712-85419c97edbd"),
    ANDESITE("Andesite", "d775e83e-5862-48f9-8692-47810609ec76"),

    // OTHER Blocks
    DIRT("Dirt", "51283055-2380-4aa1-a4a1-ea4b944fb34b"),
    COBBLESTONE("Cobblestone", "749579ca-61f3-4de1-b855-00636448a06d"),
    SOUL_SAND("SoulSand", "9530ebab-c532-488d-85e6-f5f0afe2434c"),
    NETHERRACK("Netherrack", "54f4ac29-c030-4c62-ac0c-584af860502b");

    private final String name;
    private final String value;

    EnumUUID(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static EnumUUID getByName(final String name) {
        for (final EnumUUID nvp : values()) {
            if (nvp.getName().equals(name)) {
                return nvp;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
