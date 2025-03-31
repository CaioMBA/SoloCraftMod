package org.ofa.solocraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Item> SMALL_MANA_CRYSTAL =
            ITEMS.register("small_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEDIUM_MANA_CRYSTAL =
            ITEMS.register("medium_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LARGE_MANA_CRYSTAL =
            ITEMS.register("large_mana_crystal", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
