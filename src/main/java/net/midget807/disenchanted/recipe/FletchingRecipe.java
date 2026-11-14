package net.midget807.disenchanted.recipe;

import net.midget807.disenchanted.registry.DisenchantedRecipeBookCategories;
import net.midget807.disenchanted.registry.DisenchantedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public interface FletchingRecipe extends Recipe<FletchingRecipeInput> {
    @Override
    default RecipeType<FletchingRecipe> getType() {
        return DisenchantedRecipe.FLETCHING_TYPE;
    }

    @Override
    RecipeSerializer<? extends FletchingRecipe> getSerializer();

    default boolean matches(FletchingRecipeInput input, Level level) {
        return this.baseIngredient().test(input.base())
                && Ingredient.testOptionalIngredient(this.addition1Ingredient(), input.addition1())
                && Ingredient.testOptionalIngredient(this.addition2Ingredient(), input.addition2());
    }

    Ingredient baseIngredient();
    Optional<Ingredient> addition1Ingredient();
    Optional<Ingredient> addition2Ingredient();

    @Override
    default RecipeBookCategory recipeBookCategory() {
        return DisenchantedRecipeBookCategories.FLETCHING;
    }
}
