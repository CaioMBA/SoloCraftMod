package org.ofa.solocraft.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.ModEntities;
import org.ofa.solocraft.entity.client.ModModelLayers;
import org.ofa.solocraft.entity.client.RhinoModel;
import org.ofa.solocraft.entity.custom.RhinoEntity;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void RegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.RHINO.get(), RhinoEntity.createAttributes().build());
    }
}
