package net.midget807.disenchanted.event;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;

public class DefaultItemComponentListener {
    public static void execute() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.LEATHER_BOOTS, builder -> modifyLeatherBoots(builder));
            context.modify(Items.LEATHER_LEGGINGS, builder -> modifyLeatherLeggings(builder));
            context.modify(Items.LEATHER_CHESTPLATE, builder -> modifyLeatherChestplate(builder));
            context.modify(Items.LEATHER_HELMET, builder -> modifyLeatherHelmet(builder));

            context.modify(Items.COPPER_BOOTS, builder -> modifyCopperBoots(builder));
            context.modify(Items.COPPER_LEGGINGS, builder -> modifyCopperLeggings(builder));
            context.modify(Items.COPPER_CHESTPLATE, builder -> modifyCopperChestplate(builder));
            context.modify(Items.COPPER_HELMET, builder -> modifyCopperHelmet(builder));
        });
    }

    //Leather Armour
    private static void modifyLeatherBoots(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Wanderer's Boots"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.leather_boots").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_boots"),
                                1,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.FEET
                ).add(

                        Attributes.FALL_DAMAGE_MULTIPLIER,
                        new AttributeModifier(
                                DisenchantedMain.id("feather_falling"),
                                -0.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.FEET
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

    private static void modifyLeatherLeggings(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Wanderer's Pants"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.leather_leggings").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_leggings"),
                                2,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.LEGS
                ).add(
                        Attributes.SNEAKING_SPEED,
                        new AttributeModifier(
                                DisenchantedMain.id("swift_sneak"),
                                0.45,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.LEGS
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }


    private static void modifyLeatherChestplate(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Wanderer's Tunic"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.leather_chestplate").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_chestplate"),
                                3,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.CHEST
                ).add(

                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(
                                DisenchantedMain.id("reach"),
                                1,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.CHEST
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

    private static void modifyLeatherHelmet(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Wanderer's Cap"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.leather_helmet").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_helmet"),
                                1,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.HEAD
                ).add(
                        Attributes.SAFE_FALL_DISTANCE,
                        new AttributeModifier(
                                DisenchantedMain.id("safe_fall"),
                                3,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.HEAD
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

    //Copper Armour
    private static void modifyCopperBoots(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Diver's Boots"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.copper_boots").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_boots"),
                                1,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.FEET
                ).add(

                        Attributes.WATER_MOVEMENT_EFFICIENCY,
                        new AttributeModifier(
                                DisenchantedMain.id("depth_strider"),
                                0.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.FEET
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

    private static void modifyCopperLeggings(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Diver's Leggings"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.copper_leggings").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_leggings"),
                                3,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.LEGS
                ).add(
                        Attributes.WATER_MOVEMENT_EFFICIENCY,
                        new AttributeModifier(
                                DisenchantedMain.id("strider"),
                                0.5,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.LEGS
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }


    private static void modifyCopperChestplate(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Diver's Chestplate"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.copper_chestplate").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_chestplate"),
                                4,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.CHEST
                ).add(
                        Attributes.SUBMERGED_MINING_SPEED,
                        new AttributeModifier(
                                DisenchantedMain.id("aqua_affinity"),
                                4,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.CHEST
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

    private static void modifyCopperHelmet(DataComponentMap.Builder builder) {
        builder.set(DataComponents.ITEM_NAME, Component.literal("Diver's Helmet"));
        builder.set(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.minecraft.copper_helmet").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
        )));

        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                .add(
                        Attributes.ARMOR,
                        new AttributeModifier(
                                DisenchantedMain.id("armour_helmet"),
                                2,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.HEAD
                ).add(
                        Attributes.OXYGEN_BONUS,
                        new AttributeModifier(
                                DisenchantedMain.id("respiration"),
                                3,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.HEAD
                )
                .build()
        );

        builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);

        builder.set(
                DataComponents.TOOLTIP_DISPLAY,
                builder.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT)
                        .withHidden(DataComponents.UNBREAKABLE, true)
        );
    }

}
