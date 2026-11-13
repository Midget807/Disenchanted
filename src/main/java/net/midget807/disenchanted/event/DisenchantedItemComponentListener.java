package net.midget807.disenchanted.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.midget807.disenchanted.component.ExpandableTooltip;
import net.midget807.disenchanted.registry.DisenchantedDataComponents;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;

public class DisenchantedItemComponentListener {
    public static void execute() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(DisenchantedItems.OVERWORLD_TABLET, builder -> modifyOverworldTablet(builder));
            context.modify(DisenchantedItems.UNDERWORLD_TABLET, builder -> modifyUnderworldTablet(builder));
        });
    }

    private static void modifyUnderworldTablet(DataComponentMap.Builder builder) {
        builder.set(DisenchantedDataComponents.EXPANDABLE_TOOLTIP, new ExpandableTooltip(List.of(
                Component.translatable("item.disenchanted.lore.overworld_tablet_line2").append(Component.literal("\\n \\n"))
        )));
        builder.set(
                DisenchantedDataComponents.EXPANDABLE_TOOLTIP,
                builder.getOrDefault(DisenchantedDataComponents.EXPANDABLE_TOOLTIP, ExpandableTooltip.DEFAULT)
                        .withLines(
                                Component.translatable("item.disenchanted.lore.overworld_tablet_line3").withStyle(Style.EMPTY.withColor(ChatFormatting.RED).withBold(true))
                        )
        );
    }

    private static void modifyOverworldTablet(DataComponentMap.Builder builder) {
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.empty(),
                Component.translatable("item.disenchanted.lore.overworld_tablet_line2").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN).withItalic(true)),
                Component.translatable("item.disenchanted.lore.overworld_tablet_line3").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN).withItalic(true))
        )));
    }
}
