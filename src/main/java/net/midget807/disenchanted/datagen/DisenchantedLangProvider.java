package net.midget807.disenchanted.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.midget807.disenchanted.registry.DisenchantedItems;
import net.midget807.disenchanted.util.DisenchantedTextUtil;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DisenchantedLangProvider extends FabricLanguageProvider {
    public DisenchantedLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        DisenchantedItems.ITEMS.forEach((identifier, item) -> {
            String output = DisenchantedTextUtil.convertPathNameToName(identifier.getPath());
            translationBuilder.add(item, output);
        });

        addSmithingTooltips(translationBuilder, "diamond_upgrade.applies_to", "Iron Equipment");
        addSmithingTooltips(translationBuilder, "diamond_upgrade.ingredients", "Heavy Crystal");
        addSmithingTooltips(translationBuilder, "diamond_upgrade.base_slot_description", "Add iron armor, weapon, or tool");
        addSmithingTooltips(translationBuilder, "diamond_upgrade.additional_description", "Add Heavy Crystal");

        addLore(translationBuilder, "overworld_tablet_line1", " ");
        addLore(translationBuilder, "overworld_tablet_line2", "Contains the artisanal secret of");
        addLore(translationBuilder, "overworld_tablet_line3", "alloying iron with dense crystal");

    }

    /** Adds translation for Smithing Template tooltips. Uses the prepended string of <blockquote><pre>"item.disenchanted.smithing_template."</pre></blockquote>. <br>
     * Example:
     * <blockquote><pre>
     *     "item.disenchanted.smithing_template.diamond_upgrade.applies_to"
     * </pre></blockquote>
     * @param append The details of the translation key following aforementioned prepended string. Note: do not add the "." at the start of this argument.
     * @param translation The output translation.
     * */
    public static void addSmithingTooltips(TranslationBuilder translationBuilder, String append, String translation) {
        translationBuilder.add("item.disenchanted.smithing_template.".concat(append), translation);
    }

    public static void addLore(TranslationBuilder translationBuilder, String append, String translation) {
        translationBuilder.add("item.disenchanted.lore.".concat(append), translation);
    }
}
