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

import java.util.Objects;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void RegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ModEntities.ENTITIES.forEach((entityTypeReg, def) -> {
            event.registerLayerDefinition(
                    Objects.requireNonNull(ModEntities.ENTITIES.get(entityTypeReg).clientData()).modelLayer(),
                    Objects.requireNonNull(ModEntities.ENTITIES.get(entityTypeReg).clientData()).modelDefinition()
            );
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ModEntities.ENTITIES.forEach(ModEventBusClientEvents::registerEntityRenderer);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> void registerEntityRenderer(
            RegistryObject<? extends EntityType<?>> entityTypeReg,
            ModEntities.EntityDefinition<?> def
    ) {
        EntityRenderers.register(
                (EntityType<T>) entityTypeReg.get(),
                (EntityRendererProvider<T>) Objects.requireNonNull(def.clientData()).renderer()
        );
    }
}
