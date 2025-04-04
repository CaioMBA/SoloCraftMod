package org.ofa.solocraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SolocraftMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("blocks_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.blocks_tab"))
                            .icon(() -> new ItemStack(ModBlocks.MANA_CRYSTAL_BLOCK.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModBlocks.MANA_CRYSTAL_BLOCK.get());
                                output.accept(ModBlocks.MANA_DETECTION_ORB.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_STAIRS.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_SLAB.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_BUTTON.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_PRESSURE_PLATE.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_FENCE.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_FENCE_GATE.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_WALL.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_DOOR.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_TRAPDOOR.get());
                            })
                            .build());


    public static final RegistryObject<CreativeModeTab> CRYSTALS_TAB =
            CREATIVE_MODE_TABS.register("crystals_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.crystals_tab"))
                            .icon(() -> new ItemStack(ModItems.MANA_CRYSTAL.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModItems.MANA_CRYSTAL_DUST.get());
                                output.accept(ModItems.MANA_CRYSTAL_SHARD.get());
                                output.accept(ModItems.MANA_CRYSTAL.get());


                                output.accept(ModBlocks.SMALL_MANA_CRYSTAL_BUD.get());
                                output.accept(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get());
                                output.accept(ModBlocks.LARGE_MANA_CRYSTAL_BUD.get());
                                output.accept(ModBlocks.MANA_CRYSTAL_CLUSTER.get());
                            })
                            .build());

    public static final RegistryObject<CreativeModeTab> FOOD_TAB =
            CREATIVE_MODE_TABS.register("food_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.food_tab"))
                            .icon(() -> new ItemStack(ModItems.STRAWBERRY.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModItems.STRAWBERRY.get());
                            })
                            .build());

    public static final RegistryObject<CreativeModeTab> TOOLS_TAB =
            CREATIVE_MODE_TABS.register("tools_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.tools_tab"))
                            .icon(() -> new ItemStack(ModItems.MANA_CRYSTAL_STAFF.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModItems.ORB_OF_AVARICE.get());
                                output.accept(ModItems.MANA_CRYSTAL_STAFF.get());
                                output.accept(ModItems.MANA_DETECTOR.get());
                                output.accept(ModItems.MANA_CRYSTAL_SWORD.get());
                                output.accept(ModItems.MANA_CRYSTAL_PICKAXE.get());
                                output.accept(ModItems.MANA_CRYSTAL_AXE.get());
                                output.accept(ModItems.MANA_CRYSTAL_SHOVEL.get());
                                output.accept(ModItems.MANA_CRYSTAL_HOE.get());
                            })
                            .build());

    public static final RegistryObject<CreativeModeTab> EQUIPMENT_TAB =
            CREATIVE_MODE_TABS.register("equipment_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.equipment_tab"))
                            .icon(() -> new ItemStack(ModItems.MANA_CRYSTAL_CHESTPLATE.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModItems.MANA_CRYSTAL_HELMET.get());
                                output.accept(ModItems.MANA_CRYSTAL_CHESTPLATE.get());
                                output.accept(ModItems.MANA_CRYSTAL_LEGGINGS.get());
                                output.accept(ModItems.MANA_CRYSTAL_BOOTS.get());
                            })
                            .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
