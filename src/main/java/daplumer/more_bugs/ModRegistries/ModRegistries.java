package daplumer.more_bugs.ModRegistries;

import daplumer.more_bugs.BugsoPlenty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Map;
public class ModRegistries<T>{
    /**
     * This is an enum made to merge all registry methods of different
     * data types to make updating data registration easier.
     */
    protected final Registry<T> registry;
    private final RegistryKey<? extends Registry<T>> registryKey;
    public static final Map<Class<?>,Class<?>> TypeToBuilder = Map.of(
            Item.class, Item.Settings.class,
            Block.class, Block.Settings.class,
            ItemGroup.class, ItemGroup.Builder.class,
            BlockItem.class, BlockItem.Settings.class
    );

    public ModRegistries(Registry<T> registry) {
        this.registry = registry;
        this.registryKey = registry.getKey();
        String name = registryKey.getValue().getPath();
        BugsoPlenty.LOGGER.info("  Registering Mod {}s for {}", name , BugsoPlenty.NAME);
    }
    public T register(String name, T settings){
        RegistryKey<T> key = RegistryKey.of(registryKey, Identifier.of(BugsoPlenty.MOD_ID,name));
        return Registry.register(registry, key, settings);
    }
    public <B> T register(Identifier id, Item.Settings settings){
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Registry.register(registry, key, createFromSettings(settings.registryKey(RegistryKey.of(this.registryKey, id))));
    }
    protected <B> T createFromSettings(B settings){
        Class<?> SettingsClass = settings.getClass();

    }

    public T get(String name){
        return registry.get(Identifier.of(BugsoPlenty.MOD_ID,name));
    }
    public enum Settings {
        ITEM(Item.Settings.class),
        BLOCK(Block.Settings.class);
        private Settings(Class<?> SettingsClass){
            SettingsClass.getOuter
        }

    }
}
