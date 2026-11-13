package net.midget807.disenchanted.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class DisenchantedModelProvider extends FabricModelProvider {
    public DisenchantedModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(DisenchantedItems.OVERWORLD_TABLET, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DisenchantedItems.UNDERWORLD_TABLET, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(DisenchantedItems.HEAVY_CRYSTAL, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(DisenchantedItems.CUT_LEATHER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DisenchantedItems.COPPER_PLATING, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DisenchantedItems.GOLDEN_PLATING, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DisenchantedItems.CHAIN_MAIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(DisenchantedItems.REINFORCED_IRON_PLATING, ModelTemplates.FLAT_ITEM);
    }
}
