package net.midget807.disenchanted.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.world.item.Item;

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

        this.generateWithCustomHandModel(itemModelGenerators, DisenchantedItems.CHARYBDIS);
    }

    /** Generates the 16x16 GUI model and allows for a custom handheld model to be used. Append "_handheld" to assign the desired model JSON.
     * <blockquote><pre>
     *     Generated:
     *     "custom_mod:models/item/custom_item.json" -> GUI
     *     Manual:
     *     "custom_mod:models/item/custom_item_handheld.json" -> Handheld
     * </pre></blockquote>
     * */
    public void generateWithCustomHandModel(ItemModelGenerators itemModelGenerators, Item item) {
        ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked handModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item, "_handheld"));
        itemModelGenerators.itemModelOutput.accept(item, ItemModelGenerators.createFlatModelDispatch(flatModel, handModel));
    }
}
