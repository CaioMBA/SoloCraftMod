package org.ofa.solocraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.ofa.solocraft.sound.ModSounds;

import java.util.List;

public class ManaDetectionOrbBlock extends Block {
    public ManaDetectionOrbBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState,
                                 Level pLevel,
                                 BlockPos pPos,
                                 Player pPlayer,
                                 InteractionHand pHand,
                                 BlockHitResult pHit) {

        pLevel.playSound(pPlayer,
                        pPos,
                        ModSounds.MANA_DETECTION_ORB_SCANNING.get(),
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack,
                                @Nullable BlockGetter pLevel,
                                List<Component> pTooltip,
                                TooltipFlag pFlag) {

        String tooltipKey = String.format("%s.tooltip", this.getDescriptionId());
        pTooltip.add(Component.translatable(tooltipKey));

        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
