package net.midget807.disenchanted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.midget807.disenchanted.util.DisenchantedTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

import static net.midget807.disenchanted.util.DisenchantedTags.Items.*;

public class DisenchantedItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public DisenchantedItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DIAMOND_SMITHING_UPGRADE_TEMPLATES).add(
                DisenchantedItems.getResourceKey(DisenchantedItems.OVERWORLD_TABLET),
                DisenchantedItems.getResourceKey(DisenchantedItems.UNDERWORLD_TABLET)
        );
    }

}
