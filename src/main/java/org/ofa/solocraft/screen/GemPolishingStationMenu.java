package org.ofa.solocraft.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.screen.base.BaseContainerMenu;

import java.awt.Point;
import java.util.List;

public class GemPolishingStationMenu extends BaseContainerMenu {
    private final ContainerData data;
    private static final int teSlotCount = 2;

    public GemPolishingStationMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()), new SimpleContainerData(2));
    }

    public GemPolishingStationMenu(int id, Inventory inv, BlockEntity be, ContainerData data) {
        super(ModMenuTypes.GEM_POLISHING_STATION_MENU.get(), id, inv, be, data, teSlotCount);
        this.data = data;

        be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            addCustomSlots(handler, List.of(
                    new Point(80, 11),  // Slot 0
                    new Point(80, 59)   // Slot 1
            ));
        });
    }

    public boolean isCrafting() {
        return this.data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowSize = 26;
        return (maxProgress != 0 && progress != 0) ? (progress * arrowSize / maxProgress) : 0;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(this.level, this.blockEntity.getBlockPos()), player, ModBlocks.GEM_POLISHING_STATION.get());
    }
}
