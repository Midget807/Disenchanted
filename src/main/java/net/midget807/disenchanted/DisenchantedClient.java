package net.midget807.disenchanted;

import net.fabricmc.api.ClientModInitializer;
import net.midget807.disenchanted.registry.DisenchantedMenus;
import net.midget807.disenchanted.screen.FletchingScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class DisenchantedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(DisenchantedMenus.FLETCHING, FletchingScreen::new);
    }
}
