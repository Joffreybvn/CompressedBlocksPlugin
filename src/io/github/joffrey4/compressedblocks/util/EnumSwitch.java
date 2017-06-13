package io.github.joffrey4.compressedblocks.util;

import io.github.joffrey4.compressedblocks.block.RegisterBlocks;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EnumSwitch {

    Enum compressedBlock;

    public EnumSwitch(Enum compressedBlock) {
        this.compressedBlock = compressedBlock;
    }

    /**
     * Return the compressed block linked to Enum element.
     * Or AIR if the Enum doesn't exist.
     *
     * @return ItemStack A compressed block ItemStack.
     */
    public ItemStack getCompressedBlock() {

        switch (compressedBlock){

            // WOODS
            case OAK_WOOD:
                return RegisterBlocks.compressedOak;

            case SPRUCE_WOOD:
                return RegisterBlocks.compressedSpruce;

            case BIRCH_WOOD:
                return RegisterBlocks.compressedBirch;

            case JUNGLE_WOOD:
                return RegisterBlocks.compressedJungle;

            case ACACIA_WOOD:
                return RegisterBlocks.compressedAcacia;

            case DARK_OAK_WOOD:
                return RegisterBlocks.compressedDarkOak;

            // PLANKS
            case OAK_PLANK:
                return RegisterBlocks.compressedOakPlank;

            case SPRUCE_PLANK:
                return RegisterBlocks.compressedSprucePlank;

            case BIRCH_PLANK:
                return RegisterBlocks.compressedBirchPlank;

            case JUNGLE_PLANK:
                return RegisterBlocks.compressedJunglePlank;

            case ACACIA_PLANK:
                return RegisterBlocks.compressedAcaciaPlank;

            case DARK_OAK_PLANK:
                return RegisterBlocks.compressedDarkOakPlank;

            // FALLING blocks
            case GRAVEL:
                return RegisterBlocks.compressedGravel;

            case SAND:
                return RegisterBlocks.compressedSand;

            case RED_SAND:
                return RegisterBlocks.compressedRedSand;

            // STONE blocks
            case STONE:
                return RegisterBlocks.compressedStone;

            case GRANITE:
                return RegisterBlocks.compressedGranite;

            case DIORITE:
                return RegisterBlocks.compressedDiorite;

            case ANDESITE:
                return RegisterBlocks.compressedAndesite;

            // OTHER blocks
            case DIRT:
                return RegisterBlocks.compressedDirt;

            case COBBLESTONE:
                return RegisterBlocks.compressedCobblestone;

            case SOUL_SAND:
                return RegisterBlocks.compressedSoulSand;

            case NETHERRACK:
                return RegisterBlocks.compressedNetherrack;

            default:
                return new ItemStack(Material.AIR);
        }
    }

    public ItemStack getAmountCompressedBlock(int amount) {
        ItemStack itemStack = getCompressedBlock().clone();
        itemStack.setAmount(amount);
        return itemStack;
    }
}
