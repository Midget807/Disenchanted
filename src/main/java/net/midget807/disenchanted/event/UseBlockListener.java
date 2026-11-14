package net.midget807.disenchanted.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.midget807.disenchanted.registry.DisenchantedStats;
import net.midget807.disenchanted.screen.FletchingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class UseBlockListener {
    public static void execute() {
        UseBlockCallback.EVENT.register((player, level, interactionHand, blockHitResult) -> {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);

            if (state.is(Blocks.FLETCHING_TABLE)) {
                executeFletchingTableInteraction(level, player, pos);
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

    private static final Component FLETCHING_TITLE = Component.translatable("container.disenchanted.fletching");
    private static void executeFletchingTableInteraction(Level level, Player player, BlockPos pos) {
        if (!level.isClientSide()) {
            player.openMenu(
                    new SimpleMenuProvider(
                            (containerId, inventory, playerEntity) -> new FletchingMenu(containerId, inventory, ContainerLevelAccess.create(level, pos)), FLETCHING_TITLE
                    )
            );
            player.awardStat(DisenchantedStats.INTERACT_WITH_FLETCHING_TABLE);
        }
    }

}
