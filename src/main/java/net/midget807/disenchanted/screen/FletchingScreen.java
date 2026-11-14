package net.midget807.disenchanted.screen;

import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.Optional;

public class FletchingScreen extends ItemCombinerScreen<FletchingMenu> {
    private static final Identifier ERROR_SPRITE = Identifier.withDefaultNamespace("container/smithing/error");
    private static final Component ERROR_TOOLTIP = Component.translatable("container.upgrade.error_tooltip");
    private static final Component MISSING_BASE_TOOLTIP = Component.translatable("container.disenchanted.fletching.missing_base_tooltip");
    private static final int TITLE_LABEL_X = 44;
    private static final int TITLE_LABEL_Y = 15;

    public FletchingScreen(FletchingMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, DisenchantedMain.id("textures/gui/container/fletching.png"));
        this.titleLabelX = 44;
        this.titleLabelY = 15;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);
        this.extractOnboardingTooltips(graphics, mouseX, mouseY);
    }

    @Override
    protected void extractErrorIcon(GuiGraphicsExtractor graphics, int xo, int yo) {
        if (this.hasRecipeError()) {
            //graphics.blitSprite(RenderPipelines.GUI_TEXTURED, ERROR_SPRITE, xo + 104, yo + 46, 28, 21);
        }
    }

    private void extractOnboardingTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        Optional<Component> tooltip = Optional.empty();
        if (this.hasRecipeError() && this.isHovering(65, 46, 28, 21, mouseX, mouseY)) {
            tooltip = Optional.of(ERROR_TOOLTIP);
        }

        if (this.hoveredSlot != null) {
            ItemStack base = this.menu.getSlot(0).getItem();
            ItemStack hoveredStack = this.hoveredSlot.getItem();
            if (base.isEmpty()) {
                if (this.hoveredSlot.index == 0) {
                    tooltip = Optional.of(MISSING_BASE_TOOLTIP);
                }
            }
        }

        tooltip.ifPresent(component -> graphics.setTooltipForNextFrame(this.font, this.font.split(component, 115), mouseX, mouseY));
    }

    private boolean hasRecipeError() {
        return this.menu.hasRecipeError();
    }
}
