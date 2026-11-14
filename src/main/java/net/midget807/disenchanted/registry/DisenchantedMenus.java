package net.midget807.disenchanted.registry;

import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.screen.FletchingMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class DisenchantedMenus {
    public static final net.minecraft.world.inventory.MenuType<FletchingMenu> FLETCHING = register("fletching", FletchingMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType.MenuSupplier<T> constructor) {
        return Registry.register(BuiltInRegistries.MENU, name, new MenuType<>(constructor, FeatureFlags.VANILLA_SET));
    }

    public static void registerModMenus() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Menus");
    }
}
