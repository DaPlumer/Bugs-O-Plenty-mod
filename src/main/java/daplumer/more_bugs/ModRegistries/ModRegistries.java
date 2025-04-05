package daplumer.more_bugs.ModRegistries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;


import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * This is a wrapper class for the {@link GeneralModDataRegisterer} to merge all data registration into two files. Find more information there.
 * @see GeneralModDataRegisterer
 * @see Registry
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public abstract class ModRegistries{
    static Logger CUSTOM_DATA_REGISTERER = Logger.getLogger("custom_data_registerer");

    protected static final Function<String, ModDataRegisterer<Item, Item.Settings>>                           ITEM_REGISTERER_CONSTRUCTOR       = generalRegisterer(Registries.ITEM,       Item.Settings::registryKey, Item::new, Item.Settings::new);
    protected static final Function<String, ModDataRegisterer<Block, Block.Settings>>                         BLOCK_REGISTERER_CONSTRUCTOR      = generalRegisterer(Registries.BLOCK,      AbstractBlock.Settings::registryKey, Block::new, AbstractBlock.Settings::create);
    protected static final Function<String, ModDataRegisterer<ItemGroup, ItemGroup.Builder>>                  ITEM_GROUP_REGISTERER_CONSTRUCTOR = generalRegisterer(Registries.ITEM_GROUP, (builder, itemGroupRegistryKey) -> builder, ItemGroup.Builder::build, FabricItemGroup::builder);
    protected static final Function<String, ModDataRegisterer<RegistryKey<LootTable>,RegistryKey<LootTable>>> LOOT_TABLE_REGISTERER_CONSTRUCTOR = ModLootTableRegisterer::new;
    protected static final Function<String, ModDataRegisterer<Stat<Identifier>, StatFormatter>>               STAT_REGISTERER_CONSTRUCTOR       = ModStatTypeRegisterer::new;


    private static <T,S> Function<String, ModDataRegisterer<T,S>> generalRegisterer(Registry registry, BiFunction<S, RegistryKey<T>, S> registryKeySettingsFactory, Function<S, T> defaultInstanceFactory, Supplier defaultSettingsFactory){
        return (string) -> new GeneralModDataRegisterer<>(registry,string, registryKeySettingsFactory, defaultInstanceFactory, defaultSettingsFactory);
    }



}
