package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.item.ModItems;
import org.ofa.solocraft.util.enums.ItemModelType;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>,Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SolocraftMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItemModel(ModItems.STRAWBERRY, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.AURA_FARMING_MUSIC_DISC, ItemModelType.GENERATED_ITEM);

        registerItemModel(ModItems.MANA_DETECTOR, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.ORB_OF_AVARICE, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.MANA_CRYSTAL_DUST, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.MANA_CRYSTAL_SHARD, ItemModelType.GENERATED_ITEM);
        registerItemModel(ModItems.MANA_CRYSTAL, ItemModelType.GENERATED_ITEM);

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

        registerItemModel(ModItems.MANA_CRYSTAL_HELMET, ItemModelType.ARMOR);
        registerItemModel(ModItems.MANA_CRYSTAL_CHESTPLATE, ItemModelType.ARMOR);
        registerItemModel(ModItems.MANA_CRYSTAL_LEGGINGS, ItemModelType.ARMOR);
        registerItemModel(ModItems.MANA_CRYSTAL_BOOTS, ItemModelType.ARMOR);
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
            case ARMOR -> trimmedArmorItem(((RegistryObject<Item>)registryObject));
        }
    }

    private String getPath(RegistryObject<Block> block) {
        return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = SolocraftMod.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
