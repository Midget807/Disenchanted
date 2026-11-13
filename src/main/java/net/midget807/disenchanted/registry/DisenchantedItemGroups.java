package net.midget807.disenchanted.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.world.item.CreativeModeTabs;

import static net.midget807.disenchanted.registry.DisenchantedItems.*;
import static net.minecraft.world.item.Items.*;

public class DisenchantedItemGroups {

    public static void registerModItemGroups() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Item Groups");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.insertBefore(NETHERITE_UPGRADE_SMITHING_TEMPLATE, OVERWORLD_TABLET, UNDERWORLD_TABLET);
            output.insertBefore(NETHERITE_INGOT,HEAVY_CRYSTAL);
        });
    }
}
