package net.midget807.disenchanted.registry;

import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class DisenchantedStats {
    public static final Identifier INTERACT_WITH_FLETCHING_TABLE = makeCustomStat("interact_with_fletching_table", StatFormatter.DEFAULT);

    private static Identifier makeCustomStat(final String id, final StatFormatter formatter) {
        Identifier location = Identifier.withDefaultNamespace(id);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id, location);
        Stats.CUSTOM.get(location, formatter);
        return location;
    }

    public static void registerModStats() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Stats");
    }
}
