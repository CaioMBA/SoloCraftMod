package org.ofa.solocraft.screen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS
            = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SolocraftMod.MOD_ID);

    //region Menu Data Records
    public record MenuTypeDefinition<T extends AbstractContainerMenu>(
            IContainerFactory<T> factory,
            MenuScreens.ScreenConstructor<T, ? extends AbstractContainerScreen<T>> screenFactory
    ) {}
    //endregion

    public static final Map<
            RegistryObject<? extends MenuType<?>>,
            MenuTypeDefinition<?>
            > MOD_MENUS = new LinkedHashMap<>();


    public static final RegistryObject<MenuType<GemPolishingStationMenu>> GEM_POLISHING_STATION_MENU =
            registerMenuType("gem_polishing_station_menu",
                    GemPolishingStationMenu::new,
                    GemPolishingStationScreen::new
            );

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
            String name,
            IContainerFactory<T> factory,
            MenuScreens.ScreenConstructor<T, ? extends AbstractContainerScreen<T>> screenFactory
    ) {
        RegistryObject<MenuType<T>> reg = MENUS.register(name, () -> IForgeMenuType.create(factory));
        MOD_MENUS.put(reg, new MenuTypeDefinition<>(factory, screenFactory));
        return reg;
    }

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
