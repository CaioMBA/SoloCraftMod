package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.item.ModItems;
import org.ofa.solocraft.util.enums.ItemModelType;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SolocraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItemModel(ModItems.STRAWBERRY, ItemModelType.GENERATED_ITEM);

        registerItemModel(ModItems.MANA_DETECTOR, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.ORB_OF_AVARICE, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.MANA_CRYSTAL_DUST, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.SMALL_MANA_CRYSTAL, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.MEDIUM_MANA_CRYSTAL, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.LARGE_MANA_CRYSTAL, ItemModelType.GENERATED_ITEM);

        registerItemModel(ModBlocks.MANA_CRYSTAL_DOOR, ItemModelType.GENERATED_BLOCK_ITEM);
        registerItemModel(ModBlocks.MANA_CRYSTAL_FENCE, ItemModelType.FENCE, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerItemModel(ModBlocks.MANA_CRYSTAL_BUTTON, ItemModelType.BUTTON, ModBlocks.MANA_CRYSTAL_BLOCK);
        registerItemModel(ModBlocks.MANA_CRYSTAL_WALL, ItemModelType.WALL, ModBlocks.MANA_CRYSTAL_BLOCK);

        registerItemModel(ModBlocks.MANA_CRYSTAL_STAIRS, ItemModelType.SIMPLE_BLOCK_PARENT);
        registerItemModel(ModBlocks.MANA_CRYSTAL_SLAB, ItemModelType.SIMPLE_BLOCK_PARENT);
        registerItemModel(ModBlocks.MANA_CRYSTAL_PRESSURE_PLATE, ItemModelType.SIMPLE_BLOCK_PARENT);
        registerItemModel(ModBlocks.MANA_CRYSTAL_FENCE_GATE, ItemModelType.SIMPLE_BLOCK_PARENT);

        registerItemModel(ModBlocks.MANA_CRYSTAL_TRAPDOOR, ItemModelType.TRAPDOOR);

        registerItemModel(ModItems.MANA_CRYSTAL_SWORD, ItemModelType.HANDHELD);
        registerItemModel(ModItems.MANA_CRYSTAL_PICKAXE, ItemModelType.HANDHELD);
        registerItemModel(ModItems.MANA_CRYSTAL_AXE, ItemModelType.HANDHELD);
        registerItemModel(ModItems.MANA_CRYSTAL_SHOVEL, ItemModelType.HANDHELD);
        registerItemModel(ModItems.MANA_CRYSTAL_HOE, ItemModelType.HANDHELD);
    }

    private void registerItemModel(RegistryObject<?> registryObject, ItemModelType type) {
        registerItemModel(registryObject, type, null);
    }

    private void registerItemModel(RegistryObject<?> registryObject, ItemModelType type, RegistryObject<Block> baseBlock) {
        String name = ForgeRegistries.ITEMS.containsKey(registryObject.getId())
                ? registryObject.getId().getPath()
                : ForgeRegistries.BLOCKS.getKey((Block) registryObject.get()).getPath();

        ResourceLocation blockTexture = new ResourceLocation(SolocraftMod.MOD_ID, "block/" + name);
        ResourceLocation itemTexture = new ResourceLocation(SolocraftMod.MOD_ID, "item/" + name);

        switch (type) {
            case GENERATED_ITEM -> withExistingParent(name, mcLoc("item/generated")).texture("layer0", itemTexture);
            case GENERATED_BLOCK_ITEM -> withExistingParent(name, mcLoc("item/generated")).texture("layer0", itemTexture);
            case SIMPLE_BLOCK_PARENT -> withExistingParent(SolocraftMod.MOD_ID + ":" + name, modLoc("block/" + name));
            case TRAPDOOR -> withExistingParent(name, modLoc("block/" + name + "_bottom"));
            case FENCE -> withExistingParent(name, mcLoc("block/fence_inventory"))
                    .texture("texture", new ResourceLocation(SolocraftMod.MOD_ID, "block/" + getPath(baseBlock)));
            case BUTTON -> withExistingParent(name, mcLoc("block/button_inventory"))
                    .texture("texture", new ResourceLocation(SolocraftMod.MOD_ID, "block/" + getPath(baseBlock)));
            case WALL -> withExistingParent(name, mcLoc("block/wall_inventory"))
                    .texture("wall", new ResourceLocation(SolocraftMod.MOD_ID, "block/" + getPath(baseBlock)));
            case HANDHELD -> withExistingParent(name, mcLoc("item/handheld"))
                    .texture("layer0", itemTexture);
        }
    }

    private String getPath(RegistryObject<Block> block) {
        return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
    }
}
