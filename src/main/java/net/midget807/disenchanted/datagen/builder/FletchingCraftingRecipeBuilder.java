package net.midget807.disenchanted.datagen.builder;

import net.midget807.disenchanted.recipe.FletchingCraftingRecipe;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Optional;

public class FletchingCraftingRecipeBuilder {
    private final Ingredient base;
    private final Ingredient addition1;
    private final Ingredient addition2;
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();

    public FletchingCraftingRecipeBuilder(Ingredient base, Ingredient addition1, Ingredient addition2, RecipeCategory category, ItemStackTemplate result) {
        this.base = base;
        this.addition1 = addition1;
        this.addition2 = addition2;
        this.category = category;
        this.result = result;
    }

    public static FletchingCraftingRecipeBuilder fletching(Ingredient base, Ingredient addition1, Ingredient addition2, RecipeCategory category, Item result) {
        return new FletchingCraftingRecipeBuilder(base, addition1, addition2, category, new ItemStackTemplate(result));
    }
    public static FletchingCraftingRecipeBuilder fletching(Ingredient base, Ingredient addition1, Ingredient addition2, Item result) {
        return new FletchingCraftingRecipeBuilder(base, addition1, addition2, RecipeCategory.COMBAT, new ItemStackTemplate(result));
    }
    public static FletchingCraftingRecipeBuilder fletching(Ingredient base, Ingredient addition1, Ingredient addition2, Item result, int count) {
        return new FletchingCraftingRecipeBuilder(base, addition1, addition2, RecipeCategory.COMBAT, new ItemStackTemplate(result).withCount(count));
    }
    public static FletchingCraftingRecipeBuilder fletching(Ingredient base, Ingredient addition1, Item result) {
        return new FletchingCraftingRecipeBuilder(base, addition1, Ingredient.of(Items.AIR), RecipeCategory.COMBAT, new ItemStackTemplate(result));
    }
    public static FletchingCraftingRecipeBuilder fletching(Ingredient base, Ingredient addition1, Item result, int count) {
        return new FletchingCraftingRecipeBuilder(base, addition1, Ingredient.of(Items.AIR), RecipeCategory.COMBAT, new ItemStackTemplate(result).withCount(count));
    }
    
    public FletchingCraftingRecipeBuilder unlocks(String name, Criterion<?> criterion) {
        this.advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    public void save(RecipeOutput output, String id) {
        this.save(output, ResourceKey.create(Registries.RECIPE, Identifier.parse(id)));
    }

    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        FletchingCraftingRecipe recipe = new FletchingCraftingRecipe(
                new Recipe.CommonInfo(true),
                this.base,
                Optional.of(this.addition1),
                Optional.of(this.addition2),
                this.result
        );
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
