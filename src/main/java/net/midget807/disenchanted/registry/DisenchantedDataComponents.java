package net.midget807.disenchanted.registry;

import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.component.ExpandableTooltip;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class DisenchantedDataComponents {
    public static final DataComponentType<ExpandableTooltip> EXPANDABLE_TOOLTIP = register(
            "expandable_tooltip", b -> b.persistent(ExpandableTooltip.CODEC).networkSynchronized(ExpandableTooltip.STREAM_CODEC).cacheEncoding()
    );

    private static <T> DataComponentType<T> register(final String name, final UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, DisenchantedMain.id(name), builder.apply(DataComponentType.builder()).build());
    }

    public static void registerModDataComponents() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Data Components");
    }
}
