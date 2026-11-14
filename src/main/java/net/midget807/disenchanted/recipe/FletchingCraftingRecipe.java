package net.midget807.disenchanted.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class FletchingCraftingRecipe implements FletchingRecipe {
    public static final MapCodec<FletchingCraftingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
                    Ingredient.CODEC.fieldOf("base").forGetter(o -> o.base),
                    Ingredient.CODEC.optionalFieldOf("addition1").forGetter(o -> o.addition1),
                    Ingredient.CODEC.optionalFieldOf("addition2").forGetter(o -> o.addition2),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
            ).apply(i, FletchingCraftingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FletchingCraftingRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC,
            o -> o.commonInfo,
            Ingredient.CONTENTS_STREAM_CODEC,
            o -> o.base,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
            o -> o.addition1,
            Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC,
            o -> o.addition2,
            ItemStackTemplate.STREAM_CODEC,
            o -> o.result,
            FletchingCraftingRecipe::new
    );
    public static final RecipeSerializer<FletchingCraftingRecipe> SERIALIZER = new RecipeSerializer<FletchingCraftingRecipe>(MAP_CODEC, STREAM_CODEC);
    protected final Recipe.CommonInfo commonInfo;
    private final Ingredient base;
    private final Optional<Ingredient> addition1;
    private final Optional<Ingredient> addition2;
    private final ItemStackTemplate result;

    public FletchingCraftingRecipe(Recipe.CommonInfo commonInfo, Ingredient base, Optional<Ingredient> addition1, Optional<Ingredient> addition2, ItemStackTemplate result) {
        this.commonInfo = commonInfo;
        this.base = base;
        this.addition1 = addition1;
        this.addition2 = addition2;
        this.result = result;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public ItemStack assemble(FletchingRecipeInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return commonInfo.showNotification();
    }

    @Override
    public Ingredient baseIngredient() {
        return this.base;
    }

    @Override
    public Optional<Ingredient> addition1Ingredient() {
        return this.addition1;
    }

    @Override
    public Optional<Ingredient> addition2Ingredient() {
        return this.addition2;
    }

    @Override
    public RecipeSerializer<? extends FletchingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.createFromOptionals(List.of(Optional.of(this.base), this.addition1, this.addition2));
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new FletchingRecipeDisplay(
                        this.base.display(),
                        Ingredient.optionalIngredientToDisplay(this.addition1),
                        Ingredient.optionalIngredientToDisplay(this.addition2),
                        new SlotDisplay.ItemStackSlotDisplay(this.result),
                        new SlotDisplay.ItemSlotDisplay(Items.FLETCHING_TABLE)
                )
        );
    }
}
