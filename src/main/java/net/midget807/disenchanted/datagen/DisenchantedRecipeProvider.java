package net.midget807.disenchanted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.midget807.disenchanted.util.DisenchantedTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeProvider.getItemName;

public class DisenchantedRecipeProvider extends FabricRecipeProvider {
    public DisenchantedRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> bootstrapContext, BootstrapContext<Advancement> bootstrapContext1) {
        return new RecipeProvider(bootstrapContext, bootstrapContext1) {
            @Override
            public void buildRecipes() {
                this.diamondSmithing(Items.IRON_CHESTPLATE, RecipeCategory.COMBAT, Items.DIAMOND_CHESTPLATE);
                this.diamondSmithing(Items.IRON_LEGGINGS, RecipeCategory.COMBAT, Items.DIAMOND_LEGGINGS);
                this.diamondSmithing(Items.IRON_HELMET, RecipeCategory.COMBAT, Items.DIAMOND_HELMET);
                this.diamondSmithing(Items.IRON_BOOTS, RecipeCategory.COMBAT, Items.DIAMOND_BOOTS);
                this.diamondSmithing(Items.IRON_SWORD, RecipeCategory.COMBAT, Items.DIAMOND_SWORD);
                this.diamondSmithing(Items.IRON_SPEAR, RecipeCategory.COMBAT, Items.DIAMOND_SPEAR);
                this.diamondSmithing(Items.IRON_AXE, RecipeCategory.TOOLS, Items.DIAMOND_AXE);
                this.diamondSmithing(Items.IRON_PICKAXE, RecipeCategory.TOOLS, Items.DIAMOND_PICKAXE);
                this.diamondSmithing(Items.IRON_HOE, RecipeCategory.TOOLS, Items.DIAMOND_HOE);
                this.diamondSmithing(Items.IRON_SHOVEL, RecipeCategory.TOOLS, Items.DIAMOND_SHOVEL);
            }

            public void diamondSmithing(final Item base, final RecipeCategory category, final Item result) {
                SmithingTransformRecipeBuilder.smithing(
                                this.tag(DisenchantedTags.Items.DIAMOND_SMITHING_UPGRADE_TEMPLATES), Ingredient.of(base), Ingredient.of(Items.DIAMOND), category, result
                        )
                        .unlocks("has_netherite_ingot", this.has(Items.DIAMOND))
                        .save(this.output, getItemName(result) + "_smithing");
            }
        };
    }

    @Override
    public String getName() {
        return "Disenchanted Recipes";
    }
}
