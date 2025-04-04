package org.ofa.solocraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.block.ModBlocks;
import org.ofa.solocraft.item.ModItems;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final Map<RecipeSerializer<?>, String> COOKING_SUFFIXES = Map.of(
            RecipeSerializer.SMELTING_RECIPE, "from_smelting",
            RecipeSerializer.BLASTING_RECIPE, "from_blasting",
            RecipeSerializer.CAMPFIRE_COOKING_RECIPE, "from_campfire",
            RecipeSerializer.SMOKING_RECIPE, "from_smoking"
    );


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreCooking(
                pWriter,
                RecipeSerializer.SMELTING_RECIPE,
                List.of(ModItems.MANA_CRYSTAL_SHARD.get()),
                RecipeCategory.MISC,
                ModItems.MANA_CRYSTAL_DUST.get(),
                0.25F,
                200,
                "mana_crystal");

        oreCooking(
                pWriter,
                RecipeSerializer.BLASTING_RECIPE,
                List.of(ModItems.MANA_CRYSTAL_SHARD.get()),
                RecipeCategory.MISC,
                ModItems.MANA_CRYSTAL_DUST.get(),
                0.25F,
                100,
                "mana_crystal");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MANA_CRYSTAL.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.MANA_CRYSTAL_SHARD.get())
                .unlockedBy(getHasName(ModItems.MANA_CRYSTAL_SHARD.get()), has(ModItems.MANA_CRYSTAL_SHARD.get()))
                .save(pWriter, new ResourceLocation(SolocraftMod.MOD_ID, "mana_crystal_from_shards"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MANA_CRYSTAL_SHARD.get(), 9)
                .requires(ModItems.MANA_CRYSTAL.get())
                .unlockedBy(getHasName(ModItems.MANA_CRYSTAL.get()), has(ModItems.MANA_CRYSTAL.get()))
                .save(pWriter, new ResourceLocation(SolocraftMod.MOD_ID, "mana_crystal_shards_from_crystal"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANA_CRYSTAL_BLOCK.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.MANA_CRYSTAL.get())
                .unlockedBy(getHasName(ModItems.MANA_CRYSTAL.get()), has(ModItems.MANA_CRYSTAL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MANA_CRYSTAL.get(), 9)
                .requires(ModBlocks.MANA_CRYSTAL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.MANA_CRYSTAL_BLOCK.get()), has(ModBlocks.MANA_CRYSTAL_BLOCK.get()))
                .save(pWriter, new ResourceLocation(SolocraftMod.MOD_ID, "mana_crystal_from_block"));
    }



    protected static void oreCooking(
            Consumer<FinishedRecipe> recipeConsumer,
            RecipeSerializer<? extends AbstractCookingRecipe> serializer,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group
    ) {

        String suffix = COOKING_SUFFIXES.getOrDefault(serializer, "unknown");

        if (suffix.equals("unknown")) {
            throw new IllegalStateException("Unexpected recipe serializer: " + serializer);
        }

        for (ItemLike ingredient : ingredients) {
            SimpleCookingRecipeBuilder
                    .generic(Ingredient.of(ingredient),
                            category,
                            result,
                            experience,
                            cookingTime,
                            serializer)
                    .group(group)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(recipeConsumer,
                            new ResourceLocation(SolocraftMod.MOD_ID,
                                    "%s_%s_%s".formatted(
                                            getItemName(result),
                                            suffix,
                                            getItemName(ingredient)
                                    )
                            )
                    );
        }
    }
}
