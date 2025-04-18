package org.ofa.solocraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES
            = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SolocraftMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemPolishingStationBlockEntity>> GEM_POLISHING_STATION_BE
            = BLOCK_ENTITIES.register("gem_polishing_station_be",
            () -> BlockEntityType.Builder.of(
                    GemPolishingStationBlockEntity::new,
                    ModBlocks.GEM_POLISHING_STATION.get()
            ).build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
