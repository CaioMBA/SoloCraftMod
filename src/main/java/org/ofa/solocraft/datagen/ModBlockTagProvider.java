package org.ofa.solocraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SolocraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.MANA_INFUSED)
                .add(
                        ModBlocks.MANA_CRYSTAL_BLOCK.get(),
                        ModBlocks.MANA_DETECTION_ORB.get(),
                        ModBlocks.SMALL_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.LARGE_MANA_CRYSTAL_BUD.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.MANA_CRYSTAL_BLOCK.get(),
                        ModBlocks.MANA_DETECTION_ORB.get(),
                        ModBlocks.SMALL_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.LARGE_MANA_CRYSTAL_BUD.get()
                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.MANA_CRYSTAL_BLOCK.get(),
                        ModBlocks.SMALL_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get(),
                        ModBlocks.LARGE_MANA_CRYSTAL_BUD.get()
                );

        this.tag(ModTags.Blocks.NEEDS_MANA_CRYSTAL_TOOL)
                .add(
                        ModBlocks.MANA_DETECTION_ORB.get()
                );

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.MANA_CRYSTAL_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MANA_CRYSTAL_FENCE_GATE.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.MANA_CRYSTAL_WALL.get());
    }
}
