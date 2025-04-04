package org.ofa.solocraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.ofa.solocraft.util.enums.BudSizeType;

public class ManaCrystalBudBlock extends DropExperienceBlock {
    private final BudSizeType size;

    public ManaCrystalBudBlock(Properties pProperties, IntProvider pXpRange, BudSizeType size) {
        super(pProperties, pXpRange);
        this.size = size;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getCollisionShape(state, level, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (size) {
            case SMALL -> Block.box(4, 0, 4, 12, 4, 12);
            case MEDIUM -> Block.box(3, 0, 3, 13, 6, 13);
            case LARGE -> Block.box(3, 0, 3, 13, 8, 13);
            case CLUSTER -> Block.box(2, 0, 2, 14, 10, 14);
        };
    }
}
