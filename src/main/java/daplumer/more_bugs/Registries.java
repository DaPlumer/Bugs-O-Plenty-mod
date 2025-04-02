package daplumer.more_bugs;

import daplumer.more_bugs.ModRegistries.ModDataRegisterer;
import daplumer.more_bugs.ModRegistries.ModRegistries;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;

public class Registries extends ModRegistries {
    public static final String namespace = "more_bugs";
    public static final ModDataRegisterer<Item, Item.Settings> ITEMS = LOCAL_ITEMS.apply(namespace);
    public static final ModDataRegisterer<Block, AbstractBlock.Settings> BLOCKS = LOCAL_BLOCKS.apply(namespace);
    public static final ModDataRegisterer<ItemGroup, ItemGroup.Builder> ITEM_GROUPS = LOCAL_ITEM_GROUPS.apply(namespace);
    public static final ModDataRegisterer<RegistryKey<LootTable>, RegistryKey<LootTable>> LOOT_TABLES = LOCAL_LOOT_TABLES.apply(namespace);
    public static final ModDataRegisterer<Stat<Identifier>, StatFormatter> STATS = LOCAL_STATS.apply(namespace);


    @Override
    public String getNamespace() {
        return "more_bugs";
    }
    public static void Initialize(){

    }
}
