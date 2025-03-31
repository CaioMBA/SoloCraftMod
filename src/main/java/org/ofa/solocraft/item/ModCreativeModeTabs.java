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

    public static final RegistryObject<CreativeModeTab> CRYSTALS_TAB =
            CREATIVE_MODE_TABS.register("crystals_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.crystals_tab"))
                            .icon(() -> new ItemStack(ModItems.SMALL_MANA_CRYSTAL.get()))
                            .displayItems((itemDisplayParameters, output) ->{
                                output.accept(ModItems.SMALL_MANA_CRYSTAL.get());
                                output.accept(ModItems.MEDIUM_MANA_CRYSTAL.get());
                                output.accept(ModItems.LARGE_MANA_CRYSTAL.get());

                                output.accept(ModBlocks.MANA_CRYSTAL_BLOCK.get());
                                output.accept(ModBlocks.SMALL_MANA_CRYSTAL_BUD.get());
                                output.accept(ModBlocks.MEDIUM_MANA_CRYSTAL_BUD.get());
                                output.accept(ModBlocks.LARGE_MANA_CRYSTAL_BUD.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
