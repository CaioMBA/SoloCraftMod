package org.ofa.solocraft.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.custom.FuelItem;
import org.ofa.solocraft.item.custom.ManaDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Item> MANA_CRYSTAL_DUST =
            ITEMS.register("mana_crystal_dust", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> SMALL_MANA_CRYSTAL =
            ITEMS.register("small_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEDIUM_MANA_CRYSTAL =
            ITEMS.register("medium_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LARGE_MANA_CRYSTAL =
            ITEMS.register("large_mana_crystal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRAWBERRY =
            ITEMS.register("strawberry", () ->
                    new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static final RegistryObject<Item> MANA_DETECTOR =
            ITEMS.register("mana_detector", () ->
                    new ManaDetectorItem(new Item.Properties()
                            .durability(100)));

    public static final RegistryObject<Item> ORB_OF_AVARICE =
            ITEMS.register("orb_of_avarice", () ->
                    new FuelItem(new Item.Properties(), 6000));

    public static final RegistryObject<Item> MANA_CRYSTAL_STAFF =
            ITEMS.register("mana_crystal_staff", () ->
                    new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_SWORD =
            ITEMS.register("mana_crystal_sword", () ->
                    new SwordItem(
                            ModToolTiers.MANA_CRYSTAL,
                            4,
                            2,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_PICKAXE =
            ITEMS.register("mana_crystal_pickaxe", () ->
                    new PickaxeItem(
                            ModToolTiers.MANA_CRYSTAL,
                            1,
                            1,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_AXE =
            ITEMS.register("mana_crystal_axe", () ->
                    new AxeItem(
                            ModToolTiers.MANA_CRYSTAL,
                            2,
                            1.5F,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_SHOVEL =
            ITEMS.register("mana_crystal_shovel", () ->
                    new ShovelItem(
                            ModToolTiers.MANA_CRYSTAL,
                            0,
                            0,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_HOE =
            ITEMS.register("mana_crystal_hoe", () ->
                    new HoeItem(
                            ModToolTiers.MANA_CRYSTAL,
                            0,
                            0,
                            new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
