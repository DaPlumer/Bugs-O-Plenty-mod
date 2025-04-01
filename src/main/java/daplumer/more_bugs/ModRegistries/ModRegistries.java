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
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a wrapper class for the {@link GeneralModDataRegisterer} to merge all data registration into two files. Find more information there.
 * @see GeneralModDataRegisterer
 * @see Registry
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public abstract class ModRegistries{
    static Logger CUSTOM_DATA_REGISTERER = Logger.getLogger("custom_data_registerer");
    public Logger LOGGER;
    private String namespace;
    public abstract String getNamespace();
    public ModDataRegisterer<Item,Item.Settings> ITEMS = generalRegisterer(Registries.ITEM, Item.Settings::registryKey, Item::new, Item.Settings::new);
    public ModDataRegisterer<Block,AbstractBlock.Settings> BLOCKS = generalRegisterer(Registries.BLOCK, Block.Settings::registryKey, Block::new, AbstractBlock.Settings::create);
    public ModDataRegisterer<ItemGroup,ItemGroup.Builder> ITEM_GROUPS = generalRegisterer(Registries.ITEM_GROUP, ((builder, key) -> builder), ItemGroup.Builder::build, FabricItemGroup::builder);
    public ModDataRegisterer<RegistryKey<LootTable>,RegistryKey<LootTable>> LOOT_TABLES = new ModLootTableRegisterer(this.getNamespace());


    public <T,S> GeneralModDataRegisterer<T,S> generalRegisterer(Registry registry, BiFunction<S,RegistryKey<T>,S> registryKeySettingsFactory, Function<S,T> defaultInstanceFactory, Supplier defaultSettingsFactory){
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
