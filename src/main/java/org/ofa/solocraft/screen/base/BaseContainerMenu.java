package org.ofa.solocraft.screen.base;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import java.awt.Point;
import java.util.List;

public abstract class BaseContainerMenu extends AbstractContainerMenu {
    protected static final int HOTBAR_SLOT_COUNT = 9;
    protected static final int PLAYER_INV_ROW_COUNT = 3;
    protected static final int PLAYER_INV_COLUMN_COUNT = 9;
    protected static final int PLAYER_INV_SLOT_COUNT = PLAYER_INV_ROW_COUNT * PLAYER_INV_COLUMN_COUNT;
    protected static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;
    protected static final int VANILLA_FIRST_SLOT_INDEX = 0;

    protected final int teSlotCount;
    protected final Level level;
    protected final BlockEntity blockEntity;

    protected BaseContainerMenu(MenuType<?> type, int id, Inventory playerInventory, BlockEntity blockEntity, ContainerData data, int teSlotCount) {
        super(type, id);
        this.teSlotCount = teSlotCount;
        this.level = playerInventory.player.level();
        this.blockEntity = blockEntity;

        checkContainerSize(playerInventory, teSlotCount); // optional
        addPlayerInventory(playerInventory);
        addPlayerHotBar(playerInventory);
        addDataSlots(data);
    }

    protected void addPlayerInventory(Inventory inv) {
        for (int i = 0; i < PLAYER_INV_ROW_COUNT; i++) {
            for (int j = 0; j < PLAYER_INV_COLUMN_COUNT; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    protected void addPlayerHotBar(Inventory inv) {
        for (int i = 0; i < HOTBAR_SLOT_COUNT; i++) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    protected void addCustomSlots(IItemHandler handler, List<Point> slotPositions) {
        for (int i = 0; i < slotPositions.size(); i++) {
            Point pos = slotPositions.get(i);
            this.addSlot(new SlotItemHandler(handler, i, pos.x, pos.y));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        int teFirstSlot = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();

        if (index < teFirstSlot) {
            if (!moveItemStackTo(sourceStack, teFirstSlot, teFirstSlot + teSlotCount, false))
                return ItemStack.EMPTY;
        } else if (index < teFirstSlot + teSlotCount) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, teFirstSlot, false))
                return ItemStack.EMPTY;
        } else {
            System.err.println("Invalid slot index: " + index);
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copy;
    }
}
