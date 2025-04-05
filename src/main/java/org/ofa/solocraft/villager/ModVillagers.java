package org.ofa.solocraft.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, SolocraftMod.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, SolocraftMod.MOD_ID);

    public static final RegistryObject<PoiType> MANA_DETECTION_POI
            = POI_TYPES.register(
                    "mana_detection_poi",
                    () -> new PoiType(
                            ImmutableSet.copyOf(
                                    ModBlocks.MANA_DETECTION_ORB.get()
                                            .getStateDefinition()
                                            .getPossibleStates()
                            ),
                            1,
                            1
                    )
    );

    public static final RegistryObject<VillagerProfession> HUNTER_ASSOCIATION_SECRETARY
            = VILLAGER_PROFESSIONS.register(
                    "hunter_association_secretary",
                    () -> new VillagerProfession(
                            "hunter_association_secretary",
                            holder -> holder.get() == MANA_DETECTION_POI.get(),
                            holder -> holder.get() == MANA_DETECTION_POI.get(),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_TOOLSMITH
                    )
            );

    public static void register(IEventBus bus) {
        POI_TYPES.register(bus);
        VILLAGER_PROFESSIONS.register(bus);
    }
}
