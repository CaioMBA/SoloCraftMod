package org.ofa.solocraft.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.ModEntities;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event) {
        ModEntities.ENTITIES.forEach((type, def) -> {
            event.put(type.get(), def.attributes().get().build());
        });
    }
}
