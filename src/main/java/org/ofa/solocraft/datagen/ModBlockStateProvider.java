package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SolocraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MANA_CRYSTAL_BLOCK);
        blockWithItem(ModBlocks.MANA_DETECTION_ORB);

        //supposed to be a cross shape
        blockWithItem(ModBlocks.SMALL_MANA_CRYSTAL_BUD);
        blockWithItem(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD);
        blockWithItem(ModBlocks.LARGE_MANA_CRYSTAL_BUD);

        stairsBlock(((StairBlock) ModBlocks.MANA_CRYSTAL_STAIRS.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.MANA_CRYSTAL_SLAB.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.MANA_CRYSTAL_BUTTON.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.MANA_CRYSTAL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.MANA_CRYSTAL_FENCE.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.MANA_CRYSTAL_FENCE_GATE.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.MANA_CRYSTAL_WALL.get()), blockTexture(ModBlocks.MANA_CRYSTAL_BLOCK.get()));

        doorBlockWithRenderType(
                ((DoorBlock) ModBlocks.MANA_CRYSTAL_DOOR.get()),
                modLoc("block/mana_crystal_door_bottom"),
                modLoc("block/mana_crystal_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(
                ((TrapDoorBlock) ModBlocks.MANA_CRYSTAL_TRAPDOOR.get()),
                modLoc("block/mana_crystal_trapdoor"),
                true,
                "cutout");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
