package daplumer.more_bugs.block;

import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    private static Block registerBlock (String name, Block block){
        return registerBlock(name, block, true);
    }
    private static Block registerBlock (String name, Block block,boolean includeItem ){
        if (includeItem){
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK,Identifier.of(BugsoPlenty.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(BugsoPlenty.MOD_ID,name), new BlockItem(block, new Item.Settings()));
    }
}
