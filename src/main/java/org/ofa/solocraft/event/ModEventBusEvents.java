package org.ofa.solocraft.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.ModEntities;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event) {
        ModEntities.ATTRIBUTES.forEach((type, supplier) -> {
            event.put((EntityType<? extends LivingEntity>) type.get(), supplier.get().build());
        });
    }
}
