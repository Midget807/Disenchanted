package net.midget807.disenchanted.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record FletchingRecipeDisplay(SlotDisplay base, SlotDisplay addition1, SlotDisplay addition2, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<FletchingRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            SlotDisplay.CODEC.fieldOf("base").forGetter(FletchingRecipeDisplay::base),
                            SlotDisplay.CODEC.fieldOf("addition1").forGetter(FletchingRecipeDisplay::addition1),
                            SlotDisplay.CODEC.fieldOf("addition2").forGetter(FletchingRecipeDisplay::addition2),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(FletchingRecipeDisplay::result),
                            SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(FletchingRecipeDisplay::craftingStation)
                    )
                    .apply(i, FletchingRecipeDisplay::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, FletchingRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            FletchingRecipeDisplay::base,
            SlotDisplay.STREAM_CODEC,
            FletchingRecipeDisplay::addition1,
            SlotDisplay.STREAM_CODEC,
            FletchingRecipeDisplay::addition2,
            SlotDisplay.STREAM_CODEC,
            FletchingRecipeDisplay::result,
            SlotDisplay.STREAM_CODEC,
            FletchingRecipeDisplay::craftingStation,
            FletchingRecipeDisplay::new
    );

    public static final RecipeDisplay.Type<FletchingRecipeDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Type<? extends RecipeDisplay> type() {
        return TYPE;
    }
}
