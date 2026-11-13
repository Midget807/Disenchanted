package net.midget807.disenchanted.component;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.serialization.Codec;
import net.midget807.disenchanted.util.DisenchantedTextUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.List;
import java.util.function.Consumer;

public record ExpandableTooltip(List<Component> lines, List<Component> styledLines) implements TooltipProvider {
    public static final ItemLore EMPTY = new ItemLore(List.of());
    public static final int MAX_LINES = 256;
    public static final Codec<ExpandableTooltip> CODEC = ComponentSerialization.CODEC.sizeLimitedListOf(MAX_LINES).xmap(ExpandableTooltip::new, ExpandableTooltip::lines);
    public static final StreamCodec<RegistryFriendlyByteBuf, ExpandableTooltip> STREAM_CODEC = ComponentSerialization.STREAM_CODEC
            .apply(ByteBufCodecs.list(MAX_LINES))
            .map(ExpandableTooltip::new, ExpandableTooltip::lines);

    public ExpandableTooltip(final List<Component> lines) {
        this(lines, Lists.transform(lines, component -> ComponentUtils.mergeStyles(component, Style.EMPTY)));
    }

    public ExpandableTooltip {
        if (lines.size() > MAX_LINES) {
            throw new IllegalArgumentException("Got " + lines.size() + " lines, but maximum is " + MAX_LINES);
        }
    }

    public ItemLore withLines(final Component component) {
        return new ItemLore(Util.copyAndAdd(this.lines, component));
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        Minecraft client = Minecraft.getInstance();
        boolean shiftKeyDown = InputConstants.isKeyDown(InputConstants.KEY_LSHIFT) || InputConstants.isKeyDown(InputConstants.KEY_RSHIFT);
        if (shiftKeyDown) {
            DisenchantedTextUtil.getWrappedText(this.styledLines).forEach(consumer);
        } else {
            consumer.accept(Component.literal("Hold ").append(Component.literal("[Shift]").withColor(TextColor.GOLD)).append(Component.literal(" for more information.")));
        }
    }

}
