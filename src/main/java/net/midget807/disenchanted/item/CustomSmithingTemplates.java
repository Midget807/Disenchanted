package net.midget807.disenchanted.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public interface CustomSmithingTemplates {
    ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
    Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
    Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
    Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
    Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
    Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
    Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
    Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
    Identifier EMPTY_SLOT_SPEAR = Identifier.withDefaultNamespace("container/slot/spear");
    Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");
    Identifier EMPTY_SLOT_REDSTONE_DUST = Identifier.withDefaultNamespace("container/slot/redstone_dust");
    Identifier EMPTY_SLOT_QUARTZ = Identifier.withDefaultNamespace("container/slot/quartz");
    Identifier EMPTY_SLOT_EMERALD = Identifier.withDefaultNamespace("container/slot/emerald");
    Identifier EMPTY_SLOT_DIAMOND = Identifier.withDefaultNamespace("container/slot/diamond");
    Identifier EMPTY_SLOT_LAPIS_LAZULI = Identifier.withDefaultNamespace("container/slot/lapis_lazuli");
    Identifier EMPTY_SLOT_AMETHYST_SHARD = Identifier.withDefaultNamespace("container/slot/amethyst_shard");
    Identifier EMPTY_SLOT_NAUTILUS_ARMOR = Identifier.withDefaultNamespace("container/slot/nautilus_armor");
    
    Component DIAMOND_UPGRADE_APPLIES_TO = Component.translatable("item.disenchanted.smithing_template.diamond_upgrade.applies_to").withStyle(DESCRIPTION_FORMAT);
    Component DIAMOND_UPGRADE_INGREDIENTS = Component.translatable("item.disenchanted.smithing_template.diamond_upgrade.ingredients").withStyle(DESCRIPTION_FORMAT);
    Component DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.disenchanted.smithing_template.diamond_upgrade.base_slot_description");
    Component DIAMOND_UPGRADE_ADDITIONAL_DESCRIPTION = Component.translatable("item.disenchanted.smithing_template.diamond_upgrade.additional_description");

    static SmithingTemplateItem createDiamondUpgradeTemple(final Item.Properties properties) {
        return new SmithingTemplateItem(
                DIAMOND_UPGRADE_APPLIES_TO,
                DIAMOND_UPGRADE_INGREDIENTS,
                DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION,
                DIAMOND_UPGRADE_ADDITIONAL_DESCRIPTION,
                createDiamondUpgradeIconList(),
                createDiamondUpgradeMaterialList(),
                properties
        );
    }

    static List<Identifier> createDiamondUpgradeIconList() {
        return List.of(
                EMPTY_SLOT_HELMET,
                EMPTY_SLOT_SWORD,
                EMPTY_SLOT_CHESTPLATE,
                EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_AXE,
                EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_HOE,
                EMPTY_SLOT_SHOVEL,
                EMPTY_SLOT_NAUTILUS_ARMOR,
                EMPTY_SLOT_SPEAR
        );
    }

    static List<Identifier> createDiamondUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_DIAMOND);
    }
}
