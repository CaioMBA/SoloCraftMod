package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.util.enums.BlockModelType;

import javax.annotation.Nullable;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SolocraftMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_BLOCK, BlockModelType.CUBE_ALL, null);
        registerBlockWithItemModel(ModBlocks.MANA_DETECTION_ORB, BlockModelType.CUBE_ALL, null);

        registerBlockWithItemModel(ModBlocks.SMALL_MANA_CRYSTAL_BUD, BlockModelType.CROSS, null);
        registerBlockWithItemModel(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD, BlockModelType.CROSS, null);
        registerBlockWithItemModel(ModBlocks.LARGE_MANA_CRYSTAL_BUD, BlockModelType.CROSS, null);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_CLUSTER, BlockModelType.CROSS, null);

        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_STAIRS, BlockModelType.STAIRS, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_SLAB, BlockModelType.SLAB, ModBlocks.MANA_CRYSTAL_BLOCK);

        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_BUTTON, BlockModelType.BUTTON, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_PRESSURE_PLATE, BlockModelType.PRESSURE_PLATE, ModBlocks.MANA_CRYSTAL_BLOCK);

        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_FENCE, BlockModelType.FENCE, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_FENCE_GATE, BlockModelType.FENCE_GATE, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_WALL, BlockModelType.WALL, ModBlocks.MANA_CRYSTAL_BLOCK);


        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_DOOR, BlockModelType.DOOR, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerBlockWithItemModel(ModBlocks.MANA_CRYSTAL_TRAPDOOR, BlockModelType.TRAPDOOR, ModBlocks.MANA_CRYSTAL_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private String getPath(RegistryObject<?> ro) {
        return ro.getId().getPath();
    }


    private void registerBlockWithItemModel(RegistryObject<Block> block,
                                            BlockModelType type,
                                            @Nullable RegistryObject<Block> baseBlock) {
        Block b = block.get();
        String name = getPath(block);

        switch (type) {
            case CUBE_ALL -> simpleBlockWithItem(b, cubeAll(b));
            case CROSS -> simpleBlockWithItem(b,
                    models().cross(name, blockTexture(b)).renderType("cutout"));

            case STAIRS -> stairsBlock((StairBlock) b, blockTexture(baseBlock.get()));
            case SLAB -> slabBlock((SlabBlock) b, blockTexture(baseBlock.get()), blockTexture(baseBlock.get()));

            case FENCE -> fenceBlock((FenceBlock) b, blockTexture(baseBlock.get()));
            case FENCE_GATE -> fenceGateBlock((FenceGateBlock) b, blockTexture(baseBlock.get()));
            case WALL -> wallBlock((WallBlock) b, blockTexture(baseBlock.get()));

            case BUTTON -> buttonBlock((ButtonBlock) b, blockTexture(baseBlock.get()));
            case PRESSURE_PLATE -> pressurePlateBlock((PressurePlateBlock) b, blockTexture(baseBlock.get()));

            case DOOR -> doorBlockWithRenderType(
                    (DoorBlock) b,
                    modLoc("block/" + name + "_bottom"),
                    modLoc("block/" + name + "_top"),
                    "cutout");

            case TRAPDOOR -> trapdoorBlockWithRenderType(
                    (TrapDoorBlock) b,
                    modLoc("block/" + name),
                    true,
                    "cutout");

            case CROP -> simpleBlockWithItem(b, models().crop(name, blockTexture(b)).renderType("cutout"));
        }
    }



}
