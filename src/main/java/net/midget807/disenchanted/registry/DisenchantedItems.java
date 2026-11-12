package net.midget807.disenchanted.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.item.CustomSmithingTemplates;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class DisenchantedItems {

    public static final Item OVERWORLD_TABLET = register("overworld_tablet", CustomSmithingTemplates::createDiamondUpgradeTemple);
    public static final Item UNDERWORLD_TABLET = register("underworld_tablet", CustomSmithingTemplates::createDiamondUpgradeTemple);

    private static Item register(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, DisenchantedMain.id(name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, DisenchantedMain.id(name))))
        );
    }

    public static void registerModItems() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(OVERWORLD_TABLET);
            output.accept(UNDERWORLD_TABLET);
        });
    }
}
