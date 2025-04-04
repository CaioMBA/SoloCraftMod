package org.ofa.solocraft.util.enums;

import net.minecraft.util.StringRepresentable;

public enum BudSizeType implements StringRepresentable {
    SMALL, MEDIUM, LARGE, CLUSTER;

    public BudSizeType next() {
        return switch (this) {
            case SMALL -> MEDIUM;
            case MEDIUM -> LARGE;
            case LARGE -> CLUSTER;
            case CLUSTER -> null;
        };
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
