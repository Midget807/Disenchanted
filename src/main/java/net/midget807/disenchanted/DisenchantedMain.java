package net.midget807.disenchanted;

import net.fabricmc.api.ModInitializer;
import net.midget807.disenchanted.event.DefaultItemComponentListener;
import net.midget807.disenchanted.event.DisenchantedItemComponentListener;
import net.midget807.disenchanted.registry.DisenchantedDataComponents;
import net.midget807.disenchanted.registry.DisenchantedItemGroups;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisenchantedMain implements ModInitializer {
	public static final String MOD_ID = "disenchanted";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		DisenchantedItems.registerModItems();
		DisenchantedItemGroups.registerModItemGroups();
		DisenchantedDataComponents.registerModDataComponents();

		DefaultItemComponentListener.execute();
		DisenchantedItemComponentListener.execute();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
