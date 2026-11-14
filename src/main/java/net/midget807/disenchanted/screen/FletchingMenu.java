package net.midget807.disenchanted.screen;

import net.midget807.disenchanted.recipe.FletchingRecipe;
import net.midget807.disenchanted.recipe.FletchingRecipeInput;
import net.midget807.disenchanted.registry.DisenchantedMenus;
import net.midget807.disenchanted.registry.DisenchantedRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Prediction;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public class FletchingMenu extends ItemCombinerMenu {
    public static final int BASE_SLOT = 0;
    public static final int ADDITION_1_SLOT = 1;
    public static final int ADDITION_2_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    public static final int BASE_SLOT_X_PLACEMENT = 26;
    public static final int ADDITION_1_SLOT_X_PLACEMENT = 54;
    public static final int ADDITION_2_SLOT_X_PLACEMENT = 82;
    private static final int RESULT_SLOT_X_PLACEMENT = 135;
    public static final int SLOT_Y_PLACEMENT = 48;
    public static final int CONTAINER_START = 0;
    public static final int CONTAINER_END = 4;
    public static final int INVENTORY_START = CONTAINER_END;
    public static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE - Inventory.SELECTION_SIZE;
    public static final int HOTBAR_START = INVENTORY_END;
    public static final int HOTBAR_END = HOTBAR_START + Inventory.SELECTION_SIZE;
    private final Level level;
    private final DataSlot hasRecipeError = DataSlot.standalone();

    public FletchingMenu(final int containerId, final Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public FletchingMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access) {
        this(containerId, inventory, access, inventory.player.level());
    }

    public FletchingMenu(final int containerId, final Inventory inventory, final ContainerLevelAccess access, final Level level) {
        super(DisenchantedMenus.FLETCHING, containerId, inventory, access, createInputSlotDefinitions(level.recipeAccess()));
        this.level = level;
        this.addDataSlot(this.hasRecipeError).set(0);
    }

    private static ItemCombinerMenuSlotDefinition createInputSlotDefinitions(RecipeAccess recipes) {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(BASE_SLOT, BASE_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT, itemStack -> true)
                .withSlot(ADDITION_1_SLOT, ADDITION_1_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT, itemStack -> true)
                .withSlot(ADDITION_2_SLOT, ADDITION_2_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT, itemStack -> true)
                .withResultSlot(RESULT_SLOT, RESULT_SLOT_X_PLACEMENT, SLOT_Y_PLACEMENT)
                .build();
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(Blocks.FLETCHING_TABLE);
    }

    @Override
    protected void onTake(Player player, ItemStack carried) {
        carried.onCraftedBy(player, carried.getCount());
        this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
        this.shrinkStackInSlot(BASE_SLOT);
        this.shrinkStackInSlot(ADDITION_1_SLOT);
        this.shrinkStackInSlot(ADDITION_2_SLOT);
        this.access.execute((level, pos) -> level.playLocalSound(pos, SoundEvents.VILLAGER_WORK_FLETCHER, SoundSource.BLOCKS, 0.95F + level.getRandom().nextFloat() * 0.05F, 0.95F + level.getRandom().nextFloat() * 0.05F, false));
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(BASE_SLOT), this.inputSlots.getItem(ADDITION_1_SLOT), this.inputSlots.getItem(ADDITION_2_SLOT));
    }

    private FletchingRecipeInput createRecipeInput() {
        return new FletchingRecipeInput(this.inputSlots.getItem(BASE_SLOT), this.inputSlots.getItem(ADDITION_1_SLOT), this.inputSlots.getItem(ADDITION_2_SLOT));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack stack = this.inputSlots.getItem(slot);
        if (!stack.isEmpty()) {
            stack.shrink(1);
            this.inputSlots.setItem(slot, stack);
        }
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (this.level instanceof ServerLevel) {
            boolean hasRecipeError = this.getSlot(BASE_SLOT).hasItem()
                    && this.getSlot(ADDITION_1_SLOT).hasItem()
                    && this.getSlot(ADDITION_2_SLOT).hasItem()
                    && !this.getSlot(this.getResultSlot()).hasItem();
            this.hasRecipeError.set(hasRecipeError ? 1 : 0);
        }
    }

    @Override
    public void createResult() {
        FletchingRecipeInput input = this.createRecipeInput();
        Optional<RecipeHolder<FletchingRecipe>> foundRecipe;
        if (this.level instanceof ServerLevel serverLevel) {
            foundRecipe = serverLevel.recipeAccess().getRecipeFor(DisenchantedRecipe.FLETCHING_TYPE, input, serverLevel);
        } else {
            foundRecipe = Optional.empty();
        }

        foundRecipe.ifPresentOrElse(recipe -> {
            ItemStack result = recipe.value().assemble(input);
            this.resultSlots.setRecipeUsed((RecipeHolder<?>)recipe);
            this.resultSlots.setItem(0, result);
        }, () -> {
            this.resultSlots.setRecipeUsed(null);
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        });
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return target.container != this.resultSlots && super.canTakeItemForPickAll(carried, target);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == RESULT_SLOT) {
                stack.getItem().onCraftedBy(stack, player);
                if (!this.moveItemStackTo(stack, INVENTORY_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex >= CONTAINER_END && slotIndex < HOTBAR_END) {
                if (!this.moveItemStackTo(stack, 0, 3, false)) {
                    if (slotIndex < INVENTORY_END) {
                        if (!this.moveItemStackTo(stack, HOTBAR_START, HOTBAR_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(stack, INVENTORY_START, INVENTORY_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(stack, INVENTORY_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
            if (slotIndex == RESULT_SLOT) {
                player.drop(stack, false, Prediction.PREDICTED);
            }
        }

        return clicked;
    }

    public boolean hasRecipeError() {
        return this.hasRecipeError.get() > 0;
    }
}
