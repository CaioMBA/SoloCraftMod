package org.ofa.solocraft.event;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.ModEntities;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void RegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ModEntities.MODEL_DEFINITIONS.forEach((entityTypeReg, layerDefinition) -> {
            event.registerLayerDefinition(
                    ModEntities.getModelLayer(entityTypeReg),
                    layerDefinition
            );
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ModEntities.RENDERERS.forEach(ModEventBusClientEvents::registerEntityRenderer);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> void registerEntityRenderer(
            RegistryObject<? extends EntityType<?>> entityTypeReg,
            EntityRendererProvider<?> renderer
    ) {
        EntityRenderers.register(
                (EntityType<T>) entityTypeReg.get(),
                (EntityRendererProvider<T>) renderer
        );
    }
}
