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
    public abstract String getNamespace();
    protected static final Function<String, ModDataRegisterer<Item,Item.Settings>> LOCAL_ITEMS = (string) -> new GeneralModDataRegisterer<Item,Item.Settings>(Registries.ITEM,string, Item.Settings::registryKey, Item::new, Item.Settings::new);
    protected static final Function<String, ModDataRegisterer<Block,AbstractBlock.Settings>> LOCAL_BLOCKS = (string) -> new GeneralModDataRegisterer<Block,Block.Settings>(Registries.BLOCK,string, (AbstractBlock.Settings::registryKey), Block::new, AbstractBlock.Settings::create);
    protected static final Function<String, ModDataRegisterer<ItemGroup,ItemGroup.Builder>> LOCAL_ITEM_GROUPS = (string) -> new GeneralModDataRegisterer<ItemGroup,ItemGroup.Builder>(Registries.ITEM_GROUP,string, (builder, itemGroupRegistryKey) -> builder, ItemGroup.Builder::build, FabricItemGroup::builder);
    protected static final Function<String, ModDataRegisterer<RegistryKey<LootTable>,RegistryKey<LootTable>>> LOCAL_LOOT_TABLES = ModLootTableRegisterer::new;
    protected static final Function<String, ModDataRegisterer<Stat<Identifier>, StatFormatter>> LOCAL_STATS = ModStatTypeRegisterer::new;


    private  <T,S> GeneralModDataRegisterer<T,S> generalRegisterer(Registry registry, BiFunction<S,RegistryKey<T>,S> registryKeySettingsFactory, Function<S,T> defaultInstanceFactory, Supplier defaultSettingsFactory){
        return new GeneralModDataRegisterer<T,S>(registry,this.getNamespace(), registryKeySettingsFactory, defaultInstanceFactory, defaultSettingsFactory);
    }

    /**
     * Given a registered block, this function returns a function which makes a block item from the given block.
     * this is my first ever curry!
     *
     * @see BlockItem
     */
    public static Function<Item.Settings, Item> BLOCK_ITEM(Block block) {
        return (settings -> new BlockItem(block, settings));
    }

}
