package org.ofa.solocraft.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.custom.ManaDetectionOrbBlock;
import org.ofa.solocraft.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Block> MANA_CRYSTAL_BLOCK =
            registerBlock("mana_crystal_block",
                    () -> new Block(BlockBehaviour.Properties
                    .copy(Blocks.DIAMOND_BLOCK)
                    .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SMALL_MANA_CRYSTAL_BUD =
            registerBlock("small_mana_crystal_bud",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties
                            .copy(Blocks.SMALL_AMETHYST_BUD)
                            .strength(1.0F)
                            .requiresCorrectToolForDrops(),
                            UniformInt.of(1,4)
                    ));
    public static final RegistryObject<Block> MEDIUM_MANA_CRYSTAL_BUD =
            registerBlock("medium_mana_crystal_bud",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties
                            .copy(Blocks.MEDIUM_AMETHYST_BUD)
                            .strength(1.5F)
                            .requiresCorrectToolForDrops(),
                            UniformInt.of(1,8)
                    ));

    public static final RegistryObject<Block> LARGE_MANA_CRYSTAL_BUD =
            registerBlock("large_mana_crystal_bud",
                    () -> new DropExperienceBlock(BlockBehaviour.Properties
                            .copy(Blocks.LARGE_AMETHYST_BUD)
                            .strength(2F)
                            .requiresCorrectToolForDrops(),
                            UniformInt.of(1,12)
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
