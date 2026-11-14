package net.midget807.disenchanted.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipePropertySet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    @Accessor("RECIPE_PROPERTY_SETS")
    Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> disenchanted$getRecipePropertySets();
}
