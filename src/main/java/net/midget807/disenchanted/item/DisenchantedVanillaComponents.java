package net.midget807.disenchanted.item;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.LinkedHashSet;
import java.util.List;


//idk if this is the right place or filname to put any of this
//phu gonna have a stroke

//dont know what im doing (help)
//so imma just do it
//the bad way lol (rip)

public class DisenchantedVanillaComponents {

    public static void modifyVanillaComponents() {

        DisenchantedMain.LOGGER.info("Disenchanted: Modifying Vanilla Components");

        //Leather Armour
        DefaultItemComponentEvents.MODIFY.register(context -> {context.modify(Items.LEATHER_BOOTS, builder -> {

            builder.set(DataComponents.ITEM_NAME, Component.literal("Wanderer's Boots"));
            builder.set(DataComponents.LORE, new ItemLore(List.of(
                    Component.literal("Leather Boots").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(false))
            )));

            builder.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                    .add(Attributes.FALL_DAMAGE_MULTIPLIER, new AttributeModifier(
                            Identifier.fromNamespaceAndPath("disenchanted","featherfall"),
                            -0.5, AttributeModifier.Operation.ADD_VALUE
                    ), EquipmentSlotGroup.FEET)
                    .build());

            builder.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
            builder.set(DataComponents.TOOLTIP_DISPLAY, new TooltipDisplay(false, new LinkedHashSet<>()).withHidden(DataComponents.UNBREAKABLE, true));
        });});


    }

}
