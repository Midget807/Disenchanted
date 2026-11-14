package net.midget807.disenchanted.registry;

import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public class DisenchantedRecipeBookCategories {
    public static final RecipeBookCategory FLETCHING = register("fletching");

    private static RecipeBookCategory register(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, DisenchantedMain.id(name), new RecipeBookCategory());
    }

    public static void registerModRecipeCategories() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Recipe Categories");
    }
}
