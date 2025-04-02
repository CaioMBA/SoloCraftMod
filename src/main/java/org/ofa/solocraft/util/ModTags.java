package org.ofa.solocraft.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.ofa.solocraft.SolocraftMod;


public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> MANA_INFUSED = tag("mana_infused");
        public static final TagKey<Block> NEEDS_MANA_CRYSTAL_TOOL = tag("needs_mana_crystal_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(SolocraftMod.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> MANA_CRYSTAL = tag("mana_crystal");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(SolocraftMod.MOD_ID, name));
        }
    }
}
