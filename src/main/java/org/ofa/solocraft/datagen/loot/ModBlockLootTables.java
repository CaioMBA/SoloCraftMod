package org.ofa.solocraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.MANA_CRYSTAL_BLOCK.get());
        this.dropSelf(ModBlocks.MANA_DETECTION_ORB.get());

        this.add(ModBlocks.SMALL_MANA_CRYSTAL_BUD.get(),
                block->createSimpleOreDrops(ModBlocks.SMALL_MANA_CRYSTAL_BUD.get(),
                        ModItems.SMALL_MANA_CRYSTAL.get(),
                        1.0F,
                        5.0F));

        this.add(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get(),
                block->createSimpleOreDrops(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get(),
                        ModItems.SMALL_MANA_CRYSTAL.get(),
                        1.0F,
                        5.0F));

        this.add(ModBlocks.LARGE_MANA_CRYSTAL_BUD.get(),
                block->createSimpleOreDrops(ModBlocks.LARGE_MANA_CRYSTAL_BUD.get(),
                        ModItems.SMALL_MANA_CRYSTAL.get(),
                        1.0F,
                        5.0F));

    }

    protected  LootTable.Builder createSimpleOreDrops(Block block, Item item, float min, float max){
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
