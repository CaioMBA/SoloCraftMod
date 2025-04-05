package org.ofa.solocraft.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.custom.FuelItem;
import org.ofa.solocraft.item.custom.ManaDetectorItem;
import org.ofa.solocraft.item.custom.ModArmorItem;
import org.ofa.solocraft.sound.ModSounds;
import org.ofa.solocraft.util.enums.ModArmorMaterials;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SolocraftMod.MOD_ID);

    public static final RegistryObject<Item> MANA_CRYSTAL_DUST =
            ITEMS.register("mana_crystal_dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MANA_CRYSTAL_SHARD =
            ITEMS.register("mana_crystal_shard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MANA_CRYSTAL =
            ITEMS.register("mana_crystal", () -> new Item(new Item.Properties()));

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

    public static final RegistryObject<Item> MANA_CRYSTAL_HELMET =
            ITEMS.register("mana_crystal_helmet", () ->
                    new ModArmorItem(
                            ModArmorMaterials.MANA_CRYSTAL,
                            ArmorItem.Type.HELMET,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_CHESTPLATE =
            ITEMS.register("mana_crystal_chestplate", () ->
                    new ModArmorItem(
                            ModArmorMaterials.MANA_CRYSTAL,
                            ArmorItem.Type.CHESTPLATE,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_LEGGINGS =
            ITEMS.register("mana_crystal_leggings", () ->
                    new ModArmorItem(
                            ModArmorMaterials.MANA_CRYSTAL,
                            ArmorItem.Type.LEGGINGS,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MANA_CRYSTAL_BOOTS =
            ITEMS.register("mana_crystal_boots", () ->
                    new ModArmorItem(
                            ModArmorMaterials.MANA_CRYSTAL,
                            ArmorItem.Type.BOOTS,
                            new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> AURA_FARMING_MUSIC_DISC =
            ITEMS.register(
                    "aura_farming_music_disc",
                    () -> new RecordItem(
                            6,
                            ModSounds.AURA_FARMING,
                            new Item.Properties().stacksTo(1),
                            2060
                    )
            );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
