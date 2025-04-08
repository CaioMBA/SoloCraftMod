package org.ofa.solocraft.entity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.ofa.solocraft.entity.client.RhinoModel;
import org.ofa.solocraft.entity.client.RhinoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.custom.RhinoEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SolocraftMod.MOD_ID);

    public static final Map<
            RegistryObject<? extends EntityType<?>>,
            EntityRendererProvider<?>
            > RENDERERS
            = new LinkedHashMap<>();

    public static final Map<
            RegistryObject<? extends EntityType<? extends LivingEntity>>,
            Supplier<AttributeSupplier.Builder>
            > ATTRIBUTES
            = new LinkedHashMap<>();

    public static final Map<
            RegistryObject<? extends EntityType<? extends LivingEntity>>,
            Supplier<LayerDefinition>
            > MODEL_DEFINITIONS = new LinkedHashMap<>();

    public static final Map<
            RegistryObject<? extends EntityType<? extends LivingEntity>>,
            ModelLayerLocation
            > MODEL_LAYERS
            = new LinkedHashMap<>();

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO = registerEntity(
            "rhino",
            () -> EntityType.Builder.of(RhinoEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 2.5f)
                    .build( "rhino"),
            RhinoRenderer::new,
            RhinoEntity::createAttributes,
            RhinoModel::createBodyLayer
    );

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerEntity(
            String name,
            Supplier<EntityType<T>> entitySupplier,
            EntityRendererProvider<? super T> renderer,
            Supplier<AttributeSupplier.Builder> attributeSupplier,
            Supplier<LayerDefinition> layerDefinition
    ) {
        RegistryObject<EntityType<T>> reg = ENTITY_TYPES.register(name, entitySupplier);
        RENDERERS.put(reg, renderer);
        ATTRIBUTES.put(reg, attributeSupplier);
        MODEL_LAYERS.put(
                reg,
                new ModelLayerLocation(
                        new ResourceLocation(
                                SolocraftMod.MOD_ID,
                                "%s_layer".formatted(name)
                        ),
                        "main")
        );
        MODEL_DEFINITIONS.put(reg, layerDefinition);
        return reg;
    }

    public static ModelLayerLocation getModelLayer(RegistryObject<? extends EntityType<?>> entityType) {
        return MODEL_LAYERS.get(entityType);
    }

    public static LayerDefinition getLayerDefinition(RegistryObject<? extends EntityType<?>> entityType) {
        return MODEL_DEFINITIONS.get(entityType).get();
    }

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
