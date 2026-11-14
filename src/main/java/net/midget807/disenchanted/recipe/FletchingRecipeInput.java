package net.midget807.disenchanted.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record FletchingRecipeInput(
        net.minecraft.world.item.ItemStack base,
        net.minecraft.world.item.ItemStack addition1,
        net.minecraft.world.item.ItemStack addition2
) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index) {
            case 0 -> base;
            case 1 -> addition1;
            case 2 -> addition2;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
        };
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return this.base.isEmpty() && this.addition1.isEmpty() && this.addition2.isEmpty();
    }
}
