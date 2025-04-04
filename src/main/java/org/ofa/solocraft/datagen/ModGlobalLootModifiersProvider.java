package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.ModItems;
import org.ofa.solocraft.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, SolocraftMod.MOD_ID);
    }

    @Override
    protected void start() {
        add(
                "mana_crystal_shard_from_iron_ore",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.IRON_ORE).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()
                }, ModItems.MANA_CRYSTAL_SHARD.get())
        );

        add(
                "mana_crystal_dust_from_skeleton",
                new AddItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                        LootItemRandomChanceCondition.randomChance(0.25F).build()
                }, ModItems.MANA_CRYSTAL_SHARD.get())
        );

        add(
                "mana_crystal_from_jungle_temples",
                new AddItemModifier(new LootItemCondition[]{
                        new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5F).build()
                }, ModItems.MANA_CRYSTAL_SHARD.get())
        );

    }
}
