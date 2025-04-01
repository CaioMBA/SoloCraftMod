package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.ofa.solocraft.SolocraftMod;
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
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SolocraftMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
