package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SolocraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.STRAWBERRY);

        simpleItem(ModItems.MANA_DETECTOR);
        simpleItem(ModItems.ORB_OF_AVARICE);
        simpleItem(ModItems.MANA_CRYSTAL_DUST);
        simpleItem(ModItems.SMALL_MANA_CRYSTAL);
        simpleItem(ModItems.MEDIUM_MANA_CRYSTAL);
        simpleItem(ModItems.LARGE_MANA_CRYSTAL);

        simpleBlockItem(ModBlocks.MANA_CRYSTAL_DOOR);
        fenceItem(ModBlocks.MANA_CRYSTAL_FENCE, ModBlocks.MANA_CRYSTAL_BLOCK);
        buttonItem(ModBlocks.MANA_CRYSTAL_BUTTON, ModBlocks.MANA_CRYSTAL_BLOCK);
        wallItem(ModBlocks.MANA_CRYSTAL_WALL, ModBlocks.MANA_CRYSTAL_BLOCK);

        evenSimplerBlockItem(ModBlocks.MANA_CRYSTAL_STAIRS);
        evenSimplerBlockItem(ModBlocks.MANA_CRYSTAL_SLAB);
        evenSimplerBlockItem(ModBlocks.MANA_CRYSTAL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.MANA_CRYSTAL_FENCE_GATE);

        trapdoorItem(ModBlocks.MANA_CRYSTAL_TRAPDOOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SolocraftMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SolocraftMod.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(SolocraftMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(SolocraftMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(SolocraftMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(SolocraftMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

}
