package daplumer.more_bugs;

import daplumer.more_bugs.ModRegistries.ModDataRegisterer;
import daplumer.more_bugs.ModRegistries.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;

import java.util.function.Function;

@SuppressWarnings({"unused", "SameParameterValue"})
class Registries extends ModRegistries {
    /**
     * the namespace used for the mod being registered. Make sure to initialize this variable to your MOD_ID
     * @see ModRegistries
     * @see ModDataRegisterer
     */
    static final String namespace = "more_bugs";
    /**
     * The general registerer for item creation. Member of the {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#ITEM_REGISTERER_CONSTRUCTOR
     */
    static final ModDataRegisterer<Item, Item.Settings>                           ITEMS =       ITEM_REGISTERER_CONSTRUCTOR.      apply(namespace);
    /**
     * The general registerer for block creation. Member of the {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#BLOCK_REGISTERER_CONSTRUCTOR
     */
    static final ModDataRegisterer<Block, Block.Settings>                         BLOCKS =      BLOCK_REGISTERER_CONSTRUCTOR.     apply(namespace);
    /**
     * The general registerer for item group creation. Member of the {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#ITEM_GROUP_REGISTERER_CONSTRUCTOR
     */
    static final ModDataRegisterer<ItemGroup, ItemGroup.Builder>                  ITEM_GROUPS = ITEM_GROUP_REGISTERER_CONSTRUCTOR.apply(namespace);
    /**
     * WIP DO NOT USE!
     */
    static final ModDataRegisterer<RegistryKey<LootTable>,RegistryKey<LootTable>> LOOT_TABLES = LOOT_TABLE_REGISTERER_CONSTRUCTOR.apply(namespace);
    /**
     * Mod registerer for statistic registration
     * @implNote see {@link daplumer.more_bugs.ModRegistries.ModStatTypeRegisterer ModStatTypeRegisterer} for implementation information.
     * @see daplumer.more_bugs.ModRegistries.ModStatTypeRegisterer ModStatTypeRegisterer
     * @see daplumer.more_bugs.ModRegistries.ModStatTypeRegisterer#register(String, StatFormatter, Function) register(name, instanceSettings, instanceFactory)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#STAT_REGISTERER_CONSTRUCTOR
     *
     */
    static final ModDataRegisterer<Stat<Identifier>, StatFormatter>               STATS =       STAT_REGISTERER_CONSTRUCTOR.      apply(namespace);


    /**
     * This function is used for telling the registerer that the {@code item} being registered has a {@code block} associated with it.
     * @param block The block associated with the item currently being registered.
     * @implNote Place this function in the {@code instanceFactory} parameter in the {@linkplain ModDataRegisterer#register(String, Object, Function) registration function,}
     *  E.g.
     *
     * <p>
     *     <h>
     *  {@link  ModDataRegisterer#register(String, Object, Function)
     * public static final BlockItem FOO_ITEM =
     * <p>
     * ModDataRegisterer.register(
     *  <pre>
     *     "foo",
     *     new Item.Settings(),
     *     BLOCK_ITEM(FOO_BLOCK)</pre>
     * );}
     * <p>
     * @see #BLOCKS
     * @see #ITEMS
     * @see ModDataRegisterer#register(String, Object, Function) register(String, Object, Function)
     * @see ModRegistries
     * @see ModDataRegisterer
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     */
    static Function<Item.Settings, Item> BLOCK_ITEM(Block block) {
        return (settings -> new BlockItem(block, settings));
    }

    /**
     * Register static registerers for usage in the mod initializer.
     * @see Registries
     * @see daplumer.more_bugs.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see ModDataRegisterer
     * @see ModRegistries
     */
    static void Initialize(){
    }
}