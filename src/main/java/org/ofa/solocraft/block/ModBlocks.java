package org.ofa.solocraft.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.custom.ManaCrystalBudBlock;
import org.ofa.solocraft.block.custom.ManaDetectionOrbBlock;
import org.ofa.solocraft.item.ModItems;
import org.ofa.solocraft.util.enums.BudSizeType;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Block> MANA_CRYSTAL_BLOCK =
            registerBlock("mana_crystal_block",
                    () -> new Block(BlockBehaviour.Properties
                    .copy(Blocks.DIAMOND_BLOCK)
                    .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> MANA_CRYSTAL_STAIRS =
            registerBlock("mana_crystal_stairs",
                    () -> new StairBlock(()-> ModBlocks.MANA_CRYSTAL_BLOCK.get().defaultBlockState(),
                            BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> MANA_CRYSTAL_SLAB =
            registerBlock("mana_crystal_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> MANA_CRYSTAL_BUTTON =
            registerBlock("mana_crystal_button",
                    () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON),
                            BlockSetType.IRON,
                            10,
                            true));

    public static final RegistryObject<Block> MANA_CRYSTAL_PRESSURE_PLATE =
            registerBlock("mana_crystal_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,
                            BlockBehaviour.Properties
                                    .copy(Blocks.DIAMOND_BLOCK)
                                    .sound(SoundType.AMETHYST),
                            BlockSetType.IRON
                            ));

    public static final RegistryObject<Block> MANA_CRYSTAL_FENCE =
            registerBlock("mana_crystal_fence",
                    () -> new FenceBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> MANA_CRYSTAL_FENCE_GATE =
            registerBlock("mana_crystal_fence_gate",
                    () -> new FenceGateBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST),
                            SoundEvents.CHAIN_BREAK,
                            SoundEvents.ANVIL_BREAK));

    public static final RegistryObject<Block> MANA_CRYSTAL_WALL =
            registerBlock("mana_crystal_wall",
                    () -> new WallBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> MANA_CRYSTAL_DOOR =
            registerBlock("mana_crystal_door",
                    () -> new DoorBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)
                            .noOcclusion(),
                            BlockSetType.STONE));

    public static final RegistryObject<Block> MANA_CRYSTAL_TRAPDOOR =
            registerBlock("mana_crystal_trapdoor",
                    () -> new TrapDoorBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)
                            .noOcclusion(),
                            BlockSetType.STONE));

    public static final RegistryObject<Block> SMALL_MANA_CRYSTAL_BUD =
            registerBlock("small_mana_crystal_bud",
                    () -> new ManaCrystalBudBlock(BlockBehaviour.Properties
                            .copy(Blocks.SMALL_AMETHYST_BUD)
                            .strength(1.0F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                            UniformInt.of(1,4),
                            BudSizeType.SMALL
                    ));
    public static final RegistryObject<Block> MEDIUM_MANA_CRYSTAL_BUD =
            registerBlock("medium_mana_crystal_bud",
                    () -> new ManaCrystalBudBlock(BlockBehaviour.Properties
                            .copy(Blocks.MEDIUM_AMETHYST_BUD)
                            .strength(1.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                            UniformInt.of(4,8),
                            BudSizeType.MEDIUM
                    ));

    public static final RegistryObject<Block> LARGE_MANA_CRYSTAL_BUD =
            registerBlock("large_mana_crystal_bud",
                    () -> new ManaCrystalBudBlock(BlockBehaviour.Properties
                            .copy(Blocks.LARGE_AMETHYST_BUD)
                            .strength(2F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                            UniformInt.of(8,12),
                            BudSizeType.LARGE
                    ));

    public static final RegistryObject<Block> MANA_CRYSTAL_CLUSTER =
            registerBlock("mana_crystal_cluster",
                    () -> new ManaCrystalBudBlock(BlockBehaviour.Properties
                            .copy(Blocks.AMETHYST_CLUSTER)
                            .strength(2.5F)
                            .requiresCorrectToolForDrops()
                            .noOcclusion(),
                            UniformInt.of(12,16),
                            BudSizeType.CLUSTER
                    ));

    public static final RegistryObject<Block> MANA_DETECTION_ORB =
            registerBlock("mana_detection_orb",
                    () -> new ManaDetectionOrbBlock(BlockBehaviour.Properties
                            .copy(Blocks.DIAMOND_BLOCK)
                            .sound(SoundType.AMETHYST)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockObject = BLOCKS.register(name, block);
        registerBlockItem(name, blockObject);
        return blockObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
