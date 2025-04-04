package org.ofa.solocraft.item.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.ofa.solocraft.util.enums.ModArmorMaterials;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<MobEffectInstance>> MATERIAL_TO_EFFECTS_MAP =
            ImmutableMap.of(
                    ModArmorMaterials.MANA_CRYSTAL, ImmutableList.of(
                            new MobEffectInstance(
                                    MobEffects.DAMAGE_BOOST,
                                    200,
                                    1,
                                    false,
                                    false,
                                    true),
                            new MobEffectInstance(
                                    MobEffects.MOVEMENT_SPEED,
                                    200,
                                    0,
                                    false,
                                    false,
                                    true)
                    )
            );

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (level.isClientSide()) return;
        if (!hasFullArmorSet(player)) return;

        ArmorMaterial equippedMaterial = getEquippedArmorMaterial(player);
        if (equippedMaterial == null) return;

        List<MobEffectInstance> effects = MATERIAL_TO_EFFECTS_MAP.get(equippedMaterial);
        if (effects == null) return;

        for (MobEffectInstance effect : effects) {
            if (!player.hasEffect(effect.getEffect())) {
                player.addEffect(new MobEffectInstance(effect));
            }
        }
    }

    private boolean hasFullArmorSet(Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) return false;
        }
        return true;
    }

    private ArmorMaterial getEquippedArmorMaterial(Player player) {
        ItemStack[] armor = player.getInventory().armor.toArray(new ItemStack[0]);

        if (armor.length != 4) return null;

        ArmorItem firstItem = (ArmorItem) armor[0].getItem();
        ArmorMaterial expected = firstItem.getMaterial();

        for (ItemStack piece : armor) {
            if (!(piece.getItem() instanceof ArmorItem armorItem) || armorItem.getMaterial() != expected) {
                return null;
            }
        }
        return expected;
    }
}
