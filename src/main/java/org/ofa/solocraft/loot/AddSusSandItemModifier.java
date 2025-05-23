package org.ofa.solocraft.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddSusSandItemModifier extends LootModifier {
    public static final Supplier<Codec<AddSusSandItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec()
            .fieldOf("item").forGetter(m -> m._item)).apply(inst, AddSusSandItemModifier::new)));
    private final Item _item;

    public AddSusSandItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this._item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        for(LootItemCondition condition:this.conditions) {
            if (!condition.test(lootContext)) {
                return objectArrayList;
            }
        }
        if (lootContext.getRandom().nextFloat() < 0.10f) {
            objectArrayList.clear();
            objectArrayList.add(new ItemStack(this._item));
        }
        return objectArrayList;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
