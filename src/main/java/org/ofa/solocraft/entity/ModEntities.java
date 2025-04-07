package org.ofa.solocraft.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.entity.custom.RhinoEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SolocraftMod.MOD_ID);

    public static final RegistryObject<EntityType<RhinoEntity>> RHINO
            = ENTITY_TYPES.register("rhino",
            () -> EntityType.Builder
                    .of(
                            RhinoEntity::new,
                            MobCategory.MONSTER
                    )
                    .sized(2.5f, 2.5f)
                    .build("rhino"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
