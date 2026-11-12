package net.midget807.disenchanted.util;

import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DisenchantedTags {
    public static class Blocks {


        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, DisenchantedMain.id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DIAMOND_SMITHING_UPGRADE_TEMPLATES = createTag("diamond_smithing_upgrade_templates");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, DisenchantedMain.id(name));
        }
    }
}
