package daplumer.more_bugs.item;

import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(BugsoPlenty.MOD_ID,name), item);
    }
    public static void register(){
        BugsoPlenty.LOGGER.info("Registering Mod Items for " + BugsoPlenty.MOD_ID);
    }
}
