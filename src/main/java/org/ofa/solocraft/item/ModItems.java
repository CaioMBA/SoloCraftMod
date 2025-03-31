package org.ofa.solocraft.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.custom.FuelItem;
import org.ofa.solocraft.item.custom.ManaDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Item> SMALL_MANA_CRYSTAL =
            ITEMS.register("small_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEDIUM_MANA_CRYSTAL =
            ITEMS.register("medium_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LARGE_MANA_CRYSTAL =
            ITEMS.register("large_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MANA_DETECTOR =
            ITEMS.register("mana_detector", () ->
                    new ManaDetectorItem(new Item.Properties()
                            .durability(100)));

    public static final RegistryObject<Item> STRAWBERRY =
            ITEMS.register("strawberry", () ->
                    new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static final RegistryObject<Item> ORB_OF_AVARICE =
            ITEMS.register("orb_of_avarice", () ->
                    new FuelItem(new Item.Properties(), 6000));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
