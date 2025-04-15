package org.ofa.solocraft.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.ModEntities;
import org.ofa.solocraft.screen.ModMenuTypes;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void RegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ModEntities.ENTITIES.forEach((entityTypeReg, def) -> {
            if (def.clientData() == null) {
                return;
            }
            event.registerLayerDefinition(
                    def.clientData().modelLayer(),
                    def.clientData().modelDefinition()
            );
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ModEntities.ENTITIES.forEach(ModEventBusClientEvents::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void registerMenuScreens(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ModMenuTypes.MOD_MENUS.forEach(ModEventBusClientEvents::registerScreenSafely);
        });
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> void registerEntityRenderer(
            RegistryObject<? extends EntityType<?>> entityTypeReg,
            ModEntities.EntityDefinition<?> def
    ) {
        if(def.clientData() == null) {
            return;
        }
        EntityRenderers.register(
                (EntityType<T>) entityTypeReg.get(),
                (EntityRendererProvider<T>) Objects.requireNonNull(def.clientData()).renderer()
        );
    }

    @SuppressWarnings("unchecked")
    private static <M extends AbstractContainerMenu, S extends AbstractContainerScreen<M>> void registerScreenSafely(
            RegistryObject<? extends MenuType<?>> menuTypeReg,
            ModMenuTypes.MenuTypeDefinition<?> def
    ) {
        MenuType<M> menuType = (MenuType<M>) menuTypeReg.get();
        MenuScreens.ScreenConstructor<M, S> screenFactory = (MenuScreens.ScreenConstructor<M, S>) def.screenFactory();
        MenuScreens.register(menuType, screenFactory);
    }
}
