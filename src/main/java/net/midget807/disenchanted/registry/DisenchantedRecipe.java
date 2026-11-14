package net.midget807.disenchanted.registry;

import net.fabricmc.fabric.api.recipe.v1.FabricRecipeManager;
import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.mixin.RecipeManagerAccessor;
import net.midget807.disenchanted.recipe.FletchingCraftingRecipe;
import net.midget807.disenchanted.recipe.FletchingRecipe;
import net.midget807.disenchanted.recipe.FletchingRecipeDisplay;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class DisenchantedRecipe {
    public static final RecipeType<FletchingRecipe> FLETCHING_TYPE = registerType("fletching");

    public static <T extends Recipe<?>> RecipeType<T> registerType(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, DisenchantedMain.id(name), new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }


    public static final RecipeSerializer<FletchingCraftingRecipe> FLETCHING_SERIALIZER = registerSerializer("fletching", FletchingCraftingRecipe.SERIALIZER);

    private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(String name, RecipeSerializer<T> serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, DisenchantedMain.id(name), serializer);
    }


    public static final RecipeDisplay.Type<FletchingRecipeDisplay> FLETCHING_DISPLAY = registerDisplay("fletching", FletchingRecipeDisplay.TYPE);

    private static <T extends RecipeDisplay> RecipeDisplay.Type<T> registerDisplay(String name, RecipeDisplay.Type<T> type) {
        return Registry.register(BuiltInRegistries.RECIPE_DISPLAY, DisenchantedMain.id(name), type);
    }

    public static void registerModRecipes() {
        DisenchantedMain.LOGGER.info("Registering Disenchanted Recipes");
    }
}
