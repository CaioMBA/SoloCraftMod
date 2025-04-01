package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
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
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
