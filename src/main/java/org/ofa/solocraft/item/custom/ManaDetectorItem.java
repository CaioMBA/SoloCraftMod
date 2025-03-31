package org.ofa.solocraft.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.util.ModTags;

import java.util.List;

public class ManaDetectorItem extends Item {
    public ManaDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack,
                                @Nullable Level pLevel,
                                List<Component> pTooltipComponents,
                                TooltipFlag pIsAdvanced) {
        String tooltipKey = String.format("%s.tooltip", this.getDescriptionId());

        pTooltipComponents.add(Component.translatable(tooltipKey));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos pos = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean found = false;

            for(int y = pos.getY(); y >= pContext.getLevel().getMinBuildHeight(); y--){
                BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
                BlockState state = pContext.getLevel().getBlockState(checkPos);

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(checkPos, player, state.getBlock());
                    found = true;
                    break; // Stop after first found
                }
            }
            if(!found){
                player.sendSystemMessage(Component.literal("No mana found below you."));
            }
        }
        pContext.getItemInHand()
                .hurtAndBreak(1,
                        pContext.getPlayer(),
                        p -> p.broadcastBreakEvent(pContext.getPlayer().getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        String message = String.format("""
                ==============================================
                Found %s at:\s
                x: %d
                y: %d
                z: %d
                ==============================================\s""",
                I18n.get(block.getDescriptionId()), blockPos.getX(), blockPos.getY(), blockPos.getZ());
        player.sendSystemMessage(Component.literal(message));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.MANA_INFUSED);
    }
}
