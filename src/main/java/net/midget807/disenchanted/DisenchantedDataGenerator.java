package net.midget807.disenchanted;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.midget807.disenchanted.datagen.DisenchantedBlockTagProvider;
import net.midget807.disenchanted.datagen.DisenchantedItemTagProvider;
import net.midget807.disenchanted.datagen.DisenchantedLangProvider;
import net.midget807.disenchanted.datagen.DisenchantedModelProvider;
import net.midget807.disenchanted.datagen.DisenchantedRecipeProvider;

public class DisenchantedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DisenchantedModelProvider::new);
		pack.addProvider(DisenchantedItemTagProvider::new);
		pack.addProvider(DisenchantedBlockTagProvider::new);
		pack.addProvider(DisenchantedRecipeProvider::new);
		pack.addProvider(DisenchantedLangProvider::new);
	}
}
