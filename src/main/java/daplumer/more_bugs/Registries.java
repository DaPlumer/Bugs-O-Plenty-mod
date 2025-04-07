package daplumer.more_bugs;

import daplumer.modregisterer.ModRegistries.ModDataRegisterer;
import daplumer.modregisterer.ModRegistries.ModEntityTypeRegisterer;
import daplumer.modregisterer.ModRegistries.ModRegistries;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.stat.StatFormatter;
import java.util.function.Function;
import static daplumer.modregisterer.ModRegistries.ModRegistries.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"unused", "SameParameterValue", "unchecked"})
public enum Registries{

    /**
     * The general registerer for item creation. Member of the {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#ITEM_REGISTERER_CONSTRUCTOR
     */
    ITEMS(ITEM_REGISTERER_CONSTRUCTOR),

    /**
     * The general registerer for block creation. Member of the {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#BLOCK_REGISTERER_CONSTRUCTOR
     */
    BLOCKS(BLOCK_REGISTERER_CONSTRUCTOR),

    /**
     * The general registerer for item group creation. Member of the {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @implNote Documentation on general data registration can be found in the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register} method of the
     * {@link daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer} class.
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object, Function) register(name, instanceSettings, instanceFactory)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String, Object) register(name, instanceSettings)
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer#register(String) register(name)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#ITEM_GROUP_REGISTERER_CONSTRUCTOR
     */
    ITEM_GROUPS(ITEM_GROUP_REGISTERER_CONSTRUCTOR),

    /**
     * WIP DO NOT USE!
     */
    LOOT_TABLES(LOOT_TABLE_REGISTERER_CONSTRUCTOR),

    /**
     * Mod registerer for statistic registration
     * @implNote see {@link daplumer.modregisterer.ModRegistries.ModStatTypeRegisterer ModStatTypeRegisterer} for implementation information.
     * @see daplumer.modregisterer.ModRegistries.ModStatTypeRegisterer ModStatTypeRegisterer
     * @see daplumer.modregisterer.ModRegistries.ModStatTypeRegisterer#register(String, StatFormatter, Function) register(name, instanceSettings, instanceFactory)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#STAT_REGISTERER_CONSTRUCTOR
     *
     */
    STATS(STAT_REGISTERER_CONSTRUCTOR),

    /**
     * Mod registerer for entity types
     * @implNote see {@link ModEntityTypeRegisterer ModEntityTypeRegisterer} for implementation details.
     * @see ModEntityTypeRegisterer ModEntityTypeRegisterer
     * @see ModEntityTypeRegisterer#register(String, EntityType.Builder, Function) register(String, EntityType.Builder, Function)
     * @see ModDataRegisterer
     * @see ModRegistries
     * @see ModRegistries#ENTITY_TYPE_REGISTERER_CONSTRUCTOR
     */
    ENTITY_TYPES(ENTITY_TYPE_REGISTERER_CONSTRUCTOR);

    private final ModDataRegisterer<?,?> registerer;
    <T,S>Registries(@NotNull Function<String,ModDataRegisterer<T,S>> constructor){
        this.registerer = constructor.apply("more_bugs");
    }
    static @NotNull String getNamespace(){
        return "more_bugs";
    }


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
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     */
     public static @NotNull Function<Item.Settings, Item> BLOCK_ITEM(Block block) {
        return (settings -> new BlockItem(block, settings));
    }

    /**
     * Register static registerers for usage in the mod initializer.
     * @see Registries
     * @see daplumer.modregisterer.ModRegistries.GeneralModDataRegisterer GeneralModDataRegisterer
     * @see ModDataRegisterer
     * @see ModRegistries
     */
    static void Initialize(){
    }

    /**
     *
     */
    public <T,S> T register(String name,@Nullable S instanceSettings, @Nullable Function<S,T> instanceFactory){
        return ((ModDataRegisterer<T,S>) this.registerer).register(name ,instanceSettings, instanceFactory);
    }

    /**
     *
     */
    public <T,S> T register(String name,@Nullable S instanceSettings){
        return ((ModDataRegisterer<T,S>) this.registerer).register(name ,instanceSettings);
    }

    /**
     *
     */
    public <T,S> T register(String name){
        return ((ModDataRegisterer<T,S>) this.registerer).register(name);
    }
}