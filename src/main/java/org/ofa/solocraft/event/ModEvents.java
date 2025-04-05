package org.ofa.solocraft.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ofa.solocraft.SolocraftMod;
import org.ofa.solocraft.item.ModItems;
import org.ofa.solocraft.villager.ModVillagers;

import java.util.List;

@Mod.EventBusSubscriber(modid = SolocraftMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.HUNTER_ASSOCIATION_SECRETARY.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add(
                    (pTrader, pRandom) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(ModItems.MANA_CRYSTAL.get(), 1),
                            10,
                            8,
                            0.05F
                    )
            );

            trades.get(1).add(
                    (pTrader, pRandom) -> new MerchantOffer(
                            new ItemStack(ModItems.MANA_CRYSTAL.get(), 1),
                            new ItemStack(ModItems.MANA_CRYSTAL_SWORD.get(), 1),
                            10,
                            8,
                            0.05F
                    )
            );
        }
    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add(
                (pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 5),
                        new ItemStack(ModItems.MANA_CRYSTAL.get(), 12),
                        10,
                        8,
                        0.05F
                )
        );

        rareTrades.add(
                (pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 10),
                        new ItemStack(ModItems.MANA_CRYSTAL_CHESTPLATE.get(), 1),
                        10,
                        8,
                        0.05F
                )
        );
    }
}
