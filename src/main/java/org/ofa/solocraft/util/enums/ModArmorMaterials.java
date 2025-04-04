package org.ofa.solocraft.util.enums;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.ModItems;

import java.util.Map;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    MANA_CRYSTAL("mana_crystal",
            20,
            Map.of(
                    ArmorItem.Type.BOOTS, 4,
                    ArmorItem.Type.LEGGINGS, 5,
                    ArmorItem.Type.CHESTPLATE, 7,
                    ArmorItem.Type.HELMET, 5
            ),
            25,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            1.0F,
            1.0F,
            () -> Ingredient.of(
                    ModItems.MANA_CRYSTAL.get()
            )
    )
    ;

    private final String name;
    private final int durabilityMultiplier;
    private final Map<ArmorItem.Type, Integer> protection;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final Map<ArmorItem.Type, Integer> BASE_DURABILITY = Map.of(
            ArmorItem.Type.BOOTS, 13,
            ArmorItem.Type.LEGGINGS, 16,
            ArmorItem.Type.CHESTPLATE, 16,
            ArmorItem.Type.HELMET, 11
    );

    ModArmorMaterials(String name,
                      int durabilityMultiplier,
                      Map<ArmorItem.Type, Integer> protection,
                      int enchantmentValue,
                      SoundEvent equipSound,
                      float toughness,
                      float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protection = protection;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurabilityForType(ArmorItem.@NotNull Type type) {
        return BASE_DURABILITY.getOrDefault(type, 0) * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.@NotNull Type type) {
        return protection.getOrDefault(type, 0);
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return "%s:%s".formatted(SolocraftMod.MOD_ID, this.name);
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
