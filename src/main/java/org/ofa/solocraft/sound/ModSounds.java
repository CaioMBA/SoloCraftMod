package org.ofa.solocraft.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SolocraftMod.MOD_ID);

    public static final RegistryObject<SoundEvent> ARISE_VOICE
            = registerSoundEvents("arise_voice");

    public static final RegistryObject<SoundEvent> OKIRU_VOICE
            = registerSoundEvents("okiru_voice");

    public static final RegistryObject<SoundEvent> MANA_DETECTION_ORB_SCANNING
            = registerSoundEvents("mana_detection_orb_scanning");

    public static final RegistryObject<SoundEvent> AURA_FARMING
            = registerSoundEvents("aura_farming");

    public static final ForgeSoundType MANA_DETECTOR_CRYSTAL_SOUNDS
            = new ForgeSoundType(
                    1.0F,
                    1.0F,
                    ModSounds.MANA_DETECTION_ORB_SCANNING,
                    ModSounds.MANA_DETECTION_ORB_SCANNING,
                    ModSounds.MANA_DETECTION_ORB_SCANNING,
                    ModSounds.MANA_DETECTION_ORB_SCANNING,
                    ModSounds.MANA_DETECTION_ORB_SCANNING
            );

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(
                name,
                () -> SoundEvent.createVariableRangeEvent(
                        new ResourceLocation(SolocraftMod.MOD_ID, name)
                )
        );
    }

}
