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
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreCooking(
                pWriter,
                RecipeSerializer.SMELTING_RECIPE,
                List.of(ModItems.SMALL_MANA_CRYSTAL.get(),
                        ModItems.MEDIUM_MANA_CRYSTAL.get(),
                        ModItems.LARGE_MANA_CRYSTAL.get()),
                RecipeCategory.MISC,
                ModItems.MANA_CRYSTAL_DUST.get(),
                0.25F,
                200,
                "mana_crystal");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MANA_CRYSTAL_BLOCK.get())
                .pattern("MMM")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModItems.LARGE_MANA_CRYSTAL.get())
                .unlockedBy(getHasName(ModItems.LARGE_MANA_CRYSTAL.get()), has(ModItems.LARGE_MANA_CRYSTAL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LARGE_MANA_CRYSTAL.get())
                .requires(ModBlocks.MANA_CRYSTAL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.MANA_CRYSTAL_BLOCK.get()), has(ModBlocks.MANA_CRYSTAL_BLOCK.get()))
                .save(pWriter);
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
        final String suffix = serializer == RecipeSerializer.BLASTING_RECIPE ? "from_blasting" : "from_smelting";

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
