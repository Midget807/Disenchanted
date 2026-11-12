package net.midget807.disenchanted.registry;

import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class DisenchantedItems {



    private static Item register(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, DisenchantedMain.id(name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, DisenchantedMain.id(name))))
        );
    }

    public static void registerModItems() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Items");
    }
}
