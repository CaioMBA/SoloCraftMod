package org.ofa.solocraft.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier MANA_CRYSTAL = TierSortingRegistry.registerTier(
            new ForgeTier(
                    5,
                    1500,
                    5.0F,
                    4.0F,
                    25,
                    ModTags.Blocks.NEEDS_MANA_CRYSTAL_TOOL,
                    () -> Ingredient.of(ModItems.LARGE_MANA_CRYSTAL.get())),
            new ResourceLocation(SolocraftMod.MOD_ID,"large_mana_crystal"),
            List.of(Tiers.NETHERITE),
            List.of());
}
