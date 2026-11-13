package net.midget807.disenchanted.registry;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.item.CustomSmithingTemplates;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DisenchantedItems {
    public static final Map<Identifier, Item> ITEMS = new HashMap<>();

    public static final Item OVERWORLD_TABLET = register("overworld_tablet", CustomSmithingTemplates::createDiamondUpgradeTemple);
    public static final Item UNDERWORLD_TABLET = register("underworld_tablet", CustomSmithingTemplates::createDiamondUpgradeTemple);

    public static final Item HEAVY_CRYSTAL = register("heavy_crystal", Item::new);

    public static final Item CUT_LEATHER = register("cut_leather", Item::new);
    public static final Item COPPER_PLATING = register("copper_plating", Item::new);
    public static final Item GOLDEN_PLATING = register("golden_plating", Item::new);
    public static final Item CHAIN_MAIL = register("chain_mail", Item::new);
    public static final Item REINFORCED_IRON_PLATING = register("reinforced_iron_plating", Item::new);


    private static Item register(String name, Function<Item.Properties, Item> function) {
        Identifier id = DisenchantedMain.id(name);
        Item registered = Registry.register(BuiltInRegistries.ITEM, id,
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id)))
        );
        ITEMS.put(id, registered);
        return registered;
    }

    public static ResourceKey<Item> getResourceKey(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static void registerModItems() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Items");
    }
}
