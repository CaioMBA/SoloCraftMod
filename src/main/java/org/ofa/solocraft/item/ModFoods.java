package org.ofa.solocraft.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties STRAWBERRY =
            new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.F)
                    .fast()
                    .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200),0.5F)
                    .build();
}
